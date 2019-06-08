package com.wz.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.wz.latte_core.activities.ProxyActivity;
import com.wz.latte_core.app.Latte;
import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_ec.launcher.ILauncherListener;
import com.wz.latte_ec.launcher.LauncherDelegate;
import com.wz.latte_ec.launcher.OnLauncherFinishTag;
import com.wz.latte_ec.main.EcBottomDelegate;
import com.wz.latte_ec.sign.ISignListener;
import com.wz.latte_ec.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
//                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
//                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
