package com.nuramov.hw03Questionnaire.questionnaire;

import com.nuramov.hw03Questionnaire.entities.Answer;
import com.nuramov.hw03Questionnaire.handlers.QuestionHandler;
import com.nuramov.hw03Questionnaire.messageSource.MessagePrinter;
import com.nuramov.hw03Questionnaire.entities.Question;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class QuestionnaireImpl implements Questionnaire {

    private final List<Question> listOfQuestions;
    private final MessagePrinter messagePrinter;

    @Autowired
    public QuestionnaireImpl(QuestionHandler questionHandler, MessagePrinter messagePrinter) {
        this.listOfQuestions = questionHandler.getQuestions();
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void runQuestionnaire(BufferedReader reader) {
        int rightAnswerCount = 0;

        // Ответы вводим через консоль. Проходим в цикле по всем вопросам
        for(Question question : listOfQuestions) {
            printQuestion(question);
            printAnswerOptions(question);

            if(getAndHandleAnswer(reader, question)) {
                rightAnswerCount++;
            }
        }
        printResultOfQuestionnaire(rightAnswerCount);
    }

    /**
     * Метод printQuestion выводит вопрос на консоль
     * @param question - сущность вопроса
     */
    private void printQuestion(Question question) {
        System.out.println();
        messagePrinter.printMessage("Question");
        System.out.print(question.getNumberOfQuestion() + " - " + question.getQuestion());
    }

    /**
     * Метод printAnswerOptions позволяет печатать варианты ответов в консоль
     * @param question - сущность вопроса
     */
    private void printAnswerOptions(Question question) {
        List<Answer> listOfAnswerOptions = question.getAnswers();

        System.out.println();
        messagePrinter.printMessage("ChooseTheAnswer");

        int number = 1;
        for(Answer answer : listOfAnswerOptions) {
            System.out.println();
            messagePrinter.printMessage("Answer");
            System.out.print(number + " - " + answer.getAnswerValue());
            number++;
        }
    }

    /**
     * Метод getAndHandleAnswer принимает и обрабатывает ответ
     * @param reader - буферизированный поток на чтение символов, в нашем случае - ответа
     * @param question - сущность вопроса
     * @return - возвращает true, если ответ правильный и false, если ответ не правильный
     */
    private boolean getAndHandleAnswer(BufferedReader reader, Question question) {
        // Получаем введенный ответ и проверяем его на корректность
        String enteredValue = null;
        try {
            enteredValue = getAnswer(reader, question);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Проверяем правильность введенного ответа и выводим результат сравнения
        String correctAnswerValue = question.getCorrectAnswer().getCorrectAnswerValue();
        assert enteredValue != null;
        return enteredValue.equals(correctAnswerValue);
    }

    /**
     * Метод getAnswer позволяет получить введенный ответ и проверить его на корректность
     * @param reader - буферизированный поток на чтение символов, в нашем случае - ответа
     * @param question - сущность вопроса
     * @return - возвращаем полученный ответ
     */
    private String getAnswer(BufferedReader reader, Question question) throws IOException {
        System.out.println();
        String inputValue = "";

        do {
            inputValue = reader.readLine();
        } while (!isCorrectAnswer(inputValue, question));
        return inputValue;
    }

    /**
     * Метод isCorrectAnswer позволяет проверить корректность введенного ответа
     * @param inputValue - введенный ответ
     * @param question - сущность вопроса
     * @return - возвращает true, если введенный ответ проходит проверку
     */
    private boolean isCorrectAnswer(String inputValue, Question question) {
        int amountOfAnswerOptions = question.getAnswers().size();

        // Если пустое поле, "id = 0" или "нечисловое значение", то выдаем соответствующее сообщение
        if(inputValue.isEmpty()
                || inputValue.equals("0")
                || !Pattern.matches("\\b[\\d]+\\b", inputValue)
                || Integer.parseInt(inputValue) > amountOfAnswerOptions) {
            messagePrinter.printMessage("EnterValidAnswerValue");
            return false;
        } else {
            messagePrinter.printMessage("AnswerAccepted");
            System.out.println();
            return true;
        }
    }

    /**
     * Метод printResultOfQuestionnaire выводит суммарный результат ответов на вопросы в %
     * @param rightAnswerCount - количество правильных ответов
     */
    private void printResultOfQuestionnaire(int rightAnswerCount) {
        System.out.println();
        messagePrinter.printMessage("YouAnswered");
        System.out.print(((100/listOfQuestions.size()) * rightAnswerCount));
        messagePrinter.printMessage("ResponseRate");
    }
}
