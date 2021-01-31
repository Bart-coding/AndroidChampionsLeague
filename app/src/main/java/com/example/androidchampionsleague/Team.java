package com.example.androidchampionsleague;

import android.graphics.Bitmap;

public class Team {
    private int Id;
    private String Name;
    private int Points; //też może być nullable lub można wynieść tablice z punktami do Group
    private String LogoUrl ="brak"; //Tymczasowo
    private Integer Position; //nullable; mozna bdz zrezygnowac
    private String Website; //nullable
    private String ShortName;

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

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }
}
