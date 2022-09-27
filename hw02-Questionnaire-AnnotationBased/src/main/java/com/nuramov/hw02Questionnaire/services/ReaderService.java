package com.nuramov.hw02Questionnaire.services;

/**
 * Интерфейс ReaderService позволяет запустить авторизацию пользователя и опросник
 * с единым поток ввода/вывода информации
 */
public interface ReaderService {

    /**
     * Метод run запускает авторизацию пользователя и опросник
     */
    void run();
}
