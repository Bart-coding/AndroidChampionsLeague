package com.example.androidchampionsleague;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class KnockoutMatchResultActivity extends SensorManager {

    private String stage;
    ArrayList<Match> matchesList = new ArrayList<>(); //<Match>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_knockout_match_result);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("stage")){
            stage = getIntent().getStringExtra("stage");
        }
        Toast.makeText(getApplicationContext(), "You have Chosen "+ stage, Toast.LENGTH_LONG).show();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_knockout_match_result);
        MatchResultRecyclerViewAdapter adapter = new MatchResultRecyclerViewAdapter(this, matchesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}