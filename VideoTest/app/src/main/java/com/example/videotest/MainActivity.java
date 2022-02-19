package com.example.videotest;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        VideoView videoView = (VideoView)findViewById(R.id.videoView);

        String uriPath = "android.resource://com.example.video/"+R.raw.movie;
        Uri uri2 = Uri.parse(uriPath);
        videoView.setVideoURI(uri2);
        videoView.requestFocus();
        videoView.start();

        buttonPlay.setOnClickListener(e -> {
            videoView.start();
        });
    }
}