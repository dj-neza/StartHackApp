package com.example.team007.voicecomp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team007.voicecomp.ResultsActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RecordActivity extends AppCompatActivity {

    Button buttonStart, buttonPlay, buttonStart2, buttonPlay2;
    String AudioSavePathInDevice = "";
    String audio_name = "";
    String audio_name2 = "";
    MediaRecorder mediaRecorder;
    Random random;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer ;
    DatabaseHelper db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // prepare folder for saving recordings
        AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

        db = new DatabaseHelper(this);

        buttonStart = (Button) findViewById(R.id.recButton1);
        buttonPlay = (Button) findViewById(R.id.play1);
        buttonStart2 = (Button) findViewById(R.id.recButton2);
        buttonPlay2 = (Button) findViewById(R.id.play2);

        buttonPlay.setEnabled(false);
        buttonPlay2.setEnabled(false);

        random = new Random();

        // get random sentence from database
        ArrayList<String> allSentences = db.getAllSentences();
        int numOfSentences = allSentences.size();
        final int randNum = random.nextInt(numOfSentences);
        String randSent = allSentences.get(randNum);

        TextView generatedSentence = (TextView) findViewById(R.id.sentenceToSay);
        generatedSentence.setText(randSent);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonStart.getText().equals("rec1")) {
                    buttonStart.setText("stop rec1");
                    if (checkPermission()) {

                        audio_name = AudioSavePathInDevice + getCurrentTimeStamp() + "_AudioRecording.3gp";

                        if (mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                        MediaRecorderReady(audio_name);
                        int id1 = random.nextInt(1000000);
                        db.addRecording(id1, audio_name);

                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(RecordActivity.this, "Recording started", Toast.LENGTH_LONG).show();
                    } else {
                        requestPermission();
                    }
                }
                else if (buttonStart.getText().equals("stop rec1")) {
                    buttonStart.setText("rec1");
                    mediaRecorder.stop();
                    buttonPlay.setEnabled(true);

                    Toast.makeText(RecordActivity.this, "Recording Completed", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(audio_name);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(RecordActivity.this, "Recording Playing", Toast.LENGTH_LONG).show();
            }
        });

        buttonStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonStart2.getText().equals("rec2")) {
                    buttonStart2.setText("stop rec2");
                    if (checkPermission()) {

                        audio_name2 = AudioSavePathInDevice + getCurrentTimeStamp() + "_AudioRecording.3gp";

                        if (mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                        MediaRecorderReady(audio_name2);
                        int id2 = random.nextInt(1000000);
                        db.addRecording(id2, audio_name2);

                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(RecordActivity.this, "Recording started", Toast.LENGTH_LONG).show();
                    } else {
                        requestPermission();
                    }
                }
                else if (buttonStart2.getText().equals("stop rec2")) {
                    buttonStart2.setText("rec2");
                    mediaRecorder.stop();
                    buttonPlay2.setEnabled(true);

                    Toast.makeText(RecordActivity.this, "Recording Completed", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(audio_name2);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(RecordActivity.this, "Recording Playing", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void MediaRecorderReady(String audio_name){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(audio_name);
    }

    /*
    public String CreateRandomAudioFileName(int string){

        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }
    */

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(RecordActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(RecordActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RecordActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    public int calculatePercentage() {
        return random.nextInt(100);
    }

    public String getResponse(int percentage) {
        int p;
        if (percentage < 10) {
            p = 0;
        } else if (percentage >= 10 && percentage < 30) {
            p = 1;
        } else if (percentage >= 30 && percentage < 50) {
            p = 2;
        } else if (percentage >= 50 && percentage < 70) {
            p = 3;
        } else if (percentage >= 70 && percentage < 90) {
            p = 4;
        } else {
            p = 5;
        }
        ArrayList<String> responses = db.getResponsesByPercentage(p);
        int numOfResponses = responses.size();
        int randNum = random.nextInt(numOfResponses);
        String randResp = responses.get(randNum);
        return randResp;
    }

    public void calculate(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        int perc = calculatePercentage();
        String response = getResponse(perc);
        intent.putExtra("result", Integer.toString(perc));
        intent.putExtra("explanation", response);
        startActivity(intent);
    }
}
