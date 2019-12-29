package com.by5388.ditiezu.detail;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author by5388  on 2019/12/29.
 */
public class DetailToolTest {
    private DetailTool mTool;

    @Before
    public void setUp() throws Exception {
        mTool = new DetailTool("forum.php?mod=viewthread&tid=355108&mobile=yes");
    }

    @Test
    public void loadData() throws IOException {
        mTool.loadData();
    }
}