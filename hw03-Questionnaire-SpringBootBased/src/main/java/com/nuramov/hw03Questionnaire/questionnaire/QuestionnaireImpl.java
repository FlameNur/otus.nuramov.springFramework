package com.nuramov.hw03Questionnaire.questionnaire;

import com.nuramov.hw03Questionnaire.messageSource.MessagePrinter;
import com.nuramov.hw03Questionnaire.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class QuestionnaireImpl implements Questionnaire {

    private final Question question;
    // Используется для вывода локализованных сообщений
    private final MessagePrinter messagePrinter;

    @Autowired
    public QuestionnaireImpl(Question question, MessagePrinter messagePrinter) {
        this.question = question;
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void runQuestionnaire(BufferedReader reader) {
        int rightAnswerCount = 0;

        // Ответы вводим через консоль
        // Проходим в цикле по всем вопросам
        for(Map.Entry<String, String> entry : question.getMapOfQuestions().entrySet()) {
            printQuestions(entry.getKey(), entry.getValue());
            printAnswerOptions(question.getAnswerOptions(entry.getKey()));

            // Получаем введенный ответ и проверяем его на корректность
            String enteredValueStr = null;
            try {
                enteredValueStr = getAnswer(reader, question.getAnswerOptions(entry.getKey()).length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Проверяем правильность введенного ответа
            if(handleAnswer(enteredValueStr, entry.getKey())) {
                rightAnswerCount++;
            }
        }
        printResultOfQuestionnaire(rightAnswerCount);
    }

    /**
     * Метод printQuestions выводит вопрос на консоль
     * @param numberOfQuestion - номер вопроса
     * @param question - вопрос
     */
    private void printQuestions(String numberOfQuestion, String question) {
        System.out.println();
        messagePrinter.printMessage("Question");
        System.out.print(numberOfQuestion + " - " + question);
    }

    /**
     * Метод printAnswerOptions позволяет печатать варианты ответов в консоль
     * @param arrayOfAnswerOptions - массив с вариантами ответа
     */
    private void printAnswerOptions(String[] arrayOfAnswerOptions) {
        System.out.println();
        messagePrinter.printMessage("ChooseTheAnswer");
        for (int j = 0; j < arrayOfAnswerOptions.length; j++) {
            System.out.println();
            messagePrinter.printMessage("Answer");
            System.out.print((j + 1) + " - " + arrayOfAnswerOptions[j]);
        }
    }

    /**
     * Метод getAnswer позволяет получить введенный ответ и проверить его на корректность
     * @param reader - буферизированный поток на чтение символов, в нашем случае - ответа
     * @param arrayLength - - количество вариантов ответа
     * @return - возвращаем полученный ответ
     */
    private String getAnswer(BufferedReader reader, int arrayLength) throws IOException {
        System.out.println();
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
            messagePrinter.printMessage("EnterValidAnswerValue");
            return true;
        } else {
            messagePrinter.printMessage("AnswerAccepted");
            System.out.println();
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
        String correctAnswer = question.getCorrectAnswer(numberOfQuestion);
        return enteredValueStr.equals(correctAnswer);
    }

    /**
     * Метод printResultOfQuestionnaire выводит суммарный результат ответов на вопросы в %
     * @param rightAnswerCount - количество правильных ответов
     */
    private void printResultOfQuestionnaire(int rightAnswerCount) {
        System.out.println();
        messagePrinter.printMessage("YouAnswered");
        System.out.print(((100/question.getMapOfQuestions().size()) * rightAnswerCount));
        messagePrinter.printMessage("ResponseRate");
    }
}
