package com.project.examgenerator.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ExamRequest {

    private int startPoint;
    private int endPoint;
    private int questionCount;

}
