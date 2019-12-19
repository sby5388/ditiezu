package com.by5388.ditiezu.detail;

import android.net.TrafficStats;
import android.util.Log;

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
final class ArticleListTool {
    public static final String TAG = "ArticleListTool";
    private static final String BASE_URL = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=%d&mobile=yes&page=%d";
    //private static final String BASE_URL = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=46&mobile=yes&page=1";
    private final int mIndex;
    private int mPage = 1;
    private String mUrl;

    private final List<ArticleBean> mArticleBeans;
    private final List<ChooseItem> mChooseItems;

    public ArticleListTool(int index) {
        mIndex = index;

        mArticleBeans = new ArrayList<>();
        mChooseItems = new ArrayList<>();
    }

    public boolean enableLoadNextPage() {
        // TODO: 2019/12/18 getMaxPage
        mPage++;
        return true;
    }

    public boolean isFirstPage() {
        return mPage == 1;
    }

    public boolean enableLoadPrePage() {
        Log.d(TAG, "enableLoadPrePage: mPage = " + mPage);
        if (mPage > 1) {
            mPage--;
            return true;
        }
        return false;
    }

    public void loadData() throws MalformedURLException, IOException {
        mArticleBeans.clear();
        mUrl = String.format(Locale.getDefault(), BASE_URL, mIndex, mPage);
        TrafficStats.setThreadStatsTag(10086);
        final Document parse = Jsoup.connect(mUrl).userAgent("iPhone").cookie("auth", "token").get();
        parse.charset(Charset.forName("utf-8"));
        final Elements topElements = parse.select("div[class=close]")
                .select("div[class=content]")
                .select("div[class=wp]")
                .select("div[class=ct]");
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
        return new ArticleBean(itemUrl, title, author, date, comments);
    }


    public List<ArticleBean> getArticleBeans() {
        return mArticleBeans;
    }

    public List<ChooseItem> getChooseItems() {
        return mChooseItems;
    }
}
