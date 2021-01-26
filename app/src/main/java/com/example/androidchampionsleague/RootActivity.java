package com.example.androidchampionsleague;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import static android.hardware.Sensor.TYPE_LIGHT;

public class RootActivity extends AppCompatActivity {

    // zmienne od light sensor
    protected SensorManager sensorManager;
    protected Sensor lightSensor;
    protected SensorEventListener lightEventListener;
    protected float maxValue;
    // end

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // testowanie light sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);

        if(lightSensor == null){
            Toast.makeText(this, "No Light sensor", Toast.LENGTH_SHORT).show();
            finish();
        }
        // pobieramy maksymalna wartosc natezenia swiatla jaka jest w stanie zmierzyc nasze urzadzenie
        maxValue = lightSensor.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                if(value >= maxValue / 2 ) {
                    // ustawiamy jasny motyw
                }
                else {
                    // ustawiamy ciemny motyw
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume(){
        super.onResume();
        // wlaczamy ponownie listener
        sensorManager.registerListener(lightEventListener,lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause(){
        super.onPause();
        CloseDrawer(drawerLayout);
        // wylaczamy listener, zeby nie zzerac zasobow
        sensorManager.unregisterListener(lightEventListener);
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
        RedirectActivity(this, SettingsActivity.class);
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
