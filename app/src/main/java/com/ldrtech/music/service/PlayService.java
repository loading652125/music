package com.ldrtech.music.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ldrtech.music.application.Notifier;
import com.ldrtech.music.constants.Actions;

/**
 * Created by zjs on 2018/3/25.
 */

public class PlayService extends Service {

    private static final String TAG = "Service";

    public class PlayBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " + getClass().getSimpleName());
        AudioPlayer.get().init(this);
        MediaSessionManager.get().init(this);
        Notifier.get().init(this);
        QuitTimer.get().init(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }

    public static void startCommand(Context context, String action) {
        Intent intent = new Intent(context, PlayService.class);
        intent.setAction(action);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case Actions.ACTION_STOP:
                    stop();
                    break;
            }
        }
        return START_NOT_STICKY;
    }

    private void stop() {
        AudioPlayer.get().stopPlayer();
        QuitTimer.get().stop();
        Notifier.get().cancelAll();
    }
}
