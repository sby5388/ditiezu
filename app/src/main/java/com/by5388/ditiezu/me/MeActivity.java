package com.by5388.ditiezu.me;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/18.
 */
public class MeActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return MeFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MeActivity.class);
    }
}
