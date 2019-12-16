package com.by5388.ditiezu.publish;

import com.by5388.ditiezu.bean.ChooseItem;

import java.util.List;

/**
 * @author Administrator  on 2019/12/16.
 */
public interface IPublishView {
    void onStartLoad();

    void onUpdateItem(List<ChooseItem> itemList);

    void onFinishLoad();

    void onError(String message);
}
