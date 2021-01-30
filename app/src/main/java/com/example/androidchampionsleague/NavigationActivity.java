package com.example.androidchampionsleague;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class NavigationActivity extends com.example.androidchampionsleague.SensorManager{

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        CloseDrawer(drawerLayout);
        super.onPause();
    }

    public void ClickMenu(View view){
        OpenDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        CloseDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        RedirectActivity(this, MainActivity.class);
    }

    public void ClickResults(View view) {
        RedirectActivity(this, ResultsActivity.class);
    }

    public void ClickGroups(View view) {
        RedirectActivity(this, GroupsActivity.class);
    }

    public void ClickScorers(View view) {
        RedirectActivity(this, ScorersActivity.class);
    }

    public void ClickSettings(View view) {
        RedirectActivity(this, PreferencesActivity.class);
    }

    public void ClickAboutUs(View view) {
        RedirectActivity(this, AboutUsActivity.class);
    }

    public void ClickExit(View view){
        Exit(this);
    }

    public void OpenDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void CloseDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void Exit(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.exit_message);
        builder.setPositiveButton(R.string.exit_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton(R.string.exit_unconfirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void RedirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
