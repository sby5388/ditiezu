package com.by5388.ditiezu.start;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.main.MainActivity;
import com.by5388.ditiezu.main.ModuleBean;

import java.io.IOException;
import java.util.List;

/**
 * @author by5388  on 2019/12/22.
 */
public class StartActivity extends AppCompatActivity {
    public static final String TAG = StartActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ProgressBar(this));
        final DitiezuApp app = DitiezuApp.getInstance();
        final List<ModuleBean> moduleBeans = app.getModuleBeans();
        if (moduleBeans == null || moduleBeans.isEmpty()) {
            app.getExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    final StartTools tools = StartTools.getInstance();
                    final StartActivity activity = StartActivity.this;
                    try {
                        tools.loadData();
                    } catch (final IOException e) {
                        Log.e(TAG, "run: ", e);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "run: ", e);
                                Toast.makeText(activity, "网络异常", Toast.LENGTH_SHORT).show();
//                                activity.finish();
                            }
                        });
                        return;
                    }
                    final List<ModuleBean> beans = tools.getModuleBeans();
                    if (beans == null || beans.isEmpty()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "run: ", new Exception());
                                Toast.makeText(activity, "网络异常", Toast.LENGTH_SHORT).show();
//                                activity.finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                app.setModuleBeans(beans);
                                activity.startActivity(MainActivity.newIntent(activity));
                                activity.finish();
                            }
                        });
                    }
                }
            });
        } else {
            startActivity(MainActivity.newIntent(this));
            this.finish();
        }
    }
}
