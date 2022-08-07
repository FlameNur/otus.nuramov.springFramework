package com.nuramov.hw02Questionnaire.services;

import com.nuramov.hw02Questionnaire.questionnaire.Questionnaire;
import com.nuramov.hw02Questionnaire.userAuthorization.UserAuthorization;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReaderAndCsvParserServiceImpl implements ReaderAndCsvParserService {
    UserAuthorization userAuthorization;
    Questionnaire questionnaire;

    @Autowired
    public ReaderAndCsvParserServiceImpl(UserAuthorization userAuthorization, Questionnaire questionnaire) {
        this.userAuthorization = userAuthorization;
        this.questionnaire = questionnaire;
    }

    @Override
    public void run() {

    }


    private void csvParserRun(String filePath) {
        // Поток ввода/вывода можем закрыть всего один раз, поэтому try-with-resources вызываем в этом методе,
        // а не отдельно в каждом из классов UserAuthorization и Questionnaire
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Reader fileReader = Files.newBufferedReader(Path.of(filePath));
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
            }




            runAuthorization(reader);
            runQuestionnaire(reader);
        } catch (IOException | CsvValidationException e) {
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

    private void runQuestionnaire(BufferedReader reader) {
        questionnaire.runQuestionnaire(reader);
    }
}
