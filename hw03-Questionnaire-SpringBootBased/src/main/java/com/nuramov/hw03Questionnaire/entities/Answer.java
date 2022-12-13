package com.nuramov.hw03Questionnaire.entities;

/**
 * Класс Answer представляет собой сущность варианта ответа
 */
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
