package com.example.mathapp.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathapp.R;
import com.example.mathapp.login.MainLoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * This class represent the frontend of the App.
 */
public class ViewPage extends AppCompatActivity {
    private EditText editInputResult;
    private TextView current_exercise, editPoints, feedback;
    private boolean isClicked = false;
    private Button createExercise;
    private ControllPage controller;
    private static final int MAX_WAITING_TIME = 5000; // 5 Sekundens



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        current_exercise = findViewById(R.id.currentExercise);
        editInputResult = findViewById(R.id.inputResult);
        editPoints = findViewById(R.id.changePoints);
        feedback = findViewById(R.id.feedbackText);

        this.controller = new ControllPage(this);

        createExercise = findViewById(R.id.newExercise);

        startNewExercise();

        Button checkInput = findViewById(R.id.checkInput);
        checkInput.setOnClickListener(view -> {
            if(!resultFromUserIsEmpty()){
                try {
                    controller.evaluation();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button finish = findViewById(R.id.cancle);
        finish.setOnClickListener(view -> {
            Intent newActivity = new Intent(ViewPage.this, MainLoginActivity.class);
            startActivity(newActivity);
        });

    }

    public void startNewExercise(){
        createExercise.setOnClickListener(view ->{
            if(!this.isClicked){
                this.isClicked = true;
                controller.presentProperNumbers();
                createExercise.setEnabled(false);
            }
        });
    }

    /**
     * show a text, if the currently exercise is solved right
     * @throws InterruptedException
     */
    public void showSuccessToast() {
        editInputResult.setText("");
        desingPoints();
    }


    /**
     * show a text, if the currently exercise isn't solved right
    */
    public void showFaildToast(){
        editInputResult.setText("");
    }

    /**
     * goes to the page Gratulation.
     */
    public void goToGratulation(){
        Intent newActivity = new Intent(ViewPage.this, Gratulation.class);
        startActivity(newActivity);
    }

    /**
     * print the new exercise
     * @param firstNumber first operator
     * @param operator multiplikator
     * @param secondNumber second operator
     */
    public void setExercise(int firstNumber, String operator, int secondNumber){
        current_exercise.setText(firstNumber + " " + operator + " " + secondNumber);
    }

    /**
     * print the text of the evaluation
     * @param urteil the exactly meaning of stand of points in after a game round
     */
    public void setFeedback(String urteil){
        feedback.setText(urteil);
    }

    /**
     * check the input from an user.
     */
    private boolean resultFromUserIsEmpty() {
       if(editInputResult.getText().toString().isEmpty()) {
           Toast.makeText(this, "Eingabe fehlt.", Toast.LENGTH_SHORT).show();
           return true;
       }else {
           return false;
       }
    }

    /**
     * create and print a new exercise
     */
    public void showNewExercise(int fnumber, int snumber){
        String operator = "x";
        setExercise(fnumber, operator, snumber);
    }

    private void desingPoints(){
        int textColor = ContextCompat.getColor(this, R.color.teal_700);
        editPoints.setTextColor(textColor);
    }


    /**
     * give the currently points of the game.
     * @return currently points
     */
    //TODO Please change the modificator!
    public int getViewPoint(){
        String viewpoint = editPoints.getText().toString();
        return Integer.parseInt(viewpoint);
    }

    /**
     * give the input from the user.
     * @return input
     */
    public int getInputFromUser(){
        String viewpoint = editInputResult.getText().toString();
        return Integer.parseInt(viewpoint);
    }


    /**
     * prints the currently points of the game.
     * @param currentlyPoints
     */
    public void showcurrentlyPoints(int currentlyPoints){
        editPoints.setText(String.valueOf(currentlyPoints));
    }

    public void startNewExerciseAgain(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(ViewPage.this, ViewPage.class);
                startActivity(loginIntent);
                finish(); // Aktuelle Aktivität schließen
            }
        }, MAX_WAITING_TIME);
    }

}