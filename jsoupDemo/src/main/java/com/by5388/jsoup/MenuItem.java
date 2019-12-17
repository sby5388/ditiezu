package com.by5388.jsoup;

/**
 * @author Administrator  on 2019/12/17.
 */
public class MenuItem {
    public final String mName;
    public final String mUri;

    public MenuItem(String name, String uri) {
        mName = name;
        mUri = uri;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "mName='" + mName + '\'' +
                ", mUri='" + mUri + '\'' +
                '}';
    }
}
