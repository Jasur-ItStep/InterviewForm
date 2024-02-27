package com.itstep.interviewform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/*
    System.out.println("Testing logs");-- старый способ вывода
    Log.d("LOG TEST", "Testing logs"); -- сообщение дебаг
    Log.i("LOG TEST", "Testing logs"); -- сообщение информация
    Log.v("LOG TEST", "Testing logs"); -- сообщение текста
    Log.w("LOG TEST", "Testing logs"); -- сообщение предупреждение
    Log.e("LOG TEST", "Testing logs"); -- сообщение ошибка
*/
// 1. Как только открыли программу: onCreate, onStart, onResume
// 2. Когда свернули программу: onPause, onStop
// 3. После того свернули, вернулись в программу: onRestart, onStart, onResume
// 4. Когда закрыли программу: onDestroy
// 5. Когда перевернули телефон: onDestroy, onCreate, onStart, onResume

public class MainActivity extends AppCompatActivity {

    private LinearLayout allTestsContainer;
    private final ArrayList<Quiz> quizzes = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LIFECYCLE", "onCreate was launched!");

        // Получить контейнер со всеми тестами
        allTestsContainer = findViewById(R.id.allTestsContainer);
        fillQuizzes(); // Заполняем квизы

        // Для каждого квиза создать контейнер
        for (Quiz quiz : quizzes) {
            createQuizContainer(quiz);
        }

    }

    private void fillQuizzes() {
        quizzes.add(new Quiz(
                "Каков правильный синтаксис для вывода \"Hello World\" на Java?",
                "System.out.println(\"Hello World\")",
                "print('Hello World')",
                "Console.WriteLine(\"Hello World\")",
                "cout << \"Hello world\" << endl"
        ));

        quizzes.add(new Quiz(
                "Какой тип данных используется для создания переменной, которая должна хранить текст?",
                "String",
                "str",
                "text",
                "string"
        ));

        quizzes.add(new Quiz(
                "Какой тип данных используется для создания переменной, которая должна хранить текст?",
                "String",
                "str",
                "text",
                "string"
        ));

        quizzes.add(new Quiz(
                "Какой тип данных используется для создания переменной, которая должна хранить текст?",
                "String",
                "str",
                "text",
                "string"
        ));

        quizzes.add(new Quiz(
                "Какой тип данных используется для создания переменной, которая должна хранить текст?",
                "String",
                "str",
                "text",
                "string"
        ));
    }

    private void createQuizContainer(Quiz quiz) {
        /* --------------- Создаем контейнер --------------- */

        // Создаем контейнер для теста
        LinearLayout testContainer = new LinearLayout(this);

        // Создаем layoutParams для контейнера теста
        ViewGroup.MarginLayoutParams testLayoutParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // ширина весь размер родительского
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Установка margin-ов
        testLayoutParams.setMargins(0, 40, 0, 0);

        // ViewGroup ->
        //              LayoutParams
        //              MarginLayoutParams (чтобы задавать margin)

        // Установка layoutParams
        testContainer.setLayoutParams(testLayoutParams);

        // Установка padding-ов
        testContainer.setPadding(10, 10, 10, 10);

        // Установка направления
        testContainer.setOrientation(LinearLayout.VERTICAL);
        /*
            class A {
                public static final int x = 10;
            }

            A a = new A();
            sout(a.x); --> 10

            A a2 = new A();
            sout(a2.x); --> 10

            sout(A.x);

         */

        // Установка рамки (border)
        testContainer.setBackground(getDrawable(R.drawable.border));
        // testContainer.setBackground(getResources().getDrawable(R.drawable.border));

        // Установка Id
        testContainer.setId(View.generateViewId());

        /* --------------- Создаем контейнер --------------- */


        /* --------------- Создаем вопрос --------------- */
        TextView testQuestion = new TextView(this);
        testQuestion.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // Установка шрифта
        testQuestion.setTypeface(getResources().getFont(R.font.montserrat_bold));

        // Установка вопроса (текста)
        testQuestion.setText(quiz.getQuestion());

        // Установка позиции текста (alignment)
        testQuestion.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Положить вовнутрь testContainer - testQuestion
        testContainer.addView(testQuestion);
        /* --------------- Создаем вопрос --------------- */

        /* --------------- Создаем горизонтальную прокрутку --------------- */
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);

        horizontalScrollView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        /* --------------- Создаем горизонтальную прокрутку --------------- */

        /* --------------- Создаем варианты ответов --------------- */
        RadioGroup radioButtonsContainer =  new RadioGroup(this);

        // Установка gravity
        radioButtonsContainer.setGravity(Gravity.CENTER);
        ViewGroup.MarginLayoutParams radioGroupLayout = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        radioGroupLayout.setMargins(0,40,0,0);


        radioButtonsContainer.setLayoutParams(radioGroupLayout);
        radioButtonsContainer.setOrientation(LinearLayout.HORIZONTAL);

        horizontalScrollView.addView(radioButtonsContainer);

        testContainer.addView(horizontalScrollView);

        // Строим варианты
        // Для каждого варианта из списка вариантов
        for( String choice : quiz.getChoices()) {
            RadioButton radioButton = new RadioButton(this);

            ViewGroup.MarginLayoutParams radioButtonLayout = new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            radioButtonLayout.setMargins(7,0,7,0);

            radioButton.setLayoutParams(radioButtonLayout);

            radioButton.setTypeface(getResources().getFont(R.font.montserrat_bold));

            radioButton.setText(choice);

            radioButton.setId(View.generateViewId());

            radioButtonsContainer.addView(radioButton);

        }
        /* --------------- Создаем варианты ответов --------------- */

        allTestsContainer.addView(testContainer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE", "onStart was launched!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE", "onResume was launched!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLE", "onPause was launched!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFECYCLE", "onStop was launched!");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFECYCLE", "onRestart was launched!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFECYCLE", "onDestroy was launched!");
    }
}