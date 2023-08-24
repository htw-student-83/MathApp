package com.example.mathapp.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mathapp.R;
import com.example.mathapp.login.MainLoginActivity;

/**
 * This class represent a background picture of the App
 */
public class LoadPage extends AppCompatActivity {
    private static final int MAX_WAITING_TIME = 5000; // 5 Sekundens

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_page);

        //The content of this method will be execute, when the time is over
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(LoadPage.this, MainLoginActivity.class);
                startActivity(loginIntent);
                finish(); // Aktuelle Aktivität schließen
            }
        }, MAX_WAITING_TIME);

    }
}
