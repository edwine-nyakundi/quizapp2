// FinalScoreActivity.java

package com.example.quizes_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalScoreActivity extends AppCompatActivity {

    private TextView congratsTextView;
    private TextView scoreTextView;
    private Button takeNewQuizButton;
    private Button finishButton;
    private Button attemptArithmeticButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        congratsTextView = findViewById(R.id.congrats_text_view);
        scoreTextView = findViewById(R.id.score_text_view);
        takeNewQuizButton = findViewById(R.id.take_new_quiz_button);
        finishButton = findViewById(R.id.finish_button);
        attemptArithmeticButton = findViewById(R.id.attempt_arithmetic_button);

        String name = getIntent().getStringExtra("NAME");
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        congratsTextView.setText("Congratulations " + name);
        scoreTextView.setText("Your score is: " + score + "/" + totalQuestions);

        takeNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalScoreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        attemptArithmeticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalScoreActivity.this, ArithmeticActivity.class);
                startActivity(intent);
            }
        });
    }
}
