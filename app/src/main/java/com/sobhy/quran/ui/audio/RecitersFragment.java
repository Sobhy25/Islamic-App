package com.sobhy.quran.ui.audio;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sobhy.quran.R;
import com.sobhy.quran.pojo.quran.ReciterEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecitersFragment extends Fragment {

    Toolbar toolbar;
    ReciterViewModel reciterViewModel;
    RecyclerView reciterRecyclerView;
    RecyclerView letterRecyclerView;
    reciterRVAdapter reciterAdapter;
    LetterAdapter letterAdapter;

    ProgressBar progressBar;

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

        progressBar = view.findViewById(R.id.progressBar);

//        toolbar= view.findViewById(R.id.reciters_toolbar);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);
        reciterRecyclerView = view.findViewById(R.id.fragment_list_reciters_recycler_view);
        letterRecyclerView = view.findViewById(R.id.fragment_list_reciters_recyclerview_letter);
        reciterAdapter = new reciterRVAdapter();
        letterAdapter= new LetterAdapter();
        reciterRecyclerView.setAdapter(reciterAdapter);
        letterRecyclerView.setAdapter(letterAdapter);
        reciterViewModel= new ViewModelProvider(this).get(ReciterViewModel.class);

        if(isNetworkAvailable(getContext())){
            progressBar.setVisibility(View.VISIBLE);
            reciterViewModel.getRecitersFromApi(getContext()).observe(getViewLifecycleOwner(), reciters -> {
                reciterAdapter.setList(reciters);

                List<String> firstLettersList = reciters.stream()
                        .map(ReciterEntity::getLetter)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
                letterAdapter.setList((ArrayList<String>) firstLettersList);

                reciterRecyclerView.setVisibility(View.VISIBLE);
                letterRecyclerView.setVisibility(View.VISIBLE);

                // Set ProgressBar visibility to GONE when data is received
                progressBar.setVisibility(View.GONE);
            });

            letterAdapter.setListener(letter -> {
                int position = reciterAdapter.getPositionForSection(letter);
                reciterRecyclerView.scrollToPosition(position);
            });
            
        } else {
            new LoadRecitersFromDbAsyncTask().execute();
        }
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }
        return false;
    }

    private class LoadRecitersFromDbAsyncTask extends AsyncTask<Void, Void, List<ReciterEntity>> {

        @Override
        protected List<ReciterEntity> doInBackground(Void... voids) {
            return reciterViewModel.getRecitersFromDb(getContext());
        }

        @Override
        protected void onPostExecute(List<ReciterEntity> reciterEntityArrayList) {
            if(reciterEntityArrayList.size()!= 0){
                Set<String> firstLettersSet = new HashSet<>();
                for (ReciterEntity reciter : reciterEntityArrayList) {
                    firstLettersSet.add(reciter.getLetter());
                }
                ArrayList<String> firstLettersList = new ArrayList<>(firstLettersSet);
                Collections.sort(firstLettersList);
                reciterAdapter.setList(reciterEntityArrayList);
                letterAdapter.setList(firstLettersList);
                progressBar.setVisibility(View.GONE);
                reciterRecyclerView.setVisibility(View.VISIBLE);
                letterRecyclerView.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "لا يوجد اتصال بالإنترنت ولا يوجد قرآء في قاعدة البيانات", Toast.LENGTH_LONG).show();
            }

        }
    }

}
