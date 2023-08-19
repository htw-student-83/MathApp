package com.example.mathapp.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathapp.R;
import com.example.mathapp.backend.Businesslogic;

import java.util.Random;

public class MainPage extends AppCompatActivity {
    private int product;
    private EditText editInputResult;
    private TextView current_exercise;
    private boolean isClicked = false;
    //private Businesslogic model = new Businesslogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        current_exercise = findViewById(R.id.currentExercise);
        editInputResult = findViewById(R.id.inputResult);

        Button createExercise = findViewById(R.id.newExercise);
        createExercise.setOnClickListener(view ->{
            if(!isClicked){
                getNewExercise();
                isClicked = true;
                createExercise.setEnabled(false);
            }
        });

        Button checkInput = findViewById(R.id.checkInput);
        checkInput.setOnClickListener(view -> {
            try {
                checkResultFromUser();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Button finish = findViewById(R.id.cancle);
        finish.setOnClickListener(view -> {
            finish();
        });
    }

    /**
     * print the new exercise on the display
     * @param firstNumber first operator
     * @param operator multiplikator
     * @param secondNumber second operator
     */
    public void setExercise(int firstNumber, String operator, int secondNumber){
        current_exercise.setText(firstNumber + " " + operator + " " + secondNumber);
    }

    /**
     * check the input from an user.
     */
    private void checkResultFromUser() throws InterruptedException {
       if(editInputResult.getText().toString().isEmpty()){
           Toast.makeText(this, "Eingabe fehlt.", Toast.LENGTH_SHORT).show();
       }else{
           evaluation();
        }
    }

    /**
     * evaluate the result of the user
     */
    private void evaluation() throws InterruptedException {
        if(this.product != Integer.parseInt(editInputResult.getText().toString())){
            Toast.makeText(this, "Versuch es nochmal!", Toast.LENGTH_SHORT).show();
            editInputResult.setText("");
        }else{
            Toast.makeText(this, "gut gemacht-)", Toast.LENGTH_SHORT).show();
            Thread.sleep(2000);
            editInputResult.setText("");
            getNewExercise();
        }
    }


    /**
     * create a new exercise
     */
    public void getNewExercise(){
        Random ersteZahl = new Random();
        Random zweiteZahl = new Random();
        int firstMultiplikator = ersteZahl.nextInt(10);
        int secondMultiplikator = zweiteZahl.nextInt(10);
        this.product = firstMultiplikator * secondMultiplikator;
        String operator = "x";
        setExercise(firstMultiplikator, operator, secondMultiplikator);
    }
}