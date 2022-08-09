package com.nuramov.hw02Questionnaire.csvParser;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс CsvParserImpl реализует интерфейс csvParser
 */
@Service
public class CsvParserImpl implements CsvParser{

    @Override
    public Map<String, String> getFileFromResourceAsMap(String filePath) {
        Map<String, String> mapOfItems;

        if(filePath != null) {
            mapOfItems = getInfoAsMap(filePath);
        } else {
            throw new IllegalArgumentException("File not found!");
        }
        return mapOfItems;
    }

    /**
     * Метод getInfoAsMap позволяет считать информацию из .csv файла
     * @param filePath - расположение .csv файла
     * @return - Map<String, String>, где ключ - номер (id),
     * значение - вопрос/варианты ответа/номер правильного ответа
     */
    private Map<String, String> getInfoAsMap(String filePath) {
        // Используем TreeMap, чтобы сразу отсортировать информацию по ключам
        Map<String, String> mapOfItems = new TreeMap<>();

        // Загрузчик текущего класса, чтобы получить поток вводимой информации из .csv файла
        ClassLoader classLoader = getClass().getClassLoader();
        try(InputStream inputStream = classLoader.getResourceAsStream(filePath);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
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
