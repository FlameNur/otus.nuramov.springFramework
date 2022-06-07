package com.nuramov.hw01Questionnaire.ValuesToCheck;

import com.nuramov.hw01Questionnaire.CsvParser.CsvParser;
import com.nuramov.hw01Questionnaire.CsvParser.CsvParserImpl;

import java.util.Map;

public class ValuesToCheckImpl implements ValuesToCheck{
    String filePath;

    public ValuesToCheckImpl(String filePath) {
        if(filePath != null) {
            this.filePath = filePath;
        } else {
            throw new IllegalArgumentException("File not found!");
        }
    }

    @Override
    public Map<String, String> getValuesAsMap() {
        CsvParser csvParser = new CsvParserImpl(filePath);
        return csvParser.getFileFromResourceAsMap();
    }
}
