package com.by5388.ditiezu.temp;

import android.net.Uri;

import com.by5388.ditiezu.bean.ArticleBean;
import com.by5388.ditiezu.bean.ChooseItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// TODO: 2019/12/16

/**
 * 硬编码解析可不好
 *
 * @author Administrator  on 2019/12/16.
 */
public class GetListByUri {
    public static final String BASE_URL = "http://www.ditiezu.com/";
    private static final String BASE_URL_0 = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=%d&mobile=yes";
    //    public static final String base_url ="http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=46&page=1";
    private final String CHOOSE_ITEM_START;
    private static final String CHOOSE_ITEM_END = "&mobile=yes\" ";

    private final int mId;
    private final String mUrl;

    public GetListByUri(int id) {
        mId = id;
        CHOOSE_ITEM_START = String.format(Locale.getDefault(), " <a href=\"forum.php?mod=forumdisplay&amp;fid=%d&amp;filter=typeid&amp;typeid=", id);

        mUrl = String.format(Locale.getDefault(), BASE_URL_0, mId);
    }

    private List<ChooseItem> mChooseItems = new ArrayList<>();

    private List<ArticleBean> mArticles = new ArrayList<>();

    public List<ChooseItem> getChooseItems() {
        return mChooseItems;
    }

    public String getData() {

        final StringBuilder stringBuilder = new StringBuilder();
        final Uri uri = getUri(mId);
        final String urlString = uri.toString();
        try {
            final URL url = new URL(urlString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            final InputStream inputStream = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("error responseCode ! " + connection.getResponseMessage());
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("byyyyy " + line);
                if (line.contains(CHOOSE_ITEM_START)) {
                    addChooseItem(line);
                }
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void addChooseItem(final String line) {
        String replace = line.trim().replace(CHOOSE_ITEM_START.trim(), "");
        replace = replace.replace(CHOOSE_ITEM_END, "").trim();
        replace = replace.replace("</a>", "").trim();
        System.out.println(replace);
        final String[] split = replace.split(">");
        if (split.length != 2) {
            return;
        }
        try {
            final ChooseItem item = new ChooseItem(split[0], split[1]);
            mChooseItems.add(item);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    private Uri getUri(int id) {
        return Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("forum.php")
                .appendQueryParameter("mod", "forumdisplay")
                .appendQueryParameter("fid", String.valueOf(id))
                .appendQueryParameter("page", String.valueOf(1))
                .appendQueryParameter("mobile", "yes")
                .build();
    }

    public void getDataByJsoup() throws IOException {
        final Document parse = Jsoup.connect(mUrl).userAgent("iPhone").get();
        parse.charset(Charset.forName("utf-8"));
        System.out.println(parse.toString());


    }

}
