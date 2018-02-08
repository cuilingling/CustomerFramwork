package com.facebook.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.facebook.myapplication.util.Logger;

import me.leolin.shortcutbadger.ShortcutBadger;


/**
 * 类名: BadgeService
 * 此类用途: ---
 *
 * @Author: peixi
 * @Date: 2018-02-02 10:47
 * @Email: cr7inmanchester.com
 * @FileName: com.facebook.myapplication.service.BadgeService.java
 */
public class BadgeService extends Service {
    private String tag = BadgeService.class.getSimpleName();
    private int parseInt;

    public BadgeService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e(tag, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String count = intent.getStringExtra("badgeCount");
        Logger.e(tag, "onStartCommand" + count);
        try {
            parseInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Error input", Toast.LENGTH_SHORT).show();
        }
        Logger.e(tag, "onStartCommand+parseInt" + parseInt);
        ShortcutBadger.applyCount(getApplicationContext(), parseInt);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e(tag, "onDestroy");
    }
}
