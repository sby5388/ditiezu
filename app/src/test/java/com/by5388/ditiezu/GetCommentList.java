package com.by5388.ditiezu;

import com.by5388.ditiezu.bean.ArticleCommentBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator  on 2019/12/18.
 */
public class GetCommentList {
    private static final String BASE_URL = "http://www.ditiezu.com/";
    private final String mUrl;
    private List<ArticleCommentBean> mCommentBeans = new ArrayList<>();

    public GetCommentList(String url) {
        mUrl = BASE_URL + url;
    }

    public void loadData() throws IOException {
        System.out.println(mUrl);
        final Document parse = Jsoup.connect(mUrl).userAgent("iPhone").get();
        parse.charset(Charset.forName("utf-8"));
        final Elements close = parse.select("div[class=close]");
        System.out.println(close.size());
        final Elements mwp = close.select("div[class=content]");
        System.out.println(mwp.size());
        final Elements topElements = mwp
                .select("div[class=wp]")
                .select("div[class=vt]")
                .select("div[class=ct]");
        System.out.println("size = " + topElements.size());
        Element commentElement = topElements.get(1);
        final Elements elements = commentElement.select("div[class=vb vc]");
        if (elements.size() == 0) {
            System.err.println("empty");
            return;
        }
        for (Element element : elements) {
            final String imageIcon = element.select("a[class=avater]").select("img").attr("src");
            final Elements user = element.select("div[class=user]");
            final String userName = user.select("a").text();
            final String userUrl = user.select("a").attr("href");
            // FIXME: 2019/12/18 时间不对，获取不到
            final String date = user.select("span[class=p_dl]").select("em").text();
            final String floor = user.select("span[class=p_nm y]").text();
            final Elements replay = element.select("div[class=vbc notb]");
//            final Elements replay = element.select("div[class=vbc]");
            final String comment = replay.select("div[class=pbody mbn]").select("div[class=mes]").text();
            // TODO: 2019/12/18 链接获取不到
            System.out.println(replay);
            final String replyUrl = replay.select("div[class=vtrim]").select("a").attr("href");
            mCommentBeans.add(new ArticleCommentBean(imageIcon, userUrl, userName, date, floor, comment, replyUrl));
        }
    }

    public List<ArticleCommentBean> getCommentBeans() {
        return mCommentBeans;
    }
}
