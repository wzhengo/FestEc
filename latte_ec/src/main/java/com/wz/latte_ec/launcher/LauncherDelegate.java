package com.wz.latte_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wz.latte_core.app.AccountManager;
import com.wz.latte_core.app.IUserChecker;
import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.ui.launcher.ScrollLauncherTag;
import com.wz.latte_core.util.LattePreference;
import com.wz.latte_core.util.timer.BaseTimerTask;
import com.wz.latte_core.util.timer.ITimerListener;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WangZhen on 2019/4/17.
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(() -> {
            if (mTvTimer != null) {
                mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                mCount--;
                if (mCount < 0) {
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                        checkIsShowScroll();
                    }
                }
            }
        });
    }

    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            //没有启动过APP，进入轮播图启动页
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
