package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public enum RankTypeEnum  {

    GIFT(1, Utils.getString(R.string.礼物榜)),
    CHOCK(2,Utils.getString(R.string.中奖榜)),
    QY(3,Utils.getString(R.string.邀请榜)),
    ZX(4,Utils.getString(R.string.专享榜));

    private int value;
    private String des;


    RankTypeEnum(int value, String des) {
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


    public static RankTypeEnum valueOf(int value){
        for (RankTypeEnum typeEnume:RankTypeEnum.values()){
            if (typeEnume.getValue()==value){
                return typeEnume;
            }
        }
        return null;
    }



}
