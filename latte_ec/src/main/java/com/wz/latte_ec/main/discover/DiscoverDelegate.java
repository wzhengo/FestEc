package com.wz.latte_ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wz.latte_core.delegate.bottom.BottomItemDelegate;
import com.wz.latte_core.delegate.web.WebDelegateImpl;
import com.wz.latte_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by WangZhen on 2019-05-26.
 */
public class DiscoverDelegate extends BottomItemDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html ");
        delegate.setTopDelegate(getParentDelegate());
        loadRootFragment(R.id.web_discovery_container, delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
