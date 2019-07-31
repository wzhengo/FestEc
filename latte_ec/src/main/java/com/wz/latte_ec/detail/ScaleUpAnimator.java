package com.wz.latte_ec.detail;

import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;

/**
 * @author wangzhen
 * @date 2019/07/30
 */
public class ScaleUpAnimator extends BaseViewAnimator {

    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", 0.8f, 1f, 1.4f, 1.2f, 1),
                ObjectAnimator.ofFloat(target, "scaleY", 0.8f, 1f, 1.4f, 1.2f, 1)
        );
    }
}
