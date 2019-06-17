package com.wz.latte_core.delegate;

/**
 * Created by WangZhen on 2019/4/11.
 */
public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
