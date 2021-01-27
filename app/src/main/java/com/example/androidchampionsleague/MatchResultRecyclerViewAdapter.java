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

//        holder.TeamImage1.setImageBitmap(mMatches.get(position).getImage1());
//        holder.TeamImage2.setImageBitmap(mMatches.get(position).getImage2());
//        holder.TeamName1.setText(mMatches.get(position).getName1());
//        holder.TeamName2.setText(mMatches.get(position).getName2());
//        holder.TeamScore1.setText(mMatches.get(position).getScore1());
//        holder.TeamScore1.setText(mMatches.get(position).getScore2());


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

        ImageView TeamImage1;
        ImageView TeamImage2;
        TextView TeamName1;
        TextView TeamName2;
        TextView TeamScore1;
        TextView TeamScore2;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            TeamImage1 = itemView.findViewById(R.id.image_team_1);
            TeamImage2 = itemView.findViewById(R.id.image_team_2);
            TeamName1 = itemView.findViewById(R.id.team_match_name_1);
            TeamName2 = itemView.findViewById(R.id.team_match_name_2);
            TeamScore1 = itemView.findViewById(R.id.team_score_1);
            TeamScore2 = itemView.findViewById(R.id.team_score_2);
            parentLayout = itemView.findViewById(R.id.parent_layout_match_result);
        }
    }
}

