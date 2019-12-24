package com.by5388.ditiezu.search;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/24.
 */
public class SearchActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return SearchFragment.newInstance();
    }
}
