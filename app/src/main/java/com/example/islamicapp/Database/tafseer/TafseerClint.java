package com.example.islamicapp.Database.tafseer;

import com.example.islamicapp.pojo.tafseer.AyahTafseer;
import com.example.islamicapp.pojo.tafseer.Tafseer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TafseerClint {
    private static final String BASE_URL= "http://api.quran-tafseer.com/";
    TafseerApi tafseerApi;
    private static TafseerClint INSTANCE;
    private TafseerClint(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tafseerApi= retrofit.create(TafseerApi.class);
    }
    public static synchronized TafseerClint getInstance(){
        if (INSTANCE==null)
            INSTANCE= new TafseerClint();
        return INSTANCE;
    }

    public Call<List<Tafseer>> getallTafseer(){
        return tafseerApi.getallTafseer();
    }
    public Call<Tafseer> getTafseer(int tafseerId){
        return tafseerApi.getTafseer(tafseerId);
    }
    public Call<AyahTafseer> getTafseerForAyah(int tafseerId, int suraNumber, int ayahNumber){
        return tafseerApi.getTafseerForAyah(tafseerId, suraNumber, ayahNumber);
    }
}
