package com.by5388.jsoup;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author by5388  on 2019/12/22.
 */
public class PrintUrlTest {
    private PrintUrl mSubject;

    @Before
    public void setUp() throws Exception {
        mSubject = new PrintUrl();
    }

    @Test
    public void print() throws IOException {
        mSubject.print("http://www.ditiezu.com/forum.php?mod=forum");
    }
}