package com.salma.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DataPayloadActivityForeground extends AppCompatActivity {
    //receives data payload of notification when app in foreground
    TextView title;
    TextView body;
    TextView name;
    TextView age;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_payload_foreground);
        title = findViewById(R.id.txt_title);
        body = findViewById(R.id.txt_body);
        name = findViewById(R.id.txt_name);
        age = findViewById(R.id.txt_age);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        age.setText(intent.getStringExtra("age"));
        title.setText(intent.getStringExtra("title"));
        body.setText(intent.getStringExtra("body"));


    }
}
