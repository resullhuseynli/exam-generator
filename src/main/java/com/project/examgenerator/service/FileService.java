package com.project.examgenerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileService {

    private static final String UPLOAD_DIR = "uploads/";

    public void uploadFile(MultipartFile file) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public Resource downloadFile(String filename) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("File does not exist");
        }
        return new UrlResource(filePath.toUri());
    }

}
