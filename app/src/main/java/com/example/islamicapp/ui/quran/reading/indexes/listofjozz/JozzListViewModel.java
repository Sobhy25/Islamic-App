package com.example.islamicapp.ui.quran.reading.indexes.listofjozz;

import android.content.Context;

import com.example.islamicapp.Database.QuranDatabase;
import com.example.islamicapp.pojo.quran.Jozz;

import java.util.ArrayList;

public class JozzListViewModel {

    public static ArrayList<Jozz> getAllJozz(Context context){
        ArrayList<Jozz> allJozz= new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            allJozz.add(QuranDatabase.getINSTANCE(context).quranDao().getJozzByNumber(i));
        }
        return allJozz;
    }
}
