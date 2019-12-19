package com.by5388.ditiezu.publish;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Administrator  on 2019/12/19.
 */
public class PublishToolsTest {
    private PublishTools mSubjects;

    @Before
    public void setUp() throws Exception {
        final int page = 46;
        final boolean vote = false;
        mSubjects = new PublishTools(page, vote);
    }

    @Test
    public void loadData() throws IOException {
        mSubjects.loadData();
    }


}