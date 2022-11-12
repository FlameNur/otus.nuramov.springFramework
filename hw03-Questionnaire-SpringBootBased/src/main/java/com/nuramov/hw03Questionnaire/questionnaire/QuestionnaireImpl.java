package com.nuramov.hw03Questionnaire.questionnaire;

import com.nuramov.hw03Questionnaire.csvParser.CsvParser;
import com.nuramov.hw03Questionnaire.messageSource.MessagePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class QuestionnaireImpl implements Questionnaire {
    // Map с вопросами (ключ - номер вопроса (id), значение - тело вопроса)
    private final Map<String, String> mapOfQuestions;
    // Map с ответами (ключ - номер вопроса (id), значение - варианты ответов, разделенные запятыми)
    private final Map<String, String> mapOfAnswers;
    // Map с правильными ответами (ключ - номер вопроса (id), значение - правильный ответ)
    private final Map<String, String> mapOfValuesToCheck;
    // Используется для вывода локализованных сообщений
    private MessagePrinter messagePrinter;

    @Autowired
    public QuestionnaireImpl(@Value("${QuestionsSource}") String questionsPath,
                             @Value("${AnswersSource}") String answersPath,
                             @Value("${ValuesToCheckSource}") String valuesToCheckPath,
                             CsvParser csvParser,
                             MessagePrinter messagePrinter) {
        this.mapOfQuestions = csvParser.getFileFromResourceAsMap(questionsPath);
        this.mapOfAnswers = csvParser.getFileFromResourceAsMap(answersPath);
        this.mapOfValuesToCheck = csvParser.getFileFromResourceAsMap(valuesToCheckPath);
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void runQuestionnaire(BufferedReader reader) {
        // Счетчик правильных ответов
        int rightAnswerCount = 0;

        // Ответы вводим через консоль
        // Проходим в цикле по всем вопросам
        for(Map.Entry<String, String> entry : mapOfQuestions.entrySet()) {
            // Выводим вопрос в консоль
            System.out.println();
            messagePrinter.printMessage("Question");
            System.out.print(entry.getKey() + " - " + entry.getValue());

            // Получаем варианты ответа в одну строку
            String answers = mapOfAnswers.get(entry.getKey());
            // Разделяем строку с вариантами ответов для вывода каждого варианта ответа отдельно
            String[] arrayOfAnswerOptions = answers.split(",");
            // Выводим варианты ответа
            printAnswerOptions(arrayOfAnswerOptions);

            // Получаем введенный ответ и проверяем его на корректность
            String enteredValueStr = null;
            try {
                enteredValueStr = getAnswer(reader, arrayOfAnswerOptions.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Проверяем правильность введенного ответа
            if(handleAnswer(enteredValueStr, entry.getKey())) {
                rightAnswerCount++;
            }
        }
        // Выводим суммарный результат ответов на вопросы в %
        System.out.println();
        messagePrinter.printMessage("YouAnswered");
        System.out.print(((100/mapOfQuestions.size()) * rightAnswerCount));
        messagePrinter.printMessage("ResponseRate");
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
        String correctAnswers = mapOfValuesToCheck.get(numberOfQuestion);
        return enteredValueStr.equals(correctAnswers);
    }
}
