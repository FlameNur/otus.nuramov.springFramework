package com.nuramov.hw02Questionnaire;

import com.nuramov.hw02Questionnaire.csvParser.CsvParserImpl;
import com.nuramov.hw02Questionnaire.questionnaire.QuestionnaireImpl;
import com.nuramov.hw02Questionnaire.userAuthorization.UserAuthorizationImpl;
import com.nuramov.hw02Questionnaire.userService.UserService;
import com.nuramov.hw02Questionnaire.userService.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(
                new UserAuthorizationImpl(),
                new QuestionnaireImpl(
                        "Questions.csv",
                        "Answers.csv",
                        "ValuesToCheck.csv",
                        new CsvParserImpl()
                ));

        userService.run();
    }
}
