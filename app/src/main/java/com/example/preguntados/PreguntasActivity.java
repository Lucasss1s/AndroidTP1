package com.example.preguntados;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PreguntasActivity extends AppCompatActivity {

    private TextView question;
    private Button option1, option2, option3, option4;
    private int[] selectedAnswers = new int[4];

    private String[] questions = {
            "¿Cuál es el mineral más duro del planeta?",
            "¿Qué fenómeno natural es responsable de la separación de la luz en un arco iris?",
            "¿Qué principio de la física explica por qué los objetos aceleran a la misma velocidad en el vacío, sin importar su masa?",
            "¿Qué le ocurre a la luz al intentar escapar de un agujero negro?"
    };

    private String[][] answers = {
            {"Adamantio", "Cuarzo", "Diamante", "Mármol"},
            {"Reflexión", "Refracción", "Evaporación", "Difracción"},
            {"Ley de gravitación universal", "Teoría de la relatividad", "Ley de conservación de la energía", "Principio de equivalencia"},
            {"Se acelera infinitamente", "Se curva y regresa", "Se detiene completamente", "Se redirige hacia otro universo"}
    };

    private int[] correctAnswers = {2, 1, 3, 1};
    private int currentQuestion = 0;
    private int score = 0;
    private String[] selectedAnswersText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        question = findViewById(R.id.question);
        option1 = findViewById(R.id.button1);
        option2 = findViewById(R.id.button2);
        option3 = findViewById(R.id.button3);
        option4 = findViewById(R.id.button4);
        selectedAnswersText = new String[questions.length];

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showQuestion();

        option1.setOnClickListener(v -> handleSelection(0));
        option2.setOnClickListener(v -> handleSelection(1));
        option3.setOnClickListener(v -> handleSelection(2));
        option4.setOnClickListener(v -> handleSelection(3));
    }

    private void showQuestion() {
        question.setText(questions[currentQuestion]);
        option1.setText(answers[currentQuestion][0]);
        option2.setText(answers[currentQuestion][1]);
        option3.setText(answers[currentQuestion][2]);
        option4.setText(answers[currentQuestion][3]);

        resetButtonColors();
        enableButtons();
    }

    private void resetButtonColors() {
        option1.setBackgroundResource(R.drawable.style_buttom);
        option2.setBackgroundResource(R.drawable.style_buttom);
        option3.setBackgroundResource(R.drawable.style_buttom);
        option4.setBackgroundResource(R.drawable.style_buttom);
    }

    private void enableButtons() {
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }

    private void disableButtons() {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }

    private void handleSelection(int selectedIndex) {
        Button selectedButton;
        selectedAnswers[currentQuestion] = selectedIndex;
        if (selectedIndex == 0) selectedButton = option1;
        else if (selectedIndex == 1) selectedButton = option2;
        else if (selectedIndex == 2) selectedButton = option3;
        else selectedButton = option4;

        if (selectedIndex == correctAnswers[currentQuestion]) {
            selectedButton.setBackgroundResource(R.drawable.button_background_green);
            question.setText("CORRECTO!");
            question.setTextColor(Color.GREEN);
            score++;
        } else {
            selectedButton.setBackgroundResource(R.drawable.button_background_red);
            question.setText("INCORRECTO!");
            question.setTextColor(Color.RED);
        }

        selectedAnswersText[currentQuestion] = answers[currentQuestion][selectedIndex];
        disableButtons();

        new Handler().postDelayed(() -> {
            question.setText("");
            question.setTextColor(Color.BLACK);
            currentQuestion++;
            if (currentQuestion < questions.length) {
                showQuestion();
            } else {
                Intent intent = new Intent(PreguntasActivity.this, ResultadosActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("selectedAnswers", selectedAnswersText);
                startActivity(intent);
                finish();
            }

        }, 1000);
    }
}
