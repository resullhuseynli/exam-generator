package com.project.examgenerator.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleFileNotFoundException(FileNotFoundException fileNotFoundException) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(fileNotFoundException.getMessage(), LocalDateTime.now()));
    }

}
