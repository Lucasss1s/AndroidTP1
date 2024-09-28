package com.example.preguntados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadosActivity extends AppCompatActivity {

    private TextView resultsText, selectedAnswersText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        resultsText = findViewById(R.id.resultsText);
        selectedAnswersText = findViewById(R.id.selectedAnswersText);
        button = findViewById(R.id.ButtonFinish);

        int score = getIntent().getIntExtra("score", 0);
        String[] selectedAnswers = getIntent().getStringArrayExtra("selectedAnswers");

        StringBuilder results = new StringBuilder("Puntaje: ");
        results.append(score);

        StringBuilder answers = new StringBuilder();
        if (selectedAnswers != null) {
            for (String answer : selectedAnswers) {
                answers.append(answer).append("\n");
            }
        }

        resultsText.setText(results.toString());
        selectedAnswersText.setText(answers.toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

