package com.nuramov.hw02Questionnaire;

import com.nuramov.hw02Questionnaire.configs.AppConfig;
import com.nuramov.hw02Questionnaire.services.ReaderService;
import com.nuramov.hw02Questionnaire.services.ReaderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ReaderService readerService = context.getBean("readerServiceImpl", ReaderServiceImpl.class);
        readerService.run();
    }
}
