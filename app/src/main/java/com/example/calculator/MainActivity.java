package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private String action = "+";
    private Number result = 0;

    public void changeAction(View view){
        Button actionButton = (Button)view;
        this.action = actionButton.getText().toString();
        TextView operator = findViewById(R.id.OperatorView);

        operator.setText(action);

        boolean res =  calculate();
        if (res){
            changeResult();
        }


    }

    public void clearNums(View view){
        EditText num1 = findViewById(R.id.NumberInput);
        EditText num2 = findViewById(R.id.NumberInput2);
        num1.setText("0");
        num2.setText("0");


    }

    public void changeResult(){
        TextView resView = findViewById(R.id.ResultView);
        resView.setText(this.result.toString());

    }
    public void setError(String val){
        TextView resView = findViewById(R.id.ResultView);
        resView.setText(val);
    }


    public Boolean calculate(){
        Float val1, val2;
        val1 = val2 = 0.0f;
        try {
            EditText ed1 = (EditText)findViewById(R.id.NumberInput);
            EditText ed2 = (EditText)findViewById(R.id.NumberInput2);

            val1 = Float.parseFloat(ed1.getText().toString());
            val2 = Float.parseFloat(ed2.getText().toString());


        } catch (NumberFormatException e){
            Log.e("Number Parsing", "Invalid num: " + e.getMessage());
            val1=val2 = 0.0f;
        }
        if (val1.isNaN() || val2.isNaN()) {
            setError("Invalid value");
            return false;
        };




        switch (this.action) {
            case "+":
                this.result = val1+val2;
                break;
            case "*":
                this.result = val1*val2;
                break;
            case "/":
                if (!val1.isNaN() && val2 == 0f){
                    setError("Zero division");

                    return false;

                }
                this.result = val1/val2;
                break;
            default:
                this.result = val1-val2;
                break;

        }
        return true;
    }









}