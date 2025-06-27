package com.project.examgenerator.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class ExamService {

    private final int QUESTION_LINE_SIZE = 6;

    public HashMap<String, Integer> getQuestions(String filename /*int startPoint, int endPoint, int questionSize*/) throws IOException {
        LinkedHashMap<String, Integer> questions = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("uploads/" + filename))) {
            String line = br.readLine();
            while (line != null) {
                int trueAnswer = 0;
                int counter = 0;
                StringBuilder question = new StringBuilder();
                while (counter < QUESTION_LINE_SIZE) {
                    if (counter == 0) {
                        line = removeNumber(line);
                    } else {
                        if (checkTrue(line)) {
                            trueAnswer = counter;
                        }
                        line = makeVariant(line, counter);
                    }
                    counter++;
                    question.append(line).append('\n');
                    line = br.readLine();
                }
                questions.put(question.toString().trim(), trueAnswer);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("File not found");
        }  catch (IOException ioException) {
            throw new IOException();
        }
        return questions;
    }

    private String removeNumber(String question) {
        int counter = 0;
        while (counter < question.length()) {
            if (question.charAt(counter) == '.') {
                question = question.substring(counter + 1);
                break;
            }
            counter++;
        }
        return question;
    }

    private boolean checkTrue(String answer) {
        answer = answer.trim();
        return answer.startsWith("√");
    }

    private String makeVariant(String line, int variant) {
        line = line.trim().substring(1);
        switch (variant) {
            case 1 -> line = "A:" + line;
            case 2 -> line = "B:" + line;
            case 3 -> line = "C:" + line;
            case 4 -> line = "D:" + line;
            case 5 -> line = "E:" + line;
        }
        return line;
    }

}
