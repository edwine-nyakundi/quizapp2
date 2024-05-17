package com.example.quizes_app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private ProgressBar progressBar;
    private TextView questionTextView;
    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;
    private Button submitButton;
    private Button nextButton;

    private String[] questions = {"Question 1", "Question 2", "Question 3", "Question 4", "Question 5"};
    private String[][] answers = {
            {"Answer 1.1", "Answer 1.2", "Answer 1.3"},
            {"Answer 2.1", "Answer 2.2", "Answer 2.3"},
            {"Answer 3.1", "Answer 3.2", "Answer 3.3"},
            {"Answer 4.1", "Answer 4.2", "Answer 4.3"},
            {"Answer 5.1", "Answer 5.2", "Answer 5.3"}
    };
    private int[] correctAnswers = {0, 1, 2, 0, 1};

    private int currentQuestionIndex = 0;
    private int selectedAnswer = -1;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        welcomeTextView = findViewById(R.id.welcome_text_view);
        progressBar = findViewById(R.id.progress_bar);
        questionTextView = findViewById(R.id.question_text_view);
        answer1Button = findViewById(R.id.answer1_button);
        answer2Button = findViewById(R.id.answer2_button);
        answer3Button = findViewById(R.id.answer3_button);
        submitButton = findViewById(R.id.submit_button);
        nextButton = findViewById(R.id.next_button);

        String name = getIntent().getStringExtra("NAME");
        welcomeTextView.setText("Welcome " + name);

        updateQuestion();

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = 0;
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = 1;
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = 2;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex++;
                selectedAnswer = -1;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        if (currentQuestionIndex < questions.length) {
            // Reset button colors
            answer1Button.setBackgroundColor(Color.parseColor("#6200EE")); // Reset to default color
            answer2Button.setBackgroundColor(Color.parseColor("#6200EE")); // Reset to default color
            answer3Button.setBackgroundColor(Color.parseColor("#6200EE")); // Reset to default color

            // Enable answer buttons
            answer1Button.setEnabled(true);
            answer2Button.setEnabled(true);
            answer3Button.setEnabled(true);

            questionTextView.setText(questions[currentQuestionIndex]);
            answer1Button.setText(answers[currentQuestionIndex][0]);
            answer2Button.setText(answers[currentQuestionIndex][1]);
            answer3Button.setText(answers[currentQuestionIndex][2]);
            progressBar.setProgress((currentQuestionIndex + 1) * 100 / questions.length);
            submitButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.GONE);
        } else {
            showFinalScore();
        }
    }


    private void checkAnswer() {
        if (selectedAnswer == -1) {
            Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        Button[] answerButtons = {answer1Button, answer2Button, answer3Button};
        for (Button button : answerButtons) {
            button.setEnabled(false); // Disable all answer buttons after submitting
        }

        if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
            score++;
            answerButtons[selectedAnswer].setBackgroundColor(Color.GREEN); // Set correct answer button to green
        } else {
            answerButtons[selectedAnswer].setBackgroundColor(Color.RED); // Set selected wrong answer button to red
            answerButtons[correctAnswers[currentQuestionIndex]].setBackgroundColor(Color.GREEN); // Set correct answer button to green
        }

        submitButton.setVisibility(View.GONE); // Hide submit button after submitting

        // Show next button
        nextButton.setVisibility(View.VISIBLE);
    }

    private void showFinalScore() {
        Intent intent = new Intent(QuizActivity.this, FinalScoreActivity.class);
        intent.putExtra("NAME", getIntent().getStringExtra("NAME"));
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.length);
        startActivity(intent);
        finish();
    }
}
