package com.by5388.ditiezu.bean;

/**
 * @author Administrator  on 2019/12/16.
 */
public class ArticleBean {
    public static final String STRING_TAG_TOP = "./mplus/img/p_1.png";
    public static final String STRING_TAG_VOTE = "./mplus/img/p1.png";
    public static final String STRING_TAG_CLOSE = "./mplus/img/l1.png";
    /**
     * String-url
     */
    public final String mUrl;
    public final String mTitle;
    public final String mAuthor;
    public final String mComment;
    public final String mDate;
    public int mType;
    private ArticleType mArticleType = ArticleType.Normal;
    private String mTag;
    public ArticleBean(String url, String title, String author, String date, String comment) {
        mUrl = url;
        mTitle = title;
        mAuthor = author;
        mComment = comment;
        mDate = date;
    }

    public void setTag(String tag) {
        mTag = tag;
        switch (tag) {
            case STRING_TAG_TOP:
                mArticleType = ArticleType.Top;
                break;
            case STRING_TAG_CLOSE:
                mArticleType = ArticleType.Close;
                break;
            case STRING_TAG_VOTE:
                mArticleType = ArticleType.Vote;
                break;
            default:
                break;
        }
    }


    public ArticleType getArticleType() {
        return mArticleType;
    }

    public enum ArticleType {
        Normal, Vote, Top, Close
    }
}
