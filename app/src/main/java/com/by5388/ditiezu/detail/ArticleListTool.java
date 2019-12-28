package com.by5388.ditiezu.detail;

import android.net.TrafficStats;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.by5388.ditiezu.bean.ArticleBean;
import com.by5388.ditiezu.bean.ChooseItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author by5388  on 2019/12/17.
 */
public final class ArticleListTool {
    public static final String TAG = "ArticleListTool";
    //                                                                  forum.php?mod=forumdisplay&fid=7&mobile=yes
    private static final String BASE_URL = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=%d&mobile=yes&page=%d";
    //private static final String BASE_URL = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=46&mobile=yes&page=1";
    //TODO: 2019/12/28   未筛选时的链接
    //http://ditiezu.com/forum.php?mod=forumdisplay&fid=23&mobile=yes
    // TODO: 2019/12/28  类型筛选的链接
    //http://ditiezu.com/forum.php?mod=forumdisplay&fid=23&mobile=yes&filter=typeid&typeid=231


    private final int mPid;
    private int mPage = 1;
    private String mUrl;
    private String mDescribe = null;
    private QueryParam mCurrentQueryParam;

    private final List<ArticleBean> mArticleBeans;
    private final List<ChooseItem> mChooseItems;

    public ArticleListTool(int pid) {
        mPid = pid;

        mArticleBeans = new ArrayList<>();
        mChooseItems = new ArrayList<>();
    }

    public boolean enableLoadNextPage() {
        if (mCurrentQueryParam != null) {
            // TODO: 2019/12/18 getMaxPage
            return true;
        }

        return true;
    }

    public boolean isFirstPage() {
        return mCurrentQueryParam != null && mCurrentQueryParam.page == 1;
    }

    public boolean enableLoadPrePage() {
        return mCurrentQueryParam != null && mCurrentQueryParam.page > 1;
    }


    public void loadData(QueryParam queryParam) throws MalformedURLException, IOException {
        if (queryParam.fid != mPid) {
            queryParam.fid = mPid;
        }
        this.mCurrentQueryParam = queryParam;
        mPage = queryParam.page;
        mArticleBeans.clear();
        TrafficStats.setThreadStatsTag(10086);
        final Document parse = Jsoup.connect(queryParam.toString()).userAgent("iPhone").cookie("auth", "token").get();
        loadData(parse);
    }


    /**
     * @throws MalformedURLException
     * @throws IOException
     * @deprecated {@link #loadData(QueryParam)}
     */
    @Deprecated
    public void loadData() throws MalformedURLException, IOException {
        if (true) {
            // TODO: 2019/12/28
            loadData(mCurrentQueryParam);
            return;
        }
        mArticleBeans.clear();
        mUrl = String.format(Locale.getDefault(), BASE_URL, mPid, mPage);
        TrafficStats.setThreadStatsTag(10086);
        final Document parse = Jsoup.connect(mUrl).userAgent("iPhone").cookie("auth", "token").get();
        loadData(parse);
    }


    private void loadData(Document parse) {
        parse.charset(Charset.forName("utf-8"));
        final Elements topElements = parse.select("div[class=close]")
                .select("div[class=content]")
                .select("div[class=wp]")
                .select("div[class=ct]");
        if (TextUtils.isEmpty(mDescribe)) {
            mDescribe = topElements.select("div[class=pt]").select("p").text();
        }

        if (mChooseItems.isEmpty()) {
            //init chooseItems
            final Elements selectItems = topElements.select("div[class=thtyss]").select("a");
            for (Element item : selectItems) {
                final String name = item.text();
                final String path = item.attr("href");
                mChooseItems.add(new ChooseItem(path, name));
            }
        }
        //init article list
        final Elements allList = topElements.select("ul[id=alist]").select("li");
        for (Element element : allList) {
            mArticleBeans.add(createArticleBean(element));
        }
    }

    private static ArticleBean createArticleBean(Element element) {
        final String title = element.select("h1").text();
        final String tag = element.select("h1").select("img").attr("src");
        final String allText = element.text();
        final String itemUrl = element.select("a").attr("href");
        final String comments = element.select("span[class=replies]").text();
        final String replace = allText.replace(title, "").replace(comments, "");
        final String[] split = replace.split("-");
        int length = split.length;
        String author = "";
        String date = "";
        if (length == 4) {
            author = split[0];
            date = replace.replace(author + "-", "");
        }
        // TODO: 2019/12/17 补充其他情况
        final ArticleBean articleBean = new ArticleBean(itemUrl, title, author, date, comments);
        if (!TextUtils.isEmpty(tag)) {
            articleBean.setTag(tag);
        }
        return articleBean;
    }


    public List<ArticleBean> getArticleBeans() {
        return mArticleBeans;
    }

    public List<ChooseItem> getChooseItems() {
        return mChooseItems;
    }

    public String getDescribe() {
        return mDescribe;
    }


    public static class QueryParam {
        private static final String FILTER_TYPE = "typeid";
        private final String mod = "forumdisplay";
        private final String mobile = "yes";
        private int page = 1;
        private int fid = 46;
        private String filter = "";
        private String typeid = "";

        private QueryParam() {

        }

        @NonNull
        @Override
        public String toString() {
            // TODO: 2019/12/28 需要对这里转换成相应的网站链接
            // FIXME: 2019/12/28  还没有测试
            final Uri.Builder builder = Uri.parse("http://www.ditiezu.com").buildUpon().appendEncodedPath("forum.php");
            builder.appendQueryParameter("mod", mod)
                    .appendQueryParameter("mobile", mobile)
                    .appendQueryParameter("fid", String.valueOf(fid))
                    .appendQueryParameter("page", String.valueOf(page));
            if (FILTER_TYPE.equals(filter) && !TextUtils.isEmpty(typeid)) {
                builder.appendQueryParameter("filter", filter)
                        .appendQueryParameter("typeid", typeid);
            }
            return builder.build().toString();
        }

        public static class Builder {
            private int page = 1;
            private int fid = 46;
            private String filter = "";
            private String typeid = "";

            public Builder() {
            }

            public Builder setPage(int page) {
                this.page = page;
                return this;
            }

            public Builder setFid(int fid) {
                this.fid = fid;
                return this;
            }

            public Builder setFilter(String filter) {
                this.filter = filter;
                return this;
            }

            public Builder setTypeid(String typeid) {
                this.typeid = typeid;
                return this;
            }

            public int getPage() {
                return page;
            }

            public int getFid() {
                return fid;
            }

            public String getFilter() {
                return filter;
            }

            public String getTypeid() {
                return typeid;
            }

            public QueryParam build() {
                final QueryParam queryParam = new QueryParam();
                queryParam.page = this.page;
                queryParam.fid = this.fid;
                if (FILTER_TYPE.equals(filter) && !TextUtils.isEmpty(typeid)) {
                    queryParam.filter = this.filter;
                    queryParam.typeid = this.typeid;
                }
                return queryParam;
            }
        }
    }


}
