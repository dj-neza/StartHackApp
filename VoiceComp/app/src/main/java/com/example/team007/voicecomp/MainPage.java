package com.example.team007.voicecomp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.Cursor;
import android.widget.TextView;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        DatabaseHelper db = new DatabaseHelper(this);
        //db.deleteSentence(0);
        //db.addSentence("Tomorrow is going to be a good day!");
        //db.addResponse(3, "The odds are ever in your favor!");
        ArrayList<String> allSentences = db.getAllSentences();

        String text = "";
        for (int i = 0; i < allSentences.size(); i++) {
            text += allSentences.get(i) + "\n";
        }
        TextView text1 = (TextView) findViewById(R.id.text);


        ArrayList<String> allResponses = db.getResponsesByPercentage(3);

        for (int i = 0; i < allResponses.size(); i++) {
            text += allResponses.get(i) + "\n";
        }
        text1.setText(text);
        Log.d("SUCCESS", "Pac, success");

    }

    public void startTest(View view) {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }
}
