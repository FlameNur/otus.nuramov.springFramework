package com.nuramov.hw03Questionnaire.services;

/**
 * Интерфейс QuestionnaireLauncher позволяет запустить авторизацию пользователя и опросник
 * с единым поток ввода/вывода информации
 */
public interface QuestionnaireLauncher {

    /**
     * Метод run запускает авторизацию пользователя и опросник
     */
    void run();
}
