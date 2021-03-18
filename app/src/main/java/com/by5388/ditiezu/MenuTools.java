package com.by5388.ditiezu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.main.MainActivity;
import com.by5388.ditiezu.settings.SettingsActivity;

/**
 * @author Administrator  on 2019/12/21.
 */
public class MenuTools {
    public static void toMain(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        final Intent intent = MainActivity.newIntent(activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void toMe(Context context) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
//        context.startActivity(MeActivity.newIntent(context));
        context.startActivity(SettingsActivity.newIntent(context));
    }
}
