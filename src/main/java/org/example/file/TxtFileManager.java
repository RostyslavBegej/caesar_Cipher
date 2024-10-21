package org.example.file;

import org.example.exceptions.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class TxtFileManager extends AbstractFileManager {
    public String read(Path filePath) {
        try {
            return Files.readString(filePath);
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Path filePath, String content) {
        try {
            Files.writeString(filePath, content);
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
