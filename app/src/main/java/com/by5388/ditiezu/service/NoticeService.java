package com.by5388.ditiezu.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * check software upgrade and newMessage
 *
 * @author Administrator  on 2019/12/30.
 */
public class NoticeService extends IntentService {


    public static final String TAG = NoticeService.class.getSimpleName();

    public NoticeService() {
        super(TAG);
    }

    public static Intent newIntent(Context context) {
        final Intent intent = new Intent(context, NoticeService.class);
        return intent;
    }

    public static void setPublishEnable(@NonNull Context context, boolean enable) {
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) {
            Log.e(TAG, "setPublishEnable: ", new Exception("alarmManager == null"));
            return;
        }
        final Intent service = newIntent(context);
        final PendingIntent pendingIntent = PendingIntent.getService(context, 100, service, 0);
        if (enable) {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), TimeUnit.MINUTES.toMillis(30), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    public static boolean isPublishEnable(Context context) {
        final Intent service = newIntent(context);
        final PendingIntent pendingIntent = PendingIntent.getService(context, 100, service, PendingIntent.FLAG_NO_CREATE);
        return pendingIntent != null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: action = " + intent.getAction());
    }
}
