package com.test.komachi.chiasenhac;



import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class FragmentTab1 extends Fragment {
    private Music music;
    private MediaPlayer mediaPlayer;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        timeCur = v.findViewById(R.id.timeCurrent);
        timeTotal = v.findViewById(R.id.timeTotal);
        imageView = v.findViewById(R.id.picture);
        next = v.findViewById(R.id.next);
        pause = v.findViewById(R.id.pause);
        previous = v.findViewById(R.id.previous);
        play = v.findViewById(R.id.play);
        return v;
    }
    /**
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listen);
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        seekBar =findViewById(R.id.seekBar);
        timeTotal.setText("00:00");
        timeCur.setText("00:00");
        Intent intent = getIntent();
        music = (Music) intent.getSerializableExtra("music");
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        objectAnimator = ObjectAnimator.ofFloat(imageView,"rotation",0f,360f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(0xffffffff);
        objectAnimator.setDuration(2200);
        //animation = AnimatorInflater.loadAnimator(this, R.animator.rotate);
        //animation.setTarget(imageView);
        //AnimationUtils.loadAnimation(this,R.anim.rotate);
        try {
            mediaPlayer.setDataSource(music.link);
            mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)
        } catch (IOException e){
            Log.d("aaaaa","ASSDA");
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp){
                mediaPlayer.start();
                int time = mediaPlayer.getDuration()/1000;
                seekBar.setMax(time);
                pause.setVisibility(View.VISIBLE);
                objectAnimator.start();
                //animation.start();
                //imageView.startAnimation(animation);
                timeTotal.setText(String.format("%1$02d:%1$02d",(int)time/60,time%60));

            }
        } );
        Picasso.with(this).load(music.cover).into(imageView);
        //imageView.startAnimation(animation);
        final Handler mHandler = new Handler();
        ListenActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                    timeCur.setText(String.format("%1$02d:%2$02d",(int)mCurrentPosition/60,mCurrentPosition%60));
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
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                    timeCur.setText(String.format("%1$02d:%2$02d",(int)progress/60,progress%60));
                }
            }
        });
    }
    public void play(View v){
        mediaPlayer.start();
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);
        //imageView.startAnimation(animation);
        //animation.start();
        objectAnimator.resume();
    }

    public void pause(View v){
        mediaPlayer.pause();
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.VISIBLE);
        //animation.pause();
        objectAnimator.pause();
    }**/
}