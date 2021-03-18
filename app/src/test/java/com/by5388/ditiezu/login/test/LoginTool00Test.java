package com.by5388.ditiezu.login.test;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Administrator  on 2020/1/9.
 */
public class LoginTool00Test {
    LoginTool00 mSubject;

    @Before
    public void setUp() throws Exception {
        mSubject = new LoginTool00();
    }

    @Test
    public void register() throws IOException {
        mSubject.register("shenby", "123456");
    }
}