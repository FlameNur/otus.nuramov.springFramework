package com.nuramov.hw02Questionnaire.userService;

import com.nuramov.hw02Questionnaire.questionnaire.Questionnaire;
import com.nuramov.hw02Questionnaire.userAuthorization.UserAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    UserAuthorization userAuthorization;
    Questionnaire questionnaire;

    @Autowired
    public UserServiceImpl(UserAuthorization userAuthorization, Questionnaire questionnaire) {
        this.userAuthorization = userAuthorization;
        this.questionnaire = questionnaire;
    }

    @Override
    public void runAuthorization() {
        userAuthorization.runUserAuthorization();
    }

    @Override
    public void runQuestionnaire() {
        questionnaire.runQuestionnaire();
    }
}
