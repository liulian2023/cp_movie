package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.giftRain;

public class GiftRainBeen {
    int clickCount = 0;//点击红包的次数(第一次点击时请求拆包接口)
    boolean isHaveShowResult = false;//是否显示过抢包结果
    boolean isHaveRequestData =false;//是否有返回抢包数据
    boolean isEnd;
    boolean isSuccese =false;  //红包领取成功
    boolean isFail = false;   //红包领取失败
    String price;  //红包领取金额
    String failStr; //红包领取失败提示


    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFailStr() {
        return failStr;
    }

    public void setFailStr(String failStr) {
        this.failStr = failStr;
    }

    public boolean isSuccese() {
        return isSuccese;
    }

    public void setSuccese(boolean succese) {
        isSuccese = succese;
    }

    public boolean isFail() {
        return isFail;
    }

    public void setFail(boolean fail) {
        isFail = fail;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public boolean isHaveShowResult() {
        return isHaveShowResult;
    }

    public void setHaveShowResult(boolean haveShowResult) {
        isHaveShowResult = haveShowResult;
    }

    public boolean isHaveRequestData() {
        return isHaveRequestData;
    }

    public void setHaveRequestData(boolean haveRequestData) {
        isHaveRequestData = haveRequestData;
    }
}
