package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class LiveNoticeEntity {
    String jsonStr="";

    private LiveNoticeEntity() {
    }
    public static LiveNoticeEntity liveNoticeEntity;
    public static LiveNoticeEntity getInstance(){
        if(liveNoticeEntity==null){
            liveNoticeEntity = new LiveNoticeEntity();
        }
        return liveNoticeEntity;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
