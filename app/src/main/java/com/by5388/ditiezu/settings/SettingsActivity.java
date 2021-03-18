package com.by5388.ditiezu.settings;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.BaseSingleFragmentActivity;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/30.
 */
public class SettingsActivity extends BaseSingleFragmentActivity {
    public static Intent newIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return SettingsFragment.newInstance();
    }
}
