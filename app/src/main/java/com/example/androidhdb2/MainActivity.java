package com.example.androidhdb2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidhdb2.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bto_button, resale_button, sob_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                break;
            case (R.id.resale_button):
                Toast.makeText(this, "Open Resale page", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.sob_button):
                Toast.makeText(this, "Open SOB page", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}