package com.example.team007.voicecomp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class RecordActivity extends Activity {

    Button buttonStart, buttonPlay, buttonStart2, buttonPlay2;
    String AudioSavePathInDevice = "";
    String audio_name = "";
    String audio_name2 = "";
    MediaRecorder mediaRecorder;
    Random random;
    MediaPlayer mediaPlayer ;
    public static final int RequestPermissionCode = 1;
    DatabaseHelper db = null;

    int[] images = { R.drawable.female, R.drawable.male };
    String[] spinnerVals = {"female", "male"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // prepare folder for saving recordings


        db = new DatabaseHelper(this);

        buttonStart = (Button) findViewById(R.id.recButton1);
        buttonPlay = (Button) findViewById(R.id.play1);
        buttonStart2 = (Button) findViewById(R.id.recButton2);
        buttonPlay2 = (Button) findViewById(R.id.play2);
        buttonPlay.setEnabled(false);
        buttonPlay2.setEnabled(false);

        Spinner spin1 = (Spinner) findViewById(R.id.spinn);
        spin1.setAdapter(new MyAdapter(this, R.layout.spinner, spinnerVals));
        Spinner spin2 = (Spinner) findViewById(R.id.spinn2);
        spin2.setAdapter(new MyAdapter(this, R.layout.spinner, spinnerVals));

        random = new Random();

        // get random sentence from database
        ArrayList<String> allSentences = db.getAllSentences();
        int numOfSentences = allSentences.size();
        final int randNum = random.nextInt(numOfSentences);
        String randSent = allSentences.get(randNum);

        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/extravaganzza.ttf");

        TextView generatedSentence = (TextView) findViewById(R.id.sentenceToSay);
        generatedSentence.setText(randSent);
        generatedSentence.setTypeface(typeface);

        buttonStart.setTypeface(typeface);
        buttonPlay.setTypeface(typeface);
        buttonStart2.setTypeface(typeface);
        buttonPlay2.setTypeface(typeface);

        TextView y = (TextView) findViewById(R.id.you);
        y.setTypeface(typeface);
        TextView m = (TextView) findViewById(R.id.match);
        m.setTypeface(typeface);
        Button calc = (Button) findViewById(R.id.calculate);
        calc.setTypeface(typeface);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonStart.getText().equals("RECORD") || buttonStart.getText().equals("AGAIN")) {
                    buttonStart.setText("STOP");
                    buttonStart.setSelected(true);

                    if (checkPermission()) {

                        AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

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
                else if (buttonStart.getText().equals("STOP")) {
                    buttonStart.setText("AGAIN");
                    buttonStart.setSelected(false);
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
                if (buttonStart2.getText().equals("RECORD") || buttonStart2.getText().equals("RECORD AGAIN")) {
                    buttonStart2.setText("STOP RECORDING");
                    buttonStart2.setSelected(true);
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
                else if (buttonStart2.getText().equals("STOP RECORDING")) {
                    buttonStart2.setText("RECORD AGAIN");
                    buttonStart2.setSelected(false);
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

    public void MediaRecorderReady(String an){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(an);
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
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS").format(new Date());
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

    public class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context ctx, int txtViewResId, String[] objects) {
            super(ctx, txtViewResId, objects);
        }
        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return getCustomView(position, cnvtView, prnt);
        }
        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return getCustomView(pos, cnvtView, prnt);
        }

    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        View mySpinner = inflater.inflate(R.layout.spinner, parent, false);
        ImageView left_icon = (ImageView) mySpinner.findViewById(R.id.female);
        left_icon.setImageResource(images[position]);
        return mySpinner;
    }

}
