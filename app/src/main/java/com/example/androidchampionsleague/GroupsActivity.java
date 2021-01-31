package com.example.androidchampionsleague;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

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

public class GroupsActivity extends NavigationActivity {

    private ArrayList<Group> groupsList = new ArrayList<>(); //wczesniej <String>
    //private ArrayList<String> groupsNames = new ArrayList<>();
    final String groupsNames[] = {"A", "B", "C", "D", "E", "F"}; //albo po kolei a potem posortuje sie literami; podobnie z kolejnoscia w tabeli

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TryChangeTheme();
        setContentView(R.layout.activity_groups);
        drawerLayout = findViewById(R.id.drawer_layout);

        /*groupsNames.add("A");
        groupsNames.add("B");
        groupsNames.add("C");
        groupsNames.add("D");
        groupsNames.add("E");
        groupsNames.add("F");
        groupsNames.add("G");
        groupsNames.add("H");*/

        Retrofit retrofit = getRetrofitInstance();
        GroupsService groupsService = retrofit.create(GroupsService.class);
        Call<Object> call = groupsService.getGroups();

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
                    return;
                }
                Log.e("TAG", "response: "+new Gson().toJson(response.body()) );

                String obj = new Gson().toJson((response.body()));
                if (obj!=null) {

                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(obj);//<--
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray standings = null;
                    try {
                        standings = jsonObj.getJSONArray("standings");//<--
                        Log.e("TAG", "standings: "+ standings);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < standings.length(); i++) {
                        JSONObject standingObject = null;
                        try {
                            standingObject = standings.getJSONObject(i);//<--
                            //Log.e("TAG", "scorer: "+ s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Group group = new Group();
                        //JSONObject groupName = null; //JSONElement
                        String groupName = null;
                        JSONArray table = null;

                            try {
                            //Pobranie nazwy grupy
                            group.setName(standingObject.getString("group"));//<--
                                //groupName = standingObject.getJSONObject("group");//<--
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            //Pobranie druzyn
                            try {
                                table = standingObject.getJSONArray("table"); //////<--
                                Log.e("TAG", "table: "+ table);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            for (int j = 0; j < table.length(); j++) { //iteracja po drużynach
                                JSONObject tableObject = null; //teamObject

                                try {
                                    tableObject = table.getJSONObject(j);//<--

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                }

                                Team team = new Team();
                                try {
                                    //Pobranie i ustawienie pozycji w grupie
                                    team.setPosition(tableObject.getInt("position"));//<-- //Integer
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                }

                                try {
                                    //Pobranie i ustawienie punktow danej drużynie
                                    team.setPoints(tableObject.getInt("points"));//<-- //Integer
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                JSONObject teamJObject = null;

                                try {
                                    teamJObject = tableObject.getJSONObject("team");//<--

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                }

                                //ustawienie id, nazwy i logo
                                try {
                                    team.setId(teamJObject.getInt("id"));//<--
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                }
                                try {
                                    team.setName(teamJObject.getString("name"));//<--
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                }
                                try { //Bitmapa
                                    team.setLogoUrl(teamJObject.getString("crestUrl"));//<--
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                }

                            group.addTeam(team); //Dodanie drużyny do grupy
                            } //koniec pętli wewnętrznej dla iterowania po table

                        groupsList.add(group); //Dodanie grupy do listy grup


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
        GroupsRecyclerViewAdapter adapter = new GroupsRecyclerViewAdapter(this, groupsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void ClickGroups(View view){
        recreate();
    }
}