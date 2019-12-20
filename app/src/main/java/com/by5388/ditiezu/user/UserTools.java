package com.by5388.ditiezu.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author Administrator  on 2019/12/20.
 */
public class UserTools {
    // TODO: 2019/12/20 测试WanAndroid的接口
    private HashMap<HttpUrl, List<Cookie>> mCookieStore = new HashMap<>();
    private OkHttpClient mClient;
    private CookieJar mCookieJar;

    public UserTools() {
        // TODO: 2019/12/20 设置保存cookie 相关的数据
        mCookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
                //保存cookie
                System.out.println("cookie save = " + url);

                mCookieStore.put(url, cookies);
                for (Cookie cookie : cookies) {
                    System.out.println(cookie.name() + " : " + cookie.value());


                }

            }

            @Override
            public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
                //加载本地的cookie
                System.out.println("cookie load = " + url);
                final List<Cookie> cookies = mCookieStore.get(url);
                if (cookies == null) {
                    System.err.println("unload cookie data");
                    return null;
                }
                return cookies;
            }
        };
    }

    private void startLoad() {
        mClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .cookieJar(mCookieJar)
                .build();
    }

    public void login() {
        final Map<String, String> param = new HashMap<>();
        param.put("username", "by5388");
        param.put("password", "");

        final Callback callback = new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        };

    }


    public OkHttpClient getClient() {
        return mClient;
    }

    public void setClient(OkHttpClient client) {
        mClient = client;
    }
}
