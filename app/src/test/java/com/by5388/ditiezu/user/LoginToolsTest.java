package com.by5388.ditiezu.user;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Administrator  on 2019/12/23.
 */
public class LoginToolsTest {

    private LoginTools mSubject;


    @Before
    public void setUp() throws Exception {
        mSubject = new LoginTools();
    }

    @Test
    public void loadData() throws IOException {
        mSubject.loadData();
        mSubject.loadData2();
    }
}