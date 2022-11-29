package com.nuramov.hw03Questionnaire.questionService;

import com.nuramov.hw03Questionnaire.answers.Answers;
import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import com.nuramov.hw03Questionnaire.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    // Map с вопросами (ключ - номер вопроса, значение - тело вопроса)
    private final Map<String, String> mapOfQuestions;
    private final Answers answers;

    @Autowired
    public QuestionServiceImpl(@Value("${QuestionsSource}") String questionsPath, Answers answers, CsvParser csvParser) {
        this.answers = answers;
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
            question.setAnswerOptions(answers.getAnswerOptions(numberOfQuestion));
            question.setCorrectAnswer(answers.getCorrectAnswer(numberOfQuestion));

            listOfQuestions.add(positionInList, question);
            positionInList++;
        }
        return listOfQuestions;
    }
}
