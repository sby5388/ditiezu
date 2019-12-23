package com.by5388.ditiezu.user;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Administrator  on 2019/12/23.
 */
public class JsoupLoginIteyeTest {
    private JsoupLoginIteye jli;

    @Before
    public void setUp() throws Exception {
        jli = new JsoupLoginIteye();
    }

    @Test
    public void login() throws Exception {
        // TODO: 2019/12/23 需要换成真实的UserName + password
        jli.login("username", "password");
    }
}