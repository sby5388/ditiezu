package com.by5388.ditiezu.user;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author Administrator  on 2019/12/26.
 */
public class CookieToolTest {
    private CookieTool mCookieTool;

    @Before
    public void setUp() throws Exception {
        mCookieTool = new CookieTool();
    }

    @Test
    public void getCookie() {
        final Map<String, String> cookie = mCookieTool.getCookie();
        for (String key : cookie.keySet()) {
            System.out.println(key + " : " + cookie.get(key));
        }
    }
}