package com.sobhy.quran.ui.tafseer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sobhy.quran.R;
import com.sobhy.quran.pojo.tafaseer.TafseerName;

import java.util.ArrayList;

public class TafseerAdapter extends RecyclerView.Adapter<TafseerAdapter.TafseerViewHolder> {
    ArrayList<String> tafseerEntityArrayList = new ArrayList<>();
    ArrayList<TafseerName> tafseerNameEntities = new ArrayList<>();

    @NonNull
    @Override
    public TafseerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tafseer, parent, false);
        TafseerViewHolder viewHolder = new TafseerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TafseerViewHolder holder, int position) {
        TafseerName names= tafseerNameEntities.get(position);
        String TafseerEntity = tafseerEntityArrayList.get(position);
        holder.tafseerName.setText(names.getName());
        holder.tafseerText.setText(TafseerEntity);

        holder.tafseerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tafseerText.getVisibility() == View.GONE){
                    holder.tafseerText.setVisibility(View.VISIBLE);
                } else {
                    holder.tafseerText.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tafseerEntityArrayList.size();
    }

    public void setList(ArrayList<String> tafseerEntityArrayList, ArrayList<TafseerName> tafseerNameEntityList) {
        this.tafseerEntityArrayList = tafseerEntityArrayList;
        this.tafseerNameEntities = tafseerNameEntityList;
        notifyDataSetChanged();
    }
//    public void setTafseerNameList(ArrayList<TafseerNameEntity> tafseerNameList){
//        this.tafseerNameEntities= tafseerNameList;
//        notifyDataSetChanged();
//    }

    class TafseerViewHolder extends RecyclerView.ViewHolder {
        TextView tafseerName, tafseerText;
        public TafseerViewHolder(@NonNull View itemView) {
            super(itemView);
            tafseerName= itemView.findViewById(R.id.item_tafseer_name);
            tafseerText= itemView.findViewById(R.id.item_tafseer_text);
        }
    }
}