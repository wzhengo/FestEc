package com.wz.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.util.callback.CallbackManager;
import com.wz.latte_core.util.callback.CallbackType;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;
import com.wz.latte_ec.main.personal.address.AddressDelegate;
import com.wz.latte_ec.main.personal.list.ListAdapter;
import com.wz.latte_ec.main.personal.list.ListBean;
import com.wz.latte_ec.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wangzhen
 * @date 2019/06/17
 */
public class SettingsDelegate extends LatteDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                        Toast.makeText(getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                    } else {
                        CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                        Toast.makeText(getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                    }
                })
                .setText("消息推送")
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
