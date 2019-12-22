package com.by5388.jsoup;

/**
 * @author by5388  on 2019/12/22.
 */
public class CityBean {
    public final String mDynamic;
    public final String mName;
    public final String mDescribe;
    public final String mIconUrl;
    public final String mUrl;

    public CityBean(String dynamic, String name, String describe, String iconUrl, String url) {
        mDynamic = dynamic;
        mName = name;
        mDescribe = describe;
        mIconUrl = iconUrl;
        mUrl = url;
    }


}
