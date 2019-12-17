package com.by5388.ditiezu.detail;

import android.widget.Toast;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.bean.ArticleBean;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleViewModel {

    private final ArticleBean mArticleBean;

    public ArticleViewModel(ArticleBean articleBean) {
        mArticleBean = articleBean;
    }

    public String getTitle() {
        return mArticleBean.mTitle;
    }

    public String getAuthor() {
        return mArticleBean.mAuthor;
    }

    public String getComment() {
        return String.valueOf(mArticleBean.mComment);
    }

    public String getDate() {
        return mArticleBean.mDate;
    }

    public void onClick() {
        // TODO: 2019/12/17
        final DitiezuApp app = DitiezuApp.getInstance();
        Toast.makeText(app, mArticleBean.mTitle, Toast.LENGTH_SHORT).show();
    }

}
