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
    private int sumOfValue;

    public Questionnaire(Questions questions, Answers answers, ValuesToCheck valuesToCheck) {
        this.questions = questions;
        this.answers = answers;
        this.valuesToCheck = valuesToCheck;

        sumOfValue = 0;
    }

    /**
     *
     */
    public void runQuestionnaire() {
        Map<String, String> mapOfQuestions = questions.getQuestionsAsMap();
        Map<String, String> mapOfAnswers = answers.getAnswersAsMap();

        // Порядковый номер вопроса
        int i = 0;
        // Введенный ответ
        String enteredValueStr;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Проходим в цикле по всем вопросам
            for(Map.Entry<String, String> entry : mapOfQuestions.entrySet()) {
                System.out.println("\nВопрос: " + entry.getKey() + " - " + entry.getValue());
                // В каждой итерации порядковый номер вопроса увеличивается на 1
                i++;
                // Находим строку с вариантами ответа в Map по ключу, который соответствует номеру вопроса
                String answers = mapOfAnswers.get(String.valueOf(i));
                // Разделяем строку с вариантами ответов для вывода каждого варианта ответа отдельно
                String[] ans = answers.split(",");
                System.out.println("Выберете один из вариантов ответа. Запишите номер ответа:");
                for (int j = 0; j < ans.length; j++) {
                    System.out.println("Ответ: " + (j + 1) + " - " + ans[j]);
                }

                boolean correctValue = true;
                while (correctValue) {
                    //
                    enteredValueStr = reader.readLine();
                    //
                    correctValue = enteredValueCheck(enteredValueStr, ans.length, i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Arrived null. Something went wrong");
        }
        // Выводим суммарный результат ответов на вопросы в %
        System.out.println("\nПоздравляю! Вы ответили на " + ((100/mapOfQuestions.size()) * sumOfValue) + "% вопросов верно");
    }

    /**
     *
     * @param enteredValueStr
     * @param length
     * @param i
     * @return
     */
    private boolean enteredValueCheck(String enteredValueStr, int length, int i) {
        Map<String, String> mapOfValuesToCheck = valuesToCheck.getValuesAsMap();
        boolean correctValue = true;

        // Если пустое поле, "id = 0" или "нечисловое значение", то выдаем соответствующее сообщение
        if(enteredValueStr.equals("")
                || enteredValueStr.equals("0")
                || !Pattern.matches("\\b[\\d]+\\b", enteredValueStr)
                || Integer.parseInt(enteredValueStr) > length) {
            System.out.println("Enter a valid id value");
        } else {
            System.out.println("Ваш ответ принят");

            //
            String correctAnswers = mapOfValuesToCheck.get(String.valueOf(i));
            if(enteredValueStr.equals(correctAnswers)) {
                //
                sumOfValue++;
            }
            correctValue = false;
        }
        return correctValue;
    }
}
