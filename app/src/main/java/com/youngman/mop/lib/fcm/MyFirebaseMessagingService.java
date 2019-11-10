package com.youngman.mop.lib.fcm;

import android.app.ActivityManager;
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
import com.youngman.mop.view.fcmalert.FcmAlertActivity;
import com.youngman.mop.view.clubstatistics.ClubStatisticsActivity;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-27.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "Channel ID";
    private final long[] VIBRATE = {500, 1000, 500, 1000};

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        LogUtils.logDebug(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            notifyBackground(remoteMessage);
//        }
//
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            notifyBackground(remoteMessage);
            if (isAppRunning(getApplicationContext())) {//포그라운드
                notifyForeground(remoteMessage);
            } else {//백그라운드
                notifyBackground(remoteMessage);
            }
        }

    }

    private boolean isAppRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        for (int i = 0; i < procInfos.size(); i++) {
            if (procInfos.get(i).processName.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private void notifyForeground(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, FcmAlertActivity.class);
        intent.putExtra("EXTRA_MESSAGE", remoteMessage.getNotification().getBody());
        startActivity(intent);
    }

    private void notifyBackground(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, ClubStatisticsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationCompat.setContentIntent(pendingIntent);
        notificationCompat.setWhen(System.currentTimeMillis());
        notificationCompat.setSmallIcon(R.drawable.common_google_signin_btn_icon_light);
        notificationCompat.setContentTitle(remoteMessage.getNotification().getTitle());
        notificationCompat.setContentText(remoteMessage.getNotification().getBody());
        notificationCompat.setVibrate(VIBRATE);
        notificationCompat.setSound(uri);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notificationCompat.build());
    }
}
