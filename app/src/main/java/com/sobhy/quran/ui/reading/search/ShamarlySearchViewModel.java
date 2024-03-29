package com.sobhy.quran.ui.reading.search;

import android.content.Context;

import com.sobhy.quran.Database.shamarly.ShamarlyDatabase;
import com.sobhy.quran.pojo.quran.ShamarlyAyah;

import java.util.ArrayList;

public class ShamarlySearchViewModel {
    public static ArrayList<ShamarlyAyah> getAyatContain(Context context, String keyword){
        return (ArrayList<ShamarlyAyah>) ShamarlyDatabase.getInstance(context).shamarlyDao().getShamarlyAyatByText(keyword);
    }
    public static ArrayList<ShamarlyAyah> getAyatStartWith(Context context, String keyword){
        return (ArrayList<ShamarlyAyah>) ShamarlyDatabase.getInstance(context).shamarlyDao().getShamarlyAyatStartWith(keyword);
    }
    public static ArrayList<ShamarlyAyah> getAyatEndsWith(Context context, String keyword){
        return (ArrayList<ShamarlyAyah>) ShamarlyDatabase.getInstance(context).shamarlyDao().getShamarlyAyatEndWith(keyword);
    }

    public static ArrayList<ShamarlyAyah> getAyatContainWithFormation(Context context, String keyword){
        return (ArrayList<ShamarlyAyah>) ShamarlyDatabase.getInstance(context).shamarlyDao().getShamarlyAyatByTextWithFormation(keyword);
    }
    public static ArrayList<ShamarlyAyah> getAyatStartWithWithFormation(Context context, String keyword){
        return (ArrayList<ShamarlyAyah>) ShamarlyDatabase.getInstance(context).shamarlyDao().getShamarlyAyatStartWithWithFormation(keyword);
    }
    public static ArrayList<ShamarlyAyah> getAyatEndsWithWithFormation(Context context, String keyword){
        return (ArrayList<ShamarlyAyah>) ShamarlyDatabase.getInstance(context).shamarlyDao().getShamarlyAyatEndWithWithFormation(keyword);
    }
}
