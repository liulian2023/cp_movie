package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChatUpdateMessageMedol implements Serializable {
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatUpdateMessageMedol(String content) {
        this.content = content;
    }
}
