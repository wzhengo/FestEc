package com.wz.festec.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.wz.latte_core.delegate.web.event.Event;

/**
 * @author wangzhen
 * @date 2019/06/02
 */
public class TestEvent extends Event {

    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall()", null);
                }
            });
        }
        return null;
    }
}
