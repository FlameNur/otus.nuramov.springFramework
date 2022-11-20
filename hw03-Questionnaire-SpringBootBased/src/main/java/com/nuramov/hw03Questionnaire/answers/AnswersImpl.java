package com.nuramov.hw03Questionnaire.answers;

import com.nuramov.hw03Questionnaire.correctAnswer.CorrectAnswer;
import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AnswersImpl implements Answers {

    // Map с ответами (ключ - номер вопроса, значение - варианты ответов, разделенные запятыми)
    private final Map<String, String> mapOfAnswers;
    private final CorrectAnswer correctAnswer;

    @Autowired
    public AnswersImpl(@Value("${AnswersSource}") String answersPath, CorrectAnswer correctAnswer, CsvParser csvParser) {
        this.correctAnswer = correctAnswer;
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
        return correctAnswer.getCorrectAnswer(numberOfQuestion);
    }
}
