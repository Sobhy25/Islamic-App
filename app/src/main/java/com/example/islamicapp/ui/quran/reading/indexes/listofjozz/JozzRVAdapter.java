package com.example.islamicapp.ui.quran.reading.indexes.listofjozz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.ui.quran.reading.OnRecyclerViewItemClickListener;
import com.example.islamicapp.R;
import com.example.islamicapp.pojo.Jozz;

import java.util.ArrayList;

public class JozzRVAdapter extends RecyclerView.Adapter<JozzRVAdapter.JozzViewHolder> {
    ArrayList<Jozz> JozzArrayList = new ArrayList<>();
    OnRecyclerViewItemClickListener listener;

    public JozzRVAdapter(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public JozzViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_jozz, parent, false);
        JozzViewHolder viewHolder = new JozzViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JozzViewHolder holder, int position) {
        Jozz Jozz = JozzArrayList.get(position);
        holder.numberOfJozz.setText("جزء\n"+Integer.toString(Jozz.getJozzNumber()));
        holder.FirstAyahInJozz.setText(Jozz.getFirstAyahInJozz());
        holder.startPage.setText("صفحة: "+Integer.toString(Jozz.getStartPage()));

        holder.jozzStartPage= Jozz.getStartPage();
    }

    @Override
    public int getItemCount() {
        return JozzArrayList.size();
    }

    public void setList(ArrayList<Jozz> JozzArrayList) {
        this.JozzArrayList = JozzArrayList;
        notifyDataSetChanged();
    }

    class JozzViewHolder extends RecyclerView.ViewHolder {
        TextView numberOfJozz, FirstAyahInJozz, startPage;
        int jozzStartPage;
        public JozzViewHolder(@NonNull View itemView) {
            super(itemView);
            numberOfJozz = itemView.findViewById(R.id.item_jozz_number);
            FirstAyahInJozz = itemView.findViewById(R.id.item_jozz_name);
            startPage= itemView.findViewById(R.id.item_start_page);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(jozzStartPage);
                }
            });
        }
    }
}