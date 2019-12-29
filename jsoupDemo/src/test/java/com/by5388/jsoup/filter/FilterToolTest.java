package com.by5388.jsoup.filter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author by5388  on 2019/12/28.
 */
public class FilterToolTest {

    @Test
    public void temp() {
        new FilterTool().temp("forum.php?mod=forumdisplay&fid=23&filter=typeid&typeid=231&mobile=yes");
    }
}