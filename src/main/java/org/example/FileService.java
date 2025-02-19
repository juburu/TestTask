package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

public class FileService {
    public static boolean canCreateOutputPath = true;
    public static final List<Long> integers = new ArrayList<>();
    public static final List<Double> floats = new ArrayList<>();
    public static final List<String> strings = new ArrayList<>();

    public static void processFiles() {
        List<BufferedReader> readers = new ArrayList<>();
        try {
            for (String fileName : ArgumentsManager.sourceFiles) {
                readers.add(Files.newBufferedReader(Paths.get(fileName)));
            }
            boolean hasMoreLines;
            do {
                hasMoreLines = false;
                for (BufferedReader reader : readers) {
                    String line = reader.readLine();
                    if (line != null) {
                        processLine(line.trim());
                        hasMoreLines = true;
                    }
                }
            } while (hasMoreLines);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
            canCreateOutputPath = false;
        } finally {
            // Закрываем все файлы
            for (BufferedReader reader : readers) {
                try {
                    reader.close();
                } catch (IOException ignored) {}
            }
        }
    }

        public static void processLine(String line) {
        if (line.isEmpty()) return;
        try {
            if (isInteger(line)) {
                integers.add(Long.parseLong(line));
            } else if (isFloat(line)) {
                floats.add(Double.parseDouble(line));
            } else {
                strings.add(line);
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка при парсинге строки: " + line);
        }
    }
    public static boolean isInteger(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//    private static void processLine(String line) {
//        if (line.isEmpty()) return;
////        String integerRegexp = "-?\\d+";
////        String floatRegexp = "-?\\d*\\.\\d+(?:[eE][-+]?\\d+)?";
//        try {
//            if (line.matches(integerRegexp)) {
//                integers.add(Long.parseLong(line));
//            } else if (line.matches(floatRegexp)) {
//                floats.add(Double.parseDouble(line));
//            } else {
//                strings.add(line);
//            }
//        } catch (NumberFormatException e) {
//            System.err.println("Ошибка при парсинге строки: " + line);
//        }
//    }

    public static void writeOutput() {
        if (canCreateOutputPath) {
            createOutputDirectory();
        }
        String intFileName = "integers.txt";
        String floatFileName = "floats.txt";
        String strFileName = "strings.txt";

        if (!integers.isEmpty()) {
            writeDataToFile(integers, intFileName);
        }
        if (!floats.isEmpty()) {
            writeDataToFile(floats, floatFileName);
        }
        if (!strings.isEmpty()) {
            writeDataToFile(strings, strFileName);
        }
    }

    public static void createOutputDirectory() {
        String rootPath = "./";
        ArgumentsManager.outputPath = rootPath + ArgumentsManager.outputPath;
        Path path = Path.of(ArgumentsManager.outputPath);

        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.err.println("Не удалось создать папку для вывода результатов! " + e.getMessage());
                System.err.println("Файлы будут созданы в корневой папке проекта!");
                System.err.println();
                ArgumentsManager.outputPath = rootPath;
            }
        }
    }

    private static <T> void writeDataToFile(List<T> data, String fileName) {
        String fullPath = ArgumentsManager.outputPath + File.separator + ArgumentsManager.outputPrefix + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath, ArgumentsManager.addtoFile))) {
            for (T item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fullPath);
            System.err.println();
        }
    }
}
