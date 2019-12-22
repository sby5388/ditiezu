package com.by5388.ditiezu.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

/**
 * @author by5388  on 2019/12/22.
 */
public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    private boolean mRefresh = false;
    private MainFragmentAdapter mAdapter;

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
    public void refresh() {
        // TODO: 2019/12/22 可能需要把刷新工具做一个单例
        if (mRefresh) {
            Toast.makeText(this, "刷新中…", Toast.LENGTH_SHORT).show();
            return;
        }
        mRefresh = true;
        Toast.makeText(this, "开始刷新", Toast.LENGTH_SHORT).show();
        final DitiezuApp app = DitiezuApp.getInstance();
        app.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final StartTools startTools = new StartTools();
                try {
                    startTools.loadData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final List<ModuleBean> moduleBeans = startTools.getModuleBeans();
                if (moduleBeans == null || moduleBeans.isEmpty()) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh = false;
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "结束", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
