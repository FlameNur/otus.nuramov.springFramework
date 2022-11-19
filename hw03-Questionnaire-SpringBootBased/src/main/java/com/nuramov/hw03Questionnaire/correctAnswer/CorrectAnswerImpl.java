package com.nuramov.hw03Questionnaire.correctAnswer;

import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CorrectAnswerImpl implements CorrectAnswer{

    // Map с правильными ответами (ключ - номер вопроса, значение - правильный ответ)
    private final Map<String, String> mapOfValuesToCheck;

    @Autowired
    public CorrectAnswerImpl(@Value("${ValuesToCheckSource}") String valuesToCheckPath, CsvParser csvParser) {
        this.mapOfValuesToCheck = csvParser.getFileFromResourceAsMap(valuesToCheckPath);
    }

    @Override
    public String getCorrectAnswer(String numberOfQuestion) {
        return mapOfValuesToCheck.get(numberOfQuestion);
    }
}
