package com.wz.latte_ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wz.latte_core.delegate.bottom.BottomItemDelegate;
import com.wz.latte_ec.R;
import com.wz.latte_ec.main.sort.content.ContentDelegate;
import com.wz.latte_ec.main.sort.list.VerticalListDelegate;

/**
 * Created by WangZhen on 2019-04-28.
 */
public class SortDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, new VerticalListDelegate());
        getSupportDelegate().loadRootFragment(R.id.sort_content_container,ContentDelegate.newInstance(1));
    }
}
