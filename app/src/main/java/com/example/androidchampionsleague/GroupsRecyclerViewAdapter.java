package com.example.androidchampionsleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupsRecyclerViewAdapter extends RecyclerView.Adapter<GroupsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mGroups = new ArrayList<>();
    private Context mContext;

    public GroupsRecyclerViewAdapter(Context context, ArrayList<String> groups ) {
        mGroups = groups;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        String [] groups = mGroups.get(position).getTeams();
//        int [] points = mGroups.get(position).getPoints();
//
//        holder.group.setText(mGroups.get(position).getGroup());
//        holder.team_name_1.setText(groups[0]);
//        holder.team_name_2.setText(groups[1]);
//        holder.team_name_3.setText(groups[2]);
//        holder.team_name_4.setText(groups[3]);
//        holder.team_points_1.setText(String.valueOf(points[0]));
//        holder.team_points_2.setText(String.valueOf(points[2]));
//        holder.team_points_3.setText(String.valueOf(points[3]));
//        holder.team_points_4.setText(String.valueOf(points[4]));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, mGroups.get(position).getTeam(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView group;
        TextView team_name_1;
        TextView team_name_2;
        TextView team_name_3;
        TextView team_name_4;
        TextView team_points_1;
        TextView team_points_2;
        TextView team_points_3;
        TextView team_points_4;

        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.group);
            team_name_1 = itemView.findViewById(R.id.team_name_1);
            team_name_2 = itemView.findViewById(R.id.team_name_2);
            team_name_3 = itemView.findViewById(R.id.team_name_3);
            team_name_4 = itemView.findViewById(R.id.team_name_4);
            team_points_1 = itemView.findViewById(R.id.team_points_1);
            team_points_2 = itemView.findViewById(R.id.team_points_2);
            team_points_3 = itemView.findViewById(R.id.team_points_3);
            team_points_4 = itemView.findViewById(R.id.team_points_4);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

