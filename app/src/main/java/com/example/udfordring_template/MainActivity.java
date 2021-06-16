package com.example.udfordring_template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private boolean counterIsRunning = false;
    private SensorManager sensorManager;
    private TextView textViewStepCounter, textViewStepDetector;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    int stepCount = 0;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
                //ask for permission
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
            }
            super.onCreate(savedInstanceState);
            getSupportActionBar().hide(); //hide the title bar
            setContentView(R.layout.activity_main);
            addListenerOnButton();

            textViewStepCounter = findViewById(R.id.Parameter);

            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

            if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){

                mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

                isCounterSensorPresent = true;

            } else {
                textViewStepCounter.setText("Counter is not present");
                isCounterSensorPresent = false;
            }

        }

    public void addListenerOnButton(){
        Button counterButton = (Button) findViewById(R.id.Counter);
        counterButton.setEnabled(true);
        counterButton.setText("START");
        TextView textView = (TextView) findViewById(R.id.Parameter);
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterButton.setEnabled(false);
                counterIsRunning = true;
                onResume();

                new CountDownTimer(5000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        counterButton.setText("" + millisUntilFinished / 1000);

                    }

                    public void onFinish() {
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        int result = 333;
                        intent.putExtra("RESULT", result);
                        counterIsRunning = false;
                        onPause();
                        startActivity(intent);
                    }

                }.start();


            }
        });
    }

    private void startChallenge() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            sensorManager.registerListener(this,mStepCounter, SensorManager.SENSOR_DELAY_FASTEST);
        }
        if (!counterIsRunning) {
            addListenerOnButton();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER ) != null){
            sensorManager.unregisterListener(this,mStepCounter);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor == mStepCounter){
            stepCount = (int) event.values[0];
            textViewStepCounter.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}