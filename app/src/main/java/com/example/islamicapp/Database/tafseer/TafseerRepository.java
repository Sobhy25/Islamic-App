package com.example.islamicapp.Database.tafseer;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.islamicapp.pojo.tafseer.AyahTafseer;
import com.example.islamicapp.pojo.tafseer.Tafseer;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TafseerRepository {
    MutableLiveData<List<Tafseer>> tafseerListMutableLiveData= new MutableLiveData<>();
    MutableLiveData<AyahTafseer> ayahTafseerMutableLiveData= new MutableLiveData<>();
    Context context;
    public TafseerRepository(Context context){
        this.context= context;
    }
    public LiveData<List<Tafseer>> getAllTafseerFromApi(){
        TafseerClint.getInstance().getallTafseer().enqueue(new Callback<List<Tafseer>>() {
            @Override
            public void onResponse(Call<List<Tafseer>> call, Response<List<Tafseer>> response) {
                if(response.isSuccessful()){
                    tafseerListMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Tafseer>> call, Throwable t) {
            }
        });
        return tafseerListMutableLiveData;
    }
    public LiveData<AyahTafseer> getTafseerOfAyahFromApi(int tafseerId, int soraNumber, int ayahNumber){
        Call<AyahTafseer> call= TafseerClint.getInstance().getTafseerForAyah(tafseerId, soraNumber, ayahNumber);
        call.enqueue(new Callback<AyahTafseer>() {
            @Override
            public void onResponse(Call<AyahTafseer> call, Response<AyahTafseer> response) {
                ayahTafseerMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AyahTafseer> call, Throwable t) {

            }
        });
        return ayahTafseerMutableLiveData;
    }
}
