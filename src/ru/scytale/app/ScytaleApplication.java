package ru.scytale.app;

import ru.scytale.domain.Key;
import ru.scytale.domain.Scytale;
import ru.scytale.utils.FileUtils;

import static ru.scytale.constant.Constants.*;

import java.util.Scanner;

/**
 * Основной класс приложения.
 */
public class ScytaleApplication {

    /**
     * Метод для подбора ключа для расшифровки файла.
     */
    public void start() {
        Scanner console = new Scanner(System.in);
        printMenu();
        String fileName = console.nextLine();
        String initialMessage = FileUtils.readFile("src/resources/" + fileName);
        boolean decoded = false;
        int ind = 0;
        Scytale scytale = new Scytale();
        long startTime = System.currentTimeMillis();
        do {
            scytale.createMessage(initialMessage, new Key(KEYS.get(ind)), false);
            if (scytale.isDecodedHasSpacesAtTheEnd(initialMessage)) {
                printDecodedCandidate(scytale);
                decoded = console.nextLine().equals("y");
            }
            ind++;
        } while (!decoded || ind == KEYS.size() - 1);
        long endTime = System.currentTimeMillis();
        long seconds = (int) ((endTime - startTime) / 1000) % 60 ;
        if (decoded) {
            printDecodingResult(scytale, initialMessage, seconds, ind, fileName);
            FileUtils.writeFile(scytale.getText(), fileName, false);
        } else {
            System.out.println("Текст не удалось расшифровать, используя ключи из диапозона 12 <= n*m <= 36");
        }
    }

    /**
     * Распечатать приветственное меню.
     */
    private void printMenu() {
        System.out.println("Привет!");
        System.out.println(LINES);
        System.out.println("Введите название файла для расшифровки:");
        System.out.println(LINES);
    }

    /**
     * Распечатать текст кандидата на правильный ключ.
     * @param scytale - объект класса {@link Scytale}
     */
    private void printDecodedCandidate(Scytale scytale) {
        System.out.println(scytale.getText());
        System.out.println("Текст расшифрован? Нажмите [y/n]");
    }

    /**
     * Вывести сведения о дешифровке тексте.
     * @param scytale - объект класса {@link Scytale}.
     * @param initialMessage - исходный текст
     * @param seconds - количество секунд, потраченное для дешифрования
     * @param keyNum - номеро правильного варианта ключа
     * @param fileName - наименование файла с исходным тестом
     */
    private void printDecodingResult(Scytale scytale, String initialMessage, Long seconds, Integer keyNum, String fileName) {
        System.out.println(FILE_NAME + fileName.replace("src/resources/", ""));
        System.out.println(SYM_COUNT + initialMessage.length());
        System.out.println(DECODING_TIME + seconds + "c.");
        System.out.println(CORRECT_KEY + scytale.getKey());
        System.out.println(KEY_NUM + keyNum);
    }
}
