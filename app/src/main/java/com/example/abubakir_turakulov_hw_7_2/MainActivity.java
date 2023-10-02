package com.example.abubakir_turakulov_hw_7_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Double first, second;
    private Boolean isOperationClick;
    private Operation currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
    }

    public void onNumberClick(View view) {
        String textButton = ((Button) view).getText().toString();
        if (textButton.equals("AC")) {
            textView.setText("0");
            first = 0.0;
            second = 0.0;
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(textButton);
        } else if (!textView.getText().toString().contains(".") && textButton.equals(".")) {
            textView.append(".");
        } else {
            textView.append(textButton);
        }
        isOperationClick = false;
    }

    private void updateTextView(Double result) {
        if (result % 1 == 0) {
            textView.setText(String.valueOf(result.intValue()));
        } else {
            textView.setText(String.valueOf(result));
        }
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.btn_plus) {
            first = Double.valueOf(textView.getText().toString());
            currentOperation = Operation.ADDITION;
        } else if (view.getId() == R.id.btn_minus) {
            first = Double.valueOf(textView.getText().toString());
            currentOperation = Operation.SUBTRACTION;
        } else if (view.getId() == R.id.btn_multiply) {
            first = Double.valueOf(textView.getText().toString());
            currentOperation = Operation.MULTIPLICATION;
        } else if (view.getId() == R.id.btn_divide) {
            first = Double.valueOf(textView.getText().toString());
            currentOperation = Operation.DIVISION;
        } else if (view.getId() == R.id.btn_equal) {
            second = Double.valueOf(textView.getText().toString());
            Double result = calculateOperation(first, second, currentOperation);
            updateTextView(result);
        } else if (view.getId() == R.id.btn_percent) {
            if (currentOperation != null) {
                second = Double.valueOf(textView.getText().toString());
                Double result = calculateOperation(first, second, Operation.PERCENT);
                updateTextView(result);
            }
        }
        isOperationClick = true;
    }

    public void onDotClick(View view) {
        if (!textView.getText().toString().contains(".")) {
            textView.append(".");
        }
    }

    private Double calculateOperation(Double first, Double second, Operation operation) {
        switch (operation) {
            case ADDITION:
                return first + second;
            case SUBTRACTION:
                return first - second;
            case MULTIPLICATION:
                return first * second;
            case DIVISION:
                if (second == 0.0) {
                    Toast.makeText(MainActivity.this, "На ноль делить нельзя!", Toast.LENGTH_SHORT).show();
                    return 0.0;
                } else {
                    return first / second;
                }
            case PERCENT:
                return first * (second / 100.0);
            default:
                return 0.0;
        }
    }
}
