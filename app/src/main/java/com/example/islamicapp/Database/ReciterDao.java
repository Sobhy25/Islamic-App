package com.example.islamicapp.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.islamicapp.pojo.MoshafEntity;
import com.example.islamicapp.pojo.ReciterEntity;

import java.util.List;

@Dao
public interface ReciterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMoshaf(MoshafEntity moshaf);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMoshafs(List<MoshafEntity> moshafEntityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReciter(ReciterEntity... reciter);

    @Insert
    void insertReciters(List<ReciterEntity> reciterEntityList);

//    @Query("select * from moshaf where reciterId= :reciterId")
//    List<MoshafEntity> getAllMoshaf(int reciterId);

    @Query("select * from reciter")
    List<ReciterEntity> getAllReciter();

    @Query("select * from reciter where id= :reciterId")
    ReciterEntity getReciterById(int reciterId);
}
