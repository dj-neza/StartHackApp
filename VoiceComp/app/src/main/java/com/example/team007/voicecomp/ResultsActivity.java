package com.example.team007.voicecomp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String res  = intent.getStringExtra("result");
        String exp  = intent.getStringExtra("explanation");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/extravaganzza.ttf");
        Button b = (Button) findViewById(R.id.calcAgain);
        b.setTypeface(typeface);
        TextView t1 = (TextView) findViewById(R.id.resultExplanation);
        t1.setTypeface(typeface);
        t1.setText(exp);
        TextView t2 = (TextView) findViewById(R.id.showResult);
        t2.setTypeface(typeface);
        t2.setText(res);

    }
    
    public void calcAgain(View view) {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }
}
