package com.wz.latte_ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.net.RestClient;
import com.wz.latte_core.net.callback.ISuccess;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;
import com.wz.latte_ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

import static com.wz.latte_ec.main.personal.PersonalDelegate.ORDER_TYPE;

/**
 * @author wangzhen
 * @date 2019/06/12
 */
public class OrderListDelegate extends LatteDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecycleView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list.php")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecycleView.setLayoutManager(manager);
                        final ArrayList<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecycleView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
