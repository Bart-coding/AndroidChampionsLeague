package com.example.androidchampionsleague;

import java.util.Comparator;

public class SortTeamsByName implements Comparator<Team> {
    @Override
    public int compare(Team o1, Team o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
