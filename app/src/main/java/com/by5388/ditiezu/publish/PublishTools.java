package com.by5388.ditiezu.publish;

import com.by5388.ditiezu.bean.ChooseItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Administrator  on 2019/12/19.
 */
final class PublishTools {
    private final int mPageId;
    private final boolean mVote;
    private final List<ChooseItem> mChooseItems;
    private static final String PUBLISH_NORMAL = "http://www.ditiezu.com/forum.php?mod=post&action=newthread&fid=%d&mobile=yes";
    private static final String PUBLISH_VOTE = "http://www.ditiezu.com/forum.php?mod=post&action=newthread&fid=%d&special=1&mobile=yes";
    //http://www.ditiezu.com/forum.php?mod=post&action=newthread&fid=46&special=1&mobile=yes 投票
    //http://www.ditiezu.com/forum.php?mod=post&action=newthread&fid=46&mobile=yes 正常
    public final String mUrl;

    PublishTools(int pageId, boolean vote) {
        mPageId = pageId;
        mVote = vote;
        final String format = vote ? PUBLISH_VOTE : PUBLISH_NORMAL;
        mUrl = String.format(Locale.getDefault(), format, pageId);
        mChooseItems = new ArrayList<>();
    }

    void loadData() throws IOException {
        // TODO: 2019/12/19 webView.getSettings().getUserAgentString()
        final Document document = Jsoup.connect(mUrl).userAgent("iPhone").get();
        final Elements select = document.select("div[class=close]")
                .select("div[class=content]");
        // TODO: 2019/12/19 没有获取到数据，因为没有登录
        System.out.println(select.toString());
        final Elements mainElements = select
                .select("div[class=wp]")
                .select("div[class=ct]")
                .select("div[class=ipc]");
        System.out.println(mainElements.toString());
        final Elements selectList = mainElements.select("form")
                .select("div[class=box]")
                .select("div[id=postbox]")
                .select("div[class=inbox]")
                .select("div[class=ptypes]")
                .select("div[class='ipcl ptype']")
                .select("select");
        System.out.println(selectList.toString());
        for (Element element : selectList) {
            final String text = element.text();
            final String attr = element.select("option").attr("value");
            System.out.println(text + " -- " + attr);
        }

    }

}
