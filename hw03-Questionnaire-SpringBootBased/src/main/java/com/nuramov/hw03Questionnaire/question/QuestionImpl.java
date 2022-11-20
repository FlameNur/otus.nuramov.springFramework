package com.nuramov.hw03Questionnaire.question;

import com.nuramov.hw03Questionnaire.answers.Answers;
import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QuestionImpl implements Question{

    // Map с вопросами (ключ - номер вопроса, значение - тело вопроса)
    private final Map<String, String> mapOfQuestions;
    private final Answers answers;

    @Autowired
    public QuestionImpl(@Value("${QuestionsSource}") String questionsPath, Answers answers, CsvParser csvParser) {
        this.answers = answers;
        this.mapOfQuestions = csvParser.getFileFromResourceAsMap(questionsPath);
    }

    @Override
    public Map<String, String> getMapOfQuestions() {
        return mapOfQuestions;
    }

    @Override
    public String[] getAnswerOptions(String numberOfQuestion) {
        return answers.getAnswerOptions(numberOfQuestion);
    }

    @Override
    public String getCorrectAnswer(String numberOfQuestion) {
        return answers.getCorrectAnswer(numberOfQuestion);
    }
}
