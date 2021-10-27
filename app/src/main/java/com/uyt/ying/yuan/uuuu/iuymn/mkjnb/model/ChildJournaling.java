package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.Date;

public class ChildJournaling implements Serializable {
    String name;
    String level;
    String touzhu;
    String winOrLose;
    String cz;
    String tx;
    String zj;
    String gryj;
    String hdfd;
    long id;
    Date start;
    Date end;
    String orderBy;


//    public ChildJournaling(String name, String level, String touzhu, String winOrLose, String cz, String tx, String zj, String gryj, String hdfd) {
//        this.name = name;
//        this.level = level;
//        this.touzhu = touzhu;
//        this.winOrLose = winOrLose;
//        this.cz = cz;
//        this.tx = tx;
//        this.zj = zj;
//        this.gryj = gryj;
//        this.hdfd = hdfd;
//
//    }


    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ChildJournaling(String name, String level, String touzhu, String winOrLose, String cz, String tx, String zj, String gryj, String hdfd, long id, Date start, Date end, String orderBy) {

        this.name = name;
        this.level = level;
        this.touzhu = touzhu;
        this.winOrLose = winOrLose;
        this.cz = cz;
        this.tx = tx;
        this.zj = zj;
        this.gryj = gryj;
        this.hdfd = hdfd;
        this.id = id;
        this.start = start;
        this.end = end;
        this.orderBy = orderBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public String getGryj() {
        return gryj;
    }

    public void setGryj(String gryj) {
        this.gryj = gryj;
    }

    public String getHdfd() {
        return hdfd;
    }

    public void setHdfd(String hdfd) {
        this.hdfd = hdfd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTouzhu() {
        return touzhu;
    }

    public void setTouzhu(String touzhu) {
        this.touzhu = touzhu;
    }

    public String getWinOrLose() {
        return winOrLose;
    }

    public void setWinOrLose(String winOrLose) {
        this.winOrLose = winOrLose;
    }
}
