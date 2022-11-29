package com.nuramov.hw03Questionnaire.questionService;

import com.nuramov.hw03Questionnaire.question.Question;

import java.util.List;

/**
 * Интерфейс QuestionService используется для сборки и выдачи списка вопросов для опросника
 */
public interface QuestionService {

    /**
     * Метод getQuestions возвращает список с вопросами
     * @return - список с вопросами
     */
    List<Question> getQuestions();
}
