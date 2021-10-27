package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class MineInfoEntity extends CommonModel  {
  private   String remark;
  private int imageId;
  private Long messageNum;

    public Long getMessageNum() {

        return messageNum;
    }

    public void setMessageNum(Long messageNum) {
        this.messageNum = messageNum;
    }

    public MineInfoEntity(String remark, int imageId,  Long messageNum) {

        this.remark = remark;
        this.imageId = imageId;
        this.messageNum = messageNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


}
