package com.by5388.ditiezu.main;

import java.util.Locale;

/**
 * @author by5388  on 2019/12/14.
 */
public final class PageData {
    public int mIndex;
    public final String mName;
    private static final String URL_FORMAT_DATA = "http://www.ditiezu.com/forum.php?mod=forumdisplay&fid=%d&mobile=yes";


    public PageData(int index, String name) {
        mIndex = index;
        mName = name;
    }

    public final String getUrl() {
        return String.format(Locale.getDefault(), URL_FORMAT_DATA, mIndex);
    }
}
