package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public enum GameTypeEnum {

    KUAISAN(1,Utils.getString(R.string.快三)),
    SSC(2,Utils.getString(R.string.时时彩)),
    RACE(3,Utils.getString(R.string.赛车)),
    MARKSIX(4,Utils.getString(R.string.六合彩)),
    DANDAN(5,Utils.getString(R.string.PC蛋蛋)),
    HAPPY8(6,Utils.getString(R.string.快乐8)),
    LUCKFARM(7,Utils.getString(R.string.幸运农场)),
    HAPPY10(8,Utils.getString(R.string.快乐10)),
    XUANWU(9,Utils.getString(R.string.十一选5)),
    LIVESHOP(1212, Utils.getString(R.string.直播购彩));



    /**
     * 状态值
     */
    private int value;
    /**
     * 类型描述
     */
    private String description;

    GameTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static GameTypeEnum valueOf(long value){
        for (GameTypeEnum typeEnume:GameTypeEnum.values()){
            if (typeEnume.getValue()==value){
                return typeEnume;
            }
        }
        return null;
    }


}
