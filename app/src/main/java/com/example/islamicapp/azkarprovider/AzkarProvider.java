package com.example.islamicapp.azkarprovider;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.islamicapp.pojo.Zekr;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AzkarProvider {

    @Nullable
    public static ArrayList<Zekr> getAllAzkar(Context context) {
        ArrayList<Zekr> azkar;
        try {
            InputStream inputStream = context.getAssets().open("azkar/azkar.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            azkar = gson.fromJson(json, new TypeToken<List<Zekr>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return azkar;
    }


    public static ArrayList<Zekr> getAzkarByType(Context context,@NonNull String zekrType){
        ArrayList<Zekr> filteredZekrs = new ArrayList<>();
        for (Zekr zekr : getAllAzkar(context)) {
            if (zekr.getCategory().equals(zekrType)) {
                filteredZekrs.add(zekr);
            }
        }
        return filteredZekrs;
    }


}
