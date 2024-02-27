package com.itstep.interviewform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LIFECYCLE", "onCreate was launched!");

        Quiz quiz1 = new Quiz(
                "Каков правильный синтаксис для вывода \"Hello World\" на Java?",
                "System.out.println(\"Hello World\")",
                "print('Hello World')",
                "Console.WriteLine(\"Hello World\")",
                "cout << \"Hello world\" << endl"
        );


        // Создаем контейнер для теста
        LinearLayout testContainer = new LinearLayout(this);

        // Создаем layoutParams для контейнера теста
        ViewGroup.LayoutParams testLayoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // ширина весь размер родительского
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Установка layoutParams
        testContainer.setLayoutParams(testLayoutParams);







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