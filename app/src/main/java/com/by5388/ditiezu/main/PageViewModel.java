package com.by5388.ditiezu.main;

import android.content.Context;
import android.view.View;

import com.by5388.ditiezu.bean.PageData;
import com.by5388.ditiezu.detail.ArticleListActivity;

/**
 * @author by5388  on 2019/12/15.
 */
public class PageViewModel {
    private final PageData mMainData;

    public PageViewModel(PageData mainData) {
        mMainData = mainData;
    }

    public String getTitle() {
        return mMainData.mName;
    }

    public void showDetail(View view) {
        final Context context = view.getContext();
//        context.startActivity(DetailActivity.newIntent(context, mMainData));
        context.startActivity(ArticleListActivity.newIntent(context, mMainData));
    }
}
