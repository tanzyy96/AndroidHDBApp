package com.example.androidhdb2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidhdb2.R;

public class BtoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bto_detail);

        Intent intent = getIntent();
        String btotype = intent.getStringExtra("BtoType");
    }
}
