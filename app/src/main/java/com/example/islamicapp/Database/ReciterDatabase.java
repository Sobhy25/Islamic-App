package com.example.islamicapp.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.islamicapp.pojo.MoshafEntity;
import com.example.islamicapp.pojo.ReciterEntity;

@androidx.room.Database(entities = {MoshafEntity.class, ReciterEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class ReciterDatabase extends RoomDatabase {
    public abstract ReciterDao dao();

    private static ReciterDatabase INSTANCE;
    public static synchronized ReciterDatabase getInstance(Context context){
        if(INSTANCE== null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), ReciterDatabase.class, "reciter_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}