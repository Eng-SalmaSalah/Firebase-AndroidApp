package com.salma.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Navigator extends AppCompatActivity {
    Button authenticationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        authenticationBtn = findViewById(R.id.btnAuth);
        authenticationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Navigator.this, MainActivity.class);
                Navigator.this.startActivity(myIntent);
            }
        });
    }
}
