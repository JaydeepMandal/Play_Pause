package com.example.ee.play_pause;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton playBtn,pauseBtn,stopBtn,forwardBtn,rewindBtn;
    MediaPlayer mp;
    SeekBar seekBar;
    TextView songLength,currLength;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currLength = (TextView) findViewById(R.id.currentPos);
        songLength = (TextView) findViewById(R.id.songLength);
        playBtn = (ImageButton) findViewById(R.id.imageButton4);
        stopBtn = (ImageButton) findViewById(R.id.imageButton3);
        pauseBtn = (ImageButton) findViewById(R.id.imageButton);
        forwardBtn = (ImageButton) findViewById(R.id.forwardButton);
        rewindBtn = (ImageButton) findViewById(R.id.rewindButton);
        mp = MediaPlayer.create(getApplicationContext(),R.raw.radio_tublite);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
    }

    public void clickable(View v){

        switch (v.getId()){
            case R.id.closeButton:
                finish();
            case R.id.imageButton4:
                mp.start();

                songLength.setText((mp.getDuration()/1000)/60 + ":" + (mp.getDuration()/1000)%60);
                handler = new Handler();
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seekBar.setMax(mp.getDuration()/1000);
                        seekBar.setProgress(mp.getCurrentPosition()/1000);
                        currLength.setText((mp.getCurrentPosition()/1000)/60 + ":" + (mp.getCurrentPosition()/1000)%60);
                        handler.postDelayed(this,1000);
                    }
                });

                playBtn.setVisibility(View.INVISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton:
                mp.pause();
                pauseBtn.setVisibility(View.INVISIBLE);
                playBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton3:
                if(mp.isPlaying()){
                    mp.seekTo(0);
                    mp.pause();
                    pauseBtn.setVisibility(View.INVISIBLE);
                    playBtn.setVisibility(View.VISIBLE);
                }
                else {
                    mp.seekTo(0);
                }
                break;
            case R.id.forwardButton:
                mp.seekTo(mp.getCurrentPosition()+5000);
                break;
            case R.id.rewindButton:
                mp.seekTo(mp.getCurrentPosition()-5000);
                break;
            default:
                break;
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

}
