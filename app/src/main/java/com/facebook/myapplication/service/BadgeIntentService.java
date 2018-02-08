package com.facebook.myapplication.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import com.facebook.myapplication.R;
import com.facebook.myapplication.util.Logger;

import me.leolin.shortcutbadger.ShortcutBadger;

public class BadgeIntentService extends IntentService {

    private int notificationId = 0;

    public BadgeIntentService() {
        super("BadgeIntentService");
    }

    private NotificationManager mNotificationManager;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Logger.e("badgeIntentservice", "onStart");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Logger.e("badgeIntentservice", "onHandleIntent");
        if (intent != null) {
            int badgeCount = intent.getIntExtra("badgeCount", 0);
            String alert = intent.getStringExtra("alert");

            if (mNotificationManager != null) {
                mNotificationManager.cancel(notificationId);
                notificationId++;
            }

            Notification.Builder builder = new Notification.Builder(getApplicationContext())
                    .setContentTitle("")
                    .setContentText(alert)
                    .setSmallIcon(R.drawable.ic_launcher);
            Notification notification = builder.build();
            ShortcutBadger.applyNotification(getApplicationContext(), notification, badgeCount);
            if (mNotificationManager != null) {
                mNotificationManager.notify(notificationId, notification);
            }
        }
    }
}
