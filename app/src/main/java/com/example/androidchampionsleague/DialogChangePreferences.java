package com.example.androidchampionsleague;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DialogChangePreferences extends DialogFragment {

    ArrayList<Team> teams = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_preferences,container,false);

        // tymczasowo dla testów
        Team t1 = new Team();
        t1.setName("Borussia Dortmund");
        t1.setLogoUrl("https://crests.football-data.org/4.svg");
        Team t2 = new Team();
        t2.setName("FC Bayern München");
        t2.setLogoUrl("https://crests.football-data.org/5.svg");
        Team t3 = new Team();
        t3.setName("Chelsea FC");
        t3.setLogoUrl("https://crests.football-data.org/61.svg");
        teams.add(t1);
        teams.add(t2);
        teams.add(t3);

        // init RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_dialog_preferences);
        PreferencesRecyclerViewAdapter adapter = new PreferencesRecyclerViewAdapter(getActivity(),this, teams);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}