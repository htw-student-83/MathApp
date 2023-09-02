package com.example.mathapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mathapp.R;
import com.example.mathapp.frontend.ViewPage;

import androidx.appcompat.app.AppCompatActivity;

public class MainLoginActivity extends AppCompatActivity {

    private EditText editPIN;
    private DB_Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        editPIN = findViewById(R.id.editTextNumberPassword2);

        Button registration = findViewById(R.id.button16);
        Button forgotPassword = findViewById(R.id.button18);
        Button login = findViewById(R.id.button13);

        login.setOnClickListener(view -> ckeckUserInput());
        registration.setOnClickListener(view -> goToRegistration());
        forgotPassword.setOnClickListener(view -> getPassword());
    }

    public void ckeckUserInput(){
        int maxuserinput = 4;
        if(editPIN.getText().toString().isEmpty()){
            Toast.makeText(this, "Please input your PIN!", Toast.LENGTH_SHORT).show();
        }else{
          if(editPIN.getText().toString().length() < maxuserinput){
                Toast.makeText(this, "Your PIN is too shurt", Toast.LENGTH_SHORT).show();
            }else if(editPIN.getText().toString().length() > maxuserinput) {
              Toast.makeText(this, "Your PIN is too long", Toast.LENGTH_SHORT).show();
            }else{
                int inputPIN = Integer.parseInt(editPIN.getText().toString());
                helper = new DB_Helper(this);
                boolean pinIsKnown =  this.helper.login(String.valueOf(inputPIN));
                if(pinIsKnown){
                    goToMainPage();
                    editPIN.setText("");
                }else{
                    Toast.makeText(this, "Your PIN is unknown", Toast.LENGTH_SHORT).show();
                }
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

    private void goToMainPage() {
        Intent newActivity = new Intent(MainLoginActivity.this, ViewPage.class);
        startActivity(newActivity);
    }

}