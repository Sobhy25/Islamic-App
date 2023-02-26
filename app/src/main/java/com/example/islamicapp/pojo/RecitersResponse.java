package com.example.islamicapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class RecitersResponse {
    @SerializedName("reciters")
    private List<ReciterEntity> reciters;

    public List<ReciterEntity> getReciters() {
        Collections.sort(reciters);
        return reciters;
    }
}
