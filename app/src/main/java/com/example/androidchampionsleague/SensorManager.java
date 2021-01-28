package com.example.androidchampionsleague;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.hardware.Sensor.TYPE_LIGHT;

public class SensorManager extends AppCompatActivity {

    // zmienne od light sensor
    protected android.hardware.SensorManager sensorManager;
    protected Sensor lightSensor;
    protected SensorEventListener lightEventListener;
    protected float maxValue;
    protected float currentValue;
    // end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // pobieranie light sensor z sytemu
        sensorManager = (android.hardware.SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);

        // jezeli telefon nie ma sensora
        if(lightSensor == null){
            Toast.makeText(this, "No Light sensor", Toast.LENGTH_SHORT).show();
            finish();
        }

        // pobieramy maksymalna wartosc natezenia swiatla jaka jest w stanie zmierzyc nasze urzadzenie
        maxValue = lightSensor.getMaximumRange();

        // metoda nasluchujaca zmiane wartosci natezenia swiatla
        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                currentValue = sensorEvent.values[0];
                switch (ThemeToChange()){
                    case 1:
                        ChangeThemeToLight();
                        break;
                    case 2:
                        ChangeThemeToDark();
                        break;
                    default:
                        break;
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
        sensorManager.registerListener(lightEventListener,lightSensor, android.hardware.SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause(){
        super.onPause();
        // wylaczamy listener, zeby nie zzerac zasobow
        sensorManager.unregisterListener(lightEventListener);
    }

    private int ThemeToChange(){
        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if(currentValue >= maxValue / 2 ) {
            // zmiana tylko wtedy, gdy poprzedni to ciemny motyw
            if("dark".equals(outValue.string)){
                // ustawiamy jasny motyw
                Log.e("Theme","DO ZMIANY JASNY" + currentValue);
                return 1;
            }
        }
        else {
            // zmiana tylko wtedy, gdy poprzedni to jasny motyw
            if("light".equals(outValue.string)) {
                // ustawiamy ciemny motyw
                Log.e("Theme","DO ZMIANY CIEMNY" + currentValue);
                return 2;
            }
        }
        return 0;
    }

    private void ChangeThemeToLight(){
        Log.e("Theme","zmiana na jasny" + currentValue);
        Resources.Theme theme = super.getTheme();
        //theme.applyStyle(R.style.Theme_AndroidChampionsLeague, true);
        setTheme(R.style.Theme_AndroidChampionsLeague);
    }

    private void ChangeThemeToDark(){
        Log.e("Theme","zmiana na ciemny" + currentValue);
        Resources.Theme theme = super.getTheme();
        //theme.applyStyle(R.style.ThemeDark_AndroidChampionsLeague, true);
        setTheme(R.style.ThemeDark_AndroidChampionsLeague);
    }
}
