package com.project.examgenerator.controller;

import com.project.examgenerator.request.ExamRequest;
import com.project.examgenerator.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping("/questions")
    public ResponseEntity<LinkedHashMap<String, Integer>> postQuestions(
            @RequestParam String filename,
            @RequestBody ExamRequest request
            ) throws IOException {
        return ResponseEntity.ok(examService.getQuestions(filename, request));
    }
}
