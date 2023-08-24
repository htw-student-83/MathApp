package com.example.mathapp.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainForgotPasswordActivity extends AppCompatActivity {
    private EditText phonenumber;
    private MainRegistrationActivity pinSender = new MainRegistrationActivity();
    private static final int SMS_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forgot_password);
        phonenumber = findViewById(R.id.editNumber);
        Button sendButton = findViewById(R.id.button19);
        sendButton.setOnClickListener(view -> {
            try {
                onClick();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * check the input from the user
     */
    private void checkInputNumberFromUser() throws InterruptedException {
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
    // * @param inputNumber
     */
    private void sendPinAgain(String inputNumber) throws InterruptedException {
        DB_Helper helper = new DB_Helper(this);
        if(!helper.checkMobileNumber(inputNumber)){
            Toast.makeText(this, "the mobile number is unknown.", Toast.LENGTH_SHORT).show();
        }else{
            String knownPIN = helper.sendKnownPin(inputNumber);
            pinSender.sendSavedPINCode(inputNumber, Integer.parseInt(knownPIN));
            goBackToLogin();
        }
    }


    private void goBackToLogin() throws InterruptedException {
        Thread.sleep(3000);
        Intent newActivity = new Intent(MainForgotPasswordActivity.this, MainLoginActivity.class);
        startActivity(newActivity);
    }

    private void onClick() throws InterruptedException {
        // Prüfe die SMS-Berechtigung
        if (ContextCompat.checkSelfPermission(MainForgotPasswordActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Wenn keine Berechtigung vorhanden ist, fordere sie an
            ActivityCompat.requestPermissions(MainForgotPasswordActivity.this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
        } else {
            // Wenn die Berechtigung vorhanden ist, sende die SMS
           checkInputNumberFromUser();
        }
    }

}