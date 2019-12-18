package com.by5388.ditiezu.main;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator
 */
public class MainActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return PageListFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
