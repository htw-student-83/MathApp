package com.example.mathapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainForgotPasswordActivity extends AppCompatActivity {

    private EditText phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forgot_password);
        phonenumber = findViewById(R.id.editNumberForgotPIN);

        Button sendButton = findViewById(R.id.button19);
        sendButton.setOnClickListener(view -> {
            checkInputNumberFromUser();
        });
    }

    /**
     * check the input from the user
     */
    private void checkInputNumberFromUser() {
        if (phonenumber.getText().toString().isEmpty()) {
            Toast.makeText(this,"Which moible number?", Toast.LENGTH_SHORT).show();
        }else{
            String knownMobilenumber = phonenumber.getText().toString();
            if(!TextUtils.isDigitsOnly(knownMobilenumber)){
                Toast.makeText(this,"Your number is too shurt.", Toast.LENGTH_SHORT).show();
            }else if(knownMobilenumber.length() < 11){
                Toast.makeText(this,"Your number is invalid.", Toast.LENGTH_SHORT).show();
            }else {
                sendPinAgain(knownMobilenumber);
            }
        }
    }

    /**
     * send the saved pin-code to the input mobile number
     * @param inputNumber
     */
    private void sendPinAgain(String inputNumber){
        MainRegistrationActivity pinsender = new MainRegistrationActivity();
        //Database db = new Database();
        //TODO den richtigen PIN aus der DB auslesen!
        //int knownNumber = db.getPersonData(inputNumber);
        //pinsender.sendPINCode(inputNumber, knownNumber);
    }

    private void goBackToLogin() throws InterruptedException {
        Thread.sleep(3000);
        Intent newActivity = new Intent(MainForgotPasswordActivity.this, MainLoginActivity.class);
        startActivity(newActivity);
    }

}