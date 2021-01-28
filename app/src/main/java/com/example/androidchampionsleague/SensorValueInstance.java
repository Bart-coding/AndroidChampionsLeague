package com.example.androidchampionsleague;

public class SensorValueInstance {
    private static float currentValue;

    private SensorValueInstance(){}

    public static float getCurrentSensorValue(){
        return currentValue;
    }
    public static void setCurrentSensorValue(float value){
        currentValue = value;
    }
}
