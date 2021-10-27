package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import java.util.List;

public class LiveLotteryEntity {
    private List<OpenNoMulBean> OpenMulNoList;
    private long cpId;
    private int game;
    private int typeId;
    private String typeName;
    private String image_url;
    private String openQishu;   //上期开奖期数
    private String currentQishu;   //当前期数
    private boolean isShow;       //是否显示或者隐藏
 //   private String currCountTime;//当前倒计时显示的时间
    private boolean isXian = false;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isXian() {
        return isXian;
    }

    public void setXian(boolean xian) {
        isXian = xian;
    }

    public List<OpenNoMulBean> getOpenMulNoList() {
        return OpenMulNoList;
    }

    public void setOpenMulNoList(List<OpenNoMulBean> openMulNoList) {
        OpenMulNoList = openMulNoList;
    }

    public long getCpId() {
        return cpId;
    }

    public void setCpId(long cpId) {
        this.cpId = cpId;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getOpenQishu() {
        return openQishu;
    }

    public void setOpenQishu(String openQishu) {
        this.openQishu = openQishu;
    }

    public String getCurrentQishu() {
        return currentQishu;
    }

    public void setCurrentQishu(String currentQishu) {
        this.currentQishu = currentQishu;
    }


    //    public String getCurrCountTime() {
//        return currCountTime;
//    }
//
//    public void setCurrCountTime(String currCountTime) {
//        this.currCountTime = currCountTime;
//    }
}
