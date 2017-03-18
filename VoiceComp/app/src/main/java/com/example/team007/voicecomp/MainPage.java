package com.example.team007.voicecomp;

import android.app.Activity;
import android.content.Intent;
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
        startActivity(intent);
    }

   /* public static void buttonEffect(View button){
        button.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }*/


}
