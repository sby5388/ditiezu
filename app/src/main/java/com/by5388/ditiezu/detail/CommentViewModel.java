package com.by5388.ditiezu.detail;

import com.by5388.ditiezu.bean.ArticleCommentBean;

/**
 * @author Administrator  on 2019/12/18.
 */
public class CommentViewModel {

    private final ArticleCommentBean mBean;

    public CommentViewModel(ArticleCommentBean bean) {
        mBean = bean;
    }

    public String getUrlIcon() {
        return mBean.mUserIconUrl;
    }

    public String getUrlAuthor() {
        return mBean.mUserUrl;
    }

    public String getAuthor() {
        return mBean.mUserName;
    }

    public String getDate() {
        return mBean.mDate;
    }

    public String getTag() {
        return mBean.mTag;
    }

    public String getComment() {
        return mBean.mComment;
    }

    public String getUrlReply() {
        return mBean.mActionUrl;
    }
}
