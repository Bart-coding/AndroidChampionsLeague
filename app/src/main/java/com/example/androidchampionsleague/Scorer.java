package com.example.androidchampionsleague;

public class Scorer {
    private String Name;
    private int Goals;
    private String Team;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public int getGoals() {
        return Goals;
    }
    public void setGoals(int goals) {
        Goals = goals;
    }

    public String getTeam() {
        return Team;
    }
    public void setTeam(String team) {
        Team = team;
    }

    @Override
    public String toString() {
        return Name + " (" + Team + ") " + Goals;
    }
}
