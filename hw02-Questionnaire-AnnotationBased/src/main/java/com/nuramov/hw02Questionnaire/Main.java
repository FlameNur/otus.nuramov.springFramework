package com.nuramov.hw02Questionnaire;

import com.nuramov.hw02Questionnaire.configs.AppConfig;
import com.nuramov.hw02Questionnaire.services.QuestionnaireLauncher;
import com.nuramov.hw02Questionnaire.services.QuestionnaireLauncherImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        QuestionnaireLauncher questionnaireLauncher = context.getBean("questionnaireLauncherImpl", QuestionnaireLauncherImpl.class);
        questionnaireLauncher.run();
    }
}
