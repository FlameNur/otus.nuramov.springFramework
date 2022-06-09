package com.nuramov.hw01Questionnaire;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Запускаем опросник Questionnaire используя spring с конфигурацией в .xml файле
 */
public class Main {
    public static void main(String[] args) {
        // Context для конфигурации spring
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // Получаем объект класса Questionnaire через контекст, где прописаны все аргументы и другие бины
        Questionnaire questionnaire = context.getBean("questionnaireBean", Questionnaire.class);
        // Запускаем опросник
        questionnaire.runQuestionnaire();

        context.close();
    }
}
