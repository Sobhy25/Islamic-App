package com.example.islamicapp.ui.quran.reading.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.islamicapp.R;
import com.example.islamicapp.pojo.quran.Ayah;

import java.util.ArrayList;

public class QuranSearch extends AppCompatActivity implements TextWatcher, AdapterView.OnItemSelectedListener {
    Spinner searchSpinner;
    EditText searchEditText;
    TextView searchResultTextView;
    RecyclerView resultRecyclerView;
    QuranSearchRVAdapter quranSearchRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_search);
        searchSpinner= findViewById(R.id.quran_search_spinner);
        searchEditText= findViewById(R.id.quran_search_edit_text);
        searchResultTextView= findViewById(R.id.quran_research_results);
        resultRecyclerView= findViewById(R.id.quran_search_recycler_view);
        quranSearchRVAdapter= new QuranSearchRVAdapter(pageNumber -> {
            Intent intent= new Intent().putExtra("pageNumber", pageNumber);
            setResult(1, intent);
            finish();
        });
        resultRecyclerView.setAdapter(quranSearchRVAdapter);
        searchSpinner.setOnItemSelectedListener(this);
        searchEditText.addTextChangedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(searchEditText.getText().toString().isEmpty()){
            quranSearchRVAdapter.setList(new ArrayList<>());
        }else{
            if(searchEditText.getText().toString().contains("ٌ")|| searchEditText.getText().toString().contains("ُ")||
                    searchEditText.getText().toString().contains("ً")||searchEditText.getText().toString().contains("َ")||
                    searchEditText.getText().toString().contains("ِ")|| searchEditText.getText().toString().contains("ٍ")||
                    searchEditText.getText().toString().contains("ْ")||searchEditText.getText().toString().contains("ّ")){
                ArrayList<Ayah> resultAyah;
                switch (position){
                    case 0:
                        searchEditText.setHint(R.string.search_edit_text_hint);
                        resultAyah = SearchResultViewModel.getAyatContainWithFormation(getApplicationContext(), searchEditText.getText().toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 1:
                        searchEditText.setHint(R.string.search_type_startwith);
                        resultAyah = SearchResultViewModel.getAyatStartWithWithFormation(getApplicationContext(), searchEditText.getText().toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 2:
                        searchEditText.setHint(R.string.search_type_endwith);
                        resultAyah = SearchResultViewModel.getAyatEndsWithWithFormation(getApplicationContext(), searchEditText.getText().toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                }
            } else{
                ArrayList<Ayah> resultAyah;
                switch (position){
                    case 0:
                        searchEditText.setHint(R.string.search_edit_text_hint);
                        resultAyah = SearchResultViewModel.getAyatContain(getApplicationContext(), searchEditText.getText().toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 1:
                        searchEditText.setHint(R.string.search_type_startwith);
                        resultAyah = SearchResultViewModel.getAyatStartWith(getApplicationContext(), searchEditText.getText().toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 2:
                        searchEditText.setHint(R.string.search_type_endwith);
                        resultAyah = SearchResultViewModel.getAyatEndsWith(getApplicationContext(), searchEditText.getText().toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                }
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int position = searchSpinner.getSelectedItemPosition();
        if (s.toString().isEmpty()){
            quranSearchRVAdapter.setList(new ArrayList<>());
        } else {
            if(s.toString().contains("ٌ")|| s.toString().contains("ُ")||s.toString().contains("ً")||s.toString().contains("َ")||s.toString().contains("ِ")||
                    s.toString().contains("ٍ")||s.toString().contains("ْ")||s.toString().contains("ّ")){
                ArrayList<Ayah> resultAyah;
                switch (position) {
                    case 0:
                        resultAyah = SearchResultViewModel.getAyatContainWithFormation(getApplicationContext(), s.toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        break;
                    case 1:
                        resultAyah = SearchResultViewModel.getAyatStartWithWithFormation(getApplicationContext(), s.toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        break;
                    case 2:
                        resultAyah = SearchResultViewModel.getAyatEndsWithWithFormation(getApplicationContext(), s.toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        break;
                }
            } else {
                ArrayList<Ayah> resultAyah;
                switch (position) {
                    case 0:
                        resultAyah = SearchResultViewModel.getAyatContain(getApplicationContext(), s.toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        break;
                    case 1:
                        resultAyah = SearchResultViewModel.getAyatStartWith(getApplicationContext(), s.toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        break;
                    case 2:
                        resultAyah = SearchResultViewModel.getAyatEndsWith(getApplicationContext(), s.toString());
                        quranSearchRVAdapter.setList(resultAyah);
                        break;
                }
            }
        }
    }
    @Override
    public void afterTextChanged(Editable s) {
        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
    }
}