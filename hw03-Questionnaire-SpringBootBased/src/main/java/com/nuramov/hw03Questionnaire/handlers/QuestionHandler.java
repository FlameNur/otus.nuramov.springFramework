package com.nuramov.hw03Questionnaire.handlers;

import com.nuramov.hw03Questionnaire.entities.Question;

import java.util.List;

/**
 * Интерфейс QuestionHandler используется для сборки и выдачи списка вопросов для опросника
 */
public interface QuestionHandler {

    /**
     * Метод getQuestions возвращает список с вопросами
     * @return - список с вопросами
     */
    List<Question> getQuestions();
}
