package com.nuramov.hw02Questionnaire.userAuthorization;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UserAuthorizationImpl implements UserAuthorization{

    @Override
    public void runUserAuthorization(BufferedReader reader) {

        boolean correctValue = true;

        while (correctValue) {
            // Считываем ответ
            try {
                System.out.println("Введите ваше имя: ");
                reader.readLine();

                System.out.println("Введите вашу фамилию: ");
                reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Проверяем корректность введенного ответа: true-некорректный, false-корректный
            correctValue = false;
        }
    }
}
