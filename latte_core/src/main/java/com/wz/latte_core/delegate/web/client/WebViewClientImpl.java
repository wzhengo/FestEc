package com.wz.latte_core.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wz.latte_core.app.Latte;
import com.wz.latte_core.delegate.IPageLoadListener;
import com.wz.latte_core.delegate.web.WebDelegate;
import com.wz.latte_core.delegate.web.route.Router;
import com.wz.latte_core.ui.loader.LatteLoader;
import com.wz.latte_core.util.LatteLogger;

/**
 * @author wangzhen
 * @date 2019/05/28
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener;

    private static final Handler HANDLER = Latte.getHandler();

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
