package com.nuramov.hw03Questionnaire.userAuthorization;


import com.nuramov.hw03Questionnaire.messageSource.MessagePrinter;
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
        messagePrinter.printMessage("EnterYourName");

        // Считываем ответ и проверяем на корректность
        while(true) {
            try {
                if(isCorrectName(reader.readLine())) break;
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
    private boolean isCorrectName(String name) {
        boolean check = true;
        // Имя должно содержать только латинские буквы
        if(!Pattern.matches("\\b[a-zA-Z]+\\b", name)) {
            messagePrinter.printMessage("EnterCorrectName");
            check = false;
        }
        return check;
    }
}
