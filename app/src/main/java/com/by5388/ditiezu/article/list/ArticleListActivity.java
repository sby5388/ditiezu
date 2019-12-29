package com.by5388.ditiezu.article.list;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.by5388.ditiezu.SingleFragmentActivity;
import com.by5388.ditiezu.main.CityBean;

import java.util.regex.Pattern;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleListActivity extends SingleFragmentActivity {
    private static final String INTEGER_INDEX = "index";
    private static final String STRING_TITLE = "title";
    private static final String STRING_TITLE2 = "title2";
    private static final String STRING_ICON_URL = "url";
    private static final int DEFAULT_INDEX = 46;


    public static Intent newIntent(Context context, CityBean cityBean) {
        final int index = Integer.parseInt(Pattern.compile("[^0-9]").matcher(cityBean.mUrl).replaceAll("").trim());
        final Intent intent = new Intent(context, ArticleListActivity.class);
        intent.putExtra(INTEGER_INDEX, index);
        intent.putExtra(STRING_TITLE, cityBean.mName);
        intent.putExtra(STRING_TITLE2, cityBean.mDescribe);
        intent.putExtra(STRING_ICON_URL, cityBean.mIconUrl);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        final Intent intent = getIntent();
        final int index = intent.getIntExtra(INTEGER_INDEX, DEFAULT_INDEX);
        final String title = intent.getStringExtra(STRING_TITLE);
        final String title2 = intent.getStringExtra(STRING_TITLE2);
        final String iconUrl = intent.getStringExtra(STRING_ICON_URL);
        return ArticleListFragment.newInstance(index, title,title2, iconUrl);
    }
}
