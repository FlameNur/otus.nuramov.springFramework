package com.nuramov.hw02Questionnaire;

import com.nuramov.hw02Questionnaire.configs.AppConfig;
import com.nuramov.hw02Questionnaire.services.ReaderAndCsvParserService;
import com.nuramov.hw02Questionnaire.services.ReaderAndCsvParserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ReaderAndCsvParserService readerAndCsvParserService = context.getBean("readerAndCsvParserServiceImpl", ReaderAndCsvParserServiceImpl.class);
        readerAndCsvParserService.run();
    }
}
