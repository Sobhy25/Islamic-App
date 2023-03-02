package com.example.islamicapp.Database;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.islamicapp.pojo.quran.Ayah;
import com.example.islamicapp.pojo.quran.Jozz;
import com.example.islamicapp.pojo.quran.Sora;

import java.util.List;

@Dao
public interface QuranDao {

    @Query("select * from quran where page= :page")
    List<Ayah> getAyatByPage(int page);

    @Query("select * from quran where aya_text_emlaey like '%'||:text||'%'")
    List<Ayah> getAyatByText(String text);

    @Query("select * from quran where aya_text like '%'||:text||'%'")
    List<Ayah> getAyatByTextWithFormation(String text);

    @Query("select * from quran where aya_text_emlaey like :text||'%'")
    List<Ayah> getAyatStartWith(String text);

    @Query("select * from quran where aya_text like :text||'%'")
    List<Ayah> getAyatStartWithWithFormation(String text);

    @Query("select * from quran where aya_text_emlaey like '%'||:text")
    List<Ayah> getAyatEndWith(String text);

    @Query("select * from quran where aya_text like '%'||:text")
    List<Ayah> getAyatEndWithWithFormation(String text);

    @Query("SELECT sora as soraNumber, min(page) as startPage, max(page) as endPage,max(aya_no) as numbersOfAyat, sora_name_en as EnglishName, sora_name_ar as arabicName FROM quran WHERE sora= :soraNumber")
    Sora getSoraByNumber(int soraNumber);

    @Query("select jozz as jozzNumber, min(page) as startPage, max(page) as endPage, min(id) as ayahId, aya_text as firstAyahInJozz from quran where jozz= :jozzNumber ")
    Jozz getJozzByNumber(int jozzNumber);

    @Query("select sora as soraNumber, min(page) as startPage, max(page) as endPage,max(aya_no) as numbersOfAyat, sora_name_en as EnglishName, sora_name_ar as arabicName from quran where sora_name_ar= :soraName")
    List<Sora> getSoraByName(String soraName);

    @Query("select aya_no from quran where id= :ayahId")
    int getAyahNumberByItsId(int ayahId);
    @Query("select sora from quran where id= :ayahId")
    int getSoraNumberOfAyahByItsId(int ayahId);

    @Query("select * from quran where id= :ayahId")
    Ayah getAyahByItsId(int ayahId);

}
