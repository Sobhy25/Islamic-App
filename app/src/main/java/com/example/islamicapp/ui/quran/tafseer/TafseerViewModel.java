package com.example.islamicapp.ui.quran.tafseer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.islamicapp.Database.tafseer.TafseerRepository;
import com.example.islamicapp.pojo.tafseer.AyahTafseer;
import com.example.islamicapp.pojo.tafseer.Tafseer;

import java.util.List;

public class TafseerViewModel extends AndroidViewModel {
    TafseerRepository tafseerRepository;
    public TafseerViewModel(@NonNull Application application) {
        super(application);
        tafseerRepository= new TafseerRepository(application);
    }

    public LiveData<List<Tafseer>> getAllTafseerFromApi(){
        return tafseerRepository.getAllTafseerFromApi();
    }

    public LiveData<AyahTafseer> getTafseerOfAyahFromApi(int tafseerId, int suraNumber, int ayahNumber){
        return tafseerRepository.getTafseerOfAyahFromApi(tafseerId, suraNumber, ayahNumber);
    }

}
