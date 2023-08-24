package com.example.mathapp.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * This class represent the Register page to register a new member
 */
public class MainRegistrationActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_REQUEST_CODE = 1;
    private EditText firstname, lastname, mobilephone;
    private int initialPINCode = 1000;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registration);
        firstname = findViewById(R.id.editTextTextPersonName3);
        lastname = findViewById(R.id.editTextTextPersonName4);
        mobilephone = findViewById(R.id.editmoibilenumber);
        Button conformRegistration = findViewById(R.id.register);
        conformRegistration.setOnClickListener(view -> {
            try {
                onClick();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void onClick() throws InterruptedException {
        // Prüfe die SMS-Berechtigung
        if (ContextCompat.checkSelfPermission(MainRegistrationActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Wenn keine Berechtigung vorhanden ist, fordere sie an
            ActivityCompat.requestPermissions(MainRegistrationActivity.this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
        } else {
            // Wenn die Berechtigung vorhanden ist, sende die SMS
            checkData();
        }
    }

    /**
     * Check the user input
     * @throws InterruptedException
     */
    public void checkData() throws InterruptedException {
        String fName = firstname.getText().toString();
        String lName = lastname.getText().toString();
        phoneNumber = mobilephone.getText().toString();
        if(fName.isEmpty() || lName.isEmpty() || phoneNumber.isEmpty()){
            Toast.makeText(this, "One or more fields are empty.", Toast.LENGTH_LONG).show();
        }else{
            if(!TextUtils.isDigitsOnly(phoneNumber)) {
                Toast.makeText(this, "Your number is invalid.", Toast.LENGTH_LONG).show();
            }else if(phoneNumber.length() < 11){
                Toast.makeText(this, "Your number is too shurt.", Toast.LENGTH_LONG).show();
            }else{
                sendToDB(fName, lName, phoneNumber);
            }
        }
    }

    /**
     * send the valid data from an user to the db
     * @param firstname firstname
     * @param lastname lastname
     * @param phoneNumber, mobile number to get the personally pin
     * @throws InterruptedException if something going wrong by switched from one side to an other side
     */
    private void sendToDB(String firstname, String  lastname, String phoneNumber) throws InterruptedException {
        int pin = createNewPIN();
        DB_Helper helper = new DB_Helper(MainRegistrationActivity.this);
        //Solange in der Schleife verweilen, bis ein neuer PIN erstellt wurde
        while (helper.login(String.valueOf(pin))){
            pin = createNewPIN();
        };
        helper.registerMembern(String.valueOf(pin), firstname, lastname, phoneNumber);
        sendPINCode(phoneNumber, pin);
        Intent newActivity = new Intent(MainRegistrationActivity.this, MainLoginActivity.class);
        Thread.sleep(2000);
        startActivity(newActivity);
    }


    private int createNewPIN() {
        // - a new PIN get out if the code isn't already in the db otherwise increment to the next number
        return ++this.initialPINCode;
    }


    /**
     * send a new pin to a mobile number
     * @param phonenumber mobile number
     * @param pin Login-Pin
     */
    public void sendPINCode(String phonenumber, int pin) {
        String message = "Dein PIN lautet: " + pin;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phonenumber, null, message, null, null);
    }

    /**
     * send the known pin-code from the db
     * @param phonenumber mobile number
     * @param pin Login-Pin
     */
    public void sendSavedPINCode(String phonenumber, int pin) {
        String message = "Dein gespeicherter PIN lautet: " + pin;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phonenumber, null, message, null, null);
    }

}