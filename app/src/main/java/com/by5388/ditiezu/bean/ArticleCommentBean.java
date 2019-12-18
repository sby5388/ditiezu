package com.by5388.ditiezu.bean;

/**
 * @author Administrator  on 2019/12/18.
 */
public class ArticleCommentBean  {
    public final String mUrlIcon;
    public final String mUrlAuthor;
    public final String mAuthor;
    public final String mDate;
    public final String mFloor;
    public final String mComment;
    public final String mUrlReply;

    public ArticleCommentBean(String urlIcon, String urlAuthor, String author, String date, String tag, String comment, String urlReply) {
        mUrlIcon = urlIcon;
        mUrlAuthor = urlAuthor;
        mAuthor = author;
        mDate = date;
        mFloor = tag;
        mComment = comment;
        mUrlReply = urlReply;
    }

    public String getUrlIcon() {
        return mUrlIcon;
    }

    public String getUrlAuthor() {
        return mUrlAuthor;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getFloor() {
        return mFloor;
    }

    public String getComment() {
        return mComment;
    }

    public String getUrlReply() {
        return mUrlReply;
    }

    @Override
    public String toString() {
        return "ArticleCommentBean{" +
                "mUrlIcon='" + mUrlIcon + '\'' +
                ", mUrlAuthor='" + mUrlAuthor + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mFloor='" + mFloor + '\'' +
                ", mComment='" + mComment + '\'' +
                ", mUrlReply='" + mUrlReply + '\'' +
                '}';
    }
}
