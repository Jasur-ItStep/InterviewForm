package com.itstep.interviewform;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

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

/*
Атрибуты:
    visibility - видимость предмета (invisible, visible, gone)
    setVisibility(View.INVISIBLE)

    clickable / enabled - делает предмет либо нажимаемым, либо нет
    отличие, что у enabled есть серый фон, когда нельзя нажать
 */

public class MainActivity extends AppCompatActivity {
    private LinearLayout allTestsContainer; // Контейнер где находятся все тесты
    private EditText fullNameInput, ageInput; // Текстовые поля ФИО и возраста
    private SeekBar salarySeekBar; // Ползунок для ЗП
    private CheckBox workExperienceCheckBox, // Чек-боксы Опыт работы
            teamWorkCheckBox, // Чек-боксы Командная работа
            businessTripCheckBox; // Чек-боксы Готовность к командировкам
    private Button sendBtn; // Кнопка для отправки теста
    private TextView resultTxt;  // Невидимый текст с результатом

    private Form currentForm; // Текущая анкета, которую заполняет пользователь
    private final ArrayList<Quiz> quizzes = new ArrayList<>(); // Список вопросов
    private int score = 0; // Сколько баллов начислено у пользователя
    private int maxScore = 0; // Максимальное количество баллов


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LIFECYCLE", "onCreate was launched!");

        // Получить контейнер со всеми тестами
        allTestsContainer = findViewById(R.id.allTestsContainer);
        // allTestsContainer.setVisibility(View.INVISIBLE);

        // Получить текстовое поле для ФИО
        fullNameInput = findViewById(R.id.fullNameInput);

        // Получить текстовое поле для возраста
        ageInput = findViewById(R.id.ageInput);

        // Получить ползунок для зарплаты
        salarySeekBar = findViewById(R.id.salarySeekBar);

        // Получить поле с галочкой для опыта, командной работы, командировкам
        workExperienceCheckBox = findViewById(R.id.workExperience);
        teamWorkCheckBox = findViewById(R.id.teamWork);
        businessTripCheckBox = findViewById(R.id.businessTrip);

        // Получить кнопку для отправки теста
        sendBtn = findViewById(R.id.sendBtn);

        // Получить текст с результатом
        resultTxt = findViewById(R.id.result);

        fillQuizzes(); // Заполняем квизы

        // Создает текущую форму
        currentForm = new Form(quizzes.size());

        // Для каждого квиза создать контейнер

//        for (Quiz quiz : quizzes) {
//            createQuizContainer(quiz);
//        }
        for (int i = 0; i < quizzes.size(); i++) {
            createQuizContainer(quizzes.get(i), i);
        }

        // Навешиваем события на виджеты

        // Даем слушатель изменения текста
        fullNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                currentForm.setFullName(fullNameInput.getText().toString());
                sendBtn.setEnabled(currentForm.validateData());
            }
        });

        // Даем слушатель изменения фокуса на элементе
        ageInput.setOnFocusChangeListener((view, isFocused) -> {
            // String -> int ( Integer.parseInt )
            if (isFocused) return;
            String ageText = ageInput.getText().toString();
            if (ageText.isEmpty()) return;

            int age = Integer.parseInt(ageText);
            if (age >= 21 && age < 40) {
                ageInput.setTextColor(Color.BLACK);
                currentForm.setAge(age);
                sendBtn.setEnabled(currentForm.validateData());
                return;
            }
            sendBtn.setEnabled(false);
            Toast.makeText(
                    MainActivity.this,
                    "Возраст нам нужен от 21 до 40 :)",
                    Toast.LENGTH_LONG)
            .show();
            ageInput.setTextColor(Color.RED);
        });

        // Даем слушатель изменения прогресса на ползунке
        salarySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = salarySeekBar.getProgress();
                if (progress >= 800 && progress <= 1600) {
                    Toast.makeText(
                                    MainActivity.this,
                                    "Желаемая ЗП: $" + progress,
                                    Toast.LENGTH_LONG)
                            .show();
                    currentForm.setSalary(progress);
                    sendBtn.setEnabled(currentForm.validateData());
                    return;
                }

                sendBtn.setEnabled(false);
                Toast.makeText(
                                MainActivity.this,
                                "Зарплату можем предложить только от $800 до $1600",
                                Toast.LENGTH_LONG)
                        .show();
            }
        });
        // Даем слушатель изменения галочки в чек-боксе (для опыта работы)
        workExperienceCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            currentForm.setWorkExperience(isChecked);
        });
        // Даем слушатель изменения галочки в чек-боксе (для командной работы)
        teamWorkCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentForm.setTeamWork(isChecked);
        });
        // Даем слушатель изменения галочки в чек-боксе (готов ли к командировкам)
        businessTripCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentForm.setBusinessTrip(isChecked);
        });

        // Даем слушатель клика на предмет (sendBtn)
        sendBtn.setOnClickListener(view -> {
            score = 0;
            maxScore = 0;
            if (currentForm.isWorkExperience()) {
                score += 2; // score = score + 2;
            }
            maxScore += 2;
            if (currentForm.isTeamWork()) {
                score++; // score = score + 1; score += 1;
            }
            maxScore++;

            if (currentForm.isBusinessTrip()) {
                score++;
            }
            maxScore++;



            // Каждый вариант ответа в answers проверить с каждым ответом в quizzes
            for(int i = 0; i < quizzes.size(); i++) {
                if(currentForm.getAnswers()[i].equals(quizzes.get(i).getCorrect())) {
                    score += 2;
                }
                maxScore += 2;
            }


            resultTxt.setVisibility(View.VISIBLE);

            if (score < 10 ) {
                resultTxt.setText("Вы не прошли этап тестирования, так как набрали "
                        + score +"/" + maxScore + ":( Попробуйте еще!");
            } else {
                resultTxt.setText("Вы прошли этап тестирования, набрав " + score + "/" + maxScore + "!" +
                        "Нашел отдел HR скоро с Вами свяжутся!");
            }

        });
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

    private void createQuizContainer(Quiz quiz, int index) {
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

        // Вешаем событие выбора на RadioGroup
        radioButtonsContainer.setOnCheckedChangeListener((radioGroup, id) -> {
            RadioButton checkRadioBtn = radioButtonsContainer.findViewById(id);
            if (checkRadioBtn != null) {
                String choice = checkRadioBtn.getText().toString();
                currentForm.getAnswers()[index] = choice;
                // Log.d("USER SELECT", "Your choices: " + Arrays.toString(currentForm.getAnswers()));
            }
        });


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