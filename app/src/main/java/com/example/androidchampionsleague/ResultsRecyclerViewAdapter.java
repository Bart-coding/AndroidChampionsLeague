package com.example.androidchampionsleague;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Stage> mStages;
    private Context mContext;

    public ResultsRecyclerViewAdapter(Context context, ArrayList<Stage> stages ) {
        mStages = stages;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.stageName.setText(mStages.get(position).getName());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(position == 0){
                    intent = new Intent(mContext, GroupMatchResultActivity.class);
                }
                else{
                    intent = new Intent(mContext, KnockoutMatchResultActivity.class);
                }
                intent.putExtra("stage",mStages.get(position).getHeader());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView stageName;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            stageName = itemView.findViewById(R.id.stage_name);
            parentLayout = itemView.findViewById(R.id.parent_layout_results);
        }
    }
}

