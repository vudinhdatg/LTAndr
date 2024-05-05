package com.example.sua_btl_2;

public class Song_list {
    private String title;
    private int  avt;

    public Song_list(String title, int avt) {
        this.title = title;
        this.avt = avt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvt() {
        return avt;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }
}
