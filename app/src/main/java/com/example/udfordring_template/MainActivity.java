package com.example.udfordring_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

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
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterButton.setEnabled(false);

                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        counterButton.setText("" + millisUntilFinished / 1000);

                    }

                    public void onFinish() {
                        //counterButton.setText("done!");
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        startActivity(intent);
                    }

                }.start();
            }
        });
    }
}