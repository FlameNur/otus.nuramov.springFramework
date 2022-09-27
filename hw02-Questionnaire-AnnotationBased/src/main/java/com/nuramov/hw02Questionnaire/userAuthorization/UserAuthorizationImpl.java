package com.nuramov.hw02Questionnaire.userAuthorization;

import com.nuramov.hw02Questionnaire.messageSource.MessagePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

@Service
public class UserAuthorizationImpl implements UserAuthorization{
    private MessagePrinter messagePrinter;

    @Autowired
    public UserAuthorizationImpl(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void runUserAuthorization(BufferedReader reader) {
        messagePrinter.printMessageRu("Введите ваше имя латинскими буквами: ");

        // Считываем ответ и проверяем на корректность
        while(true) {
            try {
                if(nameCheck(reader.readLine())) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод nameCheck проверяет введенное имя на корректность
     * @param name - введенное имя
     * @return - возвращает true, если имя введено корректно, и false, если нет
     */
    private boolean nameCheck(String name) {
        boolean check = true;
        // Имя должно содержать только латинские буквы
        if(!Pattern.matches("\\b[a-zA-Z]+\\b", name)) {
            messagePrinter.printMessageRu("Введите корректное имя латинскими буквами");
            check = false;
        }
        return check;
    }
}
