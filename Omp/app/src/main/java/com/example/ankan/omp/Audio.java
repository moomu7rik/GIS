package com.example.ankan.omp;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Audio extends AppCompatActivity{
    Button btnstart, btnstop, btnplay, btndone;
    MediaRecorder mediaRecorder;
    String pathSave;
    MediaPlayer mediaPlayer;
    final int REQUEST_PERMISSION_CODE = 100;
    private static final String IMAGE_DIRECTORY_NAME = "OMP_STORAGE";
    private static final String MEDIA_DIRECTORY_NAME = "OMP_MEDIA_STORAGE";

    public static String gprefa ="audio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        if(!checkPermissionFromDevice()){
            requestPermission();
        }
        btnstart = (Button) findViewById(R.id.btnstart);
        btnstop = (Button) findViewById(R.id.btnstop);
        btnplay = (Button) findViewById(R.id.btnplay);
        btndone = (Button) findViewById(R.id.btndone);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermissionFromDevice()) {


                    // pathSave = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + UUID.randomUUID() + "_map.3gp";
                    // External sdcard location
                    File mediaStorageDir = new File(
                            Environment
                                    .getExternalStorageDirectory().getAbsoluteFile()+"/"+
                                    IMAGE_DIRECTORY_NAME+"/"+MEDIA_DIRECTORY_NAME);

                    // Create the storage directory if it does not exist
                    if (!mediaStorageDir.exists()) {
                        if (!mediaStorageDir.mkdirs()) {
                            Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                                    + IMAGE_DIRECTORY_NAME + " directory");

                        }
                    }

                    // Create a media file name
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                            Locale.getDefault()).format(new Date());

                    pathSave = mediaStorageDir.getPath() + File.separator
                            + "map_AUDIO" + timeStamp + ".3gp";

                    setupMediaRecorder();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    btnplay.setEnabled(false);
                    btndone.setEnabled(false);
                    Toast.makeText(Audio.this, "Recording..."+pathSave, Toast.LENGTH_SHORT).show();
                } else {
                    requestPermission();
                }
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaRecorder.stop();
                mediaRecorder.release();
                btnstop.setEnabled(false);
                btnplay.setEnabled(true);
                btnstart.setEnabled(true);
                btndone.setEnabled(false);

            }
        });
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstop.setEnabled(false);
                btnstart.setEnabled(false);
                btndone.setEnabled(true);
                mediaPlayer=new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(pathSave);
                    mediaPlayer.prepare();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                mediaPlayer.start();
                Toast.makeText(Audio.this, "Playing...", Toast.LENGTH_SHORT).show();

            }
        });
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstop.setEnabled(true);
                btnplay.setEnabled(true);
                btnstart.setEnabled(true);
                btndone.setEnabled(true);
                if(mediaPlayer!=null)
                {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    setupMediaRecorder();


                }
                SharedPreferences.Editor editor = getSharedPreferences(gprefa,MODE_PRIVATE).edit();
                editor.putString("aname", pathSave);
                editor.commit();




            }
        });







    }
    private void setupMediaRecorder(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);

    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    private boolean checkPermissionFromDevice(){
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED && record_audio_result == PackageManager.PERMISSION_GRANTED;
    }
}