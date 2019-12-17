package com.by5388.ditiezu.bean;

/**
 * @author Administrator  on 2019/12/16.
 */
public class ArticleBean {
    /**
     * String-url
     */
    public final String mUrl;
    public final String mTitle;
    public final String mAuthor;
    public final String mComment;
    public final String mDate;
    public int mType;

    public ArticleBean(String url, String title, String author, String date, String comment) {
        mUrl = url;
        mTitle = title;
        mAuthor = author;
        mComment = comment;
        mDate = date;
    }
}
