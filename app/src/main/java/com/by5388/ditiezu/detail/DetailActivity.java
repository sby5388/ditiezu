package com.by5388.ditiezu.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.by5388.ditiezu.SingleFragmentActivity;
import com.by5388.ditiezu.main.PageData;

/**
 * @author by5388  on 2019/12/15.
 */
public class DetailActivity extends SingleFragmentActivity {


    private static final String DATA_KEY = "main_data";
    private static final String STRING_TITLE = "title";
    private static final String INTEGER_ID = "id";
    private DetailFragment mDetailFragment;

    public static Intent newIntent(Context context, PageData mainData) {
        final Intent intent = new Intent(context, DetailActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putString(STRING_TITLE, mainData.mName);
        bundle.putInt(INTEGER_ID, mainData.mIndex);
        intent.putExtra(DATA_KEY, bundle);
        return intent;

    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof DetailFragment) {
            mDetailFragment = (DetailFragment) fragment;
        }
    }

    @Override
    public Fragment createFragment() {
        final Bundle bundle = getIntent().getBundleExtra(DATA_KEY);
        if (bundle == null) {
            return new DetailFragment();
        }
        final PageData mainData = new PageData(bundle.getInt(INTEGER_ID), bundle.getString(STRING_TITLE));

        return DetailFragment.newInstance(mainData);
    }

    @Override
    public void onBackPressed() {
        if (mDetailFragment != null) {
            final boolean handle = mDetailFragment.isKeyDown(KeyEvent.KEYCODE_BACK);
            if (handle) {
                return;
            }
        }
        super.onBackPressed();
    }
}
