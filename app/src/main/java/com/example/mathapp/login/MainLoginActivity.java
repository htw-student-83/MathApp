package com.example.mathapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainLoginActivity extends AppCompatActivity {

    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        editPassword = findViewById(R.id.editTextNumberPassword2);

        Button registration = findViewById(R.id.button16);
        Button forgotPassword = findViewById(R.id.button18);
        Button login = findViewById(R.id.button13);

        login.setOnClickListener(view -> ckeckUserInput());
        registration.setOnClickListener(view -> goToRegistration());
        forgotPassword.setOnClickListener(view -> getPassword());
    }

    /*
    private void ckeckUserInputt() {
        String code = editPassword.getText().toString();
        String[] codeArray = code.split("");
        if(code.isEmpty()){
            Toast.makeText(this,"Input your PIN!", Toast.LENGTH_SHORT).show();
        }else if(codeArray.length!=6){
            Toast.makeText(this,"PIN is invalid", Toast.LENGTH_SHORT).show();
        }else{
            // - compare the inputPIN with all PINS in the dabase
            // - if the code is already in the db, than forward to the next page, otherwise Toast...
            Intent newActivity = new Intent(MainLoginActivity.this, MainPage.class);
            startActivity(newActivity);
        }
    }
     */

    private void ckeckUserInput(){
        int maxuserinput = 4;
        if(editPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Welche Nummer?", Toast.LENGTH_SHORT).show();
        }else{
            if(!TextUtils.isDigitsOnly(editPassword.getText())){
                Toast.makeText(this, "Ungültige Eingabe!", Toast.LENGTH_SHORT).show();
            }else if(editPassword.getText().toString().length() < maxuserinput){
                Toast.makeText(this, "Der PIN ist zu kurz", Toast.LENGTH_SHORT).show();
            }else {
                String inputNumber = editPassword.getText().toString().trim();
                //TODO Abgleich mit einem DB-Eintrag
                //sendPINCode(inputNumber);
            }
        }
    }

    private void getPassword() {
        Intent newActivity = new Intent(MainLoginActivity.this, MainForgotPasswordActivity.class);
        startActivity(newActivity);
    }


    private void goToRegistration() {
        Intent newActivity = new Intent(MainLoginActivity.this, MainRegistrationActivity.class);
        startActivity(newActivity);
    }
}