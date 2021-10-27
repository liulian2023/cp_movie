package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

/**
 * 用户在活动的状态
 */
public class HBStatusEntity  {


    private int success;
    private String message;
    private int isComplete;//0未完成活动;1已经完成活动
    private int isReceive;//0未领取;1已经领取完了( 先要isComplete=1才行)


    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public int getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(int isReceive) {
        this.isReceive = isReceive;
    }
}
