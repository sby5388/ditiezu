package com.by5388.ditiezu.user;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator  on 2019/12/23.
 */
public class LoginTools {
    private static final String BASE_URL = "http://www.ditiezu.com/";
    private static final String LOGIN_URL = "http://www.ditiezu.com/member.php?mod=logging&action=login&mobile=yes";
    private static final String LOGIN_URL0 = "http://www.ditiezu.com/member.php?";
    private Map<String, String> mCookieMap = new HashMap<>();


    private String mSecureCodeUrl;

    public void loadData() throws IOException {
        final Connection.Response response = Jsoup
                .connect(LOGIN_URL)
                .userAgent("iPhone")
                .method(Connection.Method.GET)
                .execute();
        final Document parse = response.parse();
        parse.charset(Charset.forName("utf-8"));
        final Elements select = parse.select("div[class=login_s]");
        System.out.println(select.toString());

        System.out.println(parse.toString());
        mCookieMap.putAll(response.cookies());
        for (String key : mCookieMap.keySet()) {
            System.out.println(key + " : " + mCookieMap.get(key));
        }


    }


    public void loadData2() throws IOException {
        final Map<String, String> data = new HashMap<>();
        data.put("mod", "logging");
        data.put("action", "login");
        data.put("mobile", "yes");
        final Document post = Jsoup.connect(LOGIN_URL0).userAgent("iPhone")
                .cookie("auth", "token")
                .cookies(mCookieMap)
                .data(data)
                .get();
        post.charset(Charset.forName("utf-8"));
        System.out.println(post.toString());

    }


    public void loadData3() throws IOException {
        final Map<String, String> data = new HashMap<>();
        data.put("mod", "logging");
        data.put("action", "login");
        data.put("mobile", "yes");
        final Connection connection = Jsoup.connect(LOGIN_URL0);
        final Connection.Request request = connection.request();
        final String s = request.requestBody();

        final Document post = Jsoup.connect(LOGIN_URL0).userAgent("iPhone")
                .cookie("auth", "token")
                .data(data)
                .get();
        post.charset(Charset.forName("utf-8"));
        System.out.println(post.toString());

    }

    public String getSecureCodeUrl() {
        return BASE_URL + mSecureCodeUrl;
    }


}
