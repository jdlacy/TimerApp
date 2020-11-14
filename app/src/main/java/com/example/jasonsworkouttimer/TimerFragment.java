package com.example.jasonsworkouttimer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;

public class TimerFragment extends Fragment{
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private String title;
    private TextView titleText;
    private TextView tv;
    private boolean paused = true;
    private FloatingActionButton fab;
    private Drawable PLAY;
    private Drawable PAUSE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = getArguments().getString("title");
        Log.i("TITLE?", "got " + title);
        seconds = getArguments().getInt("seconds");
        Log.i("SECONDS?", "got " + seconds);
        tv = getView().findViewById(R.id.time_text);
        titleText = getView().findViewById(R.id.title_text);
        fab = getView().findViewById(R.id.play_pause_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startTimer(view);}
            }
        );

        tv.setText(String.format("%02d : %02d : %02d", hours, minutes, seconds));
        titleText.setText(title);

        PLAY = getResources().getDrawable(android.R.drawable.ic_media_pause);
        PAUSE = getResources().getDrawable(android.R.drawable.ic_media_play);
        if (savedInstanceState != null) {
            paused = savedInstanceState.getBoolean("paused");
            seconds = savedInstanceState.getInt("seconds");
            title = savedInstanceState.getString("title");
            setIcon();
        }
    }

    private void setIcon() {
        Drawable icon = paused ? PAUSE : PLAY;
        fab.setImageDrawable(icon);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("paused", paused);
    }

    public void startTimer (View view) {
        paused = !paused;
        setIcon();
        tv.setText(String.format("%02d : %02d : %02d", hours, minutes, seconds));
    }

    public void runTimer() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                int sec = seconds % 60;
                int min = (seconds % 3600) / 60;
                int hour = seconds / 3600;
                if (!paused && (seconds != 0) ){
                    tv.setText(String.format("%02d : %02d : %02d", hour, min, sec));
                    seconds--;
                }
                else if(!paused && (seconds == 0)) {
                    fab.setEnabled(false);
                    tv.setText("You did it!");
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


    public static TimerFragment newInstance(int seconds, String title) {
        TimerFragment tf = new TimerFragment();
        Bundle args = new Bundle();
        args.putInt("seconds", seconds);
        args.putString("title", title);
        tf.setArguments(args);
        return tf;
    }
}

