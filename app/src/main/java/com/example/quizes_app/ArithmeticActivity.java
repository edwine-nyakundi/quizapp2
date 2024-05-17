package com.example.quizes_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//calculation logic
public class ArithmeticActivity extends AppCompatActivity {

    private EditText number1EditText;
    private EditText number2EditText;
    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic);

        number1EditText = findViewById(R.id.number1_edit_text);
        number2EditText = findViewById(R.id.number2_edit_text);
        addButton = findViewById(R.id.add_button);
        subtractButton = findViewById(R.id.subtract_button);
        multiplyButton = findViewById(R.id.multiply_button);
        divideButton = findViewById(R.id.divide_button);
        resultTextView = findViewById(R.id.result_text_view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operation.ADD);
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operation.SUBTRACT);
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operation.MULTIPLY);
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operation.DIVIDE);
            }
        });
    }

    private void performOperation(Operation operation) {
        String number1Str = number1EditText.getText().toString();
        String number2Str = number2EditText.getText().toString();

        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double number1 = Double.parseDouble(number1Str);
        double number2 = Double.parseDouble(number2Str);
        double result = 0;

        switch (operation) {
            case ADD:
                result = number1 + number2;
                break;
            case SUBTRACT:
                result = number1 - number2;
                break;
            case MULTIPLY:
                result = number1 * number2;
                break;
            case DIVIDE:
                if (number2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = number1 / number2;
                break;
        }

        resultTextView.setText(String.valueOf(result));
    }

    private enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
