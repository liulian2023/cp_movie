package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public enum PackType {
    /**
     * 红包类型 趣约  天降 专享
     */
    QY(1, Utils.getString(R.string.趣约)),
    TJ(2,Utils.getString(R.string.天降)),
    ZX(3,Utils.getString(R.string.专享));


    private int value;
    private String des;

    PackType(int value, String des) {
        this.value = value;
        this.des = des;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public static PackType valueOf(int value){
        for (PackType typeEnume:PackType.values()){
            if (typeEnume.getValue()==value){
                return typeEnume;
            }
        }
        return null;
    }
}
