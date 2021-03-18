package com.by5388.ditiezu.detail;

import com.by5388.ditiezu.bean.ArticleCommentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2019/12/29.
 */
public class ArticleDetailBean {
    private List<ArticleCommentBean> mCommentBeans;
    private String mPostId;
    private String mTitle;
    private String mUrl;
    private String mFav = "收藏";

    private String mFavUrl;


    private String mAuthorName;
    private String mAuthorUrl;

    private String mMainContent;

    private String mDate;
    private String mContentId;
    private String mReply;
    private String mReplyUrl;

    //正序、逆序
    private String mSortName;
    //0：正序浏览，1：逆序浏览
    private int mSortType;

    private String mSortUrl;

    private String mReplyCount;


    public ArticleDetailBean() {
        mCommentBeans = new ArrayList<>();
    }

    void addComment(ArticleCommentBean bean) {
        this.mCommentBeans.add(bean);
    }

    public List<ArticleCommentBean> getCommentBeans() {
        return mCommentBeans;
    }

    public String getPostId() {
        return mPostId;
    }

    public void setPostId(String postId) {
        mPostId = postId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getFav() {
        return mFav;
    }

    public void setFav(String fav) {
        mFav = fav;
    }

    public String getFavUrl() {
        return mFavUrl;
    }

    public void setFavUrl(String favUrl) {
        mFavUrl = favUrl;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public String getAuthorUrl() {
        return mAuthorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        mAuthorUrl = authorUrl;
    }

    public String getMainContent() {
        return mMainContent;
    }

    public void setMainContent(String mainContent) {
        mMainContent = mainContent;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getContentId() {
        return mContentId;
    }

    public void setContentId(String contentId) {
        mContentId = contentId;
    }

    public String getReply() {
        return mReply;
    }

    public void setReply(String reply) {
        mReply = reply;
    }

    public String getReplyUrl() {
        return mReplyUrl;
    }

    public void setReplyUrl(String replyUrl) {
        mReplyUrl = replyUrl;
    }

    public String getSortName() {
        return mSortName;
    }

    public void setSortName(String sortName) {
        mSortName = sortName;
    }

    public int getSortType() {
        return mSortType;
    }

    public void setSortType(int sortType) {
        mSortType = sortType;
    }

    public String getSortUrl() {
        return mSortUrl;
    }

    public void setSortUrl(String sortUrl) {
        mSortUrl = sortUrl;
    }

    public String getReplyCount() {
        return mReplyCount;
    }

    public void setReplyCount(String replyCount) {
        mReplyCount = replyCount;
    }
}
