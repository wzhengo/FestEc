package com.wz.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.net.RestClient;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;

import butterknife.BindView;

/**
 * @author wangzhen
 * @date 2019/06/17
 */
public class AboutDelegate extends LatteDelegate {

    @BindView(R2.id.tv_info)
    TextView textView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(response -> {
                    final String info = JSON.parseObject(response).getString("data");
                    textView.setText(info);
                })
                .build()
                .get();
    }
}
