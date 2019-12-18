package com.by5388.ditiezu;

import com.by5388.ditiezu.bean.ArticleCommentBean;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator  on 2019/12/18.
 */
public class GetCommentTest {
    private GetCommentList mSubject;

    @Before
    public void setUp() throws Exception {
        mSubject = new GetCommentList("forum.php?mod=viewthread&tid=650279&mobile=yes");

    }

    @Test
    public void getCommentBeans() throws IOException {
        mSubject.loadData();
        final List<ArticleCommentBean> commentBeans = mSubject.getCommentBeans();
        System.out.println(commentBeans.size());
        for (ArticleCommentBean bean : commentBeans) {
            System.out.println(bean);
        }
    }
}