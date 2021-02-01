package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsActivity extends NavigationActivity {

    ListView listView;
    ArrayList<Stage> stagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_results);
        drawerLayout = findViewById(R.id.drawer_layout);

        initStagesList();
    }

    @Override
    public void ClickResults(View view) {
        recreate();
    }

    private void initStagesList(){
        stagesList.add(new Stage(getString(R.string.group_stage),"GROUP_STAGE"));
        stagesList.add(new Stage(getString(R.string.round_of_16_stage),"ROUND_OF_16"));
        stagesList.add(new Stage(getString(R.string.quarterfinal_stage),"QUARTER_FINALS"));
        stagesList.add(new Stage(getString(R.string.semifinal_stage),"SEMI_FINALS"));
        stagesList.add(new Stage(getString(R.string.final_stage),"FINAL"));
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_results);
        ResultsRecyclerViewAdapter adapter = new ResultsRecyclerViewAdapter(this, stagesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}