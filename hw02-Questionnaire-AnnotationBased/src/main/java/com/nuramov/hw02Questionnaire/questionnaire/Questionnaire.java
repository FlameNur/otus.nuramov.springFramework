package com.nuramov.hw02Questionnaire.questionnaire;

import java.io.BufferedReader;
import java.util.Map;

/**
 * Интерфейс Questionnaire представляет собой опросник с вопросами и ответами на выбор
 */
public interface Questionnaire {

    /**
     * Метод runQuestionnaire запускает опросник с вопросами и ответами на выбор
     * @param reader -
     */
    void runQuestionnaire(BufferedReader reader);
}
