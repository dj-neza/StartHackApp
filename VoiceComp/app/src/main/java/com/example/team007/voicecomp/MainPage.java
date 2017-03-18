package com.example.team007.voicecomp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/extravaganzza.ttf");
        TextView tekst = (TextView) findViewById(R.id.mainpagetext);
        tekst.setTypeface(typeface);

    }

    public void startTest(View view) {
        Intent intent = new Intent(this, RecordActivity.class);
        Button b = (Button) findViewById(R.id.mainpagetext);
        b.setBackgroundColor(Color.parseColor("#D3D3D3"));
        startActivity(intent);
    }


}
