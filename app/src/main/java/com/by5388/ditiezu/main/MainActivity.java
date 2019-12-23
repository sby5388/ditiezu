package com.by5388.ditiezu.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.start.StartTools;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.List;

import static com.by5388.ditiezu.main.MainFragment.INTENT_FILTER_LOAD_FINISH;

/**
 * @author by5388  on 2019/12/22.
 */
public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    private boolean mRefresh = false;
    private MainFragmentAdapter mAdapter;
    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        mAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void refresh() {
        // TODO: 2019/12/22 可能需要把刷新工具做一个单例
        if (mRefresh) {
            show("刷新中…");
            return;
        }
        mRefresh = true;
        show("开始刷新");
        final DitiezuApp app = DitiezuApp.getInstance();
        app.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final StartTools startTools = StartTools.getInstance();
                try {
                    startTools.loadData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final List<ModuleBean> moduleBeans = startTools.getModuleBeans();
                if (moduleBeans == null || moduleBeans.isEmpty()) {
                    // TODO: 2019/12/23 补充刷新失败
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh = false;
                        MainActivity.this.sendBroadcast(new Intent(INTENT_FILTER_LOAD_FINISH));
                        MainActivity.this.show("刷新成功");
                    }
                });
            }
        });

    }

    private void show(String s) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
