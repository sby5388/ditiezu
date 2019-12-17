package com.by5388.ditiezu.detail;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.by5388.ditiezu.SingleFragmentActivity;
import com.by5388.ditiezu.bean.PageData;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleListActivity extends SingleFragmentActivity {
    private static final String INTEGER_INDEX = "index";
    private static final String STRING_TITLE = "title";
    private static final int DEFAULT_INDEX = 46;

    public static Intent newIntent(Context context, PageData pageData) {
        final Intent intent = new Intent(context, ArticleListActivity.class);
        intent.putExtra(INTEGER_INDEX, pageData.mIndex);
        intent.putExtra(STRING_TITLE, pageData.mName);
        return intent;
    }

    @Override
    public Fragment createFragment() {

        final Intent intent = getIntent();
        final int index = intent.getIntExtra(INTEGER_INDEX, DEFAULT_INDEX);
        final String title = intent.getStringExtra(STRING_TITLE);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        return ArticleListFragment.newInstance(index);
    }
}
