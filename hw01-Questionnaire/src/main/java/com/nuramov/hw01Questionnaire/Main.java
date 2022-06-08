package com.nuramov.hw01Questionnaire;

import com.nuramov.hw01Questionnaire.Answers.AnswersImpl;
import com.nuramov.hw01Questionnaire.Questions.QuestionsImpl;
import com.nuramov.hw01Questionnaire.ValuesToCheck.ValuesToCheckImpl;

public class Main {
    public static void main(String[] args) {
        Questionnaire questionnaire = new Questionnaire(
                new QuestionsImpl("Questions.csv"),
                new AnswersImpl("Answers.csv"),
                new ValuesToCheckImpl("ValuesToCheck.csv")
        );

        questionnaire.runQuestionnaire();




        // Для spring
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Что-то...
        //context.close();
    }
}
