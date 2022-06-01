package com.nuramov.hw1Questionnaire;

import com.nuramov.hw1Questionnaire.Answers.Answers;
import com.nuramov.hw1Questionnaire.Answers.AnswersImpl;
import com.nuramov.hw1Questionnaire.Questions.Questions;
import com.nuramov.hw1Questionnaire.Questions.QuestionsImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        Questions questions = new QuestionsImpl();
        Answers answers = new AnswersImpl();
        Questionnaire questionnaire = new Questionnaire(questions, answers);




        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );


        // Что-то...


        context.close();
    }
}
