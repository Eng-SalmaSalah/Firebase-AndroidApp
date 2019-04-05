package com.salma.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DataPayloadActivity extends AppCompatActivity {
    //receives data payload of notifications when app in background
TextView name;
TextView age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_payload);
        name=findViewById(R.id.txt_name);
        age=findViewById(R.id.txt_age);
        name.setText(getIntent().getStringExtra("name"));
        age.setText(getIntent().getStringExtra("age"));
    }
}
