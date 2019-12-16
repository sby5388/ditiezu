package com.by5388.ditiezu.temp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Administrator  on 2019/12/16.
 */
public class GetListByUriTest {
    private GetListByUri mSubject;

    @Before
    public void setUp() throws Exception {
        mSubject = Mockito.mock(GetListByUri.class);
    }

    @Test
    public void getData() {
        mSubject.getData(46);
    }
}