package com.project.examgenerator.service;

import com.project.examgenerator.model.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class ExamService {

    private final int QUESTION_LINE_SIZE;

    public ExamService(@Value("${QUESTION_LINE_SIZE}") int questionLineSize) {
        QUESTION_LINE_SIZE = questionLineSize;
    }

    public List<Question> getQuestions(String filename,
                                                                  int startPoint,
                                                                  int endPoint,
                                                                  int questionCount) throws IOException {
        List<Question> questions = new ArrayList<>();
        final long LINE_COUNT = countLines(filename, endPoint);
        try (BufferedReader br = new BufferedReader(new FileReader("uploads/" + filename))) {
            for (int i = 1; i < startPoint; i++) {
                for (int j=0 ; j < QUESTION_LINE_SIZE; j++) {
                    br.readLine();
                }
            }
            String line = br.readLine();
            int range = endPoint - startPoint;
            int questionNumber = 0;
            while (line != null && questionNumber != endPoint) {
                Question questionGenerated = new Question();
                ArrayList<String> answers = new ArrayList<>();
                int counter = 0;
                while (counter < QUESTION_LINE_SIZE) {
                    if (counter == 0) {
                        questionGenerated.setQuestionNumber(getQuestionNumber(line));
                        line = removeNumber(line);
                        questionGenerated.setQuestionText(line);
                    } else {
                        if (checkTrue(line)) {
                            questionGenerated.setTrueAnswer(trueVariant(counter));
                        }
                        line = makeVariant(line, counter);
                        answers.add(line);
                    }
                    counter++;
                    line = br.readLine();
                }
                questionGenerated.setAnswers(answers);
                questions.add(questionGenerated);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException(filename + " not found");
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

    private char trueVariant(int variant) {
        char trueVariant = ' ';
        switch (variant) {
            case 1 -> trueVariant = 'A';
            case 2 -> trueVariant = 'B';
            case 3 -> trueVariant = 'C';
            case 4 -> trueVariant = 'D';
            case 5 -> trueVariant = 'E';
        }
        return trueVariant;
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

    private long countLines(String filename, int endPoint) throws IOException {
        long lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("uploads/" + filename))) {
            while(br.readLine() != null) {
                lineCount++;
            }
            if((lineCount / QUESTION_LINE_SIZE) < endPoint) {
                throw  new IOException("End point is more than " + lineCount/6);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(filename + " not found");
        }
        return lineCount;
    }

    private int getQuestionNumber(String question) {
        int counter = 0;
        while (question.charAt(counter) != '.') {
            counter++;
        }
        return  Integer.parseInt(question.substring(0, counter));
    }

    private Set<Integer> randomNumbersGenerator(int startPoint, int endPoint, int questionCount) {
        Set<Integer> randomNumbers = new HashSet<>();
        int randomNumber;
        Random random = new Random();
        while (randomNumbers.size() < questionCount) {
            randomNumber = random.nextInt(endPoint - startPoint + 1) + startPoint;
            randomNumbers.add(randomNumber);
        }
        return randomNumbers;
    }

}
