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

        // Ответы вводим через консоль. Проходим в цикле по всем вопросам
        for(Map.Entry<String, String> entry : question.getMapOfQuestions().entrySet()) {
            String numberOfQuestion = entry.getKey();

            printQuestion(numberOfQuestion);
            printAnswerOptions(numberOfQuestion);

            // Проверяем правильность введенного ответа
            if(getAndHandleAnswer(reader, numberOfQuestion)) {
                rightAnswerCount++;
            }
        }
        printResultOfQuestionnaire(rightAnswerCount);
    }

    /**
     * Метод printQuestion выводит вопрос на консоль
     * @param numberOfQuestion - номер вопроса
     */
    private void printQuestion(String numberOfQuestion) {
        System.out.println();
        messagePrinter.printMessage("Question");
        System.out.print(numberOfQuestion + " - " + question.getQuestion(numberOfQuestion));
    }

    /**
     * Метод printAnswerOptions позволяет печатать варианты ответов в консоль
     * @param numberOfQuestion - номер вопроса
     */
    private void printAnswerOptions(String numberOfQuestion) {
        String[] arrayOfAnswerOptions = question.getAnswerOptions(numberOfQuestion);

        System.out.println();
        messagePrinter.printMessage("ChooseTheAnswer");
        for (int j = 0; j < arrayOfAnswerOptions.length; j++) {
            System.out.println();
            messagePrinter.printMessage("Answer");
            System.out.print((j + 1) + " - " + arrayOfAnswerOptions[j]);
        }
    }

    /**
     * Метод getAndHandleAnswer принимает и обрабатывает ответ
     * @param reader - буферизированный поток на чтение символов, в нашем случае - ответа
     * @param numberOfQuestion - номер вопроса
     * @return - возвращает true, если ответ правильный и false, если ответ не правильный
     */
    private boolean getAndHandleAnswer(BufferedReader reader, String numberOfQuestion) {
        // Получаем введенный ответ и проверяем его на корректность
        String enteredValue = null;
        try {
            enteredValue = getAnswer(reader, numberOfQuestion);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Проверяем правильность введенного ответа
        String correctAnswer = question.getCorrectAnswer(numberOfQuestion);
        assert enteredValue != null;
        return enteredValue.equals(correctAnswer);
    }

    /**
     * Метод getAnswer позволяет получить введенный ответ и проверить его на корректность
     * @param reader - буферизированный поток на чтение символов, в нашем случае - ответа
     * @param numberOfQuestion - номер вопроса
     * @return - возвращаем полученный ответ
     */
    private String getAnswer(BufferedReader reader, String numberOfQuestion) throws IOException {
        System.out.println();
        String enteredValue = "";
        boolean correctValue = true;

        while (correctValue) {
            // Считываем ответ
            enteredValue = reader.readLine();
            // Проверяем корректность введенного ответа: true-некорректный, false-корректный
            correctValue = checkAnswer(enteredValue, numberOfQuestion);
        }
        return enteredValue;
    }

    /**
     * Метод checkAnswer позволяет проверить корректность введенного ответа
     * @param enteredValue - введенный ответ
     * @param numberOfQuestion - номер вопроса
     * @return - возвращает false, если введен ответ, чтобы прервать цикл и запустить следующий вопрос
     */
    private boolean checkAnswer(String enteredValue, String numberOfQuestion) {
        int numberOfAnswerOptions = question.getAnswerOptions(numberOfQuestion).length;

        // Если пустое поле, "id = 0" или "нечисловое значение", то выдаем соответствующее сообщение
        if(enteredValue.isEmpty()
                || enteredValue.equals("0")
                || !Pattern.matches("\\b[\\d]+\\b", enteredValue)
                || Integer.parseInt(enteredValue) > numberOfAnswerOptions) {
            messagePrinter.printMessage("EnterValidAnswerValue");
            return true;
        } else {
            messagePrinter.printMessage("AnswerAccepted");
            System.out.println();
            return false;
        }
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
