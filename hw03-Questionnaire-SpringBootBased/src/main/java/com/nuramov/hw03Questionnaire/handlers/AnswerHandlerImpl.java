package com.nuramov.hw03Questionnaire.handlers;

import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AnswerHandlerImpl implements AnswerHandler{

    // Map с ответами (ключ - номер вопроса, значение - варианты ответов, разделенные запятыми)
    private final Map<String, String> mapOfAnswers;
    //private final CorrectAnswerDel correctAnswerDel;

    @Autowired
    public AnswerHandlerImpl(@Value("${AnswersSource}") String answersPath, CsvParser csvParser) {
        //this.correctAnswerDel = correctAnswerDel;
        this.mapOfAnswers = csvParser.getFileFromResourceAsMap(answersPath);
    }

    @Override
    public String[] getAnswerOptions(String numberOfQuestion) {
        // Получаем варианты ответа в одну строку
        String answers = mapOfAnswers.get(numberOfQuestion);
        // Разделяем строку с вариантами ответов для вывода каждого варианта ответа отдельно
        return answers.split(",");
    }

    @Override
    public String getCorrectAnswer(String numberOfQuestion) {
        //return correctAnswerDel.getCorrectAnswer(numberOfQuestion);
        return null;
    }


    // По CorrectAnswer
    /*
    // Map с правильными ответами (ключ - номер вопроса, значение - правильный ответ)
    private final Map<String, String> mapOfValuesToCheck;

    @Autowired
    public CorrectAnswerDelImpl(@Value("${ValuesToCheckSource}") String valuesToCheckPath, CsvParser csvParser) {
        this.mapOfValuesToCheck = csvParser.getFileFromResourceAsMap(valuesToCheckPath);
    }

    @Override
    public String getCorrectAnswer(String numberOfQuestion) {
        return mapOfValuesToCheck.get(numberOfQuestion);
    }
     */
}
