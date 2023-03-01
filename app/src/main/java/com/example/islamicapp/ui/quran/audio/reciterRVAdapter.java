package com.example.islamicapp.ui.quran.audio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.R;
import com.example.islamicapp.pojo.quran.ReciterEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class reciterRVAdapter extends RecyclerView.Adapter<reciterRVAdapter.ReciterViewHolder> {
    List<ReciterEntity> ReciterArrayList = new ArrayList<>();



    public int getPositionForSection(String letter) {
        for (int i = 0; i < ReciterArrayList.size(); i++) {
            if (ReciterArrayList.get(i).getLetter().equals(letter)) {
                return i;
            }
        }
        return 0;
    }

    public String getLetterForPosition(int position){
        return ReciterArrayList.get(position).getLetter();
    }


    @NonNull
    @Override
    public ReciterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reciter, parent, false);
        return new ReciterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReciterViewHolder holder, int position) {
        ReciterEntity Reciter = ReciterArrayList.get(position);
        holder.textViewName.setText(Reciter.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent= new Intent(v.getContext(), ListOfSuras.class);
            intent.putExtra("reciterId", Reciter.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ReciterArrayList != null? ReciterArrayList.size() : 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<ReciterEntity> ReciterArrayList) {
        this.ReciterArrayList = ReciterArrayList;
        notifyDataSetChanged();
    }


    static class ReciterViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;

        public ReciterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.item_reciter_name);
        }
    }
}