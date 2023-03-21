package com.sobhy.quran.ui.tafseer;

import android.content.Context;

import com.sobhy.quran.Database.tafaseer.TafaseerDao;
import com.sobhy.quran.Database.tafaseer.TafaseerDatabase;
import com.sobhy.quran.pojo.tafaseer.TafseerName;

import java.util.ArrayList;
import java.util.List;

public class TafaseerViewModel {
    TafaseerDao tafaseerDao;

    public TafaseerViewModel(Context context) {
        this.tafaseerDao = TafaseerDatabase.getInstance(context).tafaseerDao();
    }

    TafseerName getNameOfTafseer(int id){
        return tafaseerDao.getNameOfTafseer(id);
    }
    List<TafseerName> getNamesOfAllTafaseer(){
        return tafaseerDao.getNamesOfAllTafseer();
    }
    List<String> getAllTafaseerOfAyah(int ayahId){
        List<String> tafaseer= new ArrayList<>();
        if(tafaseerDao.getMa3anyOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getMa3anyOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد معاني");
        if(tafaseerDao.getMuyassarTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getMuyassarTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getTabaryTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getTabaryTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getIbnKatherTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getIbnKatherTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getSa3dyTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getSa3dyTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getQortobyTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getQortobyTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getBaghawyTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getBaghawyTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getTanweerTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getTanweerTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getWaseetTafseerOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getWaseetTafseerOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");
        if(tafaseerDao.getE3rabOfAyah(ayahId)!= null)
            tafaseer.add(tafaseerDao.getE3rabOfAyah(ayahId));
        else
            tafaseer.add("لا يوجد تفسير");

        return tafaseer;
    }
}
