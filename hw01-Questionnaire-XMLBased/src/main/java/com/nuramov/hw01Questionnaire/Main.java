package com.nuramov.hw01Questionnaire;

import com.nuramov.hw01Questionnaire.questionnaire.Questionnaire;
import com.nuramov.hw01Questionnaire.questionnaire.QuestionnaireImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Запускаем опросник QuestionnaireImpl используя spring с конфигурацией в .xml файле
 */
public class Main {
    public static void main(String[] args) {
        // Context для конфигурации spring
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // Получаем объект класса QuestionnaireImpl через контекст, где прописаны все аргументы и другие бины
        Questionnaire questionnaire = context.getBean("questionnaireBean", QuestionnaireImpl.class);
        // Запускаем опросник
        questionnaire.runQuestionnaire();

        context.close();
    }
}
