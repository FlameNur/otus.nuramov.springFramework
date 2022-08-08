package com.nuramov.hw02Questionnaire.services;

import com.nuramov.hw02Questionnaire.csvParser.CsvParser;
import com.nuramov.hw02Questionnaire.csvParser.CsvParserImpl;
import com.nuramov.hw02Questionnaire.questionnaire.Questionnaire;
import com.nuramov.hw02Questionnaire.userAuthorization.UserAuthorization;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ReaderServiceImpl implements ReaderService {
    UserAuthorization userAuthorization;
    Questionnaire questionnaire;

    private String questionsPath;
    private String answersPath;
    private String valuesToCheckPath;
    private CsvParser csvParser;

    @Autowired
    public ReaderServiceImpl(UserAuthorization userAuthorization,
                             Questionnaire questionnaire,
                             @Value("${QuestionsSource}") String questionsPath,
                             @Value("${AnswersSource}") String answersPath,
                             @Value("${ValuesToCheckSource}") String valuesToCheckPath,
                             CsvParser csvParser
    ) {
        this.userAuthorization = userAuthorization;
        this.questionnaire = questionnaire;

        this.questionsPath = questionsPath;
        this.answersPath = answersPath;
        this.valuesToCheckPath = valuesToCheckPath;
        this.csvParser = csvParser;
    }


    public void BBBBrun() {

    }

    @Override
    public void run() {
        // Пробуем так
        Map<String, String> mapOfQuestions = csvParser.getFileFromResourceAsMap(questionsPath);
        Map<String, String> mapOfAnswers = csvParser.getFileFromResourceAsMap(answersPath);
        Map<String, String> mapOfValuesToCheck = csvParser.getFileFromResourceAsMap(valuesToCheckPath);






        // Поток ввода/вывода можем закрыть всего один раз, поэтому try-with-resources вызываем в этом методе,
        // а не отдельно в каждом из классов UserAuthorization и Questionnaire
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Считываем с .csv файла
            /*Reader fileReader = Files.newBufferedReader(Path.of(filePath));
            CSVReader csvReader = new CSVReader(fileReader)) {


            // Используем TreeMap, чтобы сразу отсортировать информацию по ключам
            Map<String, String> mapOfItems = new TreeMap<>();

            // Считываем строки из .csv файла
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                // Разделяем строки на ключ и значение и записываем в Map

                // Надо как-то решить и эту проблему
                //String[] items = line.split(";");
                //mapOfItems.put(items[0], items[1]);
            }*/




            runAuthorization(reader);
            runQuestionnaire(reader, mapOfQuestions, mapOfAnswers, mapOfValuesToCheck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public List<String[]> readLineByLine(Path filePath) throws Exception {
        List<String[]> list = new ArrayList<>();
        try (Reader fileReader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(fileReader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
        }
        return list;
    }*/



    private void runAuthorization(BufferedReader reader) {
        userAuthorization.runUserAuthorization(reader);
    }

    private void runQuestionnaire(BufferedReader reader,
                                  Map<String, String> mapOfQuestions,
                                  Map<String, String> mapOfAnswers,
                                  Map<String, String> mapOfValuesToCheck
    ) {
        questionnaire.runQuestionnaire(reader, mapOfQuestions, mapOfAnswers, mapOfValuesToCheck);
    }
}
