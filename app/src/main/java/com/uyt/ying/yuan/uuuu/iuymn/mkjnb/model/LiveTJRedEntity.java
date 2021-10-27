package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class LiveTJRedEntity {
    private String jsonStr="";

    private LiveTJRedEntity() {
    }
    public static LiveTJRedEntity liveTJRedEntity;
    public static LiveTJRedEntity getInstance(){
        if(liveTJRedEntity == null){
            liveTJRedEntity = new LiveTJRedEntity();
        }
        return liveTJRedEntity;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
