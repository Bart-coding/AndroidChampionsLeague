package com.example.androidchampionsleague;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class KnockoutStageMatchesResultsActivity extends SensorManager {

    private String stage;
    ArrayList<Match> matchesList = new ArrayList<>(); //<Match>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_knockout_stage_matches_results);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("stage")){
            stage = getIntent().getStringExtra("stage");
        }
        Toast.makeText(getApplicationContext(), "You have Chosen "+ stage, Toast.LENGTH_LONG).show();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_knockout_stage_m_r);
        MatchResultRecyclerViewAdapter adapter = new MatchResultRecyclerViewAdapter(this, matchesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}