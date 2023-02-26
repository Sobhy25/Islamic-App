package com.example.islamicapp.ui.quran.audio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamicapp.R;
import com.example.islamicapp.pojo.ReciterEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecitersFragment extends Fragment {

    ReciterViewModel reciterViewModel;
    RecyclerView reciterRecyclerView;
    RecyclerView letterRecyclerView;
    reciterRVAdapter reciterAdapter;
    LetterAdapter letterAdapter;

    public RecitersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reciters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reciterRecyclerView = view.findViewById(R.id.fragment_list_reciters_recycler_view);
        letterRecyclerView = view.findViewById(R.id.fragment_list_reciters_recyclerview_letter);
        reciterAdapter = new reciterRVAdapter();
        letterAdapter= new LetterAdapter();
        reciterRecyclerView.setAdapter(reciterAdapter);
        letterRecyclerView.setAdapter(letterAdapter);
        reciterViewModel= new ViewModelProvider(this).get(ReciterViewModel.class);

//        Thread thread= new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<ReciterEntity> reciterEntityArrayList= reciterViewModel.getRecitersFromDb(getContext());
//
//                reciterAdapter.setList(reciterEntityArrayList);
//
//                Set<String> firstLettersSet= new HashSet<>();
//                for (ReciterEntity reciter: reciterEntityArrayList) {
//                    firstLettersSet.add(reciter.getLetter());
//                }
//                ArrayList<String> firstLettersList = new ArrayList<>(firstLettersSet);
//                Collections.sort(firstLettersList);
//                letterAdapter.setList(firstLettersList);
//            }
//        });
//        thread.start();




        reciterViewModel.getRecitersFromApi(getContext()).observe(getViewLifecycleOwner(), reciters -> {
            reciterAdapter.setList(reciters);

//            new SaveDataTask().execute(reciters);

            Set<String> firstLettersSet= new HashSet<>();
            for (ReciterEntity reciter: reciters) {
                firstLettersSet.add(reciter.getLetter());
            }
            ArrayList<String> firstLettersList = new ArrayList<>(firstLettersSet);
            Collections.sort(firstLettersList);
            letterAdapter.setList(firstLettersList);
        });

        letterAdapter.setListener(letter -> {
            int position = reciterAdapter.getPositionForSection(letter);
            reciterRecyclerView.scrollToPosition(position);
        });

    }


//    private class SaveDataTask extends AsyncTask<List<Reciter>, Void, Void> {
//
//        @Override
//        protected Void doInBackground(List<Reciter>... reciters) {
//
//            // convert API models to Room Entities
//            List<ReciterEntity> roomReciters= new ArrayList<>();
//            for(Reciter reciter: reciters[0]){
//                List<Moshaf> apiMoshaf= reciter.getMoshaf();
//                List<MoshafEntity> roomMoshafList= new ArrayList<>();
//                for (Moshaf moshaf: apiMoshaf){
//                    MoshafEntity roomMoshaf= new MoshafEntity(moshaf.getId(), moshaf.getName(), moshaf.getServer(), moshaf.getSurah_total(), moshaf.getSurah_list(), reciter.getId());
//                    roomMoshafList.add(roomMoshaf);
//                    ReciterDatabase.getInstance(getContext()).dao().insertMoshaf(roomMoshaf);
//                }
//                ReciterEntity roomReciter= new ReciterEntity(reciter.getId(), reciter.getName(), reciter.getLetter(), roomMoshafList);
//                roomReciters.add(roomReciter);
//            }
//            ReciterDatabase.getInstance(getContext()).dao().insertReciters(roomReciters);
//
//            return null;
//        }
//    }
}
