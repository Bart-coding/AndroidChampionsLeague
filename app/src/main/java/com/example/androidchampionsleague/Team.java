package com.example.androidchampionsleague;

import android.graphics.Bitmap;

public class Team {
    private int Id;
    private String Name;
    private int Points;
    private String LogoUrl;
    private Integer Position; //nullable; mozna bdz zrezygnowac

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public Integer getPosition() { return Position; }


    public void setPosition(Integer position) {
        Position = position;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public String getLogoUrl() {
        return LogoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        LogoUrl = logoUrl;
    }
}
