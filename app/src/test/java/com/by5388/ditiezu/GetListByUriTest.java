package com.by5388.ditiezu;

import com.by5388.ditiezu.temp.GetListByUri;

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
        mSubject = new GetListByUri(46);
    }

    @Test
    public void getData() {
        mSubject.getData();
    }
}