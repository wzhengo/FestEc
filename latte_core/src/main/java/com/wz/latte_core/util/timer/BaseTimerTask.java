package com.wz.latte_core.util.timer;

import java.util.TimerTask;

/**
 * Created by WangZhen on 2019/4/17.
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
