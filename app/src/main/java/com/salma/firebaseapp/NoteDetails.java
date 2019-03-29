package com.salma.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NoteDetails extends AppCompatActivity {
TextView noteName;
TextView noteBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        noteName=findViewById(R.id.openedNoteName);
        noteBody=findViewById(R.id.openedNoteBody);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String body = intent.getStringExtra("body");
        noteName.setText(name);
        noteBody.setText(body);
    }
}
