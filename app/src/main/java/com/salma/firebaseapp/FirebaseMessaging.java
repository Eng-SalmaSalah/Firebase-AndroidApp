package com.salma.firebaseapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessaging extends FirebaseMessagingService {
    private static final String TAG = "progress";

    public FirebaseMessaging() {
    }
//onNewTokenFires when token is generated
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //recieve notification in foreground handling
        /*Intent intent =new Intent(FirebaseMessaging.this,DataPayloadActivityForeground.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if(remoteMessage.getNotification()!=null)
        {
            String title=remoteMessage.getNotification().getTitle();
            String body=remoteMessage.getNotification().getBody();
            intent.putExtra("title",title);
            intent.putExtra("body",body);
        }
        if(remoteMessage.getData()!=null)
        {
            String name=remoteMessage.getData().get("name");
            String age=remoteMessage.getData().get("age");
            intent.putExtra("name",name);
            intent.putExtra("age",age);
        }
        startActivity(intent);*/

        //custom notification(only allowed in foreground)
        sendNotification(remoteMessage);

    }

    private void sendNotification(RemoteMessage remoteMessage) {
        String messageBody=remoteMessage.getNotification().getBody();
        Intent intent = new Intent(this, Navigator.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //pending intent:intent hatsht8l kman shwya 3lshan lma l user ydos 3l notification yro7 ll app
        //gives the device access to the app on clicking the notification
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this,channelId)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle(getString(R.string.fcm_message))//notification title
                        .setContentText(messageBody)//notification body
                        .setAutoCancel(true)//when user opens notification it disappears from tray
                        .setSound(defaultSoundUri)//set notification sound as default tone
                        .setContentIntent(pendingIntent);
//3lshan l notification tzhr f mkan l notifications
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }



    }

