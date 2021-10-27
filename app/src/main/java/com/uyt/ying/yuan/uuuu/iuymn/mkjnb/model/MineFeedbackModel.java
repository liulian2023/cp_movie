package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class MineFeedbackModel extends CommonModel {
    String  time;
    String type;
    String content;
    String id;
    String reply;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public MineFeedbackModel(String time, String type, String content, String id,String reply) {

        this.time = time;
        this.type = type;
        this.content = content;
        this.id = id;
        this.reply = reply;
    }
}
