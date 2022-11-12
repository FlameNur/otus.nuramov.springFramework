package com.nuramov.hw03Questionnaire.questionnaire;

import java.io.BufferedReader;

/**
 * Интерфейс Questionnaire представляет собой опросник с вопросами и ответами на выбор
 */
public interface Questionnaire {

    /**
     * Метод runQuestionnaire запускает опросник с вопросами и ответами на выбор
     * @param reader - буферизированный поток на чтение символов
     */
    void runQuestionnaire(BufferedReader reader);
}
