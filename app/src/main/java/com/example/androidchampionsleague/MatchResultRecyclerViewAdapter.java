package com.example.androidchampionsleague;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;

public class MatchResultRecyclerViewAdapter extends RecyclerView.Adapter<MatchResultRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Match> mMatches;
    private Context mContext;

    public MatchResultRecyclerViewAdapter(Context context, ArrayList<Match> matches ) {
        mMatches = matches;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(position%2==1){
            holder.parentLayout.setBackgroundColor(Utils.getColorByThemeAttr(mContext,R.attr.HighlightedColorSecondary));
        }
        else {
            holder.parentLayout.setBackgroundColor(Utils.getColorByThemeAttr(mContext,R.attr.colorOnPrimary));
        }

        Match match = mMatches.get(position);
        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();

        Utils.fetchSvg(mContext, homeTeam.getLogoUrl(), holder.Team1Image);
        holder.Team1Name.setText(homeTeam.getShortName());
        holder.Team1Score.setText(String.valueOf(match.getHomeFullScore()));

        Utils.fetchSvg(mContext, awayTeam.getLogoUrl(), holder.Team2Image);
        holder.Team2Name.setText(awayTeam.getShortName());
        holder.Team2Score.setText(String.valueOf(match.getAwayFullScore()));


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, newClass.class);
//                intent.putExtra("match",mMatches.get(position));
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView Team1Image;
        ImageView Team2Image;

        TextView Team1Name;
        TextView Team2Name;

        TextView Team1Score;
        TextView Team2Score;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            Team1Image = itemView.findViewById(R.id.match_team_1_image);
            Team2Image = itemView.findViewById(R.id.match_team_2_image);

            Team1Name = itemView.findViewById(R.id.match_team_1_name);
            Team2Name = itemView.findViewById(R.id.match_team_2_name);

            Team1Score = itemView.findViewById(R.id.match_team_1_score);
            Team2Score = itemView.findViewById(R.id.match_team_2_score);

            parentLayout = itemView.findViewById(R.id.parent_layout_match_result);
        }
    }
}

