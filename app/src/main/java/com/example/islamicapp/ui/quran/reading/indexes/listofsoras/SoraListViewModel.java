package com.example.islamicapp.ui.quran.reading.indexes.listofsoras;

import android.content.Context;

import com.example.islamicapp.Database.QuranDatabase;
import com.example.islamicapp.pojo.quran.Sora;

import java.util.ArrayList;

public class SoraListViewModel {

    public static ArrayList<Sora> getAllSoras(Context context){
        ArrayList<Sora> allSoras= new ArrayList<>();
        for (int i = 1; i <= 114; i++) {
            allSoras.add(QuranDatabase.getINSTANCE(context).quranDao().getSoraByNumber(i));
        }
        return allSoras;
    }
}
