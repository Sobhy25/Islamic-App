package com.example.islamicapp.ui.quran.tafseer;

import static com.example.islamicapp.ui.quran.audio.RecitersFragment.isNetworkAvailable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.islamicapp.Database.QuranDatabase;
import com.example.islamicapp.R;
import com.example.islamicapp.pojo.quran.Ayah;
import com.example.islamicapp.pojo.tafseer.Tafseer;

public class TafseerOfAyah extends AppCompatActivity {
    Toolbar toolbar;
    TextView ayahText, tafseerName, tafseerText;
    Spinner tafseerSpinner;
    TafseerViewModel tafseerViewModel;
    int ayahId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tafseer_of_ayah);
        toolbar= findViewById(R.id.tafseer_tool_bar);
        setSupportActionBar(toolbar);
        ayahText= findViewById(R.id.tafseer_ayah_text);
        tafseerName= findViewById(R.id.tafseer_tafseer_name);
        tafseerText= findViewById(R.id.tafseer_tafseer_text);
        tafseerSpinner= findViewById(R.id.tafseer_spinner);
        LinearLayout tafseerLayout= findViewById(R.id.tafseer_layout_tafseer);
        TextView noInternetTv= findViewById(R.id.tafseer_tv_no_connection);
        tafseerViewModel= new ViewModelProvider(this).get(TafseerViewModel.class);

        ayahId= getIntent().getIntExtra("Ayah_Id", -1);
        Ayah ayah= QuranDatabase.getINSTANCE(this).quranDao().getAyahByItsId(ayahId);
        toolbar.setTitle("سورة "+ayah.getSora_name_ar()+" - آية "+ ayah.getAya_no());
        ayahText.setText(ayah.getAya_text());


        if(isNetworkAvailable(this)){
            noInternetTv.setVisibility(View.GONE);
            tafseerLayout.setVisibility(View.VISIBLE);

            tafseerViewModel.getAllTafseerFromApi().observe(this, tafseers -> {
                ArrayAdapter<Tafseer> tafseerArrayAdapter= new ArrayAdapter<>(TafseerOfAyah.this, android.R.layout.simple_spinner_item, tafseers);
                tafseerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tafseerSpinner.setAdapter(tafseerArrayAdapter);
            });
            tafseerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    tafseerViewModel.getTafseerOfAyahFromApi(position+1, ayah.getSora(), ayah.getAya_no()).observe(TafseerOfAyah.this, ayahTafseer -> {
                        tafseerName.setText(ayahTafseer.getTafseer_name());
                        tafseerText.setText(ayahTafseer.getText());
                    });
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            tafseerLayout.setVisibility(View.GONE);
            noInternetTv.setVisibility(View.VISIBLE);
        }
    }


}