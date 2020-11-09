package com.example.jasonsworkouttimer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;

public class TimerFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    public static TimerFragment newInstance() {
        TimerFragment tf = new TimerFragment();
        return tf;
    }



    private int seconds = 0;
    private TextView tv;
    private boolean paused = true;
    private FloatingActionButton fab;
    private Drawable PLAY;
    private Drawable PAUSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.time_text);
        fab = findViewById(R.id.play_pause_fab);
        PLAY = getResources().getDrawable(android.R.drawable.ic_media_pause);
        PAUSE = getResources().getDrawable(android.R.drawable.ic_media_play);
        if (savedInstanceState != null) {
            paused = savedInstanceState.getBoolean("paused");
            seconds = savedInstanceState.getInt("seconds");
            setIcon();
        }
        runTimer();
    }

    private void setIcon() {
        Drawable icon = paused ? PAUSE : PLAY;
        fab.setImageDrawable(icon);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("paused", paused);
    }

    public void startTimer(View view) {
        paused = !paused;
        setIcon();
    }

    public void runTimer() {
        final Handler handler = new Handler(getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                int sec = seconds % 60;
                int min = (seconds % 3600) / 60;
                int hour = seconds / 3600;
                tv.setText(String.format("%02d : %02d : %02d", hour, min, sec));
                if (!paused)
                    seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }
}

