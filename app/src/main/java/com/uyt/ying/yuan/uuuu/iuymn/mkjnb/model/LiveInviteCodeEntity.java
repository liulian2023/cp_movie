package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class LiveInviteCodeEntity {
    String jsonStr="";
    public static LiveInviteCodeEntity liveInviteCodeEntity;

    private LiveInviteCodeEntity() {
    }
    public static LiveInviteCodeEntity getInstance(){
        if(liveInviteCodeEntity == null){
            liveInviteCodeEntity = new LiveInviteCodeEntity();
        }
        return liveInviteCodeEntity;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
