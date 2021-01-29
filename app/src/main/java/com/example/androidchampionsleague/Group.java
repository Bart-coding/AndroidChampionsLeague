package com.example.androidchampionsleague;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Group {
    private String Name;
    private ArrayList<Team> teams = new ArrayList<>(); //można dodać sortowanie po pozycjach na wypadek gdyby w response były jakimś cudem nieposortowane

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public Team getTeam(int position) {
        return teams.get(position-1);//
    }

    /*TO-DO*/
    public String[] getTeams() {
    String[] teamsNames = new String[4];
        for (int i = 0; i<teams.size(); i++) {
            teamsNames[i] = ((Team) teams.get(i)).getName();
        }
    return teamsNames;
    }

    public int[] getPoints() {
        int[] teamsPoints = new int[4];
        for (int i = 0; i<teams.size(); i++) {
            teamsPoints[i] = ((Team) teams.get(i)).getPoints();
        }
        return teamsPoints;
    }

    public String[] getLogoUrls() {

        String[] teamsLogoUrls = new String[4];
        for (int i = 0; i<teams.size(); i++) {
            teamsLogoUrls[i] = ((Team) teams.get(i)).getLogoUrl();
        }
        return teamsLogoUrls;
    }
}
