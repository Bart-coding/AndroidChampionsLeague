package com.example.androidchampionsleague;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.androidchampionsleague.RetrofitInstance.getRetrofitInstance;

public class ScorersActivity extends RootActivity{

    ListView listView;

   ArrayList<Scorer> scorersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorers);
        drawerLayout = findViewById(R.id.drawer_layout);

        listView = findViewById(R.id.ListView); // lista strzelcow w widoku

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
                            team = s.getJSONObject("team");
                            item.setName(player.getString("name"));
                            item.setTeam(team.getString("name"));
                            item.setGoals(s.getInt("numberOfGoals"));
//                            item=player.getString("name") + " (";
//                            item+=team.getString("name") + ") ";
//                            item+="Goals: " + s.getString("numberOfGoals");
                            scorersList.add(item);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                setListView();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                return;
            }
        });
    }

    private void setListView() {
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                scorersList
        );
        listView.setAdapter(adapter);
    }

    @Override
    public void ClickScorers(View view){
        recreate();
    }
}