package com.example.androidchampionsleague;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class HomeActivity extends NavigationActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_home);
        
        drawerLayout = findViewById(R.id.drawer_layout);

        setPreferences();

        MediaPlayer ring= MediaPlayer.create(getApplicationContext(),R.raw.swiftly_610);
        ring.start();

        /*String url = "https://www.youtube.com/watch?v=BgomX3qD-iA"; // your URL here
        MediaPlayer mediaPlayer = new MediaPlayer();
        /*mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();*/




    }

    @Override
    public void ClickHome(View view){
        recreate();
    }

    private void setPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("com.example.androidchampionsleague", Context.MODE_PRIVATE);
        String teamName = sharedPref.getString("team_name","none");
        String teamImageURL = sharedPref.getString("team_image","none");
        String teamWebsite = sharedPref.getString("team_website", "none");

        TextView textViewTeam = findViewById(R.id.home_team_name);
        ImageView imageView = findViewById(R.id.home_team_image);
        if(teamName != "none" && teamImageURL != "none") {
            textViewTeam.setText(teamName);
            Utils.fetchSvg(this, teamImageURL, imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent websiteIntent = new Intent ("android.intent.action.VIEW",
                            Uri.parse(teamWebsite));
                    startActivity(websiteIntent);
                }
            });
        }
        else{
            textViewTeam.setText("");
            TextView header = findViewById(R.id.home_header);
            header.setText(R.string.go_to_preferences);
            imageView.setImageBitmap(null);
        }
    }
}