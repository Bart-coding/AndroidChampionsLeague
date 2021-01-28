package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends NavigationActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public void ClickHome(View view){
        recreate();
    }
}