package com.wz.latte_core.util.callback;

import java.util.WeakHashMap;

/**
 * @author wangzhen
 * @date 2019/06/17
 */
public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class HOLDER {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return HOLDER.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }
}
