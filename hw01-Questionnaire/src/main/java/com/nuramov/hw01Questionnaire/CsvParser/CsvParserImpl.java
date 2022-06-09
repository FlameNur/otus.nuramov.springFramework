package com.nuramov.hw01Questionnaire.CsvParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс CsvParserImpl реализует интерфейс CsvParser
 */
public class CsvParserImpl implements CsvParser{

    @Override
    public Map<String, String> getFileFromResourceAsMap(String filePath) {
        InputStream is;

        if(filePath != null) {
            is = getFileFromResourceAsStream(filePath);
        } else {
            throw new IllegalArgumentException("File not found!");
        }
        return printInputStream(is);
    }

    /**
     * Метод getFileFromResourceAsStream позволяет получить поток вводимой информации
     * @param filePath - расположение .csv файла
     * @return - поток вводимой информации из .csv файла
     */
    private InputStream getFileFromResourceAsStream(String filePath) {
        // Загрузчик текущего класса, чтобы получить поток вводимой информации из .csv файла
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + filePath);
        } else {
            return inputStream;
        }
    }

    /**
     * Метод printInputStream позволяет
     * @param is - поток вводимой информации из .csv файла
     * @return - Map<String, String>,
     * где ключ - номер (id), значение - вопрос/варианты ответа/номер правильного ответа
     */
    private Map<String, String> printInputStream(InputStream is) {
        // Используем TreeMap, чтобы сразу отсортировать информацию по ключам
        Map<String, String> mapOfItems = new TreeMap<>();

        // Считываем поток вводимой информации
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            // Считываем строки из .csv файла
            String line;
            while ((line = reader.readLine()) != null) {
                // Разделяем строки на ключ и значение и записываем в Map
                String[] items = line.split(";");
                mapOfItems.put(items[0], items[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapOfItems;
    }
}
