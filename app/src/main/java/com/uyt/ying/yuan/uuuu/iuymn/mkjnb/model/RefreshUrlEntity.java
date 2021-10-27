package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;

public class RefreshUrlEntity  {


    /**
     * isLiving : 1
     * liveUrl : rtmp://pull.purikura.com.cn/live/2341268_1581771368?txSecret=e085e9c70027a749d9335c6f3d6c9e4d&txTime=5E47EECC
     * message : success
     * status : success
     */

    private int isLiving;
    private String liveUrl;
    private String message;
    private String status;

    public int getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(int isLiving) {
        this.isLiving = isLiving;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        try {
            this.liveUrl = AESUtil.decrypt(liveUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
