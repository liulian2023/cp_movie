package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common;

//倒计时抽象类
public abstract class CountDownTimer {

    public CountDownTimer(long millisInFuture, long countDownInterval) {
        throw new RuntimeException("Stub!");
    }

    public final synchronized void cancel() {
        throw new RuntimeException("Stub!");
    }

    public final synchronized CountDownTimer start() {
        throw new RuntimeException("Stub!");
    }

    public abstract void onTick(long var1);

    public abstract void onFinish();
}

