package com.astro.weatherappcustom;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Sensor sensorTemperature;
    private Sensor sensorWeight;
    private String word =" grad";
    private String word2 =" vlag";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor : list) {
            Log.i("!!sensor!!", sensor.getName());
        }
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorWeight = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        sensorManager.registerListener(this, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorWeight, SensorManager.SENSOR_DELAY_NORMAL);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("SensorTemperature", Float.toString(event.values[0]));
        Log.i("SensorWeight", Float.toString(event.values[1]));
        TextView temperature = findViewById(R.id.textView1);
        temperature.setText(event.values[0]+word);
        TextView weight = findViewById(R.id.textView2);
        weight.setText(event.values[1]+word2);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }



}
