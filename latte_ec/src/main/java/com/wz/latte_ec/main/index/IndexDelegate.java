package com.wz.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.wz.latte_core.delegate.bottom.BottomItemDelegate;
import com.wz.latte_core.util.callback.CallbackManager;
import com.wz.latte_core.util.callback.CallbackType;
import com.wz.latte_core.util.callback.IGlobalCallback;
import com.wz.latte_ec.main.index.search.SearchDelegate;
import com.wz.latte_ui.recycler.BaseDecoration;
import com.wz.latte_ui.refresh.RefreshHandler;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;
import com.wz.latte_ec.main.EcBottomDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WangZhen on 2019-04-28.
 */
public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {

    @BindView(R2.id.rv_index)
    RecyclerView mRecycleView;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.ed_search_view)
    EditText mSearchView;

    private RefreshHandler mRefreshHandler;

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode(){
        startScanWithCheck(getParentDelegate());
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecycleView();
        mRefreshHandler.firstPage("index.php");
    }

    private void initRecycleView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecycleView.setLayoutManager(manager);
        mRecycleView.addItemDecoration(
                BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecycleView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate) );
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecycleView, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), args, Toast.LENGTH_SHORT).show();
                    }
                });
        mSearchView.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }
}
