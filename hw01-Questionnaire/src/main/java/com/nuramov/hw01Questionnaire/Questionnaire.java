package com.nuramov.hw01Questionnaire;

import com.nuramov.hw01Questionnaire.Answers.Answers;
import com.nuramov.hw01Questionnaire.Questions.Questions;
import com.nuramov.hw01Questionnaire.ValuesToCheck.ValuesToCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

public class Questionnaire {
    private Questions questions;
    private Answers answers;
    private ValuesToCheck valuesToCheck;

    public Questionnaire(Questions questions, Answers answers, ValuesToCheck valuesToCheck) {
        this.questions = questions;
        this.answers = answers;
        this.valuesToCheck = valuesToCheck;
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

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

                String enteredValueStr;
                boolean correctValue = true;
                while (correctValue) {

                    enteredValueStr = reader.readLine();

                    // Если пустое поле, "id = 0" или "нечисловое значение", то выдаем ошибку с соответствующим сообщением
                    if(enteredValueStr.equals("") || enteredValueStr.equals("0")
                            || !Pattern.matches("\\b[\\d]+\\b", enteredValueStr)) {
                        System.out.println("Enter a valid id value");
                    } else {



                        System.out.println("Что-то надо тут придумать");
                        correctValue = false;
                    }
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Прилетел null");
            }

        }
    }
}
