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
            switch (args[i]){
                case "-o":
                    outputPath = args[++i];
                    break;
                case "-p":
                    outputPrefix = args[++i];
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
                    sourceFiles.add(args[i]);
                    break;
            }

        }
    }
}

