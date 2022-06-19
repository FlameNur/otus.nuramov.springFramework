package com.nuramov.hw02Questionnaire.userAuthorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserAuthorizationImpl implements UserAuthorization{

    @Override
    public void runUserAuthorization() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите ваше имя и фамилию");


            boolean correctValue = true;

            while (correctValue) {
                // Считываем ответ
                System.out.println("Имя: " + reader.readLine());
                System.out.println("Фамилия: " + reader.readLine());

                // Проверяем корректность введенного ответа: true-некорректный, false-корректный
                correctValue = false;
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
