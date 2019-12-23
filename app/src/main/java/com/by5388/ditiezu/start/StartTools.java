package com.by5388.ditiezu.start;

import com.by5388.ditiezu.main.CityBean;
import com.by5388.ditiezu.main.ModuleBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2019/12/22.
 */
public class StartTools {
    private static final String BASE_URL = "http://www.ditiezu.com/forum.php?mod=forum";
    private List<ModuleBean> mModuleBeans;
    private boolean mLoading = false;

    public List<ModuleBean> getModuleBeans() {
        return mModuleBeans;
    }

    public static StartTools getInstance() {
        return Singleton.INSTANCE;
    }

    private static class Singleton {
        private static final StartTools INSTANCE = new StartTools();
    }


    private StartTools() {
        mModuleBeans = new ArrayList<>();
    }

    public synchronized void loadData() throws IOException {
        if (mLoading) {
            return;
        }
        mLoading = true;
        // TODO: 2019/12/22 使用cookie
        final Document document = Jsoup.connect(BASE_URL).userAgent("iPhone").get();
        document.charset(Charset.forName("utf-8"));
        //System.out.println(document.toString());
        final Elements catList = document
                .select("div[class=close]")
                .select("div[class=content]")
                .select("div[class=wp]")
                .select("div[class=catlist]");
        mModuleBeans.clear();
        for (Element cat : catList) {
            final String moduleName = cat.select("h1").text();
            final ModuleBean moduleBean = new ModuleBean(moduleName);
            final Elements cityList = cat.select("ul").select("li");
            for (Element city : cityList) {
                final Element urlElement = city.select("a").get(0);
                final String url = urlElement.attr("href");
                final String imageUrl = urlElement.select("img").attr("src");
                final Elements text = city.select("a[class=a]");
                final String name = text.select("p[class=f_nm]").text();
                // TODO: 2019/12/22 如何处理带空格的
                //final String describe = text.select("p.xgl").select("p .f_dp").text();
                final String describe = text.select("p").get(1).text();
                final String dynamic = text.select("span").text();
                final CityBean cityBean = new CityBean(dynamic, name, describe, imageUrl, url);
                moduleBean.addCityBean(cityBean);
            }
            mModuleBeans.add(moduleBean);
        }
        mLoading = false;

    }
}
