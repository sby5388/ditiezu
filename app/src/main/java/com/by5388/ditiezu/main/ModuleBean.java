package com.by5388.ditiezu.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2019/12/22.
 */
public class ModuleBean {
    private final String mName;
    private final List<CityBean> mCityBeans;

    public ModuleBean(String name) {
        mName = name;
        mCityBeans = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public List<CityBean> getCityBeans() {
        return mCityBeans;
    }

    public void addCityBean(CityBean cityBean) {
        this.mCityBeans.add(cityBean);
    }
}
