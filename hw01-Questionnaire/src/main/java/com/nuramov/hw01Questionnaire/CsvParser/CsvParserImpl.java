package com.nuramov.hw01Questionnaire.CsvParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class CsvParserImpl implements CsvParser{
    private String filePath;

    public CsvParserImpl(String filePath) {
        if(filePath != null) {
            this.filePath = filePath;
        } else {
            throw new IllegalArgumentException("File not found!");
        }
    }

    @Override
    public Map<String, String> getFileFromResourceAsMap() {
        InputStream is = getFileFromResourceAsStream();
        return printInputStream(is);
    }

    /**
     *
     * @return
     */
    private InputStream getFileFromResourceAsStream() {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + filePath);
        } else {
            return inputStream;
        }
    }

    /**
     *
     * @param is
     * @return
     */
    private Map<String, String> printInputStream(InputStream is) {
        Map<String, String> mapOfItems = new TreeMap<>();

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(";");
                mapOfItems.put(items[0], items[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapOfItems;
    }
}
