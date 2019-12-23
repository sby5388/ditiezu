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
    private static final String LOGIN_URL = "http://www.ditiezu.com/member.php?mod=logging&action=login&mobile=yes";
    private Map<String, String> mCookieMap = new HashMap<>();

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

}
