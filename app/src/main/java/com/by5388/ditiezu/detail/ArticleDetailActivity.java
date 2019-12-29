package com.by5388.ditiezu.detail;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/18.
 */
public class ArticleDetailActivity extends SingleFragmentActivity {
    private static final String ARTICLE_URL = "article_url";

    public static Intent newIntent(Context context, String url) {
        final Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(ARTICLE_URL, url);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        final String url = getIntent().getStringExtra(ARTICLE_URL);
        return ArticleDetailFragment.newInstance(url);
    }


}
