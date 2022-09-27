package com.nuramov.hw02Questionnaire.messageSource;

/**
 * Интерфейс MessagePrinter предназначен для вывода обработанных/локализованных сообщений
 */
public interface MessagePrinter {

    /**
     * Метод printMessageRu позволяет вывести локализованное на русский язык сообщение
     * @param message - сообщение для локализации и вывода
     */
    void printMessageRu(String message);
}
