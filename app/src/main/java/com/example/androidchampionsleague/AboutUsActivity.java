package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;

public class AboutUsActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public void ClickAboutUs(View view){
        recreate();
    }
}