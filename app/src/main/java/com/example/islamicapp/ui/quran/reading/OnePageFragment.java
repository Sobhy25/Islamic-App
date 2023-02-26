package com.example.islamicapp.ui.quran.reading;

import static com.example.islamicapp.ui.quran.reading.QuranContainer.toggleToolbarAndTaskbar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islamicapp.Database.QuranDatabase;
import com.example.islamicapp.R;
import com.example.islamicapp.pojo.Ayah;
import com.example.islamicapp.pojo.PagesManager;

import java.util.List;

public class OnePageFragment extends Fragment {
    private int pageNumber;
    TextView suraName, juzzNumber, pageNumberTv;
    public OnePageFragment() {
        // Required empty public constructor
    }

    public OnePageFragment(int pageNumber){
        this.pageNumber= pageNumber;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout frameLayout= view.findViewById(R.id.fragment_one_page);
        ImageView imageView = view.findViewById(R.id.single_page);
        suraName= view.findViewById(R.id.fragment_surah_name);
        juzzNumber= view.findViewById(R.id.fragment_juzz_number);
        pageNumberTv= view.findViewById(R.id.fragment_page_number);

        int page= PagesManager.getImageByPageNumber(view.getContext(), pageNumber);
        imageView.setImageResource(page);

        List<Ayah> ayahs= QuranDatabase.getINSTANCE(view.getContext()).quranDao().getAyatByPage(pageNumber);

        if(ayahs.size()!= 0){
            suraName.setText("سورة "+ayahs.get(ayahs.size()-1).getSora_name_ar());
            juzzNumber.setText("جزء "+ayahs.get(ayahs.size()-1).getJozz());
            pageNumberTv.setText(""+pageNumber);
        }



        frameLayout.setOnClickListener(v -> toggleToolbarAndTaskbar());

    }
}