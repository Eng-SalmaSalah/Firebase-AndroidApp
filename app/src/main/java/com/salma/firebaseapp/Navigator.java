package com.salma.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class Navigator extends AppCompatActivity {
    private static final String TAG = "progress";
    Button authenticationBtn;
    Button getTokenBtn;
    Button goToDataPayload;

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

        getTokenBtn = findViewById(R.id.Btn_getToken);
        getTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.i(TAG, "getInstanceId failed", task.getException());
                                    return;
                                }

                                // Get new Instance ID token
                                String token = task.getResult().getToken();

                                // Log and toast
                                Log.i(TAG, "token is: " + token);
                                Toast.makeText(Navigator.this, "token generated successfully", Toast.LENGTH_SHORT).show();
                                //token: fO38kYX1B2s:APA91bFUH74SSj6-Dzo1dJpVq645zTNCdkn1rlJMiQbTblBAp8EtVg71YwCM18AM4iB43BMluzOsAs6gzUjK3sq7w544BaEbgS9DRia5gcv5kQkkIrHt235gvl2ONp0iWwsGp8gA0sXW

                      //token of oreo device
                              //  dhcMO6su_M8:APA91bFd-bH_OmUAJ07h4M5g8AEZHrYgkdO862XDvFQqYU9DKtvFq1ypUpxVikdHL67AseTOD4ZyRd-xYOpHyt54zsbkkRHow3ypR4Hfe0VVWssvOVBrrmuHkmpX_ycw03ZJgT0dV16d
                            }

                        });
            }
        });



        if (getIntent().getExtras() != null) {
            String name = getIntent().getExtras().getString("name");
            String age = getIntent().getExtras().getString("age");
            if ((name != null) || (age != null)) {
                Intent intent = new Intent(Navigator.this, DataPayloadActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                startActivity(intent);
            }
        }
    }
}
