package com.wz.latte_core.app;

import com.wz.latte_core.util.LattePreference;

/**
 * Created by WangZhen on 2019/4/18.
 */
public class AccountManager {

    private enum SignTag {
        SIGN_TAG;
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    //检查用户是否登录
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
