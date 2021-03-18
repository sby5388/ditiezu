package com.by5388.ditiezu.start;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.by5388.ditiezu.BaseSingleFragmentActivity;
import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.data.DataViewModel;
import com.by5388.ditiezu.main.MainActivity;
import com.by5388.ditiezu.main.ModuleBean;

import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author by5388  on 2019/12/22.
 * TODO 数据获取使用viewModel
 */
public class StartActivity extends BaseSingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return StartFragment.newInstance();
    }

    public static final String TAG = StartActivity.class.getSimpleName();
    private Runnable mRunnableUi = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(StartActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
        }
    };
    private Runnable mRunnableUiError = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(StartActivity.this, "没有获取到正确的数据", Toast.LENGTH_SHORT).show();
        }
    };
    private Runnable mRunnableToMain = new Runnable() {
        @Override
        public void run() {
            final Activity activity = StartActivity.this;
            activity.startActivity(MainActivity.newIntent(activity));
            activity.finish();
        }
    };
    private Runnable mRunnableLoadData = new Runnable() {
        @Override
        public void run() {
            final StartTools tools = StartTools.getInstance();
            try {
                tools.loadData();
            } catch (IOException e) {
                runOnUiThread(mRunnableUi);
                return;
            }
            final List<ModuleBean> moduleBeans = tools.getModuleBeans();
            if (moduleBeans == null || moduleBeans.isEmpty()) {
                runOnUiThread(mRunnableUiError);
                return;
            }
            DitiezuApp.getInstance().setModuleBeans(moduleBeans);
            runOnUiThread(mRunnableToMain);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DataViewModel dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getMutableLiveData();
    }
}
