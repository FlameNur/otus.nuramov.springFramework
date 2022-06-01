package com.nuramov.hw1Questionnaire;

import com.nuramov.hw1Questionnaire.Answers.Answers;
import com.nuramov.hw1Questionnaire.Questions.Questions;

public class Questionnaire {
    private Questions questions;
    private Answers answers;

    public Questionnaire(Questions questions, Answers answers) {
        this.questions = questions;
        this.answers = answers;
    }


}
