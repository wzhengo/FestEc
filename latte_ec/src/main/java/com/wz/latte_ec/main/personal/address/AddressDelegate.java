package com.wz.latte_ec.main.personal.address;

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

/**
 * @author wangzhen
 * @date 2019/06/17
 */
public class AddressDelegate extends LatteDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ArrayList<MultipleItemEntity> data = new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter adapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
