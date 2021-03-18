package com.by5388.ditiezu.article.list;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.BaseSingleFragmentActivity;
import com.by5388.ditiezu.main.CityBean;

import java.util.regex.Pattern;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleListActivityBase extends BaseSingleFragmentActivity {
    private static final String INTEGER_INDEX = "index";
    private static final String STRING_TITLE = "title";
    private static final String STRING_DESCRIBE = "describe";
    private static final String STRING_ICON_URL = "url";
    private static final int DEFAULT_INDEX = 46;


    public static Intent newIntent(Context context, CityBean cityBean) {
        final int index = Integer.parseInt(Pattern.compile("[^0-9]").matcher(cityBean.mUrl).replaceAll("").trim());
        final Intent intent = new Intent(context, ArticleListActivityBase.class);
        intent.putExtra(INTEGER_INDEX, index);
        intent.putExtra(STRING_TITLE, cityBean.mName);
        intent.putExtra(STRING_DESCRIBE, cityBean.mDescribe);
        intent.putExtra(STRING_ICON_URL, cityBean.mIconUrl);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        final Intent intent = getIntent();
        final int index = intent.getIntExtra(INTEGER_INDEX, DEFAULT_INDEX);
        final String title = intent.getStringExtra(STRING_TITLE);
        final String describe = intent.getStringExtra(STRING_DESCRIBE);
        final String iconUrl = intent.getStringExtra(STRING_ICON_URL);
        return ArticleListFragment.newInstance(index, title, describe, iconUrl);
    }
}
