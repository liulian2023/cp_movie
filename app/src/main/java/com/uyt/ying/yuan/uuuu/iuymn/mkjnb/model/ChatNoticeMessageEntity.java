package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ChatNoticeMessageEntity {
    String jsonStr = "";

    private ChatNoticeMessageEntity() {
    }
    public static ChatNoticeMessageEntity chatNoticeMessageEntity;
    public static ChatNoticeMessageEntity getInstance(){
        if(chatNoticeMessageEntity == null){
            chatNoticeMessageEntity = new ChatNoticeMessageEntity();
        }
        return chatNoticeMessageEntity;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
