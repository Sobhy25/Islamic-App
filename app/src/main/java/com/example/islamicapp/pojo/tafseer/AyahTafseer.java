package com.example.islamicapp.pojo.tafseer;

public class AyahTafseer {
    int tafseer_id;
    String tafseer_name;
    String ayah_url;
    int ayah_number;
    String text;

    public AyahTafseer() {
    }

    public AyahTafseer(int tafseer_id, String tafseer_name, String ayah_url, int ayah_number, String text) {
        this.tafseer_id = tafseer_id;
        this.tafseer_name = tafseer_name;
        this.ayah_url = ayah_url;
        this.ayah_number = ayah_number;
        this.text = text;
    }

    public int getTafseer_id() {
        return tafseer_id;
    }

    public void setTafseer_id(int tafseer_id) {
        this.tafseer_id = tafseer_id;
    }

    public String getTafseer_name() {
        return tafseer_name;
    }

    public void setTafseer_name(String tafseer_name) {
        this.tafseer_name = tafseer_name;
    }

    public String getAyah_url() {
        return ayah_url;
    }

    public void setAyah_url(String ayah_url) {
        this.ayah_url = ayah_url;
    }

    public int getAyah_number() {
        return ayah_number;
    }

    public void setAyah_number(int ayah_number) {
        this.ayah_number = ayah_number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
