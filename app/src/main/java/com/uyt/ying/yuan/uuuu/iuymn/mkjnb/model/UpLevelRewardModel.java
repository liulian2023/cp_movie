

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;
/*
    每日嘉奖model
 */
public class UpLevelRewardModel extends CommonModel{
    String level;
    String chengzhang;
    String jinji;
    String tiaoji;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getChengzhang() {
        return chengzhang;
    }

    public void setChengzhang(String chengzhang) {
        this.chengzhang = chengzhang;
    }

    public String getJinji() {
        return jinji;
    }

    public void setJinji(String jinji) {
        this.jinji = jinji;
    }

    public String getTiaoji() {
        return tiaoji;
    }

    public void setTiaoji(String tiaoji) {
        this.tiaoji = tiaoji;
    }

    public UpLevelRewardModel(String level, String chengzhang, String jinji, String tiaoji) {
        this.level = level;
        this.chengzhang = chengzhang;
        this.jinji = jinji;
        this.tiaoji = tiaoji;
    }
}
