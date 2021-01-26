package com.example.androidchampionsleague;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends RootActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        drawerLayout = findViewById(R.id.drawer_layout);

        if(lightSensor == null){
            finish();
        }
        // pobieramy maksymalna wartosc natezenia swiatla jaka jest w stanie zmierzyc nasze urzadzenie
        maxValue = lightSensor.getMaximumRange();
        textView = findViewById(R.id.TextViewTest);

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                if(value >= maxValue / 3 ) {
                    // ustawiamy jasny motyw
                    textView.setBackgroundColor(Color.WHITE);
                }
                else {
                    // ustawiamy ciemny motyw
                    textView.setBackgroundColor(Color.GRAY);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    public void ClickHome(View view){
        recreate();
    }
}