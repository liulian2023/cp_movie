package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class UserLevel implements Serializable {
    String vip;
    String level;
    String integral;

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public UserLevel(String vip, String level, String integral) {

        this.vip = vip;
        this.level = level;
        this.integral = integral;
    }
}
