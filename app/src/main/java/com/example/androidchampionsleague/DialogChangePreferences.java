package com.example.androidchampionsleague;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DialogChangePreferences extends DialogFragment {

    ArrayList<Team> teams = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_preferences,container,false);

        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        TeamsListService teamsListService = retrofit.create(TeamsListService.class);
        Call<Object> call = teamsListService.getTeams();

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
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.error) + ": " + jsonObjectError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                    JSONArray teamsJSON = null;
                    try {
                        teamsJSON = jsonObj.getJSONArray("teams");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i<teamsJSON.length(); i++) {
                        JSONObject teamJSON = null;
                        try {
                            teamJSON = teamsJSON.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Team team = new Team();
                        try {
                            team.setId(teamJSON.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            team.setName(teamJSON.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            team.setLogoUrl(teamJSON.getString("crestUrl"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            team.setWebsite(teamJSON.getString("website"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        teams.add(team);
                    }
                    Collections.sort(teams, new SortTeamsByName());
                    initRecyclerView(view);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                return;
            }
        });
        return view;
    }

    public void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_dialog_preferences);
        PreferencesRecyclerViewAdapter adapter = new PreferencesRecyclerViewAdapter(getActivity(),this, teams);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}