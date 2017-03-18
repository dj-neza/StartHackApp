package com.example.team007.voicecomp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String res  = intent.getStringExtra("result");
        String exp  = intent.getStringExtra("explanation");

        TextView resultExpl = (TextView) findViewById(R.id.resultExplanation);
        resultExpl.setText(exp);
        TextView showResult = (TextView) findViewById(R.id.showResult);
        showResult.setText(res);
    }
}
