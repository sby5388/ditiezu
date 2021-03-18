package com.by5388.ditiezu.bean;

import java.util.Locale;

/**
 * @author by5388  on 2019/12/14.
 */
public final class PageData {
    private static final String URL_FORMAT_DATA = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=%d&mobile=yes";
    public final String mName;
    public int mIndex;


    public PageData(int index, String name) {
        mIndex = index;
        mName = name;
    }

    public final String getUrl() {
        return String.format(Locale.getDefault(), URL_FORMAT_DATA, mIndex);
    }
}
