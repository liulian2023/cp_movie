package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChatRedMessageMedol implements Serializable {
    private String nickname; //昵称
    private String pointGrade;//会员等级
    private String content;//红包备注
//    private String logo;//图片路径
    private String redId;//红包id

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPointGrade() {
        return pointGrade;
    }

    public void setPointGrade(String pointGrade) {
        this.pointGrade = pointGrade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getRedId() {
        return redId;
    }

    public void setRedId(String redId) {
        this.redId = redId;
    }

    public ChatRedMessageMedol(String nickname, String pointGrade, String content, String redId) {
        this.nickname = nickname;
        this.pointGrade = pointGrade;
        this.content = content;
        this.redId = redId;
    }
}
