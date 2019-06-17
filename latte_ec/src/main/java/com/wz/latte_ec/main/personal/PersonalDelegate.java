package com.wz.latte_ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wz.latte_core.delegate.bottom.BottomItemDelegate;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;
import com.wz.latte_ec.main.personal.address.AddressDelegate;
import com.wz.latte_ec.main.personal.list.ListAdapter;
import com.wz.latte_ec.main.personal.list.ListBean;
import com.wz.latte_ec.main.personal.list.ListItemType;
import com.wz.latte_ec.main.personal.order.OrderListDelegate;
import com.wz.latte_ec.main.personal.profile.UserProfileDelegate;
import com.wz.latte_ec.main.personal.settings.SettingsDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangzhen
 * @date 2019/06/11
 */
public class PersonalDelegate extends BottomItemDelegate {

    public static final String ORDER_TYPE = "ORDER_TYPE";

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings;
    private Bundle mArgs;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecycleView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonClickListener(this));
    }
}
