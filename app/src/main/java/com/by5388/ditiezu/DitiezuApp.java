package com.by5388.ditiezu;

import android.app.Application;
import android.os.StrictMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author by5388  on 2019/12/14.
 */
public class DitiezuApp extends Application {
    private static DitiezuApp sInstance;
    private Executor mExecutor;
    public static final boolean DEV_MODE = false;

    private boolean mLogin = false;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        android.os.StrictMode.enableDefaults();
        mExecutor = Executors.newFixedThreadPool(1);
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

    public static DitiezuApp getInstance() {
        return sInstance;
    }

    public Executor getExecutor() {
        return mExecutor;
    }
}
