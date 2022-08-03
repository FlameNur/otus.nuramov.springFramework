package com.nuramov.hw02Questionnaire;

import com.nuramov.hw02Questionnaire.csvParser.CsvParserImpl;
import com.nuramov.hw02Questionnaire.questionnaire.QuestionnaireImpl;
import com.nuramov.hw02Questionnaire.userAuthorization.UserAuthorizationImpl;
import com.nuramov.hw02Questionnaire.userService.UserService;
import com.nuramov.hw02Questionnaire.userService.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        userService.run();
    }
}
