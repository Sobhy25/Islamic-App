package com.example.islamicapp.ui.quran.audio;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.islamicapp.Database.ReciterRepository;
import com.example.islamicapp.pojo.ReciterEntity;

import java.util.List;

public class ReciterViewModel extends ViewModel {

    public LiveData<List<ReciterEntity>> getRecitersFromApi(Context context) {
        return new ReciterRepository(context).getReciterFromApi();
    }


    public static ReciterEntity getReciter(int reciterId){
        return ReciterRepository.getReciter(reciterId);
    }

    public List<ReciterEntity> getRecitersFromDb(Context context) {
        return new ReciterRepository(context).getRecitersFromDb();
    }
}
