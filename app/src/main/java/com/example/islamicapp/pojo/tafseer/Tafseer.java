package com.example.islamicapp.pojo.tafseer;

public class Tafseer {
    int id;
    String name;
    String language;
    String author;
    String book_name;

    public Tafseer() {
    }

    public Tafseer(int id, String name, String language, String author, String book_name) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.author = author;
        this.book_name = book_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    @Override
    public String toString() {
        return name;
    }
}
