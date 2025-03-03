package com.primetech.primetech_store.common.infrastructure.filesystem;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private final Path rootLocation = Paths.get("images/public");

    public String saveFile(MultipartFile file, String folder, String newFileName) throws IOException {
        Path folderPath = rootLocation;
        if (!folder.isEmpty()) {
            folderPath = rootLocation.resolve(folder);
        }
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        Path path = folderPath.resolve(newFileName);
        Files.write(path, file.getBytes());
        return "/" + (folder.isEmpty() ? "" : folder + "/") + newFileName;
    }

    public void deleteFile(String relativePath) throws IOException {
        Path path = Paths.get("images/public" + relativePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    public boolean isValidFile(MultipartFile file, String originalName) {
        long maxFileSize = 5 * 1024 * 1024;
        return file.getSize() <= maxFileSize &&
                (originalName.endsWith(".jpg") || originalName.endsWith(".jpeg") || originalName.endsWith(".png"));
    }
}
