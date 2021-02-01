package com.example.androidchampionsleague;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.threeten.bp.LocalDateTime;

public class MatchDetailsActivity extends SensorManager {

    private Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_match_details);

        getIncomingIntent();

        TextView tv;
        ImageView iv;

        tv = findViewById(R.id.match_details_team_1_name);
        tv.setText(match.getHomeTeam().getName());
        tv = findViewById(R.id.match_details_team_1_score);
        tv.setText(String.valueOf(match.getHomeFullScore()));
        tv = findViewById(R.id.match_details_team_1_half_score);
        tv.setText("("+String.valueOf(match.getHomeHalfScore()));
        iv = findViewById(R.id.match_details_team_1_image);
        Utils.fetchSvg(this, match.getHomeTeam().getLogoUrl(), iv);

        tv = findViewById(R.id.match_details_team_2_name);
        tv.setText(match.getAwayTeam().getName());
        tv = findViewById(R.id.match_details_team_2_score);
        tv.setText(String.valueOf(match.getAwayFullScore()));
        tv = findViewById(R.id.match_details_team_2_half_score);
        tv.setText(String.valueOf(match.getAwayHalfScore())+")");
        iv = findViewById(R.id.match_details_team_2_image);
        Utils.fetchSvg(this, match.getAwayTeam().getLogoUrl(), iv);

        tv = findViewById(R.id.match_details_date);
        tv.setText(LocalDateTime.parse(match.getDateString()).toString().replace('T',' '));

        tv = findViewById(R.id.match_details_referee);
        tv.setText(match.getReferee());

        String groupName = match.getGroupName();
        if(groupName != null){
            tv = findViewById(R.id.match_details_group);
            tv.setText(groupName);
        }
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("match")){
            match = (Match) getIntent().getSerializableExtra("match");
        }
    }
}