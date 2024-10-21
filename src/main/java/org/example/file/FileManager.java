package org.example.file;

import java.nio.file.Path;

public interface FileManager {
    String read(Path filePath);
    void write(Path filePath, String content);
}
