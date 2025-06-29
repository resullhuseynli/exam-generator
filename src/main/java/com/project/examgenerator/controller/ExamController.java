package com.project.examgenerator.controller;

import com.project.examgenerator.model.Question;
import com.project.examgenerator.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @Operation(summary = "Get All The Questions With Specific Bound")
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions(
            @RequestParam String filename,
            @RequestParam int startPoint,
            @RequestParam int endPoint) throws IOException {
        if(startPoint < 0 || endPoint < 0 || startPoint > endPoint) {
            throw new IllegalArgumentException("Invalid start and end point provided");
        }
        return ResponseEntity.ok(examService.getAllQuestions(filename, startPoint, endPoint));
    }

    @Operation(summary = "Get Random Questions With Specific Bound")
    @GetMapping("/random/questions")
    public ResponseEntity<List<Question>> getRandomQuestions(
            @RequestParam String filename,
            @RequestParam int startPoint,
            @RequestParam int endPoint,
            @RequestParam int maxQuestions) throws IOException {
        if(startPoint < 0 || endPoint < 0 || maxQuestions < 0 || startPoint > endPoint || (endPoint - startPoint) + 1 < maxQuestions) {
            throw new IllegalArgumentException("Invalid start and end point provided");
        }
        return  ResponseEntity.ok(examService.getRandomQuestions(filename, startPoint, endPoint, maxQuestions));
    }
}
