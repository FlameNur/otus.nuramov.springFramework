package com.nuramov.hw03Questionnaire.entities;

import org.springframework.stereotype.Service;

@Service
public class Answer {

    private String answerValue;
    private String answerNumber;

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public String getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(String answerNumber) {
        this.answerNumber = answerNumber;
    }
}
