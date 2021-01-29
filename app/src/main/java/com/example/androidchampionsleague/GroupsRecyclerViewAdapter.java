package com.example.androidchampionsleague;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupsRecyclerViewAdapter extends RecyclerView.Adapter<GroupsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Group> mGroups = new ArrayList<>();
    private Context mContext;

    public GroupsRecyclerViewAdapter(Context context, ArrayList<Group> groups ) {
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
        Group group = mGroups.get(position);
        String [] teams = group.getTeams();
        int [] points = group.getPoints();
        String [] logoUrls = group.getLogoUrls();

        String groupName = group.getName();
        holder.group.setText(String.valueOf(groupName.charAt(groupName.length()-1)));

        holder.team1name.setText(teams[0]);
        holder.team1points.setText(String.valueOf(points[0]));

        Utils.fetchSvg(mContext, logoUrls[0], holder.team1image); //Mam nadzieje ze zadziala :)
        //Toast.makeText(mContext, logoUrls[0], Toast.LENGTH_SHORT).show();

        holder.team2name.setText(teams[1]);
        holder.team2points.setText(String.valueOf(points[1]));
        Utils.fetchSvg(mContext, logoUrls[1], holder.team2image);

        holder.team3name.setText(teams[2]);
        holder.team3points.setText(String.valueOf(points[2]));
        Utils.fetchSvg(mContext, logoUrls[2], holder.team3image);

        holder.team4name.setText(teams[3]);
        holder.team4points.setText(String.valueOf(points[3]));
        Utils.fetchSvg(mContext, logoUrls[3], holder.team4image);




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

        TextView team1name;
        TextView team1points;
        ImageView team1image;

        TextView team2name;
        TextView team2points;
        ImageView team2image;

        TextView team3name;
        TextView team3points;
        ImageView team3image;

        TextView team4name;
        TextView team4points;
        ImageView team4image;

        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.group_number); //groupchar

            team1name = itemView.findViewById(R.id.group_team_1_name);
            team1points = itemView.findViewById(R.id.group_team_1_points);
            team1image = itemView.findViewById(R.id.group_team_1_image);

            team2name = itemView.findViewById(R.id.group_team_2_name);
            team2points = itemView.findViewById(R.id.group_team_2_points);
            team2image = itemView.findViewById(R.id.group_team_2_image);

            team3name = itemView.findViewById(R.id.group_team_3_name);
            team3points = itemView.findViewById(R.id.group_team_3_points);
            team3image = itemView.findViewById(R.id.group_team_3_image);

            team4name = itemView.findViewById(R.id.group_team_4_name);
            team4points = itemView.findViewById(R.id.group_team_4_points);
            team4image = itemView.findViewById(R.id.group_team_4_image);

            parentLayout = itemView.findViewById(R.id.parent_layout_groups);
        }
    }
}

