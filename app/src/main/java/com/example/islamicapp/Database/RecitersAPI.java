package com.example.islamicapp.Database;

import com.example.islamicapp.pojo.RecitersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecitersAPI {
    @GET("reciters?language=ar")
    Call<RecitersResponse> getReciters();
}
