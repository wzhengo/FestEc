package com.wz.latte_ec.main;

import android.graphics.Color;

import com.wz.latte_core.delegate.bottom.BaseBottomDelegate;
import com.wz.latte_core.delegate.bottom.BottomItemDelegate;
import com.wz.latte_core.delegate.bottom.BottomTabBean;
import com.wz.latte_core.delegate.bottom.ItemBuilder;
import com.wz.latte_ec.main.index.IndexDelegate;
import com.wz.latte_ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by WangZhen on 2019-04-28.
 */
public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItem(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
