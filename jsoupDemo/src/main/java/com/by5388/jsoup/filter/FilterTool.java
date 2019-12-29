package com.by5388.jsoup.filter;

/**
 * @author by5388  on 2019/12/28.
 */
public class FilterTool {
    //forum.php?mod=forumdisplay&fid=23&filter=typeid&typeid=231&mobile=yes
    void temp(String url) {
        final String[] map = url.split("&");
        for (String s:map){
            final String[] split = s.split("=");
            if (split.length == 2){
                if ("filter".equals(split[0])){
                    //typeid
                }else if ("typeid".equals(split[0])){
                    //231
                }
            }
        }

    }
}
