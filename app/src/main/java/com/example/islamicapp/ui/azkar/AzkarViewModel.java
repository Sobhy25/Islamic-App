package com.example.islamicapp.ui.azkar;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import com.example.islamicapp.azkarprovider.AzkarProvider;
import com.example.islamicapp.pojo.Zekr;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AzkarViewModel {

    public ArrayList<Zekr> getAllZekr(Context context){
        return AzkarProvider.getAllAzkar(context);
    }

    public HashSet<String> getTypes(Context context) {
        HashSet<String> types = new HashSet<>();
        for (Zekr zekr : getAllZekr(context)) {
            types.add(zekr.getCategory());
        }
        return types;
    }

    public  ArrayList<Zekr> getAzkarByType(Context context,@NonNull String zekrType){
        ArrayList<Zekr> filteredZekrs = new ArrayList<>();
        for (Zekr zekr : getAllZekr(context)) {
            if (zekr.getCategory().equals(zekrType)) {
                filteredZekrs.add(zekr);
            }
        }
        return filteredZekrs;
    }

}
