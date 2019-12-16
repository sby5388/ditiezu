package com.by5388.jsoup;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author by5388  on 2019/12/16.
 */
public class TempTest {
    private Temp mSubject;

    @Before
    public void setUp() throws Exception {
        mSubject = new Temp();
    }

    @Test
    public void test() throws IOException {
        mSubject.test();
    }
}