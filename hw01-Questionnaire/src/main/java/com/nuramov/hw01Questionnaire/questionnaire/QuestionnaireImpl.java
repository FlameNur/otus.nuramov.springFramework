package com.nuramov.hw01Questionnaire.questionnaire;

import com.nuramov.hw01Questionnaire.csvParser.CsvParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Класс QuestionnaireImpl реализует интерфейс Questionnaire
 */
public class QuestionnaireImpl implements Questionnaire {
    // Map с вопросами (ключ - номер вопроса (id), значение - тело вопроса)
    private Map<String, String> mapOfQuestions;
    // Map с ответами (ключ - номер вопроса (id), значение - варианты ответов, разделенные запятыми)
    private Map<String, String> mapOfAnswers;
    // Map с правильными ответами (ключ - номер вопроса (id), значение - правильный ответ)
    private Map<String, String> mapOfValuesToCheck;

    public QuestionnaireImpl(String questionsPath, String answersPath, String valuesToCheckPath, CsvParser csvParser) {
        this.mapOfQuestions = csvParser.getFileFromResourceAsMap(questionsPath);
        this.mapOfAnswers = csvParser.getFileFromResourceAsMap(answersPath);
        this.mapOfValuesToCheck = csvParser.getFileFromResourceAsMap(valuesToCheckPath);
    }

    @Override
    public void runQuestionnaire() {
        // Счетчик правильных ответов
        int sumOfValue = 0;

        // Ответы вводим через консоль
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Проходим в цикле по всем вопросам
            for(Map.Entry<String, String> entry : mapOfQuestions.entrySet()) {
                // Выводим вопрос в консоль
                System.out.println("\nВопрос: №" + entry.getKey() + " - " + entry.getValue());
                // Получаем варианты ответа в одну строку
                String answers = mapOfAnswers.get(entry.getKey());
                // Разделяем строку с вариантами ответов для вывода каждого варианта ответа отдельно
                String[] arrayOfAnswerOptions = answers.split(",");
                // Получаем длину массива
                int arrayLength = arrayOfAnswerOptions.length;
                // Выводим варианты ответа
                printAnswerOptions(arrayOfAnswerOptions);
                // Получаем введенный ответ и проверяем его на корректность
                String enteredValueStr = getAnswer(reader, arrayLength);
                // Проверяем правильность введенного ответа
                if(handleAnswer(enteredValueStr, entry.getKey())) {
                    sumOfValue++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Выводим суммарный результат ответов на вопросы в %
        System.out.println("\nПоздравляю! Вы ответили на " + ((100/mapOfQuestions.size()) * sumOfValue) + "% вопросов верно");
    }

    /**
     * Метод printAnswerOptions позволяет печатать варианты ответов в консоль
     * @param arrayOfAnswerOptions - массив с вариантами ответа
     */
    private void printAnswerOptions(String[] arrayOfAnswerOptions) {
        System.out.println("Выберете один из вариантов ответа. Запишите номер ответа:");
        for (int j = 0; j < arrayOfAnswerOptions.length; j++) {
            System.out.println("Ответ: №" + (j + 1) + " - " + arrayOfAnswerOptions[j]);
        }
    }

    /**
     * Метод getAnswer позволяет получить введенный ответ и проверить его на корректность
     * @param reader - буферизированный поток на чтение символов, в нашем случае - ответа
     * @param arrayLength - - количество вариантов ответа
     * @return - возвращаем полученный ответ
     */
    private String getAnswer(BufferedReader reader, int arrayLength) throws IOException {
        String enteredValueStr = "";
        boolean correctValue = true;

        while (correctValue) {
            // Считываем ответ
            enteredValueStr = reader.readLine();
            // Проверяем корректность введенного ответа: true-некорректный, false-корректный
            correctValue = checkAnswer(enteredValueStr, arrayLength);
        }
        return enteredValueStr;
    }

    /**
     * Метод checkAnswer позволяет проверить корректность введенного ответа
     * @param enteredValueStr - введенный ответ
     * @param number - количество вариантов ответа
     * @return - возвращает false, если введен ответ, чтобы прервать цикл и запустить следующий вопрос
     */
    private boolean checkAnswer(String enteredValueStr, int number) {
        // Если пустое поле, "id = 0" или "нечисловое значение", то выдаем соответствующее сообщение
        if(enteredValueStr.isEmpty()
                || enteredValueStr.equals("0")
                || !Pattern.matches("\\b[\\d]+\\b", enteredValueStr)
                || Integer.parseInt(enteredValueStr) > number) {
            System.out.println("Enter a valid id value");
            return true;
        } else {
            System.out.println("Ваш ответ принят");
            return false;
        }
    }

    /**
     * Метод handleAnswer позволяет проверить правильность введенного ответа
     * @param enteredValueStr - введенный ответ
     * @param numberOfQuestion - текущий номер (id) вопроса/ответа по порядку
     * @return - возвращает true, если ответ правильный и false, если ответ не правильный
     */
    private boolean handleAnswer(String enteredValueStr, String numberOfQuestion) {
        // Проверяем правильность ответа
        String correctAnswers = mapOfValuesToCheck.get(numberOfQuestion);
        return enteredValueStr.equals(correctAnswers);
    }
}
