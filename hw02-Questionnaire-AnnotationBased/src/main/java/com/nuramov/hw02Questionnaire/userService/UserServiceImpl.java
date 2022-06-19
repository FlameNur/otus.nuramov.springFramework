package com.nuramov.hw02Questionnaire.userService;

import com.nuramov.hw02Questionnaire.questionnaire.Questionnaire;
import com.nuramov.hw02Questionnaire.userAuthorization.UserAuthorization;

public class UserServiceImpl implements UserService{
    UserAuthorization userAuthorization;
    Questionnaire questionnaire;

    public UserServiceImpl(UserAuthorization userAuthorization, Questionnaire questionnaire) {
        this.userAuthorization = userAuthorization;
        this.questionnaire = questionnaire;
    }

    @Override
    public void run() {
        userAuthorization.runUserAuthorization();
        questionnaire.runQuestionnaire();
    }
}
