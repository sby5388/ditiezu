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
        // TODO: 2019/12/19 严格模式检查中，出现了 "via Binder call with stack"的提示，
        //  在堆栈中出现“# via Binder call with stack”字样代表该严格模式问题来自为跨进程调用
        final Context context = view.getContext();
//        context.startActivity(DetailActivity.newIntent(context, mMainData));
        context.startActivity(ArticleListActivity.newIntent(context, mMainData));
    }
}
