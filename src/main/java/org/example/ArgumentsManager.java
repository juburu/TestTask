package org.example;

import java.util.ArrayList;
import java.util.List;

public class ArgumentsManager {
    public static final List <String> sourceFiles = new ArrayList<>();
    public static String outputPath = "";
    public static String outputPrefix = "";
    public static boolean addtoFile = false;
    public static boolean shortStatistics = false;
    public static boolean fullStatistics = false;

    public static void parseArguments(String [] args){
        for (int i=0;i< args.length;i++) {
                try {
                    switch (args[i]) {
                        case "-o":
                            if (i + 1 >= args.length || args[i + 1].startsWith("-")) {
                                System.err.println("Ошибка: после флага -о необходимо указать путь к директории");
                            }
                            else {
                                outputPath = args[++i];
                            }
                            break;
                        case "-p":
                            if (i + 1 >= args.length || args[i + 1].startsWith("-")) {
                                System.err.println("Ошибка: после флага -p необходимо указать префикс.");
                            } else {
                                outputPrefix = args[++i];
                            }
                            break;
                        case "-a":
                            addtoFile = true;
                            break;
                        case "-s":
                            shortStatistics = true;
                            break;
                        case "-f":
                            fullStatistics = true;
                            break;
                        default:
                            if (args[i].startsWith("-")){
                                System.err.println("Ошибка: неизвестный флаг " + args[i] +". Флаг будет проигнорирован");
                            } else {
                                sourceFiles.add(args[i]);
                            }
                            break;

                    }
                }
                catch (Exception e) {
                    System.err.println("Ошибка при обработке аргумента: " + args[i]+". Аргумент будет проигнорирован");
                }

        }
        if (sourceFiles.isEmpty()){
            System.err.println("Ошибка: не переданы файлы для обработки. Укажите хотя бы один файл");
        }
    }

}

