package com.example.androidchampionsleague;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.androidchampionsleague.RetrofitInstance.getRetrofitInstance;

public class ScorersActivity extends NavigationActivity {

   ArrayList<Scorer> scorersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_scorers);
        drawerLayout = findViewById(R.id.drawer_layout);

        Retrofit retrofit = getRetrofitInstance();

        ScorersService scorersService = retrofit.create(ScorersService.class);

        Call<Object> call = scorersService.getScorers();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Log.e("TAG", "response: "+new Gson().toJson(response.body()) );

                String obj = new Gson().toJson((response.body()));
                if (obj!=null) {

                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray scorers = null;
                    try {
                        scorers = jsonObj.getJSONArray("scorers");
                        Log.e("TAG", "scorers: "+ scorers);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < scorers.length(); i++) {
                        JSONObject s = null;
                        try {
                            s = scorers.getJSONObject(i);
                            //Log.e("TAG", "scorer: "+ s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Scorer item = new Scorer();
                        JSONObject player = null;
                        JSONObject team = null;

                        try {
                            player = s.getJSONObject("player");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            team = s.getJSONObject("team");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            item.setName(player.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            item.setTeam(team.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            item.setGoals(s.getInt("numberOfGoals"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        scorersList.add(item);


                    }
                }
                initRecyclerView();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                return;
            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ScorersRecyclerViewAdapter adapter = new ScorersRecyclerViewAdapter(this, scorersList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void ClickScorers(View view){
        recreate();
    }
}