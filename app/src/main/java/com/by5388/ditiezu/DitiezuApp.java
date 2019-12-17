package com.by5388.ditiezu;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author by5388  on 2019/12/14.
 */
public class DitiezuApp extends Application {
    private static DitiezuApp sInstance;
    private Executor mExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        StrictMode.enableDefaults();
        mExecutor = Executors.newFixedThreadPool(2);
    }

    public static DitiezuApp getInstance() {
        return sInstance;
    }

    public Executor getExecutor() {
        return mExecutor;
    }
}
