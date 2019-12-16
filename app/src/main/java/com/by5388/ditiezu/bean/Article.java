package com.by5388.ditiezu.bean;

/**
 * @author Administrator  on 2019/12/16.
 */
public class Article {
    private final int mArticleId;
    private final String mTitle;
    private int type;

    public Article(int articleId, String title) {
        mArticleId = articleId;
        mTitle = title;
    }
}
