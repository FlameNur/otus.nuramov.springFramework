package com.nuramov.hw03Questionnaire.answers;

/**
 * Интерфейс Answers позволяет получить массив ответов по номеру вопроса
 */
public interface Answers {

    /**
     * Метод getAnswers возвращает массив ответов по номеру вопроса
     * @param numberOfQuestion - номер вопроса
     * @return - массив ответов
     */
    String[] getAnswerOptions(String numberOfQuestion);

    /**
     * Метод getCorrectAnswer выдает правильный ответ на заданный вопрос
     * @param numberOfQuestion - номер вопроса
     * @return - правильный ответ
     */
    String getCorrectAnswer(String numberOfQuestion);
}
