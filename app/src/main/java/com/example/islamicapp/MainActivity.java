package com.example.islamicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.islamicapp.ui.quran.audio.AudioActivity;
import com.example.islamicapp.ui.quran.reading.QuranContainer;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button read, listen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.main_tool_bar);
        setSupportActionBar(toolbar);
        read= findViewById(R.id.main_read);
        listen= findViewById(R.id.main_listen);
        read.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), QuranContainer.class)));
        listen.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), AudioActivity.class)));
    }
}