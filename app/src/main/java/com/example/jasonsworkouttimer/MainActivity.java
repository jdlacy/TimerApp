package com.example.jasonsworkouttimer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.crunches)).setOnClickListener(this);
        ((Button) findViewById(R.id.pushups)).setOnClickListener(this);
        ((Button) findViewById(R.id.squats)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
        switch (view.getId()) {
            case R.id.crunches:
                intent.putExtra("seconds", 45);
                intent.putExtra("title", "CRUNCH TIME!");
                startActivity(intent);
                break;
            case R.id.pushups:
                intent.putExtra("seconds", 30);
                intent.putExtra("title", "PUSHUP TIME!");
                startActivity(intent);
                break;
            case R.id.squats:
                intent.putExtra("seconds", 60);
                intent.putExtra("title", "SQUAT TIME!");
                startActivity(intent);
                break;
        }
    }
}