package com.sobhy.quran.ui.reading.search;

import android.content.Context;


import com.sobhy.quran.Database.QuranDatabase;
import com.sobhy.quran.pojo.quran.Ayah;

import java.util.ArrayList;

public class SearchResultViewModel {

    public static ArrayList<Ayah> getAyatContain(Context context, String keyword){
        return (ArrayList<Ayah>) QuranDatabase.getINSTANCE(context).quranDao().getAyatByText(keyword);
    }
    public static ArrayList<Ayah> getAyatStartWith(Context context, String keyword){
        return (ArrayList<Ayah>) QuranDatabase.getINSTANCE(context).quranDao().getAyatStartWith(keyword);
    }
    public static ArrayList<Ayah> getAyatEndsWith(Context context, String keyword){
        return (ArrayList<Ayah>) QuranDatabase.getINSTANCE(context).quranDao().getAyatEndWith(keyword);
    }

    public static ArrayList<Ayah> getAyatContainWithFormation(Context context, String keyword){
        return (ArrayList<Ayah>) QuranDatabase.getINSTANCE(context).quranDao().getAyatByTextWithFormation(keyword);
    }
    public static ArrayList<Ayah> getAyatStartWithWithFormation(Context context, String keyword){
        return (ArrayList<Ayah>) QuranDatabase.getINSTANCE(context).quranDao().getAyatStartWithWithFormation(keyword);
    }
    public static ArrayList<Ayah> getAyatEndsWithWithFormation(Context context, String keyword){
        return (ArrayList<Ayah>) QuranDatabase.getINSTANCE(context).quranDao().getAyatEndWithWithFormation(keyword);
    }

}
