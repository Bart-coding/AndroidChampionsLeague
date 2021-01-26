package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;

public class ResultsActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public void ClickResults(View view){
        recreate();
    }
}