package org.example.runner.executor;

import org.example.crypto.Cypher;
import org.example.file.FileManager;
import org.example.runner.RunOptions;

import java.nio.file.Path;

public class BruteforceExecutor extends AbstractExecutor {

    public BruteforceExecutor(RunOptions runOptions, Cypher<Integer> cypher, FileManager fileManager) {
        super(runOptions, cypher, fileManager);
    }

    @Override
    public String executeCypherCommand(String content) {
        return cypher.bruteForce(content);
    }

    @Override
    public Path getNewFilePath() {
        String newFileName = fileName.substring(0, fileName.length() - 4) + " [BRUTEFORCED].txt";

        return runOptions.getFilePath().resolveSibling(newFileName);
    }
}
