package com.example.team007.voicecomp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/extravaganzza.ttf");
        Button b = (Button) findViewById(R.id.calcAgain);
        b.setTypeface(typeface);
        TextView t1 = (TextView) findViewById(R.id.resultExplanation);
        t1.setTypeface(typeface);
        TextView t2 = (TextView) findViewById(R.id.showResult);
        t2.setTypeface(typeface);

    }


    public void calcAgain(View view) {
        Intent intent = new Intent(this, RecordActivity.class);
        Button b = (Button) findViewById(R.id.calcAgain);
        b.setBackgroundColor(Color.parseColor("#D3D3D3"));
        startActivity(intent);
    }


    public void goToMain(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);


    }
}
