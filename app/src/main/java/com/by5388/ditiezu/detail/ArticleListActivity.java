package com.by5388.ditiezu.detail;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleListActivity extends SingleFragmentActivity {
    private static final String INTEGER_INDEX = "index";
    private static final int DEFAULT_INDEX = 46;

    public static Intent newIntent(Context context, int index) {
        final Intent intent = new Intent(context, ArticleListActivity.class);
        intent.putExtra(INTEGER_INDEX, index);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        final int index = getIntent().getIntExtra(INTEGER_INDEX, DEFAULT_INDEX);
        return ArticleListFragment.newInstance(index);
    }
}
