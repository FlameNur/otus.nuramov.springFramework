package com.nuramov.hw03Questionnaire.handlers;

import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import com.nuramov.hw03Questionnaire.entities.Answer;
import com.nuramov.hw03Questionnaire.entities.CorrectAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerHandlerImpl implements AnswerHandler{

    // Map с ответами (ключ - номер вопроса, значение - варианты ответов, разделенные запятыми)
    private final Map<String, String> mapOfAnswers;
    // Map с правильными ответами (ключ - номер вопроса, значение - правильный ответ)
    private final Map<String, String> mapOfCorrectAnswer;

    @Autowired
    public AnswerHandlerImpl(@Value("${AnswersSource}") String answersPath,
                             @Value("${ValuesToCheckSource}") String correctAnswersPath,
                             CsvParser csvParser) {
        this.mapOfAnswers = csvParser.getFileFromResourceAsMap(answersPath);
        this.mapOfCorrectAnswer = csvParser.getFileFromResourceAsMap(correctAnswersPath);
    }

    @Override
    public List<Answer> getAnswers(String numberOfQuestion) {
        List<Answer> answers = new ArrayList<>();
        // Получаем варианты ответа в одну строку и разделяем их запятой
        String[] answerOption = mapOfAnswers.get(numberOfQuestion).split(",");
        for(String answerValue : answerOption) {
            Answer answer = new Answer();
            answer.setAnswerNumber(numberOfQuestion);
            answer.setAnswerValue(answerValue);
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public CorrectAnswer getCorrectAnswer(String numberOfQuestion) {
        CorrectAnswer correctAnswer = new CorrectAnswer();
        correctAnswer.setCorrectAnswerNumber(numberOfQuestion);
        correctAnswer.setCorrectAnswerValue(mapOfCorrectAnswer.get(numberOfQuestion));
        return correctAnswer;
    }
}
