package com.project.examgenerator.controller;

import com.project.examgenerator.model.Question;
import com.project.examgenerator.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping("/questions") //QUESTION NUMBER, {question, true answer}
    public ResponseEntity<List<Question>> postQuestions(
            @RequestParam String filename,
            @RequestParam int startPoint,
            @RequestParam int endPoint,
            @RequestParam int questionCount
            ) throws IOException {
        if(startPoint < 0 || endPoint < 0 || questionCount < 0 || questionCount > (endPoint - startPoint) || startPoint > endPoint) {
            throw new IllegalArgumentException("Invalid start and end point provided");
        }
        return ResponseEntity.ok(examService.getQuestions(filename, startPoint, endPoint, questionCount));
    }
}
