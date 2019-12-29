package com.by5388.ditiezu.detail;

import android.util.Log;

import com.by5388.ditiezu.bean.ArticleCommentBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author by5388  on 2019/12/29.
 */
public class DetailTool {
    public static final String TAG = "DetailTool";
    private ArticleDetailBean mDetailBean;
    private String mUrl;

    public DetailTool(String url) {
        mDetailBean = new ArticleDetailBean();
        this.mUrl = url;
    }

    public ArticleDetailBean getDetailBean() {
        return mDetailBean;
    }

    private String getUrl() {
        return "http://ditiezu.com/" + mUrl;
        //"forum.php?mod=viewthread&tid=355108&mobile=yes"
    }


    public void loadData() throws IOException {
        final Document document = Jsoup.connect(getUrl()).userAgent("iPhone").get();
        // TODO: 2019/12/29
        final Elements pageElements = document.select("div[class=close]").select("div[class=content]").select("div[class=wp]").select("div[class=vt]");
//        System.out.println(pageElements.toString());
        final Elements mainElements = pageElements.select("div[class=ct]");
        final int size = mainElements.size();
        if (size == 0) {
            Log.e(TAG, "loadData: ", new Exception());
            return;
        }
        System.out.println(size);
        final Element originElement = mainElements.get(0);
        handleOrigin(originElement);
        if (size < 1) {
            System.err.println("没有回复");
            return;
        }

        final Element replyList = mainElements.get(1);
        handleReplyList(replyList);

    }

    private void handleOrigin(Element originElement) {
        if (true) {
//            return;
        }

        final Element element = originElement;
//        final Elements select = element.select("div[class=vb]").select("div[class=notb]");
//        final Elements select = element.select("div .vb").select("div .notb");
        // TODO: 2019/12/29 这才是正确的解析div#class有多个空格的方式， https://blog.csdn.net/mengxiangxingdong/article/details/78867230
        //   <div id="post_11097206" class="vb notb">
        final Elements select = element.select(".vb.notb");
        final String postId = select.attr("id");
        mDetailBean.setPostId(postId);

        System.out.println("post_id = " + postId);
        {
            final Elements titleElements = select.select("h1[class=vt_th]");
            final String title = titleElements.text();
            final String url = titleElements.select("a").attr("href");
            mDetailBean.setTitle(title);
            mDetailBean.setUrl(url);
            System.out.println("title" + title);
            System.out.println(url);
        }
        {
            final Elements userMessage = select.select("div[class=user_first]");
            {
                final Elements fav = userMessage.select("a[class=fav]");
                final String favString = fav.text();
                mDetailBean.setFav(favString);
                final String favUrl = fav.attr("href");
                mDetailBean.setFavUrl(favUrl);
                System.out.println(favString);
                System.out.println(favUrl);
            }
            {
                final Elements a = userMessage.select("a");
                if (a.size() > 1) {
                    final Element author = a.get(1);
                    final String authorName = author.text();
                    final String authorUrl = author.attr("href");
                    System.out.println(authorName);

                    System.out.println(authorUrl);
                    System.out.println(userMessage.text());
                    mDetailBean.setAuthorName(authorName);
                    mDetailBean.setAuthorUrl(authorUrl);
                }
            }
            {
                final String date = userMessage.select("span[class=p_dl]").select("em").text();
                System.out.println(date);
                mDetailBean.setDate(date);
            }

        }

        {
            final Elements vbc = select.select("div[class=vbc]");
            {
                final Elements postMessage = vbc.select("div[class=pbody]").select("div[class=mes]").select("div[class=postmessage]");
                final String messageId = postMessage.attr("id");
                final String message = postMessage.text();
                System.out.println(messageId);
                System.out.println(message);
                mDetailBean.setContentId(messageId);
                mDetailBean.setMainContent(message);
            }
            {
                // TODO: 2019/12/29 未登录时没有回复按钮
                final Elements reply = vbc.select("div[class=vtrim]");
                if (reply != null && !reply.isEmpty()) {
                    final String replyMessage = reply.select("a").text();
                    final String replyUrl = reply.select("a").attr("href");
                    System.out.println(replyMessage);
                    System.out.println(replyUrl);

                    mDetailBean.setReply(replyMessage);
                    mDetailBean.setReplyUrl(replyUrl);
                } else {
                    System.err.println("未登录，没有回复按钮");
                }
            }
        }
        System.out.println();

    }


    private void handleReplyList(Element replyList) {
        {
            // TODO: 2019/12/29 倒序查看 ordertype=1：：：1倒序，0正序
            final Elements titls = replyList.select("div[class=titls]");
            final Elements xi2 = titls.select("a[class=xi2]");
            mDetailBean.setSortName(xi2.text());
            mDetailBean.setSortUrl(xi2.attr("href"));
            mDetailBean.setReplyCount(titls.select("span[class=y]").text());
        }

        {
            final Elements list = replyList.select(".vb.vc");
            for (Element element : list) {
                final ArticleCommentBean bean = new ArticleCommentBean();
                bean.setPostId(element.attr("id"));
//                System.out.println("post_id = " + element.attr("id"));
                {
                    final Elements avatar = element.select("a[class=avatar]");
                    bean.mUserUrl = avatar.attr("href");
                    bean.mUserIconUrl = avatar.select("img").attr("src");
                    System.out.println("\tuser_url = " + avatar.attr("href"));
                    System.out.println("\tuser_icon_url = " + avatar.select("img").attr("src"));
                }
                {
                    final Elements user = element.select("div[class=user]");
                    System.out.println("\tuser_name = " + user.select("a").text());
                    bean.mUserName = user.select("a").text();
                    final Elements date = user.select("span[class=p_dl]").select("em");
                    bean.mDate = date.text();
                    System.out.println("\tdate = " + date.text());
                    System.out.println("\tdate_id = " + date.attr("id"));

                    final Elements tag = user.select("span.p_nm.y");
                    bean.mTag = tag.text();
                    System.out.println("\ttag = " + tag.text());
                    bean.mTagColor = tag.select("font").attr("color");
                    System.out.println("\tcolor = " + tag.select("font").attr("color"));
                }
                {
                    final Elements content = element.select("div.vbc.notb");
                    final Elements postMessage = content.select("div.pbody.mbn").select("div.mes").select("div.postmessage");
                    bean.mComment = postMessage.text();
//                    System.out.println("\tpostMessage = " + postMessage.text());
//                    System.out.println("\tpostId = " + postMessage.attr("id"));
                    bean.mCommentId = postMessage.attr("id");
                    final Elements vtrim = content.select("div.vtrim");
                    if (vtrim != null && vtrim.size() > 0) {
                        bean.mActionName = vtrim.text();
                        bean.mActionUrl = vtrim.select("a").attr("href");
                        System.out.println("\taction = " + vtrim.text());
                        System.out.println("\turl = " + vtrim.select("a").attr("href"));
                    } else {
                        System.err.println("\t未登录");
                    }
                }
                System.out.println();
                mDetailBean.addComment(bean);
            }
        }

    }

}
