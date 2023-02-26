package com.example.islamicapp.ui.quran.audio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamicapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecitersDownloadedFragment extends Fragment {

    RecyclerView reciterDownloadRecyclerView;
    ReciterDownloadAdapter adapter;
    public RecitersDownloadedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reciters_downloaded, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reciterDownloadRecyclerView= view.findViewById(R.id.fragment_reciters_download_recycler);
        adapter= new ReciterDownloadAdapter();
        reciterDownloadRecyclerView.setAdapter(adapter);

        adapter.setList((ArrayList<String>) getDownloadedReciter());

    }

    private List<String> getDownloadedReciter(){
        List<String> reciters= new ArrayList<>();
        File[] recitersFolder= getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).listFiles(File::isDirectory);
        if(recitersFolder != null){
            for(File reciterFolder: recitersFolder){
                reciters.add(reciterFolder.getName());
            }
        }
        return reciters;
    }
}