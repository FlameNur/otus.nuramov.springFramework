package com.nuramov.hw03Questionnaire.handlers;

import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import com.nuramov.hw03Questionnaire.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionHandlerImpl implements QuestionHandler{

    // Map с вопросами (ключ - номер вопроса, значение - тело вопроса)
    private final Map<String, String> mapOfQuestions;
    //private final Answers answers;

    @Autowired
    public QuestionHandlerImpl(@Value("${QuestionsSource}") String questionsPath, CsvParser csvParser) {
        //this.answers = answers;
        this.mapOfQuestions = csvParser.getFileFromResourceAsMap(questionsPath);
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> listOfQuestions = new ArrayList<>();
        int positionInList = 0;

        for(Map.Entry<String, String> entry : mapOfQuestions.entrySet()) {
            String numberOfQuestion = entry.getKey();
            String questionValue = entry.getValue();
            // Проводим сборку вопроса
            Question question = new Question();
            question.setNumberOfQuestion(numberOfQuestion);
            question.setQuestion(questionValue);
            //question.setAnswerOptions(answers.getAnswerOptions(numberOfQuestion));
            //question.setCorrectAnswer(answers.getCorrectAnswer(numberOfQuestion));

            listOfQuestions.add(positionInList, question);
            positionInList++;
        }
        return listOfQuestions;
    }
}
