package com.nuramov.hw03Questionnaire.handlers;

import com.nuramov.hw03Questionnaire.entities.Answer;
import com.nuramov.hw03Questionnaire.entities.CorrectAnswer;

import java.util.List;

/**
 * Интерфейс AnswerHandler позволяет получить массив ответов по номеру вопроса
 */
public interface AnswerHandler {

    /**
     * Метод getAnswers возвращает список ответов по номеру вопроса
     * @param numberOfQuestion - номер вопроса
     * @return - список ответов
     */
    List<Answer> getAnswers(String numberOfQuestion);

    /**
     * Метод getCorrectAnswer выдает правильный ответ на заданный вопрос
     * @param numberOfQuestion - номер вопроса
     * @return - правильный ответ
     */
    CorrectAnswer getCorrectAnswer(String numberOfQuestion);
}
