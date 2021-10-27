package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class SscOfficialChooseMedol extends CommonModel {
    String id;
    String name;
    String odds;
    String parentId;
    String remake;
    int status=0; //选中状态的标识
    boolean canClick=false;

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public String getRemake() {
        return remake;
    }

    @Override
    public String toString() {
        return "SscOfficialChooseMedol{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", odds='" + odds + '\'' +
                ", parentId='" + parentId + '\'' +
                ", remake='" + remake + '\'' +
                ", status=" + status +
                '}';
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SscOfficialChooseMedol(String id, String name, String odds, String parentId) {
        this.id = id;
        this.name = name;
        this.odds = odds;
        this.parentId = parentId;
    }
}
