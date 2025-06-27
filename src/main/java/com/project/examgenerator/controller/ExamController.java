package com.project.examgenerator.controller;

import com.project.examgenerator.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping("/questions")
    public HashMap<String, Integer> getQuestions(
            //Model  model,
            @RequestParam String filename
//            @RequestBody int startPoint,
//            @RequestBody int endPoint,
//            @RequestBody int questionSize
            ) {
        //        model.addAttribute("questions", questions);
        return examService.getQuestions(filename/*, startPoint, endPoint, questionSize*/);
    }
}
