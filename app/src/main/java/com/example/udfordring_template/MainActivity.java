package com.example.udfordring_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private boolean counterIsRunning = false;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            getSupportActionBar().hide(); //hide the title bar
            setContentView(R.layout.activity_main);
            addListenerOnButton();
        }

    public void addListenerOnButton(){
        Button counterButton = (Button) findViewById(R.id.Counter1);
        TextView textView = (TextView) findViewById(R.id.Parameter);

        counterButton.setEnabled(true);
        counterButton.setText("START");

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
                        //counterButton.setText("done!");
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                        //Add sensor data in the putExtra method's value field
                        intent.putExtra("result_key", "sensor_data");
                        counterIsRunning = false;
                        startActivity(intent);
                    }

                }.start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //register your sensorListener here

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
        //Unregister your sensorListener here
    }
}