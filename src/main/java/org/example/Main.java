package org.example;

public class Main {
    public static void main (String [] args) {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        FileService fileService = new FileService();
        StatisticsService statisticsService = new StatisticsService();

        argumentsManager.parseArguments(args);
        fileService.processFiles();
        fileService.writeOutput();
        statisticsService.printStatistics();
    }
}