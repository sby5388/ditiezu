package com.by5388.jsoup;

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
public class PrintUrl {
    private List<ModuleBean> mModuleBeans;

    PrintUrl() {
        mModuleBeans = new ArrayList<>();
    }

    public void print(final String urlString) throws IOException {
        final Document document = Jsoup.connect(urlString).userAgent("iPhone").get();
        document.charset(Charset.forName("utf-8"));
        //System.out.println(document.toString());
        final Elements catList = document
                .select("div[class=close]")
                .select("div[class=content]")
                .select("div[class=wp]")
                .select("div[class=catlist]");
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
//                final String describe = text.select("p.xgl").select("p .f_dp").text();
                final String describe = text.select("p").get(1).text();
                final String dynamic = text.select("span").text();
                final CityBean cityBean = new CityBean(dynamic, name, describe, imageUrl, url);
                moduleBean.addCityBean(cityBean);
            }

            mModuleBeans.add(moduleBean);
        }

        for (ModuleBean moduleBean : mModuleBeans) {
            System.out.println("--\t " + moduleBean.getName());
            for (CityBean cityBean : moduleBean.getCityBeans()) {
                System.out.println(cityBean.mName);
                System.out.println(cityBean.mIconUrl);
                System.out.println(cityBean.mUrl);
            }

        }

    }
}
