package com.example.androidchampionsleague;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.androidchampionsleague.RetrofitInstance.getRetrofitInstance;

public class GroupMatchResultActivity extends SensorManager implements AdapterView.OnItemSelectedListener{

    private ArrayList<Match> matchesList = new ArrayList<>();
    //private ArrayList<Match> matchesList2 = new ArrayList<>();
    String urlForTeam = "v2/teams/";
    String[] matchDays= {"Matchday 1", "Matchday 2", "Matchday 3", "Matchday 4", "Matchday 5", "Matchday 6" };
    String urlForMatchday = "v2/competitions/CL/matches?season=2019&stage=GROUP_STAGE&matchday=";
    //private RecyclerView recyclerView = findViewById(R.id.recycler_view_concrete_results);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_group_match_result);

        // Linking the Views to Java’s instances
        Spinner spin1=(Spinner) findViewById(R.id.results_spinner);

        spin1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        //spin1.setSelection(-1,false); //Spinner nie jest wybrany od razu
        // Now, Create a Array Adapter
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, matchDays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Assigning the adapter to Spinner
        spin1.setAdapter(adapter);

        initRecyclerView();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (matchesList.size()!=0) {
            matchesList.clear();
        }

        Toast.makeText(getApplicationContext(), "You have Chosen "+matchDays[position], Toast.LENGTH_LONG).show();
        //String selectedMatchDay = matchDays[position];
        Retrofit retrofit = getRetrofitInstance();
        GroupMatchdayService groupMatchdayService = retrofit.create(GroupMatchdayService.class);

        int incrementedPosition = position + 1;

        Call<Object> call = groupMatchdayService.getMatches(urlForMatchday+incrementedPosition);
        //Call<Object> call = groupMatchdayService.getMatches();

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
                    notifyAdapter();
                    return;
                }

                Toast.makeText(getApplicationContext(), "Alright", Toast.LENGTH_LONG).show();

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

                        try {
                            match.setId(matchObject.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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


                        Toast.makeText(getApplicationContext(), homeTeam.getName(), Toast.LENGTH_LONG).show();
                        match.setHomeTeam(homeTeam);
                        match.setAwayTeam(awayTeam);
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
                                            break;
                                        }
                                        Team awayTeam = match.getAwayTeam();
                                        if (awayTeam.getId() == teamJSONId) {
                                            awayTeam.setLogoUrl(teamJSONLogoUrl);
                                            awayTeam.setShortName(teamJSONShortName);
                                            break;
                                        }
                                    } //koniec pętli iterującej po liście meczy

                                    //initRecyclerView ();
                                    notifyAdapter();

                                } //koniec pętli iterującej po liście 32 drużyn z wewn. response'a


                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_concrete_results); //lub globalnie
        MatchResultRecyclerViewAdapter adapter = new MatchResultRecyclerViewAdapter(this, matchesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void notifyAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_concrete_results);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}