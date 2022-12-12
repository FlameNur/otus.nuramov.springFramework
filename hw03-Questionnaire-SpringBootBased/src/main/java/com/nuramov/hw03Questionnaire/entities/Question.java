package com.nuramov.hw03Questionnaire.entities;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс Question представляет собой сущность вопроса для опросника с соответствующими полями
 */
@Service
public class Question {

    private String numberOfQuestion;
    private String question;
    private List<String> answers;
    private String correctAnswer;

    public String getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(String numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
