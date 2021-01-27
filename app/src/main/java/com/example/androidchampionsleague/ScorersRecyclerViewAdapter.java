package com.example.androidchampionsleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScorersRecyclerViewAdapter extends RecyclerView.Adapter<ScorersRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Scorer> mScorers = new ArrayList<>();
    private Context mContext;

    public ScorersRecyclerViewAdapter(Context context, ArrayList<Scorer> scorers ) {
        mScorers = scorers;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scorers_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.number.setText(String.valueOf(position+1));
        holder.name.setText(mScorers.get(position).getName());
        holder.goals.setText(String.valueOf(mScorers.get(position).getGoals()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mScorers.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mScorers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        TextView name;
        TextView goals;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            name = itemView.findViewById(R.id.scorer_name);
            goals = itemView.findViewById(R.id.goals);
            parentLayout = itemView.findViewById(R.id.parent_layout_scorers);
        }
    }
}

