package com.example.androidhdb2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidhdb2.R;

public class BtoActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bto);

        Button upcomingLaunchButton = findViewById(R.id.bto_button_upcoming);
        Button pastLaunchButton = findViewById(R.id.bto_button_past);
        upcomingLaunchButton.setOnClickListener(this);
        pastLaunchButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bto_button_upcoming:
                Intent intent_upcoming = new Intent(this, BtoDetailActivity.class);
                intent_upcoming.putExtra("BtoType", "Upcoming");
                startActivity(intent_upcoming);
            case R.id.bto_button_past:
                Intent intent_past = new Intent(this, BtoDetailActivity.class);
                intent_past.putExtra("BtoType", "Past");
                startActivity(intent_past);
        }
    }
}
