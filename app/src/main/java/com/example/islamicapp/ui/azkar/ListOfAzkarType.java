package com.example.islamicapp.ui.azkar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.islamicapp.R;

import java.util.List;

public class ListOfAzkarType extends AppCompatActivity {
    RecyclerView typeRecyclerView;
    AzkarViewModel azkarViewModel;
    AzkarTypeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_azkar_type);
        typeRecyclerView= findViewById(R.id.list_azkartype_recyclerview);
        azkarViewModel= new AzkarViewModel();
        adapter= new AzkarTypeAdapter();
        typeRecyclerView.setAdapter(adapter);
        adapter.setList(azkarViewModel.getTypes(getBaseContext()));
    }
}