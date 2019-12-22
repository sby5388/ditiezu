package com.by5388.ditiezu.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.by5388.ditiezu.bean.PageData;
import com.by5388.ditiezu.detail.ArticleListActivity2;

import java.util.regex.Pattern;

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

    public void toDetail(View view) {
        final Context context = view.getContext();
        final int index = Integer.parseInt(Pattern.compile("[^0-9]").matcher(mCityBean.mUrl).replaceAll("").trim());
        final PageData pageData = new PageData(index, mCityBean.mName);
        final Intent intent = ArticleListActivity2.newIntent(context, pageData);
        context.startActivity(intent);
    }
}
