package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsActivity extends RootActivity {

    ListView listView;
    ArrayList<String> stagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        drawerLayout = findViewById(R.id.drawer_layout);

        //listView = findViewById(R.id.list_view);

        initStagesList();
    }

    @Override
    public void ClickResults(View view) {
        recreate();
    }

    private void initStagesList(){
        stagesList.add(getString(R.string.group_stage));
        stagesList.add(getString(R.string.quarterfinal_stage));
        stagesList.add(getString(R.string.semifinal_stage));
        stagesList.add(getString(R.string.final_stage));

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ResultsRecyclerViewAdapter adapter = new ResultsRecyclerViewAdapter(this, stagesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}