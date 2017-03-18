package com.example.team007.voicecomp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Patricija on 18/03/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_SENTENCES = "Sentences";
    private static final String TABLE_RESPONSES = "Responses";
    private static final String TABLE_RECORDINGS = "Recordings";

    // Sentences Table Columns
    private static final String SENTENCE_ID = "id_sentence";
    private static final String SENTENCE_CONTENT = "content_sentence";

    // Responses Table Columns
    private static final String RESPONSE_ID = "id_response";
    private static final String RESPONSE_PERCENTAGE = "percentage";
    private static final String RESPONSE_CONTENT = "content_response";

    // Recordings Table Columns
    private static final String RECORDING_ID = "id_recording";
    //private static final String RECORDING_CONTENT = "content_sentence";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        //db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SENTENCES_TABLE = "CREATE TABLE " + TABLE_SENTENCES +
                "(" +
                SENTENCE_ID + " INT PRIMARY KEY," + // Define a primary key
                SENTENCE_CONTENT + " VARCHAR" +
                ")";

        String CREATE_RESPONSES_TABLE = "CREATE TABLE " + TABLE_RESPONSES +
                "(" +
                RESPONSE_ID + " INT PRIMARY KEY," +
                RESPONSE_PERCENTAGE + " INT," +
                RESPONSE_CONTENT + " VARCHAR" +
                ")";

        /*String CREATE_RECORDINGS_TABLE = "CREATE TABLE " + TABLE_RECORDINGS +
                "(" +
                RECORDING_ID + " INT PRIMARY KEY," +
                RECORDING_CONTENT + " ___" +
                ")";*/

        db.execSQL(CREATE_SENTENCES_TABLE);
        db.execSQL(CREATE_RESPONSES_TABLE);
        //db.execSQL(CREATE_RECORDINGS_TABLE);

        //db.execSQL("INSERT INTO Sentences(id_sentence, content_sentence) VALUES (0, 'The sun is shining and so are you.');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENTENCES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESPONSES);
            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDINGS);
            onCreate(db);
        }
    }

    // CRUD Operations

    // 1) SENTENCES

    // Insert a sentence into the database
    public void addSentence(String sentence) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SENTENCE_CONTENT, sentence);

            db.insertOrThrow(TABLE_SENTENCES, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ERROR", "Error while trying to add sentence to database");
        } finally {
            db.endTransaction();
        }
    }

    // Get all sentences from database
    public ArrayList<String> getAllSentences() {
        ArrayList<String> sentences = new ArrayList<String>();

        String SENTENCES_SELECT_QUERY = "SELECT * FROM " + TABLE_SENTENCES;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SENTENCES_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    String s = cursor.getString(cursor.getColumnIndex(SENTENCE_CONTENT));
                    sentences.add(s);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ERROR", "Error while trying to get sentences from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return sentences;
    }

    // Delete a sentence from database by id
    public void deleteSentence(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM " + TABLE_SENTENCES + " WHERE " + SENTENCE_ID + " = " + id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ERROR", "Error while trying to delete sentence from database");
        } finally {
            db.endTransaction();
        }
    }

    // 2) RESPONSES

    // Insert a response into the database
    public void addResponse(int percentage, String response) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(RESPONSE_CONTENT, response);
            values.put(RESPONSE_PERCENTAGE, percentage);

            db.insertOrThrow(TABLE_RESPONSES, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ERROR", "Error while trying to add response to database");
        } finally {
            db.endTransaction();
        }
    }

    // Get all responses from database
    public ArrayList<String> getAllResponses() {
        ArrayList<String> responses = new ArrayList<String>();

        String RESPONSES_SELECT_QUERY = "SELECT * FROM " + TABLE_RESPONSES;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(RESPONSES_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    String s = cursor.getString(cursor.getColumnIndex(RESPONSE_CONTENT));
                    responses.add(s);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ERROR", "Error while trying to get responses from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return responses;
    }

    // Get all responses of ceratin percentage from database
    /*  0 : 0-24%
        1 : 25-49%
        2 : 50-74%
        3 : 75-100%
     */
    public ArrayList<String> getResponsesByPercentage(int percentage) {
        ArrayList<String> responses = new ArrayList<String>();

        String RESPONSES_SELECT_QUERY = "SELECT * FROM " + TABLE_RESPONSES + " WHERE " + RESPONSE_PERCENTAGE + " = " + percentage;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(RESPONSES_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    String s = cursor.getString(cursor.getColumnIndex(RESPONSE_CONTENT));
                    responses.add(s);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ERROR", "Error while trying to get responses from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return responses;
    }

    // Delete a response from database by id
    public void deleteResponse(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM " + TABLE_RESPONSES + " WHERE " + RESPONSE_ID + " = " + id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ERROR", "Error while trying to delete response from database");
        } finally {
            db.endTransaction();
        }
    }
}
