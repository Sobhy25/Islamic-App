package com.example.islamicapp.Database.tafseer;

import com.example.islamicapp.pojo.tafseer.AyahTafseer;
import com.example.islamicapp.pojo.tafseer.Tafseer;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TafseerApi {
    @GET("tafseer/")
    Call<List<Tafseer>> getallTafseer();

    @GET("tafseer/{id}")
    Call<Tafseer> getTafseer(@Path("id") int TafseerId);

    @GET("tafseer/{tafseer_id}/{sura_number}/{ayah_number}")
    Call<AyahTafseer> getTafseerForAyah(@Path("tafseer_id") int tafseerId, @Path("sura_number") int suraNumber, @Path("ayah_number") int ayahNumber);
}
