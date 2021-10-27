package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel;

import java.util.Date;

public class MineBetEvenModel {
    Date startDate;
    Date endDate;
    int pageNo;
    int pageSize;
    int type;
    String typeId;
    String game;
    String remark;
    boolean isLoad;
    boolean showLoad;

    public boolean isShowLoad() {
        return showLoad;
    }

    public void setShowLoad(boolean showLoad) {
        this.showLoad = showLoad;
    }

    public MineBetEvenModel(Date startDate, Date endDate, int pageNo, int pageSize, int type, String typeId, String game, String remark, boolean isLoad, boolean showLoad) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.type = type;
        this.typeId = typeId;
        this.game = game;
        this.remark = remark;
        this.isLoad = isLoad;
        this.showLoad = showLoad;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }
}
