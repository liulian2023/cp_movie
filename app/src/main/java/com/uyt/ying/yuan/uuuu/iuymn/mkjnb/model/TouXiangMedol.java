
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class TouXiangMedol extends CommonModel  {
    String image;
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TouXiangMedol(String image, String userId) {
        this.image = image;
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
