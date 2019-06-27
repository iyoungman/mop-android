package com.youngman.mop.lib.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.youngman.mop.R;
import com.youngman.mop.util.LogUtils;
import com.youngman.mop.view.map.MapActivity;

/**
 * Created by YoungMan on 2019-06-27.
 */

public class PushNotificationService extends FirebaseMessagingService {

    private final String CHANNEL_ID = getString(R.string.default_notification_channel_id);
    private final long[] VIBRATE = {500,1000, 500, 1000};


    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        LogUtils.logDebug(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getNotification() != null) {
            handleNotification(remoteMessage);
        }
    }

    private void handleNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, MapActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationCompat.setContentIntent(pendingIntent);
        notificationCompat.setWhen(System.currentTimeMillis());
        notificationCompat.setContentTitle(remoteMessage.getNotification().getTitle());
        notificationCompat.setContentText(remoteMessage.getNotification().getBody());
        notificationCompat.setVibrate(VIBRATE);
        notificationCompat.setSound(uri);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, notificationCompat.build());
    }
}
