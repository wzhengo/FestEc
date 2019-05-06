package com.wz.latte_core.app;

import android.content.Context;

/**
 * Created by WangZhen on 2019/4/11.
 */
public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplication() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
