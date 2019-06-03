package com.wz.latte_core.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.delegate.web.WebDelegate;
import com.wz.latte_core.delegate.web.WebDelegateImpl;

/**
 * @author wangzhen
 * @date 2019/05/28
 */
public class Router {

    private Router() {
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.start(webDelegate);
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView == null) {
            throw new NullPointerException("WebView is null!");
        } else {
            webView.loadUrl(url);
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
