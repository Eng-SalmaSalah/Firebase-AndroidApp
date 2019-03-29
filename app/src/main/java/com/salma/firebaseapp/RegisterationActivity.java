package com.salma.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterationActivity extends AppCompatActivity {
Button signUpBtn;
    EditText email;
    EditText password;
    EditText displayName;
    String userEmail;
    String userPassword;
    String userDisplayName;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        firebaseAuth = FirebaseAuth.getInstance();
        displayName=findViewById(R.id.displayName);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        signUpBtn=findViewById(R.id.btnSignUp);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail=email.getText().toString();
                userDisplayName=displayName.getText().toString().trim();
                userPassword=password.getText().toString();

                //create account
                firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterationActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userDisplayName.trim()).build();
                                user.updateProfile(profileUpdates);
                                FirebaseAuth.getInstance().signOut();
                                LoginManager.getInstance().logOut();
                                finish();
                                Intent intent=new Intent(RegisterationActivity.this,MainActivity.class);
                                RegisterationActivity.this.startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(RegisterationActivity.this, "Failed to register", Toast.LENGTH_LONG).show();

                        }

                    }
                });

            }
        });
    }
}
