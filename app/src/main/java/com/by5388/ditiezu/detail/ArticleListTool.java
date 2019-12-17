package com.by5388.ditiezu.detail;

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
    private static final String BASE_URL = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=%d&mobile=yes";
    private final int mIndex;
    private final String mUrl;

    private final List<ArticleBean> mArticleBeans;
    private final List<ChooseItem> mChooseItems;

    public ArticleListTool(int index) {
        mIndex = index;
        mUrl = String.format(Locale.getDefault(), BASE_URL, index);
        mArticleBeans = new ArrayList<>();
        mChooseItems = new ArrayList<>();
    }


    public void loadData() throws MalformedURLException, IOException {
        final Document parse = Jsoup.connect(mUrl).userAgent("iPhone").get();
        parse.charset(Charset.forName("utf-8"));
        final Elements topElements = parse.select("div[class=close]")
                .select("div[class=content]")
                .select("div[class=wp]")
                .select("div[class=ct]");
        //init chooseItems
        final Elements selectItems = topElements.select("div[class=thtyss]").select("a");
        for (Element item : selectItems) {
            final String name = item.text();
            final String path = item.attr("href");
            mChooseItems.add(new ChooseItem(path, name));
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
