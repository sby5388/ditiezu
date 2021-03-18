package com.by5388.ditiezu.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.by5388.ditiezu.article.list.ArticleListActivityBase;

import androidx.annotation.Keep;

/**
 * @author by5388  on 2019/12/22.
 */
public class CityViewModel {
    private final CityBean mCityBean;

    public CityViewModel(CityBean cityBean) {
        mCityBean = cityBean;
    }

    public String getDynamic() {
        return mCityBean.mDynamic;
    }

    public String getName() {
        return mCityBean.mName;
    }

    public String getDescribe() {
        return mCityBean.mDescribe;
    }

    public String getIconUrl() {
        return mCityBean.mIconUrl;
    }

    public String getUrl() {
        return mCityBean.mUrl;
    }

    @Keep
    public void toDetail(View view) {
        final Context context = view.getContext();
        final Intent intent = ArticleListActivityBase.newIntent(context, mCityBean);
        context.startActivity(intent);
    }
}
