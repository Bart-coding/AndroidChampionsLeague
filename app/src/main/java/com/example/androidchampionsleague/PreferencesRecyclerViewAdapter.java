package com.example.androidchampionsleague;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PreferencesRecyclerViewAdapter extends RecyclerView.Adapter<PreferencesRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Team> mTeams;
    private Context mContext;
    private DialogFragment mDialog;

    public PreferencesRecyclerViewAdapter(Context context, DialogFragment dialog, ArrayList<Team> teams ) {
        mTeams = teams;
        mContext = context;
        mDialog = dialog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Team team = mTeams.get(position);
        holder.name.setText(team.getName());
        Utils.fetchSvg(mContext, team.getLogoUrl(), holder.image);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = mContext.getSharedPreferences("com.example.androidchampionsleague",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                Team chosenTeam = mTeams.get(position);
                editor.putString("team_name", chosenTeam.getName());
                editor.putString("team_image", chosenTeam.getLogoUrl());
                editor.putString("team_website", chosenTeam.getWebsite());
                editor.apply();
                mDialog.dismiss();
                Intent startIntent = new Intent(mContext, PreferencesActivity.class);
                mContext.startActivity(startIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.dialog_team_1_image);
            name = itemView.findViewById(R.id.dialog_team_1_name);

            parentLayout = itemView.findViewById(R.id.parent_layout_team_item);
        }
    }
}


