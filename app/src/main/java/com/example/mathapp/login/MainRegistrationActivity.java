package com.example.mathapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainRegistrationActivity extends AppCompatActivity {

    private EditText firstname, lastname, mobilephone;

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
                checkData();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Check the user input
     * @throws InterruptedException
     */
    private void checkData() throws InterruptedException {
        String fName = firstname.getText().toString();
        String lName = lastname.getText().toString();
        String phoneNumber = mobilephone.getText().toString();
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
     * @throws InterruptedException ...
     */
    private void sendToDB(String firstname, String  lastname, String phoneNumber) throws InterruptedException {
        int pin = createNewPIN();
        // - the datas will write into the database
        Database db = new Database(MainRegistrationActivity.this);
        db.addNewPerson(pin, firstname, lastname, phoneNumber);
        // - the PIN will send to the known mobil number
        sendPINCode(phoneNumber, pin);
        Thread.sleep(2000);
        Intent newActivity = new Intent(MainRegistrationActivity.this, MainLoginActivity.class);
        startActivity(newActivity);
    }

    private int createNewPIN() {
        // - a new PIN get out if the code isn't already in the db otherwise increment to the next number
        int minRandomNumber = 1000;
        return ++minRandomNumber;
    }


/*
    private void sendMail(String mail, int pin){
        String accessCode = String.valueOf(pin);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+mail));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your PIN");
        intent.putExtra(Intent.EXTRA_TEXT, accessCode);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
 */

    public void sendPINCode(String phonenumber, int pin) {
        //String phoneNumber = phonenumber; // Hier deine Handynummer einfügen
        String message = "Dein PIN lautet: " + pin;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phonenumber, null, message, null, null);
    }

}