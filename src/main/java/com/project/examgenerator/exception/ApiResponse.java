package com.project.examgenerator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {

    private T message;
    private LocalDateTime timestamp;

}
