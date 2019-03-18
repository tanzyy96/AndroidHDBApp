package com.example.androidhdb2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidhdb2.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bto_button, resale_button, sob_button;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userID = getIntent().getStringExtra("UserID");

        bto_button = findViewById(R.id.bto_button);
        resale_button = findViewById(R.id.resale_button);
        sob_button = findViewById(R.id.sob_button);

        bto_button.setOnClickListener(this);
        resale_button.setOnClickListener(this);
        sob_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.bto_button):
                Toast.makeText(this, "Open BTO page", Toast.LENGTH_SHORT).show();
                Intent btoIntent = new Intent(this, BtoActivity.class);
                startActivity(btoIntent);
                break;
            case (R.id.resale_button):
                Toast.makeText(this, "Open Resale page", Toast.LENGTH_SHORT).show();
                Intent resaleIntent = new Intent(this, FilterActivity.class);
                resaleIntent.putExtra("UserID", userID);
                resaleIntent.putExtra("SearchType", "Resale");
                startActivity(resaleIntent);
                break;
            case (R.id.sob_button):
                Toast.makeText(this, "Open SBF page", Toast.LENGTH_SHORT).show();
                Intent sobIntent = new Intent(this, FilterActivity.class);
                sobIntent.putExtra("SearchType", "SBF");
                break;
        }
    }
}