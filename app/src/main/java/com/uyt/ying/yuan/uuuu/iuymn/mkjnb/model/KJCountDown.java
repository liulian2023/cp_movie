/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

/*
                svn上的
                 */
public class KJCountDown implements Serializable {

    /**
     * stoptimestr : 2019-06-07 15:08:00
     * qishu : 190607020
     * jgtime : 1200
     * diff : 589000
     * tqtime : 0
     * lastqishu : 41
     * message : 操作成功
     * status : success
     */

    private String stoptimestr;
    private String qishu;
    private int jgtime;
    private int diff;
    private int tqtime;
    private String lastqishu;
    private String message;
    private String status;

    public String getStoptimestr() {
        return stoptimestr;
    }

    public void setStoptimestr(String stoptimestr) {
        this.stoptimestr = stoptimestr;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public int getJgtime() {
        return jgtime;
    }

    public void setJgtime(int jgtime) {
        this.jgtime = jgtime;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getTqtime() {
        return tqtime;
    }

    public void setTqtime(int tqtime) {
        this.tqtime = tqtime;
    }

    public String getLastqishu() {
        return lastqishu;
    }

    public void setLastqishu(String lastqishu) {
        this.lastqishu = lastqishu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
