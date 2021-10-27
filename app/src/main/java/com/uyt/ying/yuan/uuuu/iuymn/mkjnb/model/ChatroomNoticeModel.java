
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ChatroomNoticeModel extends CommonModel  {
    String title;
    String time;
    String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatroomNoticeModel(String title, String time, String content) {
        this.title = title;
        this.time = time;
        this.content = content;
    }
}
