package com.project.examgenerator.controller;

import com.project.examgenerator.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService examService;

    @Operation(summary = "The Endpoint For Uploading File")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestPart("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) return ResponseEntity.badRequest().body("File is empty");

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (!Objects.equals(contentType, "text/plain") || fileName == null || !fileName.toLowerCase().endsWith(".txt")) {
            return ResponseEntity.badRequest().body("Only plain text files are allowed");
        }
        examService.uploadFile(file);
        return ResponseEntity.ok("File uploaded: " + file.getOriginalFilename());
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Resource resource = examService.downloadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
