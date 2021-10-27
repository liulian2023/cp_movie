package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class SscTodayOpenMedol extends CommonModel  {
    String qihao;
    String lotteryNo;
    String markdx;//和值中的第二个(大小)
    String markds;//和值中的第三个(单双)
    String marklh;//和值中的第四个(龙虎)
    int game;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public String getQihao() {
        return qihao;
    }

    public void setQihao(String qihao) {
        this.qihao = qihao;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public String getMarkdx() {
        return markdx;
    }

    public void setMarkdx(String markdx) {
        this.markdx = markdx;
    }

    public String getMarkds() {
        return markds;
    }

    public void setMarkds(String markds) {
        this.markds = markds;
    }

    public String getMarklh() {
        return marklh;
    }

    public void setMarklh(String marklh) {
        this.marklh = marklh;
    }

    public SscTodayOpenMedol(String qihao, String lotteryNo, String markdx, String markds, String marklh,String time) {
        this.qihao = qihao;
        this.lotteryNo = lotteryNo;
        this.markdx = markdx;
        this.markds = markds;
        this.marklh = marklh;
        this.time = time;
    }
}
