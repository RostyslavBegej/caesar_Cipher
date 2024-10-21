package org.example.runner.executor;

import org.example.crypto.Cypher;
import org.example.file.FileManager;
import org.example.runner.RunOptions;

import java.nio.file.Path;

public class DecryptExecutor extends AbstractExecutor {
    public DecryptExecutor(RunOptions runOptions, Cypher<Integer> cypher, FileManager fileManager) {
        super(runOptions, cypher, fileManager);
    }

    @Override
    public String executeCypherCommand(String content) {
        return cypher.decrypt(content, runOptions.getKey());
    }

    @Override
    public Path getNewFilePath() {
        String newFileName = fileName.substring(0, fileName.length() - 4) + " [DECRYPTED].txt";

        return runOptions.getFilePath().resolveSibling(newFileName);
    }
}
