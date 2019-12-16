package com.by5388.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
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
        final Elements allList = topElements.select("ul[id=alist]");
        System.out.println(allList.size());
        for (Element element : allList) {
            final String title = element.text();
            final String itemUrl = element.select("a").attr("href");
            System.out.println(title);
            System.out.println(itemUrl);
        }


    }


}
