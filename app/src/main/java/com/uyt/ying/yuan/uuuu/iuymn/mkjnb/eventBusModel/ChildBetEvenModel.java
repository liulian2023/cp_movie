package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel;

import java.util.Date;

public class ChildBetEvenModel {
    int pageNo;
    int pageSize;
    String remark;
    Date startDate;
    Date endDate;
    String nickName;
    String game;
    String typeId;
    boolean isLoad;
    boolean showLoad;

    public boolean isShowLoad() {
        return showLoad;
    }

    public void setShowLoad(boolean showLoad) {
        this.showLoad = showLoad;
    }
    public int getPageNo() {
        return pageNo;

    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }

    public ChildBetEvenModel(int pageNo, int pageSize, String remark, Date startDate, Date endDate, String nickName, String game, String typeId, boolean isLoad,  boolean showLoad) {

        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.remark = remark;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nickName = nickName;
        this.game = game;
        this.typeId = typeId;
        this.isLoad = isLoad;
        this.showLoad = showLoad;
    }
}
