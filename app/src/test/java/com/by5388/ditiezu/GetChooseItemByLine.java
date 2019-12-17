package com.by5388.ditiezu;

import com.by5388.ditiezu.bean.ChooseItem;

import org.junit.Assert;

/**
 * @author Administrator  on 2019/12/16.
 */
public class GetChooseItemByLine {
    static final ChooseItem sItem = new ChooseItem(String.valueOf(48), "1号线");
    private static final String choose_item_start = " <a href=\"forum.php?mod=forumdisplay&amp;fid=8&amp;filter=typeid&amp;typeid=";
    private static final String choose_item_end = "&mobile=yes\" ";

    //START_CHOOSE
    public static ChooseItem createByLine(String line) {
        String replace = line.trim().replace(choose_item_start.trim(), "");
        replace = replace.replace(choose_item_end, "").trim();
        replace = replace.replace("</a>", "").trim();
        System.out.println(replace);
        final String[] split = replace.split(">");
        if (split.length == 2) {
            try {
                return new ChooseItem(split[0], split[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        System.out.println(split.length);
        return null;
    }

    public static void main(String[] args) {
        final ChooseItem chooseItem = createByLine("<a href=\"forum.php?mod=forumdisplay&amp;fid=8&amp;filter=typeid&amp;typeid=48&mobile=yes\" >1号线</a>");
        if (chooseItem == null) {
            throw new RuntimeException();
        }
        Assert.assertEquals(chooseItem.mName, sItem.mName);
        Assert.assertEquals(chooseItem.mUrl, sItem.mUrl);
    }

}
