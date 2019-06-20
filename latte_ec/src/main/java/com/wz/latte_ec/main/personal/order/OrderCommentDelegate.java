package com.wz.latte_ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.util.callback.CallbackManager;
import com.wz.latte_core.util.callback.CallbackType;
import com.wz.latte_core.util.callback.IGlobalCallback;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;
import com.wz.latte_ui.widget.AutoPhotoLayout;
import com.wz.latte_ui.widget.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangzhen
 * @date 2019/06/19
 */
public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分:" + mStarLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, (IGlobalCallback<Uri>) args ->
                        mAutoPhotoLayout.onCropTarget(args));
    }
}
