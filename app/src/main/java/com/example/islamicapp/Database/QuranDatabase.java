package com.example.islamicapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.islamicapp.pojo.quran.Ayah;

@Database(entities = {Ayah.class}, version = 1)
public abstract class QuranDatabase extends RoomDatabase {
    public abstract QuranDao quranDao();

    private static QuranDatabase INSTANCE;

    public static synchronized QuranDatabase getINSTANCE(Context context) {
        if(INSTANCE == null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), QuranDatabase.class, "QURAN_database")
                    .createFromAsset("databases/quran.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
