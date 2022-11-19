package com.nuramov.hw03Questionnaire.correctAnswer;

/**
 * Интерфейс CorrectAnswer позволяет получить правильный ответ по номеру вопроса
 */
public interface CorrectAnswer {

    /**
     * Метод getCorrectAnswer возвращает правильный ответ по номеру вопроса
     * @param numberOfQuestion - номер вопроса
     * @return - правильный ответ
     */
    String getCorrectAnswer(String numberOfQuestion);
}
