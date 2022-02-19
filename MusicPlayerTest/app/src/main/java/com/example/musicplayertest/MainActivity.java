package com.example.musicplayertest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlay = findViewById(R.id.buttonPlay);
        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.oboroten);

        buttonPlay.setOnClickListener(e -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                buttonPlay.setText("Play");
            } else {
                mediaPlayer.start();
                buttonPlay.setText("Pause");
            }
        });
    }
}