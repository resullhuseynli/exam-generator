package com.project.examgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private int questionNumber;
    private String questionText;
    private ArrayList<String> answers;
    private char trueAnswer;
}
