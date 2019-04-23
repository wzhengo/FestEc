package com.wz.latte_core.wechat.templates;

import com.wz.latte_core.wechat.BaseWXEntryActivity;
import com.wz.latte_core.wechat.LatteWeChat;

/**
 * Created by WangZhen on 2019/4/23.
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallBack().onSignInSuccess(userInfo);
    }
}
