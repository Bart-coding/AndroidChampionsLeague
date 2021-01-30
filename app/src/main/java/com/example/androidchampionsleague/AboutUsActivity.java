package com.example.androidchampionsleague;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class AboutUsActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_about_us);
        drawerLayout = findViewById(R.id.drawer_layout);

        SharedPreferences sharedPref = getSharedPreferences("com.example.androidchampionsleague", Context.MODE_PRIVATE);
        sharedPref.edit().clear().commit();
    }

    @Override
    public void ClickAboutUs(View view){
        recreate();
    }
}