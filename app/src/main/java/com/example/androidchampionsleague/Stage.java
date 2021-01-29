package com.example.androidchampionsleague;

public class Stage {
    private String Name;
    private String Header;

    public Stage(String name, String header) {
        Name = name;
        Header = header;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getHeader() {
        return Header;
    }
    public void setHeader(String header) {
        Header = header;
    }
}
