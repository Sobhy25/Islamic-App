package com.sobhy.quran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sobhy.quran.ui.audio.AudioActivity;
import com.sobhy.quran.ui.reading.QuranContainer;
import com.sobhy.quran.ui.reading.shamarly.ShamarlyQuranContainer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CardView read, listen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read= findViewById(R.id.main_read);
        listen= findViewById(R.id.main_listen);
        read.setOnClickListener(v ->
                showDialog()
        );
        listen.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), AudioActivity.class)));
    }

    private void showDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("اختر المصحف");
        final List<String> lables = new ArrayList<>();
        lables.add("الطبعة السعودية");
        lables.add("طبعة الشمرلي (الطابعة المصرية)");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, lables);
        builder.setAdapter(dataAdapter, (dialog, which) -> {
            switch (which){
                case 0:
                    startActivity(new Intent(getApplicationContext(), QuranContainer.class));
                    break;
                case 1:
                    startActivity(new Intent(getApplicationContext(), ShamarlyQuranContainer.class));
                    break;
            }
            Toast.makeText(MainActivity.this,"تم اختيار " +
                    lables.get(which),Toast.LENGTH_SHORT).show();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}