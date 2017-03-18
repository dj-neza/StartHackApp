package com.example.team007.voicecomp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        int procenti = Integer.valueOf(res);
        ImageView myImgView = (ImageView) findViewById(R.id.emptyHeart);
        if (procenti >= 0 && procenti < 10) {
            myImgView.setImageResource(R.drawable.hearshape_0);
        } else if (procenti >= 10 && procenti < 30) {
            myImgView.setImageResource(R.drawable.heartshape_1);
        } else if (procenti >= 30 && procenti < 50) {
            myImgView.setImageResource(R.drawable.heartshape_2);
        } else if (procenti >= 50 && procenti < 70) {
            myImgView.setImageResource(R.drawable.heartshape_3);
        } else if (procenti >= 70 && procenti < 90) {
            myImgView.setImageResource(R.drawable.heartshape_4);
        } else if (procenti >= 90 && procenti <= 100) {
            myImgView.setImageResource(R.drawable.heartshape_5);
        }
        String n = res+" %";
        t2.setText(n);

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
