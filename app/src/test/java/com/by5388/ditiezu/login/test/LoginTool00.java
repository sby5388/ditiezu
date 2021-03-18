package com.by5388.ditiezu.login.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Administrator  on 2020/1/9.
 */
public class LoginTool00 {
    private static final String BASE_URL = "http://106.12.208.156:8080/bns/addUser.do";

    void register(String userName, String password) throws IOException {
        final URL url = new URL(BASE_URL + "?userName=" + userName + "&password=" + password);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
            throw new RuntimeException("error responseCode");
        }
        final InputStream inputStream = connection.getInputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        while ((length = inputStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        final String result = new String(byteArrayOutputStream.toByteArray());
        System.out.println(result);

    }
}
