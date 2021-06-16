package com.example.udfordring_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView resultText = (TextView) findViewById(R.id.Result_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_result);

        //Intent mIntent = getIntent();
        //int result = mIntent.getIntExtra("RESULT", 0);
        //resultText.setText("" + result);

        addListenerOnButton();
    }



    public void addListenerOnButton(){
        Button retryButton = (Button) findViewById(R.id.retry_button);
        Button returnButton = (Button) findViewById(R.id.return_button);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Replace MainActivity with the MapActivity/Main activity in DTU-GO
                //https://stackoverflow.com/questions/14001963/finish-all-activities-at-a-time
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
    }
}