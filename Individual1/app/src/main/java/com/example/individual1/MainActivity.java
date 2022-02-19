package com.example.individual1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String operations = "0";
    TextView textView;
    final String divideSymbol = "÷";
    final String multiplySymbol = "×";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        View.OnClickListener numberClickEvent = view -> {
            String number = ((Button) view).getText().toString();
            addSymbol(number);
        };

        View.OnClickListener operationClickEvent = view -> {
            String operation = ((Button) view).getText().toString();
            addSymbol(operation);
        };

        findViewById(R.id.button0).setOnClickListener(numberClickEvent);
        findViewById(R.id.button1).setOnClickListener(numberClickEvent);
        findViewById(R.id.button2).setOnClickListener(numberClickEvent);
        findViewById(R.id.button3).setOnClickListener(numberClickEvent);
        findViewById(R.id.button4).setOnClickListener(numberClickEvent);
        findViewById(R.id.button5).setOnClickListener(numberClickEvent);
        findViewById(R.id.button6).setOnClickListener(numberClickEvent);
        findViewById(R.id.button7).setOnClickListener(numberClickEvent);
        findViewById(R.id.button8).setOnClickListener(numberClickEvent);
        findViewById(R.id.button9).setOnClickListener(numberClickEvent);
        findViewById(R.id.buttonMultiply).setOnClickListener(operationClickEvent);
        findViewById(R.id.buttonDivide).setOnClickListener(operationClickEvent);
        findViewById(R.id.buttonAdd).setOnClickListener(operationClickEvent);
        findViewById(R.id.buttonSubtract).setOnClickListener(operationClickEvent);
        findViewById(R.id.buttonPoint).setOnClickListener(operationClickEvent);
        findViewById(R.id.buttonEquals).setOnClickListener(e -> {
            operations = calculate();
            textView.setText(operations);
        });
        findViewById(R.id.buttonClear).setOnClickListener(e -> {
            operations = "0";
            textView.setText(operations);
        });
        findViewById(R.id.buttonDelete).setOnClickListener(e -> {
            if (operations.length() <= 1) {
                operations = "0";
                textView.setText(operations);
                return;
            }

            String text = removeLastChars(operations, 1);
            if (text.charAt(text.length() - 1) == ' ') {
                text = removeLastChars(text, 1);
            }

            operations = text;
            textView.setText(operations);
        });
    }

    public void addSymbol(String symbol) {
        String lastCharacter = Character.toString(operations.charAt(operations.length() - 1));

        if (equalsOperators(symbol)) {
            if (equalsOperators(lastCharacter)) {
                operations = removeLastChars(operations, 1);
            }
        }

        if(lastCharacter.equals("0") && operations.length() <= 1)
            operations = "";

        operations += symbol;
        textView.setText(operations);
    }

    boolean equalsOperators(String operator) {
        switch (operator) {
            case "+":
            case "-":
            case divideSymbol:
            case multiplySymbol:
                return true;
            default:
                return false;
        }
    }

    String calculate() {
        Double result = null;
        String toCalculate = operations.replaceAll(" ", "");
        toCalculate = toCalculate.replaceAll(multiplySymbol, "\\*");
        toCalculate = toCalculate.replaceAll(divideSymbol, "/");

        String[] numbersToConvert = toCalculate.replaceAll("[+\\-/*]", " ").split(" ");
        ArrayList<Double> numbers = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();

        // convert numbers
        for (String n : numbersToConvert) {
            numbers.add(Double.parseDouble(n));
        }

        // convert operators
        for (int i = 0; i < toCalculate.length(); i++) {
            String character = Character.toString(toCalculate.charAt(i));
            switch (character) {
                case "+":
                case "-":
                case "/":
                case "*":
                    operators.add(character);
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < operators.size(); i++) {
            if (result == null)
                result = numbers.get(i);
            if (operators.get(i).equals("/") && numbers.get(i + 1) == 0.0)
                return "Cannot divide by 0";

            result = calc(result, numbers.get(i + 1), operators.get(i));
        }

        return Double.toString(result);
    }

    double calc(double number1, double number2, String operator) {
        switch (operator) {
            case "*":
                return number1 * number2;
            case "/":
                return number1 / number2;
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            default:
                return 0;
        }
    }

    String removeLastChars(String string, int numberOfChars) {
        return string.substring(0, string.length() - numberOfChars);
    }

}