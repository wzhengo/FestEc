package com.wz.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wz.latte_core.delegate.bottom.BottomItemDelegate;
import com.wz.latte_ec.R;

/**
 * Created by WangZhen on 2019-04-28.
 */
public class IndexDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
