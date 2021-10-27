


package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.Objects;

public class SscOfiicialBetMedol extends CommonModel {
    String codes;
    String id;
    String isQuick;
    String name;
    String playRuleId;
    boolean quanSelecter=false;//全大小单双 按钮的选中状态字段(都设置一个默认值,避免复用造成的显示错误)
    boolean daSelecter=false;
    boolean xiaoSelecter=false;
    boolean danSelecter=false;
    boolean shuangSelecter=false;
    boolean qingSelecter=false;
    int status=0;
    String typeOneName= Utils.getString(R.string.不是大小单双);//用于判断当前是否选中了大小单双(大小单双使用单独的布局)

    public String getTypeOneName() {
        return typeOneName;
    }

    public void setTypeOneName(String typeOneName) {
        this.typeOneName = typeOneName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SscOfiicialBetMedol that = (SscOfiicialBetMedol) o;
        return quanSelecter == that.quanSelecter &&
                daSelecter == that.daSelecter &&
                xiaoSelecter == that.xiaoSelecter &&
                danSelecter == that.danSelecter &&
                shuangSelecter == that.shuangSelecter &&
                qingSelecter == that.qingSelecter &&
                Objects.equals(codes, that.codes) &&
                Objects.equals(id, that.id) &&
                Objects.equals(isQuick, that.isQuick) &&
                Objects.equals(name, that.name) &&
                Objects.equals(playRuleId, that.playRuleId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codes, id, isQuick, name, playRuleId, quanSelecter, daSelecter, xiaoSelecter, danSelecter, shuangSelecter, qingSelecter);
    }

    public boolean isQuanSelecter() {
        return quanSelecter;
    }

    public void setQuanSelecter(boolean quanSelecter) {
        this.quanSelecter = quanSelecter;
    }

    public boolean isDaSelecter() {
        return daSelecter;
    }

    public void setDaSelecter(boolean daSelecter) {
        this.daSelecter = daSelecter;
    }

    public boolean isXiaoSelecter() {
        return xiaoSelecter;
    }

    public void setXiaoSelecter(boolean xiaoSelecter) {
        this.xiaoSelecter = xiaoSelecter;
    }

    public boolean isDanSelecter() {
        return danSelecter;
    }

    public void setDanSelecter(boolean danSelecter) {
        this.danSelecter = danSelecter;
    }

    public boolean isShuangSelecter() {
        return shuangSelecter;
    }

    public void setShuangSelecter(boolean shuangSelecter) {
        this.shuangSelecter = shuangSelecter;
    }

    public boolean isQingSelecter() {
        return qingSelecter;
    }

    public void setQingSelecter(boolean qingSelecter) {
        this.qingSelecter = qingSelecter;
    }








    public SscOfiicialBetMedol() {
    }

    public SscOfiicialBetMedol(String codes, String id, String isQuick, String name, String playRuleId) {
        this.codes = codes;
        this.id = id;
        this.isQuick = isQuick;
        this.name = name;
        this.playRuleId = playRuleId;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsQuick() {
        return isQuick;
    }

    public void setIsQuick(String isQuick) {
        this.isQuick = isQuick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayRuleId() {
        return playRuleId;
    }

    public void setPlayRuleId(String playRuleId) {
        this.playRuleId = playRuleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
