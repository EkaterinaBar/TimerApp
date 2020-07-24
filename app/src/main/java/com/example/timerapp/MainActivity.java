package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView timerTxt;
    int secondsForTimer;
    CountDownTimer timer;
    public void startAudio(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.timer);
        mediaPlayer.start();
    }
    public void stopTimer(View view){
        seekBar.setEnabled(true);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        Button btnGo = (Button) findViewById(R.id.button);
        timer.cancel();
        btnStop.setVisibility(View.INVISIBLE);
        btnStop.setEnabled(false);
        btnGo.setVisibility(View.VISIBLE);
        btnGo.setEnabled(true);

    }
    public void playTimer(View view){
        seekBar.setEnabled(false);
        int milliseconds = secondsForTimer * 1000;
        final Button btnStop = (Button) findViewById(R.id.btnStop);
        final Button btnGo = (Button) findViewById(R.id.button);
        timer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long l) {
                long curSecond = l/1000;
                int minutes = (int)curSecond/60;
                int leftSec = (int)curSecond%60;
                String mins, secs;
                if (minutes < 10) mins = "0"+Integer.toString(minutes);
                else mins = Integer.toString(minutes);
                if(leftSec < 10) secs = "0"+Integer.toString(leftSec);
                else secs = Integer.toString(leftSec);
                timerTxt.setText(mins+":"+secs);
                secondsForTimer = minutes * 60 + leftSec;
            }

            @Override
            public void onFinish() {
                seekBar.setEnabled(true);
                seekBar.setProgress(0);
                secondsForTimer = 30;
                timerTxt.setText("00:30");
                btnStop.setVisibility(View.INVISIBLE);
                btnStop.setEnabled(false);
                btnGo.setVisibility(View.VISIBLE);
                btnGo.setEnabled(true);
                startAudio();
            }
        };
        timer.start();
        btnGo.setVisibility(View.INVISIBLE);
        btnGo.setEnabled(false);
        btnStop.setVisibility(View.VISIBLE);
        btnStop.setEnabled(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondsForTimer = 90;
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        timerTxt = (TextView) findViewById(R.id.textView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Info", Integer.toString(i));
                secondsForTimer = (i+1)*30;
                int minutes = secondsForTimer/60;
                int leftSec = secondsForTimer%60;
                String mins, secs;
                if (minutes < 10) mins = "0"+Integer.toString(minutes);
                else mins = Integer.toString(minutes);
                if(leftSec < 10) secs = "0"+Integer.toString(leftSec);
                else secs = Integer.toString(leftSec);
                timerTxt.setText(mins+":"+secs);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}