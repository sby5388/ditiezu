package com.by5388.ditiezu.bean;

/**
 * @author Administrator  on 2019/12/16.
 */
public class ArticleBean {
    /**
     * String-url
     */
    public final int mArticleId;
    public final String mTitle;
    public final String mAuthor;
    public final int mComment;
    public final String mDate;
    public int mType;

    public ArticleBean(int articleId, String title, String author, String date, int comment) {
        mArticleId = articleId;
        mTitle = title;
        mAuthor = author;
        mComment = comment;
        mDate = date;
    }
}
