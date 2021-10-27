package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.net_speed;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.SoftReference;
import java.util.Timer;
import java.util.TimerTask;

public class NetSpeedTimer {
    private long defaultDelay = 1000;
    private long defaultPeriod = 1000;
    private static final int ERROR_CODE = -101011010;
    private int mMsgWhat = ERROR_CODE;
    private NetSpeed mNetSpeed;
    private Handler mHandler;
    private Context mContext;
    private SpeedTimerTask mSpeedTimerTask;

    public static final int NET_SPEED_TIMER_DEFAULT = 101010;
    private Timer timer;

    public NetSpeedTimer(SoftReference<Context>context, NetSpeed netSpeed, SoftReference<Handler>  handlerSoftReference) {
        this.mContext = context.get();
        this.mNetSpeed = netSpeed;
        this.mHandler = handlerSoftReference.get();
    }

    public NetSpeedTimer setDelayTime(long delay) {
        this.defaultDelay = delay;
        return this;
    }

    public NetSpeedTimer setPeriodTime(long period) {
        this.defaultPeriod = period;
        return this;
    }

    public NetSpeedTimer setHanderWhat(int what) {
        this.mMsgWhat = what;
        return this;
    }

    /**
     * 开启获取网速定时器
     */
    public void startSpeedTimer() {
         timer = new Timer();
         if(mContext!=null){
             mSpeedTimerTask = new SpeedTimerTask(new SoftReference<>(mContext), mNetSpeed, new SoftReference<Handler>(mHandler),
                     mMsgWhat);
             timer.schedule(mSpeedTimerTask, defaultDelay, defaultPeriod);
         }
    }

    /**
     * 关闭定时器
     */
    public void stopSpeedTimer() {
        if (null != mSpeedTimerTask) {
            mSpeedTimerTask.cancel();
            mSpeedTimerTask=null;
        }
        if(timer!=null){
            timer.cancel();
            timer.cancel();
        }
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * @author
     * 静态内部类
     */
    private static class SpeedTimerTask extends TimerTask {
        private int mMsgWhat;
        private NetSpeed mNetSpeed;
        private Handler mHandler;
        private Context mContext;

        public SpeedTimerTask(SoftReference<Context>contextSoftReference, NetSpeed netSpeed,
                              SoftReference<Handler> handler, int what) {
            this.mContext = contextSoftReference.get();
            this.mHandler = handler.get();
            this.mNetSpeed = netSpeed;
            this.mMsgWhat = what;
        }

        @Override
        public void run() {
            if (null != mNetSpeed && null != mHandler) {
                Message obtainMessage = mHandler.obtainMessage();
                if (mMsgWhat != ERROR_CODE) {
                    obtainMessage.what = mMsgWhat;
                } else {
                    obtainMessage.what = NET_SPEED_TIMER_DEFAULT;
                }
                if(mContext!=null&&mHandler!=null){
                    obtainMessage.obj = mNetSpeed.getNetSpeed(mContext
                            .getApplicationInfo().uid);
                    mHandler.sendMessage(obtainMessage);
                }
            }
        }
    }


}
