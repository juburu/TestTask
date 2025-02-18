package org.example;
import java.util.Collections;

public class StatisticsService {

    public static void printStatistics() {
        if (ArgumentsManager.shortStatistics) {
            System.out.println("Краткая статистика:\nКоличество целых чисел: " + FileService.integers.size() +"\nКоличество вещественных чисел: " + FileService.floats.size()+"\nКоличество строк: " + FileService.strings.size());
        }

        if (ArgumentsManager.fullStatistics) {
            System.out.println("Полная статистика:\n");
            printIntegerFullStatistics();
            printFloatFullStatistics();
            printStringFullStatistics();
        }
    }

    public static void printIntegerFullStatistics() {
        if (FileService.integers.isEmpty()) {
            return;
        }

        long min = Collections.min(FileService.integers);
        long max = Collections.max(FileService.integers);
        double sum = FileService.integers.stream().mapToLong(Long::longValue).sum();
        double average = sum / FileService.integers.size();

        System.out.println("Целые числа:\nКоличество: " + FileService.integers.size()+ "\nМинимальное значение: " + min + "\nМаксимальное значение: " + max +"\nСумма: " + sum + "\nСреднее: " + average+"\n");
    }

    public static void printFloatFullStatistics() {
        if (FileService.floats.isEmpty()) {
            return;
        }

        double min = Collections.min(FileService.floats);
        double max = Collections.max(FileService.floats);
        double sum = FileService.floats.stream().mapToDouble(Double::doubleValue).sum();
        double average = sum / (FileService.floats.size());
        System.out.println("Вещественные числа:\nКоличество: " + FileService.floats.size() + "\nМинимальное значение: " + min+ "\nМаксимальное значение: " + max+ "\nСумма: " + sum+"\nСреднее: " + average+"\n");
    }

    public static void printStringFullStatistics() {
        if (FileService.strings.isEmpty()) {
            return;
        }
        int minLength = FileService.strings.stream().mapToInt(String::length).min().orElse(0);
        int maxLength = FileService.strings.stream().mapToInt(String::length).max().orElse(0);
        System.out.println("Строки:\nКоличество: " + FileService.strings.size()+"\nРазмер самой короткой строки: " + minLength+"\nРазмер самой длинной строки: " + maxLength+"\n");
    }
}
