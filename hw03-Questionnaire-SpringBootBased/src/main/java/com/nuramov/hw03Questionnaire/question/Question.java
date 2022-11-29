package com.nuramov.hw03Questionnaire.question;

import org.springframework.stereotype.Service;

/**
 * Класс Question представляет собой сущность вопроса для опросника с соответствующими полями
 */
@Service
public class Question {

    private String numberOfQuestion;
    private String question;
    private String[] answerOptions;
    private String correctAnswer;

    public void setNumberOfQuestion(String numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerOptions(String[] answerOptions) {
        this.answerOptions = answerOptions;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
