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

    public void setSortName(String sortName) {
        mSortName = sortName;
    }

    public void setSortType(int sortType) {
        mSortType = sortType;
    }

    public void setSortUrl(String sortUrl) {
        mSortUrl = sortUrl;
    }

    public void setReplyCount(String replyCount) {
        mReplyCount = replyCount;
    }

    public void setReply(String reply) {
        mReply = reply;
    }

    public void setReplyUrl(String replyUrl) {
        mReplyUrl = replyUrl;
    }

    public void setContentId(String contentId) {
        mContentId = contentId;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setMainContent(String mainContent) {
        mMainContent = mainContent;
    }

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public void setAuthorUrl(String authorUrl) {
        mAuthorUrl = authorUrl;
    }

    public void setFav(String fav) {
        mFav = fav;
    }

    public void setFavUrl(String favUrl) {
        mFavUrl = favUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setPostId(String postId) {
        mPostId = postId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<ArticleCommentBean> getCommentBeans() {
        return mCommentBeans;
    }

    public String getPostId() {
        return mPostId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getFav() {
        return mFav;
    }

    public String getFavUrl() {
        return mFavUrl;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getAuthorUrl() {
        return mAuthorUrl;
    }

    public String getMainContent() {
        return mMainContent;
    }

    public String getDate() {
        return mDate;
    }

    public String getContentId() {
        return mContentId;
    }

    public String getReply() {
        return mReply;
    }

    public String getReplyUrl() {
        return mReplyUrl;
    }

    public String getSortName() {
        return mSortName;
    }

    public int getSortType() {
        return mSortType;
    }

    public String getSortUrl() {
        return mSortUrl;
    }

    public String getReplyCount() {
        return mReplyCount;
    }
}
