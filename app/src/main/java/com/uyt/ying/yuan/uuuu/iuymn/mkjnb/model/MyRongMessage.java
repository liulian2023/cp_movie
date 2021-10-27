/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

import io.rong.imlib.model.MessageContent;

public class MyRongMessage implements Serializable {

    String objectName;
    MessageContent content;





    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public MessageContent getContent() {
        return content;
    }

    public void setContent(MessageContent content) {
        this.content = content;
    }

    public MyRongMessage(String objectName, MessageContent content) {
        this.objectName = objectName;
        this.content = content;
    }

    public MyRongMessage() {
    }

    @Override
    public String toString() {
        return "MyRongMessage{" +
                "objectName='" + objectName + '\'' +
                ", content=" + content +
                '}';
    }
}
