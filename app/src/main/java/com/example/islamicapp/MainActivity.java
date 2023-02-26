package com.example.islamicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.islamicapp.ui.quran.audio.AudioActivity;
import com.example.islamicapp.ui.quran.reading.QuranContainer;

public class MainActivity extends AppCompatActivity {

    Button read, listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        read= findViewById(R.id.main_read);
        listen= findViewById(R.id.main_listen);


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuranContainer.class));
            }
        });

        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AudioActivity.class));
            }
        });

    }
}