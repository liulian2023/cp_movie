package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChatTextMessageMedol implements Serializable {
    String dengji;
    String nickname;
    String content;
    //content=LiveTextMessage{nickname='null', pointGrade='null', content='{"isBack":"s:update","content":Utils.getString(R.string.安徽快三计划已更新)}'

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatTextMessageMedol(String dengji, String nickname, String content) {
        this.dengji = dengji;
        this.nickname = nickname;
        this.content = content;
    }
}
