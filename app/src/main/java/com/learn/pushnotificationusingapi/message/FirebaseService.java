package com.learn.pushnotificationusingapi.message;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.learn.pushnotificationusingapi.MainActivity;
import com.learn.pushnotificationusingapi.R;

import java.util.Random;

public class FirebaseService extends FirebaseMessagingService {
    public static final String CHANNEL_ID="truthy";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("xaxxy",remoteMessage.getData().get("title")+"x");

        Intent intent=new Intent(this, MainActivity.class);
        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId=new Random().nextInt();

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pi=PendingIntent.getActivities(this,0,new Intent[]{intent},PendingIntent.FLAG_ONE_SHOT);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "default", NotificationManager.IMPORTANCE_DEFAULT));
        }



            Log.d("xaxxy",Build.VERSION.SDK_INT+"x");

        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("message"))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .build();

        manager.notify(notificationId,notification);

    }
}
