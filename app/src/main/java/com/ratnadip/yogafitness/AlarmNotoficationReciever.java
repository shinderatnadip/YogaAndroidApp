package com.ratnadip.yogafitness;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Ratnadip on 08-Apr-18.
 */

public class AlarmNotoficationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("It's Time")
        .setContentText("Time to Training")
                .setContentInfo("Info");
        NotificationManager notificationManager =(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());

    }
}
