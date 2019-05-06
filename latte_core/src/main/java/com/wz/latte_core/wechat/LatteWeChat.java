package com.wz.latte_core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wz.latte_core.app.ConfigKeys;
import com.wz.latte_core.app.Latte;
import com.wz.latte_core.wechat.callbacks.IWeChatSignInCallBack;

/**
 * Created by WangZhen on 2019/4/23.
 */
public class LatteWeChat {

    public static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallBack mSignInCallBack = null;

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, false);
        WXAPI.registerApp(APP_ID);
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallBack callBack){
        this.mSignInCallBack = callBack;
        return this;
    }

    public IWXAPI getWXAPI() {
        return WXAPI;
    }

    public IWeChatSignInCallBack getSignInCallBack(){
        return mSignInCallBack;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
