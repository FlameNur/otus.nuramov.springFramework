package com.nuramov.hw02Questionnaire.services;

import com.nuramov.hw02Questionnaire.questionnaire.Questionnaire;
import com.nuramov.hw02Questionnaire.userAuthorization.UserAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ReaderServiceImpl implements ReaderService {
    UserAuthorization userAuthorization;
    Questionnaire questionnaire;

    @Autowired
    public ReaderServiceImpl(UserAuthorization userAuthorization, Questionnaire questionnaire) {
        this.userAuthorization = userAuthorization;
        this.questionnaire = questionnaire;
    }

    @Override
    public void run() {
        // Поток ввода/вывода можем закрыть всего один раз, поэтому try-with-resources вызываем в этом методе,
        // а не отдельно в каждом из классов UserAuthorization и Questionnaire
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            runAuthorization(reader);
            runQuestionnaire(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runAuthorization(BufferedReader reader) {
        userAuthorization.runUserAuthorization(reader);
    }

    private void runQuestionnaire(BufferedReader reader) {
        questionnaire.runQuestionnaire(reader);
    }
}
