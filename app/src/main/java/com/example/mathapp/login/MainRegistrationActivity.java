package com.example.mathapp.login;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathapp.R;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainRegistrationActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_REQUEST_CODE = 1;
    private EditText firstname, lastname, mobilephone;
    private int initialPINCode = 1000;
    private DB_Helper helper;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registration);
        helper = new DB_Helper(this);
        helper.openDB();
        firstname = findViewById(R.id.editTextTextPersonName3);
        lastname = findViewById(R.id.editTextTextPersonName4);
        mobilephone = findViewById(R.id.editmoibilenumber);
        Button conformRegistration = findViewById(R.id.register);
        conformRegistration.setOnClickListener(view -> {
            try {
                onClick(view);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void onClick(View v) throws InterruptedException {
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
    private void checkData() throws InterruptedException {
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
     * @throws InterruptedException ...
     */
    private void sendToDB(String firstname, String  lastname, String phoneNumber) throws InterruptedException {
        int pin = createNewPIN();
        //Der Toast kann nur Strings ausgeben!!!
        //Toast.makeText(this, String.valueOf(pin), Toast.LENGTH_LONG).show();
        //TODO prüfen, ob es diesen PIN in der DB bereits gibt
       /* while (pin == getPersonData()){
            pin = createNewPIN();
        }

        */
        // - the datas will write into the database
        //Database db = new Database(MainRegistrationActivity.this);
        DB_Daten db = new DB_Daten(pin, firstname, lastname, phoneNumber);
        if(this.helper.registerDaten(db)){
            sendPINCode(phoneNumber, pin);
            Intent nextPage = new Intent(MainRegistrationActivity.this, MainLoginActivity.class);
            startActivity(nextPage);
        }else{
            Toast.makeText(this, "Registration was failed.", Toast.LENGTH_LONG).show();
        }
        //boolean result = db.addNewPerson(pin, firstname, lastname, phoneNumber);
        // - the PIN will send to the known mobil number
    }



    private int createNewPIN() {
        // - a new PIN get out if the code isn't already in the db otherwise increment to the next number
        return ++this.initialPINCode;
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

    /**
     *
     * @param phonenumber
     * @param pin
     */
    public void sendPINCode(String phonenumber, int pin) {
        String message = "Dein PIN lautet: " + pin;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phonenumber, null, message, null, null);
    }

}