package com.nuramov.hw1Questionnaire;

import com.nuramov.hw1Questionnaire.Answers.Answers;
import com.nuramov.hw1Questionnaire.Answers.AnswersImpl;
import com.nuramov.hw1Questionnaire.Questions.Questions;
import com.nuramov.hw1Questionnaire.Questions.QuestionsImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Questions questions = new QuestionsImpl("Questions.csv");
        Answers answers = new AnswersImpl();
        Questionnaire questionnaire = new Questionnaire(questions, answers);




        //String filePath = "database.properties";
        String filePath = "Questions.csv";

        System.out.println("getResourceAsStream : " + filePath);
        InputStream is = getFileFromResourceAsStream(filePath);
        Map<String, String> map = printInputStream(is);
        for(Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Получаем: " + entry.getKey() + " - " + entry.getValue());
        }
    }




    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    // Сделали метод static, т.к. используем в static методе main()
    private static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        // Заменили getClass().getClassLoader(); на Main.class.getClassLoader(); т.к. используем выше в Main
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    // print input stream
    private static Map<String, String> printInputStream(InputStream is) {
        Map<String, String> mapOfItems = new HashMap<>();

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(";");
                mapOfItems.put(items[0], items[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapOfItems;
    }
}
