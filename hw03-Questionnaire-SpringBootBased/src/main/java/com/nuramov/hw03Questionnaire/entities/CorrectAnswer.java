package com.nuramov.hw03Questionnaire.entities;

import org.springframework.stereotype.Service;

@Service
public class CorrectAnswer {

    private String correctAnswerValue;
    private String correctAnswerNumber;

    public String getCorrectAnswerValue() {
        return correctAnswerValue;
    }

    public void setCorrectAnswerValue(String correctAnswerValue) {
        this.correctAnswerValue = correctAnswerValue;
    }

    public String getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    public void setCorrectAnswerNumber(String correctAnswerNumber) {
        this.correctAnswerNumber = correctAnswerNumber;
    }
}
