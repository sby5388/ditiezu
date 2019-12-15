package com.by5388.ditiezu;

import android.app.Application;
import android.os.StrictMode;

/**
 * @author by5388  on 2019/12/14.
 */
public class DitiezuApp extends Application {
    private static DitiezuApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        StrictMode.enableDefaults();
    }

    public static DitiezuApp getInstance() {
        return sInstance;
    }
}
