package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class LotteryClassModel extends CommonModel implements Serializable {
  private String id;
  private int type_id;
  private String typename;
  private int game;
  private String isopenOffice;
  private String imageurl;
  private int status;//选中状态 0:未选中 1:选中
  private String isStart;
  private long jgTime;
  private List<String>idList;
  private boolean isXian;

  public boolean isXian() {
    return isXian;
  }

  public void setXian(boolean xian) {
    isXian = xian;
  }

  public List<String> getIdList() {
    return idList;
  }

  public void setIdList(List<String> idList) {
    this.idList = idList;
  }

  public String getIsStart() {
    return isStart;
  }

  public void setIsStart(String isStart) {
    this.isStart = isStart;
  }

  public LotteryClassModel(String id, int type_id, String typename, int game, String isopenOffice, String imageurl, String isStart, long jgTime, List<String>idList) {
    this.id = id;
    this.type_id = type_id;
    this.typename = typename;
    this.game = game;
    this.isopenOffice = isopenOffice;
    this.imageurl = imageurl;
    this.isStart = isStart;
    this.jgTime=jgTime;
    this.idList=idList;
  }

  public long getJgTime() {
    return jgTime;
  }

  public void setJgTime(long jgTime) {
    this.jgTime = jgTime;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getId() {

    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getType_id() {
    return type_id;
  }

  public void setType_id(int type_id) {
    this.type_id = type_id;
  }

  public String getTypename() {
    return typename;
  }

  public void setTypename(String typename) {
    this.typename = typename;
  }

  public int getGame() {
    return game;
  }

  public void setGame(int game) {
    this.game = game;
  }

  public String getIsopenOffice() {
    return isopenOffice;
  }

  public void setIsopenOffice(String isopenOffice) {
    this.isopenOffice = isopenOffice;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }
}
