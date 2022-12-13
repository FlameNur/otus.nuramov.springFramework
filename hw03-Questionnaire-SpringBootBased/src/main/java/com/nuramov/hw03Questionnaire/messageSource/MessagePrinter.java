package com.nuramov.hw03Questionnaire.messageSource;

/**
 * Интерфейс MessagePrinter предназначен для вывода обработанных/локализованных сообщений
 */
public interface MessagePrinter {

    /**
     * Метод printMessage позволяет вывести локализованное на выбранный язык сообщение
     * @param messageKey - сообщение для локализации и вывода
     */
    void printMessage(String messageKey);
}
