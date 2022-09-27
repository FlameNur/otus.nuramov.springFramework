package com.nuramov.hw02Questionnaire.userAuthorization;

import java.io.BufferedReader;

/**
 * Интерфейс UserAuthorization позволяет выполнить авторизацию пользователя по его имени
 */
public interface UserAuthorization {

    /**
     * Метод runUserAuthorization запускает авторизацию пользователя
     * @param reader - буферизированный поток на чтение символов
     */
    void runUserAuthorization(BufferedReader reader);
}
