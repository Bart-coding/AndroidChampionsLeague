package com.example.androidchampionsleague;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDateTime;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KnockoutStageMatchesResultsActivity extends SensorManager {

    private String stage;
    ArrayList<Match> matchesList = new ArrayList<>(); //<Match>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_knockout_stage_matches_results);

        initRecyclerView();

        getIncomingIntent();

        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        MatchesService matchesService = retrofit.create(MatchesService.class);
        Call<Object> call = matchesService.getMatches(stage, null);

        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {

                if (!response.isSuccessful()) {

                    JSONObject jsonObjectError = null;
                    try {
                        jsonObjectError = new JSONObject(response.errorBody().string());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Toast.makeText(getApplicationContext(), getString(R.string.error) + ": " + jsonObjectError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //notifyAdapter();
                    return;
                }

                Log.e("TAG", "response: " + new Gson().toJson(response.body()));

                String obj = new Gson().toJson((response.body()));
                if (obj != null) {

                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(obj);//<--
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONArray matches = null;
                    try {
                        matches = jsonObj.getJSONArray("matches");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i<matches.length(); i++){
                        JSONObject matchObject = null;
                        try {
                            matchObject = matches.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Match match = new Match();

                        // get Id
                        try {
                            match.setId(matchObject.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // get Date
                        try{
                            String DateString = matchObject.getString("utcDate");
                            DateString = DateString.substring(0, DateString.length() - 1);
                            match.setDate(LocalDateTime.parse(DateString));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // get group
                        try {
                            match.setGroupName(matchObject.getString("group"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JSONObject scores = null;
                        try {
                            scores = matchObject.getJSONObject("score");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject fullTimeScores = null;
                        try {
                            fullTimeScores = scores.getJSONObject("fullTime");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject halfTimeScores = null;
                        try {
                            halfTimeScores = scores.getJSONObject("halfTime");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Setting ScoresData to Match: HomeTeam
                        try {
                            match.setHomeFullScore(fullTimeScores.getInt("homeTeam"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            match.setHomeHalfScore(halfTimeScores.getInt("homeTeam"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Setting ScoresData to Match: AwayTeam
                        try {
                            match.setAwayFullScore(fullTimeScores.getInt("awayTeam"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            match.setAwayHalfScore(halfTimeScores.getInt("awayTeam"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Pobieranie informacji o drużynach
                        JSONObject homeTeamObject = null; //firstTeam
                        try {
                            homeTeamObject = matchObject.getJSONObject("homeTeam");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Team homeTeam = new Team();

                        try {
                            homeTeam.setId(homeTeamObject.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            homeTeam.setName(homeTeamObject.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JSONObject awayTeamObject = null; //secondTeam
                        try {
                            awayTeamObject = matchObject.getJSONObject("awayTeam");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Team awayTeam = new Team();

                        try {
                            awayTeam.setId(awayTeamObject.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            awayTeam.setName(awayTeamObject.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        match.setHomeTeam(homeTeam);
                        match.setAwayTeam(awayTeam);

                        // get referee
                        try{
                            match.setReferee(matchObject.getJSONArray("referees").getJSONObject(0).getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        matchesList.add(match); //dodanie meczu do listy
                    }//koniec pętli

                    TeamsListService teamsListService = retrofit.create(TeamsListService.class);
                    Call<Object> callForTeams = teamsListService.getTeams();

                    callForTeams.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if (!response.isSuccessful()) {

                                JSONObject jsonObjectError = null;
                                try {
                                    jsonObjectError = new JSONObject(response.errorBody().string());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    Toast.makeText(getApplicationContext(), getString(R.string.error) + ": " + jsonObjectError.getString("message"), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                return;
                            }
                            String obj = new Gson().toJson(response.body());
                            if (obj!=null) {
                                JSONObject objJSON = null;
                                try {
                                    objJSON = new JSONObject(obj);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                JSONArray teamsJSON = null;
                                try {
                                    teamsJSON = objJSON.getJSONArray("teams");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                for (int j = 0; j<teamsJSON.length(); j++){ //iteracja po 32 drużynach z response'a
                                    JSONObject teamJSON = null;
                                    try {
                                        teamJSON = teamsJSON.getJSONObject(j);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    int teamJSONId = 0;
                                    try {
                                        teamJSONId  = teamJSON.getInt("id"); //pozyskanie id drużyny
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    String teamJSONLogoUrl = null;
                                    try {
                                        teamJSONLogoUrl = teamJSON.getString("crestUrl"); //pozyskanie url loga drużyny
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    String teamJSONShortName = null;
                                    try {
                                        teamJSONShortName = teamJSON.getString("tla");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    for (int k = 0; k<matchesList.size(); k++) { //iteracja po liście meczy
                                        Match match = matchesList.get(k);
                                        Team homeTeam = match.getHomeTeam();
                                        if (homeTeam.getId() == teamJSONId) {
                                            homeTeam.setLogoUrl(teamJSONLogoUrl);
                                            homeTeam.setShortName(teamJSONShortName);
                                            //break;
                                        }
                                        Team awayTeam = match.getAwayTeam();
                                        if (awayTeam.getId() == teamJSONId) {
                                            awayTeam.setLogoUrl(teamJSONLogoUrl);
                                            awayTeam.setShortName(teamJSONShortName);
                                            //break;
                                        }
                                    } //koniec pętli iterującej po liście meczy


                                    notifyAdapter();

                                } //koniec pętli iterującej po liście 32 drużyn z wewn. response'a


                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getApplicationContext(), getString(R.string.error) + ": " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.error) + ": " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("stage")){
            stage = getIntent().getStringExtra("stage");
        }
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_knockout_stage_m_r);
        MatchResultRecyclerViewAdapter adapter = new MatchResultRecyclerViewAdapter(this, matchesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void notifyAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_knockout_stage_m_r);
        recyclerView.getAdapter().notifyDataSetChanged();
    }


}