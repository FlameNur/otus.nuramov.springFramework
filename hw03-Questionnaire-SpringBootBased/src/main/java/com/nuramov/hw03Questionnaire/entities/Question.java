package com.nuramov.hw03Questionnaire.entities;

import java.util.List;

/**
 * Класс Question представляет собой сущность вопроса для опросника с соответствующими полями
 */
public class Question {

    private String numberOfQuestion;
    private String question;
    private List<Answer> answers;
    private CorrectAnswer correctAnswer;

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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public CorrectAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(CorrectAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
