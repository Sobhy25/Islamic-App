package com.sobhy.quran.ui.reading.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sobhy.quran.R;
import com.sobhy.quran.pojo.quran.Ayah;
import com.sobhy.quran.pojo.quran.ShamarlyAyah;
import com.sobhy.quran.ui.tafseer.TafseerOfAyah;

import java.util.ArrayList;

public class QuranSearch extends AppCompatActivity implements TextWatcher, AdapterView.OnItemSelectedListener {
    Spinner searchSpinner;
    EditText searchEditText;
    TextView searchResultTextView;
    RecyclerView resultRecyclerView;
    QuranSearchRVAdapter quranSearchRVAdapter;

    static Bundle extras;
     static String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_search);
        extras = getIntent().getExtras();
        source = extras.getString("source");

        searchSpinner= findViewById(R.id.quran_search_spinner);
        searchEditText= findViewById(R.id.quran_search_edit_text);
        searchResultTextView= findViewById(R.id.quran_research_results);
        resultRecyclerView= findViewById(R.id.quran_search_recycler_view);
        quranSearchRVAdapter= new QuranSearchRVAdapter(pageNumber -> {
            Intent intent = new Intent().putExtra("pageNumber", pageNumber);
            setResult(1, intent);
            finish();
        }, new QuranSearchRVAdapter.ButtonClick() {
            @Override
            public void tafseerBtnClick(int ayahId) {
                Intent intent= new Intent(getApplicationContext(), TafseerOfAyah.class);
                intent.putExtra("Ayah_Id", ayahId);
                startActivity(intent);
            }
        });
        resultRecyclerView.setAdapter(quranSearchRVAdapter);
        searchSpinner.setOnItemSelectedListener(this);
        searchEditText.addTextChangedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(searchEditText.getText().toString().isEmpty()){
            quranSearchRVAdapter.setList(new ArrayList<>());
            quranSearchRVAdapter.setShamarlyList(new ArrayList<>());
        }else{
            if(searchEditText.getText().toString().contains("ٌ")|| searchEditText.getText().toString().contains("ُ")||
                    searchEditText.getText().toString().contains("ً")||searchEditText.getText().toString().contains("َ")||
                    searchEditText.getText().toString().contains("ِ")|| searchEditText.getText().toString().contains("ٍ")||
                    searchEditText.getText().toString().contains("ْ")||searchEditText.getText().toString().contains("ّ")){
                ArrayList<Ayah> resultAyah;
                ArrayList<ShamarlyAyah> shamarlyResultAyah;
                switch (position){
                    case 0:
                        searchEditText.setHint(R.string.search_edit_text_hint);
                        if(source.equals("QuranContainer")){
                            resultAyah = SearchResultViewModel.getAyatContainWithFormation(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah= ShamarlySearchViewModel.getAyatContainWithFormation(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 1:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatStartWithWithFormation(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatStartWithWithFormation(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 2:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatEndsWithWithFormation(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatEndsWithWithFormation(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                }
            } else{
                ArrayList<Ayah> resultAyah;
                ArrayList<ShamarlyAyah> shamarlyResultAyah;
                switch (position){
                    case 0:
                        searchEditText.setHint(R.string.search_edit_text_hint);
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatContain(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatContain(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 1:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatStartWith(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatStartWith(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
                        break;
                    case 2:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatEndsWith(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatEndsWith(getApplicationContext(), searchEditText.getText().toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
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
            quranSearchRVAdapter.setShamarlyList(new ArrayList<>());
        } else {
            if(s.toString().contains("ٌ")|| s.toString().contains("ُ")||s.toString().contains("ً")||s.toString().contains("َ")||s.toString().contains("ِ")||
                    s.toString().contains("ٍ")||s.toString().contains("ْ")||s.toString().contains("ّ")){
                ArrayList<Ayah> resultAyah;
                ArrayList<ShamarlyAyah> shamarlyResultAyah;
                switch (position) {
                    case 0:
                        if(source.equals("QuranContainer")){
                            resultAyah = SearchResultViewModel.getAyatContainWithFormation(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah= ShamarlySearchViewModel.getAyatContainWithFormation(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        break;
                    case 1:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatStartWithWithFormation(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatStartWithWithFormation(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        break;
                    case 2:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatEndsWithWithFormation(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatEndsWithWithFormation(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        break;
                }
            } else {
                ArrayList<Ayah> resultAyah;
                ArrayList<ShamarlyAyah> shamarlyResultAyah;
                switch (position) {
                    case 0:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatContain(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatContain(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        break;
                    case 1:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatStartWith(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatStartWith(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        break;
                    case 2:
                        if(source.equals("QuranContainer")) {
                            resultAyah = SearchResultViewModel.getAyatEndsWith(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setList(resultAyah);
                        } else {
                            shamarlyResultAyah = ShamarlySearchViewModel.getAyatEndsWith(getApplicationContext(), s.toString());
                            quranSearchRVAdapter.setShamarlyList(shamarlyResultAyah);
                        }
                        break;
                }
            }
        }
    }
    @Override
    public void afterTextChanged(Editable s) {
        searchResultTextView.setText(String.format("نتائج البحث: %d", quranSearchRVAdapter.getItemCount()));
        Log.e("this is the result count: ", String.valueOf(quranSearchRVAdapter.getItemCount()));
    }
}