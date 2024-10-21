package org.example;

import org.example.application.ApplicationContext;
import org.example.crypto.Cypher;
import org.example.exceptions.FileNotFoundException;
import org.example.file.CSVFileManager;
import org.example.file.DOCFileManager;
import org.example.file.FileManager;
import org.example.file.TxtFileManager;
import org.example.runner.ApplicationLoader;
import org.example.runner.RunOptions;
import org.example.runner.executor.AbstractExecutor;
import org.example.runner.executor.BruteforceExecutor;
import org.example.runner.executor.DecryptExecutor;
import org.example.runner.executor.EncryptExecutor;


public class Main {
    public static void main(String[] args) {

        ApplicationLoader.getInstance().loadApplication();
        ApplicationContext applicationContext = ApplicationContext.getInstance();

        Cypher<Integer> cypher = applicationContext.getCypher();
        RunOptions runOptions = applicationContext.getArgumentsParser().parse(args);

        String fileName = runOptions.getFilePath().getFileName().toString();
        String fileExtension = fileName.substring(fileName.length() - 4);

        if (fileExtension.equals(".txt")) {
            TxtFileManager fileManager = new TxtFileManager();
            applicationContext.setFileManager(fileManager);
        } else if (fileExtension.equals(".csv")) {
            applicationContext.setFileManager(new CSVFileManager());
        } else if (fileExtension.equals(".doc")) {
            applicationContext.setFileManager(new DOCFileManager());
        }

        FileManager fileManager = applicationContext.getFileManager();
        System.out.println("File extension: " + fileExtension);

        AbstractExecutor executor = getExecutor(runOptions, cypher, fileManager);

        try {
            executor.execute();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static AbstractExecutor getExecutor(RunOptions runOptions, Cypher<Integer> cypher, FileManager fileManager) {
        return switch (runOptions.getCommand()) {
            case ENCRYPT -> new EncryptExecutor(runOptions, cypher, fileManager);
            case DECRYPT -> new DecryptExecutor(runOptions, cypher, fileManager);
            case BRUTEFORCE -> new BruteforceExecutor(runOptions, cypher, fileManager);
            default -> throw new IllegalArgumentException("Unknown command: " + runOptions.getCommand());
        };
    }
}