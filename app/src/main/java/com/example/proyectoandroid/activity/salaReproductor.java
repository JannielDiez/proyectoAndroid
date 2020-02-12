package com.example.proyectoandroid.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoandroid.R;

import java.io.IOException;

public class salaReproductor extends AppCompatActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();
    String musicURL = "https://firebasestorage.googleapis.com/v0/b/proyectoclaseandroid-60064.appspot.com/o/song.mp3?alt=media&token=72ec8e35-604d-459f-a3de-6b6606b907aa";
    private ImageButton btnStart;
    private SeekBar seekBar;
    private Runnable runnable;
    private Handler handler;
    private androidx.appcompat.widget.Toolbar toolbar;
    private ImageButton btnBack;
    private boolean pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sala_reproduccion);

        btnStart = findViewById(R.id.btnPlay);
        seekBar = findViewById(R.id.seekbar);
        toolbar = findViewById(R.id.toolbar);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        handler = new Handler();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    btnStart.setImageResource(R.drawable.ic_pause_black_24dp);
                }else {
                    mediaPlayer.pause();
                    btnStart.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                }
            }
        });
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(musicURL);
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    seekBar.setMax((mediaPlayer.getDuration()));
                   // mediaPlayer.start();
                    changeSeekBar();
                    Toast.makeText(salaReproductor.this, "Media Buffering...",Toast.LENGTH_SHORT).show();
                }
            });
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser){
                        mediaPlayer.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void changeSeekBar() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekBar();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }
}
