package com.wz.latte_core.util.callback;

import android.support.annotation.Nullable;

/**
 * @author wangzhen
 * @date 2019/06/17
 */
public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
