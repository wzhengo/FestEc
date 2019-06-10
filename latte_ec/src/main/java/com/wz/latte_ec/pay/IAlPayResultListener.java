package com.wz.latte_ec.pay;

/**
 * @author wangzhen
 * @date 2019/06/08
 */
public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
