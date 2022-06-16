package com.nuramov.hw02Questionnaire.userAuthorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserAuthorizationImpl implements UserAuthorization{

    @Override
    public void runUserAuthorization() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите ваше имя и фамилию");
            System.out.println("Имя: ");
            System.out.println("Фамилия: ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
