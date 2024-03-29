package com.by5388.ditiezu;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author by5388  on 2019/12/15.
 */
public abstract class BaseSingleFragmentActivity extends AppCompatActivity {
    public abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // TODO: 2019/12/19 异常  android.os.strictmode.DiskReadViolation
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment == null) {
            fragmentManager.beginTransaction().replace(R.id.container, createFragment()).commit();
        }
    }

    @LayoutRes
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }
}
