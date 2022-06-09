package com.nuramov.hw01Questionnaire.CsvParser;

import java.util.Map;

/**
 * Интерфейс CsvParser предназначен для работы с .csv файлом
 * и преобразования информации из .csv файла в Map<String, String>
 */
public interface CsvParser {

    /**
     * Метод getFileFromResourceAsMap позволяет получить Map<String, String> из .csv файла
     * @param filePath - расположение .csv файла
     * @return - возвращает Map<String, String>,
     * где ключ - номер (id), значение - вопрос/варианты ответа/номер правильного ответа
     */
    Map<String, String> getFileFromResourceAsMap(String filePath);
}
