package com.by5388.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author by5388  on 2019/12/16.
 */
public class JsoupDitiezuMainPage {
    //    http://www.ditiezu.com/forum-46-1.html
//    http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=46&mobile=yes
    private final int mIndex;
    private final String mUrl;
    private static final String BASE_URL = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=%d&mobile=yes";

    public JsoupDitiezuMainPage(int index) {
        mIndex = index;
        mUrl = String.format(Locale.getDefault(), BASE_URL, index);
    }

    public void test() throws MalformedURLException, IOException {
        final URL url = new URL(mUrl);
        final int timeoutMillis = (int) TimeUnit.SECONDS.toMillis(5);
//        final Document parse = Jsoup.parse(url, timeoutMillis);
        final Document parse = Jsoup.connect(mUrl).userAgent("iPhone").get();
        parse.charset(Charset.forName("utf-8"));
        System.out.println(parse.toString());
        final Elements topElements = parse.select("div[class=close]")
                .select("div[class=content]")
                .select("div[class=wp]")
                .select("div[class=ct]");


        final Elements allList = topElements.select("ul[id=alist]").select("li");
//        System.out.println(allList.size());
        for (Element element : allList) {
            final String title0 = element.select("h1").text();
            final String title = element.text();
            final String itemUrl = element.select("a").attr("href");
            final String comments = element.select("span[class=replies]").text();
            //标题
            System.out.println(title0);
            //回复数量
            System.out.println(comments);
            //链接
            System.out.println(itemUrl);
            final String trim = title.replace(title0, "")
                    .replace(comments, "").trim();
            System.out.println(trim);
            final String[] split = trim.split("-");
            final int splitIndex = split.length - 3;

        }

        if (true) {
            // TODO: 2019/12/17 test Item
            return;
        }

        final Elements selectItems = topElements.select("div[class=thtyss]").select("a");

        System.out.println(selectItems.toString());
        System.out.println(selectItems.size());
        final List<MenuItem> menuItems = new ArrayList<>();
        for (Element item : selectItems) {
//            System.out.println(item);
            final String name = item.text();
            final String path = item.attr("href");
            menuItems.add(new MenuItem(name, path));
        }


        for (MenuItem item : menuItems) {
            System.out.println(item);
        }


    }


}
