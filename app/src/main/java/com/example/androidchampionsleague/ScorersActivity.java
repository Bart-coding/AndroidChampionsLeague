package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;

public class ScorersActivity extends RootActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorers);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public void ClickScorers(View view){
        recreate();
    }
}