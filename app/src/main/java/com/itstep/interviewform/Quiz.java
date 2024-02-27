package com.itstep.interviewform;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    // 1. Вопрос
    // 2. Ответ
    // 3. Неправильные ответы (3 шт.) String[] choices;
    private final String question;
    private final String correct;
    private final ArrayList<String> choices; // Объявление переменной

    public Quiz(
        String question,
        String correct,
        String wrongAnswer1,
        String wrongAnswer2,
        String wrongAnswer3
    ) {
        this.question = question;
        this.correct = correct;
        choices = new ArrayList<>(); // Инициализация переменной
        choices.add(correct);
        choices.add(wrongAnswer1);
        choices.add(wrongAnswer2);
        choices.add(wrongAnswer3);
        Collections.shuffle(choices); // Перемешаем варианты ответа
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect() {
        return correct;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }
}
