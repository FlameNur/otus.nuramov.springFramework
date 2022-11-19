package com.nuramov.hw03Questionnaire.question;

import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QuestionImpl implements Question{

    // Map с вопросами (ключ - номер вопроса, значение - тело вопроса)
    private final Map<String, String> mapOfQuestions;

    @Autowired
    public QuestionImpl(@Value("${QuestionsSource}") String questionsPath, CsvParser csvParser) {
        this.mapOfQuestions = csvParser.getFileFromResourceAsMap(questionsPath);
    }

    @Override
    public Map<String, String> getMapOfQuestions() {
        return mapOfQuestions;
    }
}
