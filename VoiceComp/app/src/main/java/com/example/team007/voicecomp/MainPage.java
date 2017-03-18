package com.example.team007.voicecomp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.Cursor;
import android.widget.TextView;

import java.util.ArrayList;

public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        DatabaseHelper db = new DatabaseHelper(this);
        //db.restart();

        /*
        db.addSentence(0, "Nice to meet you.");
        db.addSentence(1, "It's a lovely day today.");
        db.addSentence(2, "Girls just wanna have fun.");
        db.addSentence(3, "You are beautiful no matter what they say.");
        db.addSentence(4, "Life is short, enjoy it.");
        db.addSentence(5, "There was a time when I was young.");
        db.addSentence(6, "Once upon a time in a land far away lived an ordinary girl.");
        db.addSentence(7, "If you don't go, you don't have a story.");
        db.addSentence(8, "It was raining cats and dogs yesterday.");
        db.addSentence(9, "I used to sing all the time as a child.");
        db.addSentence(10, "My all time favourite food is coconut ice cream.");
        db.addSentence(11, "Missing the bus really made me upset.");
        db.addSentence(12, "I overslept so I was late for a doctors appointment.");
        db.addSentence(13, "When he finishes his studying, he'll be a great developer.");
        db.addSentence(14, "The idea that is most innovative will receive a special reward.");
        db.addSentence(15, "Coffee is like a magical cure for people like me.");
        db.addSentence(16, "How can anyone listen to that kind of music?");
        db.addSentence(17, "We always knew this day was coming.");
        db.addSentence(18, "You lost something today but something much greater might be right around the corner.");
        db.addSentence(19, "Do you believe in magic?");
        db.addSentence(20, "We are the champions no matter what happens.");
        db.addSentence(21, "Why is it so hard to believe that somebody actually wants to be nice?");
        */

        /*
        db.addResponse(0, 0, "Ouch! That sounds really out of tune. The chemistry (if there ever was any) is gone and seems like you two might just be better off appart.");
        db.addResponse(1, 5, "Your love story is just like the sound of your voices - a perfect harmony! Nothing can break your connection because you are meant to be!");
        db.addResponse(2, 5, "You keep hitting all the high notes. Congratulations, even the karaoke machine applauds you.");
        db.addResponse(3, 5, "Both of you have a perfect sense of rythm, nobody can make you go out of sync.");
        db.addResponse(4, 2, "If you keep up the charade you might just shutter a glass or two with your arias.");
        db.addResponse(5, 1, "As a couple you keep missing the beat. Sometimes you just have to accept that every song has an ending.");
        db.addResponse(6, 3, "You two make a good choir just as long as nether one of you goes for that solo career.");
        db.addResponse(7, 4, "The absolute pitch is within your reach. Practice makes perfect. Keep up the good work.");
        db.addResponse(8, 3, "It's all fun and rhymes while you are jamming together. You two might be cute but will it last forever?");
        db.addResponse(9, 3, "Anyone can be a DJ and mix some music but only great couples can match up their volumes.");
        db.addResponse(10, 0, "Ouch! That sounds really out of tune. If your relationship was a song, someone might be in danger of losing their sense of hearing.");
        db.addResponse(11, 0, "The melody you two create is just no good. You are better of being on mute instead.");
        db.addResponse(12, 1, "The guitar strings you two are playing have all been broken. You can try to repair them but you might be wasting your time.");
        db.addResponse(13, 1, "You are so out of sync. Try getting a metronome if you are still able to think straight.");
        db.addResponse(14, 2, "The music sounds okay but sadly your rhyming skills are not in place anymore.");
        db.addResponse(15, 2, "Note after note you try, if you hit a pause at the same time you might be able to survive.");
        db.addResponse(16, 4, "No one knows which one is the Beauty and which one the Beat, but one thing is certain - you make each other complete.");
        db.addResponse(17, 4, "Life is not like piano keys (all black & white), from time to time you might miss one but all is still right.");
        */
        /*
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
        */

        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/extravaganzza.ttf");
        TextView tekst = (TextView) findViewById(R.id.mainpagetext);
        tekst.setTypeface(typeface);

    }

    public void startTest(View view) {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }
}
