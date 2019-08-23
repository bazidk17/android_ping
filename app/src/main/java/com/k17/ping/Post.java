package com.k17.ping;

import android.graphics.Bitmap;

/**
 * Created by bazid on 18-03-2018.
 */

public class Post {
    private int id;
    private String text;
    private String date;
    private String time;
    private Bitmap image;



    public Post(int id, String text, String date, String time,Bitmap image) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.time = time;
        this.image=image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
