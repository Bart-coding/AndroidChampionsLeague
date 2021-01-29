package com.example.androidchampionsleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchResultRecyclerViewAdapter extends RecyclerView.Adapter<MatchResultRecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mMatches;
    private Context mContext;

    public MatchResultRecyclerViewAdapter(Context context, ArrayList<String> matches ) {
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

//        holder.Team1Image.setImageBitmap(mMatches.get(position).getImage1());
//        holder.Team2Image.setImageBitmap(mMatches.get(position).getImage2());
//        holder.Team1Name.setText(mMatches.get(position).getName1());
//        holder.Team2Name.setText(mMatches.get(position).getName2());
//        holder.Team1Score.setText(mMatches.get(position).getScore1());
//        holder.Team2Score.setText(mMatches.get(position).getScore2());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, mMatches.get(position), Toast.LENGTH_SHORT).show();
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

