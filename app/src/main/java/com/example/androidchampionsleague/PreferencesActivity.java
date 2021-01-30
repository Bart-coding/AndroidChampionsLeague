package com.example.androidchampionsleague;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class PreferencesActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_preferences);

        drawerLayout = findViewById(R.id.drawer_layout);

        setPreferences();
    }

    @Override
    public void ClickSettings(View view) {
        recreate();
    }

    public void onClick(View view){
        DialogChangePreferences dialog = new DialogChangePreferences();
        dialog.show(getFragmentManager(),"dialog");
    }

    private void setPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("com.example.androidchampionsleague", Context.MODE_PRIVATE);
        String teamName = sharedPref.getString("team_name","none");
        String teamImageURL = sharedPref.getString("team_image","none");

        TextView textView = findViewById(R.id.preferences_team_name);
        if(teamName != "none" && teamImageURL != "none") {
            textView.setText(teamName);
            ImageView imageView = findViewById(R.id.preferences_team_image);
            Utils.fetchSvg(this, teamImageURL, imageView);
        }
        else{
            textView.setText(R.string.choose_team);
            Button button = findViewById(R.id.preferences_button);
            button.setText(R.string.choose);
        }
    }
}
