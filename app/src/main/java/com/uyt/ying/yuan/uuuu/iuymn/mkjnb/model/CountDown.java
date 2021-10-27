package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class CountDown implements Serializable {
    String imageId;
    String name;
    String periodsNum;
    String nextPeriod;
    String iconId;
    String currentLotteryTime;//倒计时结束时间
    long shijiancha;//服务器和本地时间差
    String id;
    int type_id;
    String isopenOffice;
    String isStart;
    int game;
    String waitPeriod;
    String lastLotteryQiShu;
    boolean isXian;





    public CountDown(String imageId, String name, String periodsNum, String nextPeriod, String iconId, String currentLotteryTime, long shijiancha, String id, int type_id, String isopenOffice, String isStart, int game, String waitPeriod, String lastLotteryQiShu) {
        this.imageId = imageId;
        this.name = name;
        this.periodsNum = periodsNum;
        this.nextPeriod = nextPeriod;
        this.iconId = iconId;
        this.currentLotteryTime = currentLotteryTime;
        this.shijiancha = shijiancha;
        this.id = id;
        this.type_id = type_id;
        this.isopenOffice = isopenOffice;
        this.isStart = isStart;
        this.game = game;
        this.waitPeriod = waitPeriod;
        this.lastLotteryQiShu = lastLotteryQiShu;
    }

    public void setWaitPeriod(String waitPeriod) {
        this.waitPeriod = waitPeriod;
    }


    public String getLastLotteryQiShu() {
        return lastLotteryQiShu;
    }

    public void setLastLotteryQiShu(String lastLotteryQiShu) {
        this.lastLotteryQiShu = lastLotteryQiShu;
    }
    public String getWaitPeriod() {
        return waitPeriod;
    }

    public boolean isXian() {
        return isXian;
    }

    public void setXian(boolean xian) {
        isXian = xian;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getIsopenOffice() {
        return isopenOffice;
    }

    public void setIsopenOffice(String isopenOffice) {
        this.isopenOffice = isopenOffice;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }




    public String getCurrentLotteryTime() {

        return currentLotteryTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String current) {
        this.id = id;
    }

    public long getShijiancha() {
        return shijiancha;
    }

    public void setShijiancha(long shijiancha) {
        this.shijiancha = shijiancha;
    }

//    public CountDown(String imageUrl, String name, String periodsNum, String nextPeriod, String iconId, String currentLotteryTime, long shijiancha,String id,long count) {
//        this.imageUrl = imageUrl;
//        this.name = name;
//        this.periodsNum = periodsNum;
//        this.nextPeriod = nextPeriod;
//        this.iconId = iconId;
//        this.currentLotteryTime = currentLotteryTime;
//        this.shijiancha = shijiancha;
//        this.id = id;
//        this.count = count;
//    }



    public void setCurrentLotteryTime(String currentLotteryTime) {
        this.currentLotteryTime = currentLotteryTime;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriodsNum() {
        return periodsNum;
    }

    public void setPeriodsNum(String periodsNum) {
        this.periodsNum = periodsNum;
    }

    public String getNextPeriod() {
        return nextPeriod;
    }

    public void setNextPeriod(String nextPeriod) {
        this.nextPeriod = nextPeriod;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }
}

//    /**
//     * 倒计时计算
//     */
//    private void ComputeTime() {
//        msecond--;
//        if (msecond < 0) {
//            mmin--;
//            msecond = 59;
//            if (mmin < 0) {
//                mmin = 59;
//                mhour--;
//                if (mhour < 0) {
//                    // 倒计时结束
//                    mhour = 59;
//                    mday--;
//
//                }
//            }
//
//        }
//
//    }