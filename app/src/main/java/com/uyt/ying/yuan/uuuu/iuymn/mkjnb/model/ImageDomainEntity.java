package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ImageDomainEntity {
    String jsonStr="";
    public static ImageDomainEntity imageDomainEntity;
    public static ImageDomainEntity getInstance(){
        if(imageDomainEntity == null){
            imageDomainEntity = new ImageDomainEntity();
        }
        return imageDomainEntity;
    }

    private ImageDomainEntity() {
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
