package ru.scytale.utils;

import static ru.scytale.constant.Constants.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Вспомогательный класс для работы с файлами.
 */
public class FileUtils {

    /**
     * Прочитать файл.
     * @param fileName - наименование файла
     * @return содержимое файла в виде строки
     */
    public static String readFile(String fileName) {
        String result = null;
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))){
            StringBuilder sb = new StringBuilder();
            String line = bf.readLine();
            while(line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bf.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return result;
    }

    /**
     * Создать файл
     * @param message - текст
     * @param filePrefix - наименование файла
     * @param encode - логический признак зашифрован или дефишрован исходный текст
     */
    public static void writeFile(String message, String filePrefix, boolean encode) {
        if (encode) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/" + filePrefix.replace(".txt", "") + "_output.txt"))) {
                bw.write(message);
            } catch (Exception e) {
                System.err.println(e.getLocalizedMessage());
            }
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/" + filePrefix.replace(".txt", "") + "_decoded.txt"))) {
                bw.write(message);
            } catch (Exception e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

    /**
     * Получить файл с ключами из диапазона 12<= n*m <= 36
     */
    public static void getNumsOfMatrixInFile() {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= 36; i++) {
            for (int j = 2; j <= 36; j++) {
                if (i * j >= 12 && i * j <= 36) {
                    sb.append(i + " " + j);
                    sb.append(System.lineSeparator());
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(KEYS_FILE_NAME))) {
            bw.write(sb.toString());
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
