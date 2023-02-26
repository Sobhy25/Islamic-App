package com.example.islamicapp.ui.quran.audio;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.islamicapp.Database.QuranDatabase;
import com.example.islamicapp.R;
import com.example.islamicapp.pojo.AudioSura;
import com.example.islamicapp.pojo.MoshafEntity;
import com.example.islamicapp.pojo.ReciterEntity;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListOfSuras extends AppCompatActivity {

    RecyclerView suraRecyclerView;
    AudioSurasAdapter adapter;
    ArrayList<AudioSura> sura;

    Spinner moshafSpinner;

    String reciterName;

    SurahDownloadedAdapter surahDownloadedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ofsuras);
        moshafSpinner= findViewById(R.id.list_sura_moshaf_spinner);
        suraRecyclerView= findViewById(R.id.audio_list_suras_recyclerview);



        if(getIntent().getStringExtra("reciterNameFromDownload")!= null){
            surahDownloadedAdapter= new SurahDownloadedAdapter();
            suraRecyclerView.setAdapter(surahDownloadedAdapter);

            reciterName= getIntent().getStringExtra("reciterNameFromDownload");

            Log.e("reciter name", reciterName);

            List<String> moshafList= getDownloadedMoshafForReciter();

            ArrayAdapter<String> moshafDownloadedadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moshafList);
            moshafDownloadedadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            moshafSpinner.setAdapter(moshafDownloadedadapter);

            moshafSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sura= new ArrayList<>();
                    String moshafName= moshafList.get(position);
                    Log.e("moshaf name", moshafName);
                    ArrayList<AudioSura> audioSuras= (ArrayList<AudioSura>) getSurahsFilesForMoshafs(moshafName);
                    surahDownloadedAdapter.setList(audioSuras);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {
            adapter= new AudioSurasAdapter();
            suraRecyclerView.setAdapter(adapter);

            int reciterId = getIntent().getIntExtra("reciterId", -1);
            ReciterEntity reciter = ReciterViewModel.getReciter(reciterId);

            List<MoshafEntity> moshafList = reciter.getMoshaf();
            String reciterName = reciter.getName();
            ArrayAdapter<MoshafEntity> moshafadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moshafList);
            moshafadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            moshafSpinner.setAdapter(moshafadapter);

            moshafSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sura = new ArrayList<>();
                    String moshafName = moshafList.get(position).getName();
                    int totalOfSuras = moshafList.get(position).getSurah_total();
                    String listOfSurasAsString = moshafList.get(position).getSurah_list();
                    ArrayList<Integer> listOfSuras = convertStringToArrayList(listOfSurasAsString);
                    String server = moshafList.get(position).getServer();
                    DecimalFormat formatter = new DecimalFormat("000");

                    for (int i = 0; i < totalOfSuras; i++) {
                        int suraNumber = listOfSuras.get(i);
                        String url = server + formatter.format(suraNumber) + ".mp3";
                        String suraName = QuranDatabase.getINSTANCE(getApplicationContext()).quranDao().getSoraByNumber(suraNumber).getArabicName();
                        sura.add(new AudioSura(suraNumber, url, suraName, reciterName, moshafName));
                    }
                    adapter.setList(sura);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }




    public ArrayList<Integer> convertStringToArrayList(String text){
        String[] elements = text.split(","); // step two : convert String array to list of String
        List<String> fixedLenghtList = Arrays.asList(elements); // step three : copy fixed list to an ArrayList
        return fixedLenghtList.stream()
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<String> getDownloadedMoshafForReciter(){
        List<String> moshafs= new ArrayList<>();
        File[] moshafsFolder= getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS+"/"+reciterName).listFiles(File::isDirectory);
        if(moshafsFolder != null){
            for(File reciterFolder: moshafsFolder){
                moshafs.add(reciterFolder.getName());
            }
        }
        return moshafs;
    }

    private List<AudioSura> getSurahsFilesForMoshafs(String moshafsName){
        List<AudioSura> audioSuras= new ArrayList<>();
        File[] surahsFiles= getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS+"/"+reciterName+"/"+moshafsName).listFiles(File::isFile);
        if(surahsFiles!= null){
            for (File file: surahsFiles){
                if (file.getName().endsWith(".mp3")){
                    String soraName= file.getName().replace(".mp3", "");
                    AudioSura audioSura= new AudioSura(1,file.getPath(),soraName , reciterName, moshafsName);
                    audioSuras.add(audioSura);
                }
            }
        }
        return audioSuras;
    }
}