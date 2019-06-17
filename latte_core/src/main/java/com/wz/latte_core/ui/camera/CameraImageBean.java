package com.wz.latte_core.ui.camera;

import android.net.Uri;

/**
 * 存储一些中间值
 *
 * @author wangzhen
 * @date 2019/06/15
 */
public final class CameraImageBean {

    private Uri mPath;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance() {
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path) {
        this.mPath = path;
    }
}
