/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class TouzhuModel implements Serializable {

    String groupname;
    String name;
    String id;
    String money;
    String multiple="1";
    String defaultAmount;

    public String getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(String defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
