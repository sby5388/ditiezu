package com.by5388.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Temp {
//    private static final String HTML_TEXT = "<html><div id=\"blog_list\"><div class=\"blog_title\"><a href=\"url1\">第一篇博客</a></div><div class=\"blog_title\"><a href=\"url2\">第二篇博客</a></div><div class=\"blog_title\"><a href=\"url3\">第三篇博客</a></div></div></html>";

    private static final String HTML_TEXT ="";

    private static final String FILE_PATH = "src/main/res/Test.html";

    public void test() throws IOException {
        final ArrayList<String> titles = new ArrayList<>();
        final ArrayList<String> urls = new ArrayList<>();
//        final Document document = Jsoup.parse(HTML_TEXT);
//        document.charset(Charset.forName("utf-8"));
        final Document document = Jsoup.parse(new File(FILE_PATH), "utf-8");
        //第一层 div
        final Elements select = document.select("div[id=blog_list]");
        //第二层 div
        final Elements elements = select.select("div[class=blog_title]");

        for (Element element : elements) {
            final String title = element.text();
            titles.add(title);
            final String url = element.select("a").attr("href");
            urls.add(url);
        }

        for (String t : titles) {
            System.out.println(t);
        }

        for (String url : urls) {
            System.out.println(url);
        }

    }
}
