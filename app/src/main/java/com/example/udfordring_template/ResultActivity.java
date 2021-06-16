package com.example.udfordring_template;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView resultText = (TextView) findViewById(R.id.result_text);
        String result = getIntent().getStringExtra("result_key");
        resultText.setText(result);
    }


}