package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

/**
 * 购彩大厅 全部彩票 recycleView数据适配
 */
public class Lottety extends CommonModel implements Serializable {
    private String imageId;
    private String name;
    int type_id;
    String isopenOffice;
    String isStart;
    int game;
    String qishuCount;
    List<String>idList;
    String id;
    boolean isXian= false;

    public boolean isXian() {
        return isXian;
    }

    public void setXian(boolean xian) {
        isXian = xian;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public String getQishuCount() {
        return qishuCount;
    }

    public void setQishuCount(String qishuCount) {
        this.qishuCount = qishuCount;
    }

    public String getImageId() {
        return imageId;
    }

    public String getId() {
        return id;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getIsopenOffice() {
        return isopenOffice;
    }

    public void setIsopenOffice(String isopenOffice) {
        this.isopenOffice = isopenOffice;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public Lottety(String imageId, String name, int type_id, String isopenOffice, String isStart, int game,String qishuCount,List<String>idList,String id) {
        this.imageId = imageId;
        this.name = name;
        this.type_id = type_id;
        this.isopenOffice = isopenOffice;
        this.isStart = isStart;
        this.game = game;
        this.qishuCount = qishuCount;
        this.idList = idList;
        this.id = id;
    }
}
