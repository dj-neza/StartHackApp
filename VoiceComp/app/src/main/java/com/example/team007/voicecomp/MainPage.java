package com.example.team007.voicecomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.Cursor;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        String text = "";

        SQLiteDatabase db = null;

        try {
            db = openOrCreateDatabase("db", MODE_PRIVATE, null);
            Log.d("CREATION", "Database works!");

            db.execSQL("CREATE TABLE IF NOT EXISTS Responses(id_response INT, percentage INT, content_response VARCHAR);");
            db.execSQL("INSERT INTO Responses(id_response, percentage, content_response) VALUES (0, 0, 'Some people are meant to be together, some are just not. (You are not.)');");

            //Log.d("INSERTION", "Successfully inserted values...");

            /*
            Cursor c = db.rawQuery("SELECT * FROM Sentences", null);

            int col1 = c.getColumnIndex("id_sentence");
            int col2 = c.getColumnIndex("content_sentence");
            Log.d("COLUMNS", col1 + " " + col2);

            // Check if our result was valid.
            c.moveToFirst();
            if (c != null) {
                do {
                    Log.d("CURSOR", c.toString());
                    int id_sentence = c.getInt(col1);
                    String content_sentence = c.getString(col2);
                    text += "id: " + id_sentence + " vsebina: " + content_sentence + "\n";
                } while (c.moveToNext());
            }

            Log.d("TEXT", text);
            TextView text1 = (TextView) findViewById(R.id.text);
            text1.setText(text);
            */
        }
        catch(Exception e) {
            Log.e("Error opening database", "Error", e);
        } finally {
            if (db != null)
                db.close();
        }



    }
}
