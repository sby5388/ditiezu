package com.by5388.jsoup.http.client;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Administrator  on 2019/12/24.
 */
public class TestMyHttpClient {
    private MyHttpClient mSubject;
    @Before
    public void setUp() throws Exception {
        mSubject = new MyHttpClient();
    }

    @Test
    public void start() throws IOException {
        mSubject.start();
    }
}