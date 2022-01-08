package ru.scytale.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.scytale.constant.Constants.*;

/**
 * Скитала.
 */
public class Scytale {

    /** Матрицы, содержащие зашифрованные блоки */
    private List<char[][]> encodedMatrixs;
    /** Ключ */
    private Key key;
    /** Величина одного блока */
    private Integer blockLength;
    /** Общее число блоков */
    private Integer blockCounts;

    public Key getKey() {
        return key;
    }

    /**
     * Создать сообщение
     * @param message - исходный текст
     * @param key - ключ
     * @param encode - логический признак зашифровать\дефишровать. От него зависит способ заполнения матриц encodedMatrixs
     */
    public void createMessage(String message, Key key, boolean encode) {
        this.key = key;
        this.blockLength = key.getN() * key.getM();
        this.blockCounts = message.length() / blockLength + (message.length() % blockLength == 0 ? 0 : 1);
        buildMatrix(message, encode);
    }

    /**
     * Построить зашифрованные матрицы.
     * @param message - исходный текст
     * @param encode - логический признак зашифровать\дефишровать. От него зависит способ заполнения матриц encodedMatrixs
     */
    private void buildMatrix(String message, boolean encode) {
        List<char[]> messageBlocks = buildMessageBlocks(message);
        if (!encode) {
            encodeBlocks(messageBlocks);
        } else {
            decodeBlocks(messageBlocks, message);
        }
    }

    /**
     * Зашифровать блоки сообщения.
     * @param blocks - список блоков.
     */
    private void encodeBlocks(List<char[]> blocks) {
        for (int i = 0; i < blocks.size(); i++) {
            int ind = 0;
            for (int n = 0; n < key.getN(); n++) {
                for (int m = 0; m < key.getM(); m++) {
                    encodedMatrixs.get(i)[n][m] = ind < blocks.get(i).length ? blocks.get(i)[ind] : SPACE_SYM;
                    ind++;
                }
            }
        }
    }

    /**
     * Дешифровать блоки сообщения.
     * @param blocks - список блоков
     * @param message - исходный текст
     */
    private void decodeBlocks(List<char[]> blocks, String message) {
        for (int i = 0; i < blocks.size(); i++) {
            int ind = 0;
            for (int m = 0; m < key.getM(); m++) {
                for (int n = 0; n < key.getN(); n++) {
                    encodedMatrixs.get(i)[n][m] = ind < message.length() ? blocks.get(i)[ind] : SPACE_SYM;
                    ind++;
                }
            }
        }
    }

    /**
     * Сформировать блоки из сообщения, исходя из длины текста и величины ключа.
     * @param message - исходный текст
     * @return текст, разбитый на блоки размера blockLength
     */
    private List<char[]> buildMessageBlocks(String message) {
        encodedMatrixs = new ArrayList<>(blockCounts);
        List<char[]> blocks = new ArrayList<>(blockCounts);
        for (int i = 0; i < blockCounts; i++) {
            if (i == blockCounts - 1) {
                blocks.add(getBlockWithSpaces(message, i));
            } else {
                blocks.add(message.substring(i * blockLength, (i + 1) * blockLength).toCharArray());
            }
            encodedMatrixs.add(new char[key.getN()][key.getM()]);
        }
        return blocks;
    }

    /**
     * Получить блок, дополненный пробелами в конце.
     * @param message - исходное сообщение
     * @param i - индекс, начиная с которого необходимо брать символы из исходного текста
     * @return
     */
    private char[] getBlockWithSpaces(String message, int i) {
        char[] firstPart = message.substring(i * blockLength).toCharArray();
        char[] secondPart = new char[blockLength - firstPart.length];
        for (char c : secondPart) {
            c = SPACE_SYM;
        }
        char[] block = Arrays.copyOf(firstPart, firstPart.length + secondPart.length);
        System.arraycopy(secondPart, 0, block, firstPart.length, secondPart.length);
        return block;
    }

    /**
     * Получить зашифрованный текст.
     * @return зашифрованный текст
     */
    public String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedMatrixs.size(); i++) {
            for (int n = 0; n < key.getN(); n++) {
                for (int m = 0; m < key.getM(); m++) {
                    sb.append(encodedMatrixs.get(i)[n][m]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Получить дешфированный текст.
     * @return дешифрованный текст
     */
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedMatrixs.size(); i++) {
            for (int m = 0; m < key.getM(); m++) {
                for (int n = 0; n < key.getN(); n++) {
                    sb.append(encodedMatrixs.get(i)[n][m]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Проверить что дешифрованный текст для текущего ключа является кандидатом на правильный ключ.
     * @param initialMessage - исходный текст
     * @return {@link Boolean} true - является кандидатом, false - не является кандидатом
     */
    public boolean isDecodedHasSpacesAtTheEnd(String initialMessage) {
        String decoded = getText();
        char[] chars = decoded.toCharArray();
        boolean spaces = true;
        for (int i = initialMessage.length() + 1; i < blockLength * blockCounts; i++) {
            if (chars[i] != '\u0000') {
                spaces = false;
                break;
            }
        }
        return spaces;
    }
}
