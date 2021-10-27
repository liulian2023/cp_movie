package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.net_speed;

import android.net.TrafficStats;

public class NetSpeed {
    private static final String TAG = NetSpeed.class.getSimpleName();
    private long lastTotalRxBytes = 0;
    private long lastTimeStamp = 0;

    public String getNetSpeed(int uid) {
        long nowTotalRxBytes = getTotalRxBytes(uid);
        long nowTimeStamp = System.currentTimeMillis();
        long l = nowTotalRxBytes - lastTotalRxBytes;
        long speed = (l==0l?1l:l * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换
        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;
        return String.valueOf(speed) + " kb/s";
    }


    //getApplicationInfo().uid
    public long getTotalRxBytes(int uid) {
        return TrafficStats.getUidRxBytes(uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);//转为KB
    }

}
