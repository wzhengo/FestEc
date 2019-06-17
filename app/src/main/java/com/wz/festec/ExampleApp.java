package com.wz.festec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wz.festec.event.TestEvent;
import com.wz.latte_core.app.Latte;
import com.wz.latte_core.net.interceptors.DebugInterceptor;
import com.wz.latte_core.util.callback.CallbackManager;
import com.wz.latte_core.util.callback.CallbackType;
import com.wz.latte_ec.database.DatabaseManager;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by WangZhen on 2019/4/11.
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
//                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withApiHost("http://mock.fulingjie.com/mock-android/api/")
                .withWebEvent("test",new TestEvent())
                .withJavascriptInterface("latte")
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, args -> {
                    if (JPushInterface.isPushStopped(Latte.getApplication())) {
                        //开启极光推送
                        JPushInterface.setDebugMode(true);
                        JPushInterface.init(Latte.getApplication());
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, args -> {
                    if (!JPushInterface.isPushStopped(Latte.getApplication())) {
                        JPushInterface.stopPush(Latte.getApplication());
                    }
                });
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
