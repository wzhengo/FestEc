package com.wz.latte_core.delegate.web.event;

import com.wz.latte_core.util.LatteLogger;

/**
 * @author wangzhen
 * @date 2019/06/02
 */
public class UndefineEvent extends Event {

    @Override
    public String execute(String params) {
        LatteLogger.d("UndefineEvent", params);
        return null;
    }
}
