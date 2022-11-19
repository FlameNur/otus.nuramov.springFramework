package com.nuramov.hw03Questionnaire.question;

import java.util.Map;

/**
 * Интерфейс Question позволяет получить все вопросы в виде map
 * (ключ - номер вопроса, значение - тело вопроса)
 */
public interface Question {

    /**
     * Метод getMapOfQuestions возвращает map с вопросами
     * @return - map с вопросами
     */
    Map<String, String> getMapOfQuestions();
}
