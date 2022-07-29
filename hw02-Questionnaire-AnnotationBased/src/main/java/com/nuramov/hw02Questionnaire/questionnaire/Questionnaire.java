package com.nuramov.hw02Questionnaire.questionnaire;

import java.io.BufferedReader;

/**
 * Интерфейс Questionnaire представляет собой опросник с вопросами и ответами на выбор
 */
public interface Questionnaire {

    /**
     * Метод runQuestionnaire запускает опросник с вопросами и ответами на выбор
     */
    void runQuestionnaire(BufferedReader reader);
}
