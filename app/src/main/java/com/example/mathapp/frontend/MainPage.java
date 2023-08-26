package com.example.mathapp.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathapp.R;
import com.example.mathapp.backend.Businesslogic;
import com.example.mathapp.login.MainForgotPasswordActivity;
import com.example.mathapp.login.MainLoginActivity;

import java.util.Random;

/**
 * ....
 */
public class MainPage extends AppCompatActivity {
    private int product;
    private int cnt = 0;
    private int MAX_GAME_POINTS = 5;
    private EditText editInputResult;
    private TextView current_exercise, editPoints;
    private boolean isClicked = false;
    //private Businesslogic model = new Businesslogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        current_exercise = findViewById(R.id.currentExercise);
        editInputResult = findViewById(R.id.inputResult);
        editPoints = findViewById(R.id.changePoints);

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
            Intent newActivity = new Intent(MainPage.this, MainLoginActivity.class);
            startActivity(newActivity);
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
            incrementCounter();
            desingPoints();
            if(this.cnt == MAX_GAME_POINTS){
                Intent newActivity = new Intent(MainPage.this, Gratulation.class);
                startActivity(newActivity);
            }else{
                incrementCounter();
                editPoints.setText(String.valueOf(this.cnt));
                getNewExercise();
            }
        }
    }


    /**
     * create a new exercise
     */
    private void getNewExercise(){
        Random ersteZahl = new Random();
        Random zweiteZahl = new Random();
        int firstMultiplikator = ersteZahl.nextInt(10);
        int secondMultiplikator = zweiteZahl.nextInt(10);
        this.product = firstMultiplikator * secondMultiplikator;
        String operator = "x";
        setExercise(firstMultiplikator, operator, secondMultiplikator);
    }

    private void incrementCounter(){
        String point = editPoints.getText().toString();
        int changesPoint = Integer.parseInt(point);
        changesPoint++;
        this.cnt = changesPoint;
    }

    private void desingPoints(){
        int textColor = ContextCompat.getColor(this, R.color.teal_700);
        editPoints.setTextColor(textColor);
    }

}