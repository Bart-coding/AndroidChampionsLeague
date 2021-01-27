package com.example.androidchampionsleague;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Retrofit;

import static com.example.androidchampionsleague.RetrofitInstance.getRetrofitInstance;

public class GroupsActivity extends RootActivity {

    private ArrayList<String> groupsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        drawerLayout = findViewById(R.id.drawer_layout);

        Retrofit retrofit = getRetrofitInstance();
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