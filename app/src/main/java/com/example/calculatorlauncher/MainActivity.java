package com.example.calculatorlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {
    private static final String CALCULATOR_PACKAGE = "com.example.calculator";
    private static final String FIRST_NUMBER = "first number";
    private static final String SECOND_NUMBER = "second number";
    private static final String OPERATION = "operation";
    private static final String IMAGE_URI = "image uri";

    private enum Operation {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }

    private EditText firstNumber;
    private EditText secondNumber;
    private EditText imageURI;
    private Chip chipPlus;
    private Chip chipMinus;
    private Chip chipMultiply;
    private Chip chipDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = findViewById(R.id.et_first_number);
        secondNumber = findViewById(R.id.et_second_number);
        imageURI = findViewById(R.id.et_image_uri);
        chipPlus = findViewById(R.id.chip_plus);
        chipMinus = findViewById(R.id.chip_minus);
        chipMultiply = findViewById(R.id.chip_multiply);
        chipDivide = findViewById(R.id.chip_divide);

        findViewById(R.id.b_start_calculator).setOnClickListener( view ->
                startCalculator()
        );
    }

    private void startCalculator() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(CALCULATOR_PACKAGE);
        if (launchIntent != null) {
            launchIntent.putExtra(FIRST_NUMBER, firstNumber.getText().toString());
            launchIntent.putExtra(SECOND_NUMBER, secondNumber.getText().toString());
            launchIntent.putExtra(IMAGE_URI, imageURI.getText().toString());
            launchIntent.putExtra(OPERATION, getOperation());
            startActivity(launchIntent);
        }
    }

    private String getOperation() {
        String operation = Operation.PLUS.name();

        if (chipPlus.isChecked()) {
            operation = Operation.PLUS.name();
        } else if (chipMinus.isChecked()) {
            operation = Operation.MINUS.name();
        } else if (chipMultiply.isChecked()) {
            operation = Operation.MULTIPLY.name();
        } else if (chipDivide.isChecked()) {
            operation = Operation.DIVIDE.name();
        }

        return operation;
    }
}