package com.wz.latte_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wz.latte_core.app.Latte;

/**
 * Created by wangzhen on 2018/2/28.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
