package com.example.islamicapp.ui.quran.reading.search;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.ui.quran.reading.OnRecyclerViewItemClickListener;
import com.example.islamicapp.R;
import com.example.islamicapp.pojo.quran.Ayah;
import com.example.islamicapp.ui.quran.tafseer.TafseerOfAyah;

import java.util.ArrayList;

public class QuranSearchRVAdapter extends RecyclerView.Adapter<QuranSearchRVAdapter.quranSearchViewHolder> {
    ArrayList<Ayah> AyahArrayList = new ArrayList<>();
    OnRecyclerViewItemClickListener listener;

    ButtonClick click;

    public QuranSearchRVAdapter(OnRecyclerViewItemClickListener listener, ButtonClick click) {
        this.listener = listener;
        this.click= click;
    }

    @NonNull
    @Override
    public quranSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quran_search_item_view, parent, false);
        quranSearchViewHolder viewHolder = new quranSearchViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull quranSearchViewHolder holder, int position) {
        Ayah Ayah = AyahArrayList.get(position);
        holder.soraName.setText(Ayah.getSora_name_ar());
        holder.details.setText("الآية:"+Ayah.getAya_no()+"، صفحة:"+ Ayah.getPage());
        holder.text.setText(Ayah.getAya_text());


        holder.page= Ayah.getPage();
        holder.ayahId= Ayah.getId();
    }

    @Override
    public int getItemCount() {
        return AyahArrayList.size();
    }

    public void setList(ArrayList<Ayah> AyahArrayList) {
        this.AyahArrayList = AyahArrayList;
        notifyDataSetChanged();
    }

    class quranSearchViewHolder extends RecyclerView.ViewHolder {
        TextView soraName, details, text;
        Button moveButton, tafseerButton;
        int page, ayahId;
        public quranSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            soraName= itemView.findViewById(R.id.search_sora_name);
            details= itemView.findViewById(R.id.search_sora_detals);
            text= itemView.findViewById(R.id.search_ayah_text);
            moveButton= itemView.findViewById(R.id.search_btn_move);
            tafseerButton= itemView.findViewById(R.id.search_btn_tafseer);

           moveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(page);
                }
            });

           tafseerButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   click.tafseerBtnClick(ayahId);
               }
           });
        }
    }

    interface ButtonClick{
        void tafseerBtnClick(int ayahId);
    }
}