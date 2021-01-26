package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;

public class Groups extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public void ClickGroups(View view){
        recreate();
    }
}