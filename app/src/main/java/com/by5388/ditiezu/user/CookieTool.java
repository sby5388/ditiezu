package com.by5388.ditiezu.user;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator  on 2019/12/26.
 */
public class CookieTool {

    public static final String cookie = "__cfduid=d1733ba90f4b1fd571ea4df52d828e4471552538437; yjs_id=c0e9bcbebfe0839093069b1d882b84c0; Hm_lvt_8875c662941dbf07e39c556c8d97615f=1559210481; Hm_lvt_24b7d5cc1b26f24f256b6869b069278e=1572851243,1574645867; ditiezu_bbs_20060b50_saltkey=iJRO5oce; ditiezu_bbs_20060b50_lastvisit=1577080207; ditiezu_bbs_20060b50_visitedfid=46D7; ditiezu_bbs_20060b50_auth=0b09667j1%2BfxE5Pp2MgSUWmtuNt%2F3rDpXjCKt9EO5Es89GCAvVkJPcnBKNabExOnMrx91mfQwWxg1DRIuS16N%2BKSBsI; ditiezu_bbs_20060b50_yfe_in=1; ditiezu_bbs_20060b50_smile=1D1; ditiezu_bbs_20060b50_ulastactivity=a22558tb2f3osrVmuknrSf8BewiyDj46Hr7IIKBWNXOCl4pMQXCY; Hm_lvt_a7a4e43d43d6c9bea9025f4e800e9341=1577153123,1577237055,1577274045,1577323315; ditiezu_bbs_20060b50_home_diymode=1; ditiezu_bbs_20060b50_viewuids=732690_628759; ditiezu_bbs_20060b50_seccodeSF50wZ54=8fc6u7a2IU6BLBcDFfG5LeNd2VXwjCdZoQ6ttNJ1SkHGHUq11R3tudsUg%2Bbjejcov5lcl6nnGikOwx1VRvQ; Hm_lpvt_a7a4e43d43d6c9bea9025f4e800e9341=1577358627; ditiezu_bbs_20060b50_sid=I775XR; ditiezu_bbs_20060b50_forum_lastvisit=D_7_1577083807D_46_1577361625; ditiezu_bbs_20060b50_lastact=1577361886%09member.php%09logging";

    public Map<String, String> getCookie() {
        Map<String, String> cookies = new HashMap<>();
        final String[] split = cookie.split(";");
        for (String map : split) {
            final String[] split1 = map.split("=");
            if (split1.length == 2) {
                cookies.put(split1[0], split1[1]);
            }
        }
        return cookies;
    }
}
