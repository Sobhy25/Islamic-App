package com.example.islamicapp.ui.quran.audio;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.islamicapp.R;
import com.example.islamicapp.pojo.AudioSura;
import java.io.File;
import java.util.ArrayList;

public class AudioSurasAdapter extends RecyclerView.Adapter<AudioSurasAdapter.SurasViewHolder> {
    ArrayList<AudioSura> SoraArrayList = new ArrayList<>();
    String folderName;

    @NonNull
    @Override
    public SurasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_audio_suras, parent, false);
        return new SurasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SurasViewHolder holder, int position) {
        AudioSura Sora = SoraArrayList.get(position);
        holder.suraNum.setText(String.valueOf(Sora.getSuraNumber()));
        holder.suraName.setText(Sora.getSuraName());

        holder.downloadImageView.setOnClickListener(v -> {
            folderName= Sora.getReciterName()+"/"+Sora.getMoshafName();
            File folder= new File(v.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), folderName);

            boolean success= true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }
            if (success) {
                // Folder created successfully
                // Start the download
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Sora.getSuraUrl()));
                request.setDescription("Downloading");
                request.setMimeType("audio/MP3");
                request.setTitle(Sora.getSuraName());
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalFilesDir(v.getContext(), Environment.DIRECTORY_DOWNLOADS, folderName+"/"+Sora.getSuraName()+".mp3");
                DownloadManager manager = (DownloadManager) holder.itemView.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);

            } else {
                // Failed to create folder
                Toast.makeText(v.getContext(), "Can't download file", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent= new Intent(holder.itemView.getContext(), PlayerActivity.class);
            intent.putExtra("suraList", SoraArrayList);
            intent.putExtra("position", holder.getAdapterPosition());
            holder.itemView.getContext().startActivity(intent);
        });

//        File file= new File(holder.itemView.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), folderName+"/"+Sora.getSuraName()+".mp3");
//        if(file.exists()){
//            holder.downloadImageView.setVisibility(View.GONE);
//            Sora.setSuraUrl(file.getPath());
//        }

    }

    @Override
    public int getItemCount() {
        return SoraArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<AudioSura> SoraArrayList) {
        this.SoraArrayList = SoraArrayList;
        notifyDataSetChanged();
    }

    static class SurasViewHolder extends RecyclerView.ViewHolder {
        TextView suraNum, suraName;
        ImageView downloadImageView;
        public SurasViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            ///
            //
            suraNum= itemView.findViewById(R.id.item_audio_suras_number);
            suraName= itemView.findViewById(R.id.item_audio_suras_name);
            downloadImageView= itemView.findViewById(R.id.item_audio_suras_download);

        }
    }

}