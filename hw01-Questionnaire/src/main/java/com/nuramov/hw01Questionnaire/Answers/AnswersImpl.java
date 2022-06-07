package com.nuramov.hw01Questionnaire.Answers;

import com.nuramov.hw01Questionnaire.CsvParser.CsvParser;
import com.nuramov.hw01Questionnaire.CsvParser.CsvParserImpl;

import java.util.Map;

public class AnswersImpl implements Answers{
    String filePath;

    public AnswersImpl(String filePath) {
        if(filePath != null) {
            this.filePath = filePath;
        } else {
            throw new IllegalArgumentException("File not found!");
        }
    }

    @Override
    public Map<String, String> getAnswersAsMap() {
        CsvParser csvParser = new CsvParserImpl(filePath);
        return csvParser.getFileFromResourceAsMap();
    }
}
