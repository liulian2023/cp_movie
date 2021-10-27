package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class UserTitleEntity {
    String jsonStr="";

    private UserTitleEntity() {
    }
    public static UserTitleEntity userTitleEntity;
    public static UserTitleEntity getInstance(){
        if(userTitleEntity==null){
            userTitleEntity = new UserTitleEntity();
        }
        return userTitleEntity;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
