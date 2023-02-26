package com.example.islamicapp.ui.azkar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.R;
import com.example.islamicapp.pojo.Zekr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AzkarTypeAdapter extends RecyclerView.Adapter<AzkarTypeAdapter.AzkarViewHolder> {
    List<String> typeArrayList = new ArrayList<>();

    @NonNull
    @Override
    public AzkarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_azkartype, parent, false);
        AzkarViewHolder viewHolder = new AzkarViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AzkarViewHolder holder, int position) {
        String Zekr = typeArrayList.get(position);
        holder.typeTextView.setText(Zekr);
    }

    @Override
    public int getItemCount() {
        return typeArrayList.size();
    }

    public void setList(HashSet<String> ZekrArrayList) {
        this.typeArrayList = new ArrayList<>(ZekrArrayList);
        notifyDataSetChanged();
    }

    class AzkarViewHolder extends RecyclerView.ViewHolder {
        TextView typeTextView;
        public AzkarViewHolder(@NonNull View itemView) {
            super(itemView);
            typeTextView= itemView.findViewById(R.id.item_type_tv);
        }
    }
}