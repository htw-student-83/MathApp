package com.example.mathapp.frontend;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.os.Bundle;

import com.example.mathapp.R;

public class Gratulation extends AppCompatActivity {
    private static final int MAX_WAITING_TIME = 5000; // 5 Sekundens

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratulation);
        ImageView picture = findViewById(R.id.imageView);
        picture.setBackgroundResource(R.drawable.gratulation2);
        Glide.with(this).asGif().load(R.drawable.gratulation2).into(picture);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(Gratulation.this, ViewPage.class);
                startActivity(loginIntent);
                finish(); // Aktuelle Aktivität schließen
            }
        }, MAX_WAITING_TIME);

    }
}