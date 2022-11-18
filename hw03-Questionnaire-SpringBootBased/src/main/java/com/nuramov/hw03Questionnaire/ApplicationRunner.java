package com.nuramov.hw03Questionnaire;

import com.nuramov.hw03Questionnaire.services.QuestionnaireLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        QuestionnaireLauncher questionnaireLauncher = context.getBean(QuestionnaireLauncher.class);
        questionnaireLauncher.run();
    }
}
