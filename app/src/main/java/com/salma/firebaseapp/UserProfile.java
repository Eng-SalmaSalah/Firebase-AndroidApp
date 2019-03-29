package com.salma.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    Button insertBtn;
    Button logOutBtn;
    EditText noteName;
    EditText noteBody;
    ListView notesList;
    TextView userNameTxtView;
    String currentUserName;
    String currentUserUID;
    FirebaseUser user;
    // firebase db
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth firebaseAuth;


    private ArrayAdapter<Note> adapter;

    @Override
    protected void onResume() {
        super.onResume();
        setUserData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        insertBtn = findViewById(R.id.insertBtn);
        logOutBtn=findViewById(R.id.btnLogOut);
        noteName = findViewById(R.id.noteName);
        noteBody = findViewById(R.id.openedNoteBody);
        notesList = findViewById(R.id.notesList);
        userNameTxtView = findViewById(R.id.userName);
        setUserData();
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("notes").child(currentUserUID);
        adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1);
        notesList.setAdapter(adapter);



        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                finish();
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insertedNoteName = noteName.getText().toString();
                String insertedNoteBody = noteBody.getText().toString();
                Note insertedNote = new Note(insertedNoteName, insertedNoteBody);
                mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(insertedNote);
            }
        });

        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserProfile.this, NoteDetails.class);
                intent.putExtra("name", adapter.getItem(position).getNoteName());
                intent.putExtra("body", adapter.getItem(position).getNoteBody());
                UserProfile.this.startActivity(intent);
            }
        });


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adapter.clear();
                for (DataSnapshot dataSnapshotItr : dataSnapshot.getChildren()) {
                    adapter.add(dataSnapshotItr.getValue(Note.class));
                }
                adapter.notifyDataSetChanged();
                notesList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setUserData() {

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            currentUserUID=user.getUid();
            currentUserName = user.getDisplayName();
            userNameTxtView.setText(currentUserName);
        }


    }
}
