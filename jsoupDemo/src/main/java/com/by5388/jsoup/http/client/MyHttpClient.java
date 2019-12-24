package com.by5388.jsoup.http.client;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author Administrator  on 2019/12/24.
 */
public class MyHttpClient {
    public static final String TEST_URL = "http://www.baidu.com";

    void start() throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        //http_uri_request ,
        final HttpGet httpGet = new HttpGet(TEST_URL);
        //http_response
        final CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //result_bean
        final HttpEntity entity = httpResponse.getEntity();

        System.out.println(EntityUtils.toString(entity));

    }
}
