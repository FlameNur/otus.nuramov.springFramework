package com.nuramov.hw01Questionnaire;

import com.nuramov.hw01Questionnaire.Answers.Answers;
import com.nuramov.hw01Questionnaire.Answers.AnswersImpl;
import com.nuramov.hw01Questionnaire.CsvParser.CsvParser;
import com.nuramov.hw01Questionnaire.CsvParser.CsvParserImpl;
import com.nuramov.hw01Questionnaire.Questions.Questions;
import com.nuramov.hw01Questionnaire.Questions.QuestionsImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Questionnaire questionnaire = new Questionnaire(
                new QuestionsImpl("Questions.csv"),
                new AnswersImpl("Answers.csv")
        );

        questionnaire.runQuestionnaire();




        // Для spring
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Что-то...
        //context.close();
    }
}
