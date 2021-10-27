package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChildOpenModel implements Serializable {
    String type;
    String editText;
    String editText1;

    public String getType() {
        return type;
    }

    public ChildOpenModel(String type, String editText, String editText1) {
        this.type = type;
        this.editText = editText;
        this.editText1 = editText1;
    }

    public String getEditText1() {
        return editText1;
    }

    public void setEditText1(String editText1) {
        this.editText1 = editText1;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    public ChildOpenModel(String type, String editText) {
        this.type = type;
        this.editText = editText;
    }
}
