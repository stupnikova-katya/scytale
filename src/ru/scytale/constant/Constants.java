package ru.scytale.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс с константами приложения.
 */
public class Constants {

    public static final char SPACE_SYM = '\u0000';

    public static final String KEYS_FILE_NAME = "src/resources/KEYS.txt";

    public static final String LINES =  "====================================================";

    public static final String FILE_NAME = "Название файла: ";

    public static final String SYM_COUNT = "Количество символов: ";

    public static final String DECODING_TIME = "Время, занятое на перебор ключей алгоритмом: ";

    public static final String CORRECT_KEY = "Правильный вариант ключа: ";

    public static final String KEY_NUM = "Номер правильного варианта ключа среди перебираемых: ";

    /** Ключи из диапазона 12<= n*m <= 36 */
    public static final List<int[]> KEYS = new ArrayList<>();

    static {
        KEYS.add(new int[] {2,6});
        KEYS.add(new int[] {2,7});
        KEYS.add(new int[] {2,8});
        KEYS.add(new int[] {2,9});
        KEYS.add(new int[] {2,10});
        KEYS.add(new int[] {2,11});
        KEYS.add(new int[] {2,12});
        KEYS.add(new int[] {2,13});
        KEYS.add(new int[] {2,14});
        KEYS.add(new int[] {2,15});
        KEYS.add(new int[] {2,16});
        KEYS.add(new int[] {2,17});
        KEYS.add(new int[] {2,18});
        KEYS.add(new int[] {3,4});
        KEYS.add(new int[] {3,5});
        KEYS.add(new int[] {3,6});
        KEYS.add(new int[] {3,7});
        KEYS.add(new int[] {3,8});
        KEYS.add(new int[] {3,9});
        KEYS.add(new int[] {3,10});
        KEYS.add(new int[] {3,11});
        KEYS.add(new int[] {3,12});
        KEYS.add(new int[] {4,3});
        KEYS.add(new int[] {4,4});
        KEYS.add(new int[] {4,5});
        KEYS.add(new int[] {4,6});
        KEYS.add(new int[] {4,7});
        KEYS.add(new int[] {4,8});
        KEYS.add(new int[] {4,9});
        KEYS.add(new int[] {5,3});
        KEYS.add(new int[] {5,4});
        KEYS.add(new int[] {5,5});
        KEYS.add(new int[] {5,6});
        KEYS.add(new int[] {5,7});
        KEYS.add(new int[] {6,2});
        KEYS.add(new int[] {6,3});
        KEYS.add(new int[] {6,4});
        KEYS.add(new int[] {6,5});
        KEYS.add(new int[] {6,6});
        KEYS.add(new int[] {7,2});
        KEYS.add(new int[] {7,3});
        KEYS.add(new int[] {7,4});
        KEYS.add(new int[] {7,5});
        KEYS.add(new int[] {8,2});
        KEYS.add(new int[] {8,3});
        KEYS.add(new int[] {8,4});
        KEYS.add(new int[] {9,2});
        KEYS.add(new int[] {9,3});
        KEYS.add(new int[] {9,4});
        KEYS.add(new int[] {10,2});
        KEYS.add(new int[] {10,3});
        KEYS.add(new int[] {11,2});
        KEYS.add(new int[] {11,3});
        KEYS.add(new int[] {12,2});
        KEYS.add(new int[] {12,3});
        KEYS.add(new int[] {13,2});
        KEYS.add(new int[] {14,2});
        KEYS.add(new int[] {15,2});
        KEYS.add(new int[] {16,2});
        KEYS.add(new int[] {17,2});
        KEYS.add(new int[] {18,2});
    }

}
