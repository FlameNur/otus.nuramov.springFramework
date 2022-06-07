package com.nuramov.hw01Questionnaire;

import com.nuramov.hw01Questionnaire.Answers.Answers;
import com.nuramov.hw01Questionnaire.Questions.Questions;

import java.util.Map;

public class Questionnaire {
    private Questions questions;
    private Answers answers;

    public Questionnaire(Questions questions, Answers answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public void runQuestionnaire() {
        Map<String, String> mapOfQuestions = questions.getQuestionsAsMap();
        Map<String, String> mapOfAnswers = answers.getAnswersAsMap();

        int i = 0;

        for(Map.Entry<String, String> entry : mapOfQuestions.entrySet()) {
            System.out.println("Вопрос: " + entry.getKey() + " - " + entry.getValue());

            i++;

            String answers = mapOfAnswers.get(String.valueOf(i));

            String[] ans = answers.split(",");

            System.out.println("Выберете один из заданных ответов:");
            for (int j = 0; j < ans.length; j++) {
                System.out.println("Ответ: " + (j + 1) + " - " + ans[j]);
            }


        }
    }
}
