package com.by5388.ditiezu.main;

import android.content.Context;

import com.by5388.ditiezu.bean.PageData;
import com.by5388.ditiezu.detail.DetailActivity;

/**
 * @author by5388  on 2019/12/15.
 */
public class PageViewModel {
    private final PageData mMainData;
    private final Context mContext;

    public PageViewModel(PageData mainData, Context context) {
        mMainData = mainData;
        mContext = context;
    }

    public String getTitle() {
        return mMainData.mName;
    }

    public void showDetail() {
        mContext.startActivity(DetailActivity.newIntent(mContext, mMainData));
    }
}
