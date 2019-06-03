package com.wz.latte_core.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.wz.latte_core.delegate.web.event.Event;
import com.wz.latte_core.delegate.web.event.EventManager;

/**
 * Created by WangZhen on 2019-05-26.
 */
final class LatteWebInterface {

    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().creaetEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
