package com.wz.latte_ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.net.RestClient;
import com.wz.latte_core.net.callback.ISuccess;
import com.wz.latte_ui.recycler.MultipleItemEntity;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;
import com.wz.latte_ec.main.sort.SortDelegate;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by WangZhen on 2019-05-23.
 */
public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final ArrayList<MultipleItemEntity> data = new VerticalListDataConverter()
                                .setJsonData(response).convert();
                        final SortDelegate parentDelegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, parentDelegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();

    }
}
