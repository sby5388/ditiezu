package com.by5388.ditiezu.bean;

import androidx.annotation.NonNull;

/**
 * @author Administrator  on 2019/12/18.
 */
public class ArticleCommentBean {
    public String mPostId;
    public String mUserIconUrl;
    public String mUserUrl;
    public String mUserName;
    public String mDate;
    public String mTag;
    public String mTagColor;
    public String mComment;
    public String mCommentId;
    public String mActionUrl;
    public String mActionName;

    public ArticleCommentBean() {
    }

    public ArticleCommentBean(String userIconUrl, String userUrl, String userName, String date, String tag, String comment, String urlReply) {
        mUserIconUrl = userIconUrl;
        mUserUrl = userUrl;
        mUserName = userName;
        mDate = date;
        mTag = tag;
        mComment = comment;
        mActionUrl = urlReply;
    }


    public void setTagColor(String tagColor) {
        mTagColor = tagColor;
    }

    public void setCommentId(String commentId) {
        mCommentId = commentId;
    }

    public void setPostId(String postId) {
        mPostId = postId;
    }

    public void setUserIconUrl(String userIconUrl) {
        mUserIconUrl = userIconUrl;
    }

    public void setUserUrl(String userUrl) {
        mUserUrl = userUrl;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public void setActionUrl(String actionUrl) {
        mActionUrl = actionUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "ArticleCommentBean{" +
                "mUserIconUrl='" + mUserIconUrl + '\'' +
                ", mUserUrl='" + mUserUrl + '\'' +
                ", mUserName='" + mUserName + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mTag='" + mTag + '\'' +
                ", mComment='" + mComment + '\'' +
                ", mActionUrl='" + mActionUrl + '\'' +
                '}';
    }
}
