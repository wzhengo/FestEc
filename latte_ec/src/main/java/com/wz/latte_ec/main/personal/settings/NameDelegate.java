package com.wz.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_ec.R;

/**
 * @author wangzhen
 * @date 2019/06/15
 */
public class NameDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
