package com.nuramov.hw01Questionnaire;

import com.nuramov.hw01Questionnaire.CsvParser.CsvParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Класс Questionnaire реализует
 */
public class Questionnaire {
    private String questions;
    private String answers;
    private String valuesToCheck;    
    private CsvParser csvParser;
    // Счетчик правильных ответов
    private int sumOfValue;

    public Questionnaire(String questionsPath, String answersPath, String valuesToCheckPath, CsvParser csvParser) {
        this.questions = questionsPath;
        this.answers = answersPath;
        this.valuesToCheck = valuesToCheckPath;
        this.csvParser = csvParser;

        sumOfValue = 0;
    }

    /**
     * Метод runQuestionnaire запускает опросник с вопросами и ответами на выбор
     */
    public void runQuestionnaire() {
        // Map с вопросами (ключ - номер вопроса (id), значение - тело вопроса)
        Map<String, String> mapOfQuestions = csvParser.getFileFromResourceAsMap(questions);
        // Map с ответами (ключ - номер вопроса (id), значение - варианты ответов, разделенные запятыми)
        Map<String, String> mapOfAnswers = csvParser.getFileFromResourceAsMap(answers);

        // Порядковый номер вопроса
        int i = 0;
        // Введенный ответ
        String enteredValueStr;

        // Ответы вводим через консоль
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Проходим в цикле по всем вопросам
            for(Map.Entry<String, String> entry : mapOfQuestions.entrySet()) {
                System.out.println("\nВопрос: №" + entry.getKey() + " - " + entry.getValue());
                // В каждой итерации порядковый номер вопроса увеличивается на 1
                i++;
                // Находим строку с вариантами ответа в Map по ключу, который соответствует номеру вопроса
                String answers = mapOfAnswers.get(String.valueOf(i));
                // Разделяем строку с вариантами ответов для вывода каждого варианта ответа отдельно
                String[] ans = answers.split(",");
                System.out.println("Выберете один из вариантов ответа. Запишите номер ответа:");
                for (int j = 0; j < ans.length; j++) {
                    System.out.println("Ответ: №" + (j + 1) + " - " + ans[j]);
                }

                boolean correctValue = true;
                while (correctValue) {
                    // Считываем ответ
                    enteredValueStr = reader.readLine();
                    // Проверяем корректность введенного ответа и проверяем его правильность
                    // (, количество вариантов ответа, номер (id) вопроса/ответа по порядку)
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
     * Метод enteredValueCheck позволяет проверить корректность введенного ответа и считает количество правильных ответов
     * @param enteredValueStr - введенный ответ
     * @param number - количество вариантов ответа
     * @param i - текущий номер (id) вопроса/ответа по порядку
     * @return - возвращает false, если введен ответ, чтобы прервать цикл и запустить следующий вопрос
     */
    private boolean enteredValueCheck(String enteredValueStr, int number, int i) {
        Map<String, String> mapOfValuesToCheck = csvParser.getFileFromResourceAsMap(valuesToCheck);
        boolean correctValue = true;

        // Если пустое поле, "id = 0" или "нечисловое значение", то выдаем соответствующее сообщение
        if(enteredValueStr.equals("")
                || enteredValueStr.equals("0")
                || !Pattern.matches("\\b[\\d]+\\b", enteredValueStr)
                || Integer.parseInt(enteredValueStr) > number) {
            System.out.println("Enter a valid id value");
        } else {
            System.out.println("Ваш ответ принят");

            // Проверяем правильность ответа
            String correctAnswers = mapOfValuesToCheck.get(String.valueOf(i));
            if(enteredValueStr.equals(correctAnswers)) {
                // Счетчик правильных ответов
                sumOfValue++;
            }
            correctValue = false;
        }
        return correctValue;
    }
}
