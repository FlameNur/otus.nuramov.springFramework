package com.nuramov.hw01Questionnaire.Questions;

import com.nuramov.hw01Questionnaire.CsvParser.CsvParser;
import com.nuramov.hw01Questionnaire.CsvParser.CsvParserImpl;

import java.util.Map;

public class QuestionsImpl implements Questions{
    String filePath;

    public QuestionsImpl(String filePath) {
        if(filePath != null) {
            this.filePath = filePath;
        } else {
            throw new IllegalArgumentException("File not found!");
        }
    }

    @Override
    public Map<String, String> getQuestionsAsMap() {
        CsvParser csvParser = new CsvParserImpl(filePath);
        return csvParser.getFileFromResourceAsMap();
    }
}
