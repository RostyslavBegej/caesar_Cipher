package org.example.runner.executor;

import org.example.application.ApplicationContext;
import org.example.crypto.Cypher;
import org.example.file.FileManager;
import org.example.runner.RunOptions;

import java.nio.file.Path;

public abstract class AbstractExecutor {
    protected ApplicationContext applicationContext = ApplicationContext.getInstance();
    protected Cypher<Integer> cypher = applicationContext.getCypher();
    protected String fileName;
    protected FileManager fileManager;
    protected RunOptions runOptions;

    public AbstractExecutor(RunOptions runOptions, Cypher<Integer> cypher, FileManager fileManager) {
        this.runOptions = runOptions;
        this.cypher = cypher;
        this.fileManager = fileManager;
    }


    public void execute() {
        String content = fileManager.read(runOptions.getFilePath());
        String encryptedContent = this.executeCypherCommand(content);
        Path newFilePath = getNewFilePath();
        fileManager.write(newFilePath, encryptedContent);
    }

    public abstract String executeCypherCommand(String content);
    public abstract Path getNewFilePath();
}
