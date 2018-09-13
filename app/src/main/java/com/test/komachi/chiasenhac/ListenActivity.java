package com.test.komachi.chiasenhac;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class ListenActivity extends AppCompatActivity {
    private Music music;
    ImageView imageView;
    ImageView next;
    ImageView pause;
    ImageView previous;
    ImageView play;
    SeekBar seekBar;
    TextView timeTotal;
    TextView timeCur;
    ObjectAnimator objectAnimator;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_listen, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(music.name);
        actionBar.setSubtitle(music.artist);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        timeCur = findViewById(R.id.timeCurrent);
        timeTotal = findViewById(R.id.timeTotal);
        imageView = findViewById(R.id.picture);
        next = findViewById(R.id.next);
        pause = findViewById(R.id.pause);
        previous = findViewById(R.id.previous);
        play = findViewById(R.id.play);
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        seekBar =findViewById(R.id.seekBar);
        timeTotal.setText("00:00");
        timeCur.setText("00:00");
        Intent intent = getIntent();
        music = (Music) intent.getSerializableExtra("music");
        global.mediaPlayer = new MediaPlayer();
        global.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        objectAnimator = ObjectAnimator.ofFloat(imageView,"rotation",0f,360f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(0xffffffff);
        objectAnimator.setDuration(2200);
        try {
            global.mediaPlayer.setDataSource(music.link);
            global.mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)
        } catch (IOException e){
            Log.d("aaaaa","ASSDA");
        }
        global.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp){
                global.mediaPlayer.start();
                int time = global.mediaPlayer.getDuration()/1000;
                seekBar.setMax(time);
                pause.setVisibility(View.VISIBLE);
                play.setVisibility(View.INVISIBLE);
                objectAnimator.start();
                timeTotal.setText(String.format("%1$02d:%2$02d",time/60,time%60));

            }
        } );
        Picasso.with(this).load(music.cover).into(imageView);
        //imageView.startAnimation(animation);
        final Handler mHandler = new Handler();
        ListenActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(global.mediaPlayer != null){
                    int mCurrentPosition = global.mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                    timeCur.setText(String.format("%1$02d:%2$02d",(int)mCurrentPosition/60,mCurrentPosition%60));
                    //if (mCurrentPosition==global.mediaPlayer.getDuration()/1000){global.mediaPlayer.reset();}
                    if (!global.mediaPlayer.isPlaying()){play();}
                }
                mHandler.postDelayed(this, 1000);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(global.mediaPlayer != null && fromUser){
                    global.mediaPlayer.seekTo(progress * 1000);
                    timeCur.setText(String.format("%1$02d:%2$02d",(int)progress/60,progress%60));
                }
            }
        });
    }
    public void play(){
        global.mediaPlayer.start();
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);
        //imageView.startAnimation(animation);
        //animation.start();
        objectAnimator.resume();
    }
    public void play(View v){
        play();
    }
    public void pause(View v){
        pause();
    }
    private void pause(){
        global.mediaPlayer.pause();
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.VISIBLE);
        //animation.pause();
        objectAnimator.pause();
    }
}
