package com.project.examgenerator.exception;

import com.project.examgenerator.exception.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleFileNotFoundException(FileNotFoundException fileNotFoundException) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(fileNotFoundException.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<String>> handleIOException(IOException ioException) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(ioException.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(illegalArgumentException.getMessage(), LocalDateTime.now()));
    }

}
