package com.by5388.ditiezu.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2019/12/15.
 */
public class DataTools {
    private final static List<PageData> PAGE_DATA_LIST = new ArrayList<>();
    private static List<Holder> sHolders = new ArrayList<>();


    public static List<PageData> getPageDataList() {
        if (!PAGE_DATA_LIST.isEmpty()) {
            return PAGE_DATA_LIST;
        }
        sHolders.add(new Holder(7, "北京区"));
        sHolders.add(new Holder(6, "天津区"));
        sHolders.add(new Holder(8, "上海区"));
        sHolders.add(new Holder(23, "广州区"));
        sHolders.add(new Holder(40, "长春区"));
        sHolders.add(new Holder(41, "大连区"));
        sHolders.add(new Holder(39, "武汉区"));
        sHolders.add(new Holder(38, "重庆区"));
        sHolders.add(new Holder(24, "深圳区"));
        sHolders.add(new Holder(22, "南京区"));
        sHolders.add(new Holder(53, "成都区"));
        sHolders.add(new Holder(50, "沈阳区"));
        sHolders.add(new Holder(56, "佛山区"));
        sHolders.add(new Holder(54, "西安区"));
        sHolders.add(new Holder(51, "苏州区"));
        sHolders.add(new Holder(70, "昆明区"));
        sHolders.add(new Holder(52, "杭州区"));
        sHolders.add(new Holder(55, "哈尔滨区"));
        sHolders.add(new Holder(64, "郑州区"));
        sHolders.add(new Holder(67, "长沙区"));
        sHolders.add(new Holder(65, "宁波区"));
        sHolders.add(new Holder(68, "无锡区"));
        sHolders.add(new Holder(66, "青岛区"));
        sHolders.add(new Holder(71, "南昌区"));
        sHolders.add(new Holder(72, "福州区"));
        sHolders.add(new Holder(75, "东莞区"));
        sHolders.add(new Holder(73, "南宁区"));
        sHolders.add(new Holder(74, "合肥区"));
        sHolders.add(new Holder(140, "石家庄区"));
        sHolders.add(new Holder(76, "贵阳区"));
        sHolders.add(new Holder(77, "厦门区"));
        sHolders.add(new Holder(143, "乌鲁木齐区"));
        sHolders.add(new Holder(142, "温州区"));
        sHolders.add(new Holder(148, "济南区"));
        sHolders.add(new Holder(28, "香港区"));
        sHolders.add(new Holder(36, "台湾区"));
        sHolders.add(new Holder(47, "海外区"));
        sHolders.add(new Holder(37, "综合区"));
        sHolders.add(new Holder(46, "城际高铁"));
        sHolders.add(new Holder(21, "站前广场"));
        for (Holder holder : sHolders) {
            PAGE_DATA_LIST.add(new PageData(holder.id, holder.name));
        }

        return PAGE_DATA_LIST;
    }

    private static class Holder {
        final int id;
        final String name;

        Holder(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
