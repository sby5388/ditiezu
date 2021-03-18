package com.by5388.ditiezu;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;

import com.by5388.ditiezu.main.ModuleBean;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author by5388  on 2019/12/14.
 */
public class DitiezuApp extends Application implements Executor {
    public static final boolean DEV_MODE = false;
    private static final String TAG = DitiezuApp.class.getSimpleName();
    private static DitiezuApp sInstance;
    private Executor mExecutor;
    private List<ModuleBean> mModuleBeans;
    private Handler mHandler;
    private Handler mWorkerHandler;
    private boolean mLogin = false;
    private boolean mUseHandler = true;
    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onActivityCreated: " + activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            Log.d(TAG, "onActivityStarted: " + activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
            Log.d(TAG, "onActivityResumed: " + activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
            Log.d(TAG, "onActivityPaused: " + activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            Log.d(TAG, "onActivityStopped: " + activity.getClass().getSimpleName());
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            Log.d(TAG, "onActivitySaveInstanceState: " + activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            Log.d(TAG, "onActivityDestroyed: " + activity.getClass().getSimpleName());
        }
    };

    public static DitiezuApp getInstance() {
        return sInstance;
    }

    public synchronized List<ModuleBean> getModuleBeans() {
        return mModuleBeans;
    }

    public void setModuleBeans(final List<ModuleBean> moduleBeans) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mModuleBeans = moduleBeans;
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mHandler = new Handler(Looper.getMainLooper());
        final HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        mWorkerHandler = new Handler(thread.getLooper());

        sInstance = this;

        if (BuildConfig.DEBUG) {
            registerActivityLifecycleCallbacks(mCallbacks);
        }
//        android.os.StrictMode.enableDefaults();
        mExecutor = Executors.newFixedThreadPool(1);
        initStrictMode();
    }

    /**
     * 开启严格模式，测试版
     */
    private void initStrictMode() {
        if (DEV_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyDialog() //弹出违规提示对话框
                    .penaltyLog() //在Logcat 中打印违规异常信息
                    .penaltyFlashScreen() //API等级11
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects() //API等级11
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        //————————————————
        //        版权声明：本文为CSDN博主「猴子搬来的救兵Castiel」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        //        原文链接：https://blog.csdn.net/mynameishuangshuai/article/details/51742375
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //真机永远不会执行到，只有模拟器才会调用到
        Log.d(TAG, "onTerminate: ");
        if (BuildConfig.DEBUG) {
            unregisterActivityLifecycleCallbacks(mCallbacks);
        }
    }

    @Override
    public void execute(@NonNull Runnable command) {
        if (mUseHandler) {
            mWorkerHandler.post(command);
            return;
        }
        mExecutor.execute(command);
    }

    public boolean isLogin() {
        // TODO: 2020/1/2
        if (true) {
            return false;
        }
        return mLogin;
    }

    public Executor getExecutor() {
        return mExecutor;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public Handler getWorkerHandler() {
        return mWorkerHandler;
    }
}
