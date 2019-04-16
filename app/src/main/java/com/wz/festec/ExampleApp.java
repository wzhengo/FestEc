package com.wz.festec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wz.latte_core.app.Latte;
import com.wz.latte_core.net.interceptors.DebugInterceptor;

/**
 * Created by WangZhen on 2019/4/11.
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
