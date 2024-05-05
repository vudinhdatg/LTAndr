package com.example.sua_btl_2;

public class Song {
    private  int File;
    private  String Title;

    public Song(int file, String title) {
        File = file;
        Title = title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
