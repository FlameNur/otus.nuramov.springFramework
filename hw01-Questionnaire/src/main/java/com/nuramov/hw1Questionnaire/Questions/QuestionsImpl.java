package com.nuramov.hw1Questionnaire.Questions;

public class QuestionsImpl implements Questions{
    String filePath;

    public QuestionsImpl(String filePath) {
        if(filePath != null) {
            this.filePath = filePath;
        } else {
            throw new RuntimeException("File not found!");
        }
    }


}
