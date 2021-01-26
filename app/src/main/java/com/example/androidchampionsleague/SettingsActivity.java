package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public void ClickSettings(View view) {
        recreate();
    }
}
