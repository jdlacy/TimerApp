package com.example.jasonsworkouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Workouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, TimerFragment.newInstance())
                .commit();
    }
}