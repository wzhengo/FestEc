package com.wz.latte_ec.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;

import butterknife.BindView;


/**
 * @author wangzhen
 * @date 2019/07/30
 */
public class GoodsInfoDelegate extends LatteDelegate {

    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView goodsInfoTitle;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView goodsInfoDesc;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView goodsInfoPrice;

    private JSONObject mData = null;

    private static final String ARG_GOODS_INFO = "ARG_GOODS_INFO";

    public static GoodsInfoDelegate create(@NonNull String goodsInfo) {
        final Bundle args = new Bundle();
        args.putString(ARG_GOODS_INFO, goodsInfo);
        final GoodsInfoDelegate delegate = new GoodsInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        final String goodsData;
        if (args != null) {
            goodsData = args.getString(ARG_GOODS_INFO);
            mData = JSON.parseObject(goodsData);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final String name = mData.getString("name");
        final String desc = mData.getString("description");
        final double price = mData.getDouble("price");
        goodsInfoTitle.setText(name);
        goodsInfoDesc.setText(desc);
        goodsInfoPrice.setText(String.valueOf(price));
    }
}
