package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class MountAndMedalEntity {

    /**
     * buyZhuanShi : 888
     * conditions : 888砖石购买
     * czADays : 0
     * czATotalPriceOneDay : 0
     * czBPriceOneBi : 0
     * czCTotalPrice : 0
     * czType : 0
     * effectivePerson : 0
     * type : 0
     * tzADays : 0
     * tzATotalPriceOneDay : 0
     * tzBPriceOneBi : 0
     * tzCTotalPrice : 0
     * tzType : 0
     */

    private String buyZhuanShi;
    private String conditions;
    private String czADays;
    private String czATotalPriceOneDay;
    private String czBPriceOneBi;
    private String czCTotalPrice;
    private String czType;
    private String effectivePerson;
    private String type;
    private String tzADays;
    private String tzATotalPriceOneDay;
    private String tzBPriceOneBi;
    private String tzCTotalPrice;
    private String tzType;
    private String useDays;

    private String medalInfoType;//勋章类型(0坐骑；1勋章；2称号牌)
    private String nums;//该勋章的个数
    private int status;//0未使用；1使用中

    private int customType=0;//0 已获取 1未获取 2已使用 3 未使用

    public String getUseDays() {
        return useDays;
    }

    public void setUseDays(String useDays) {
        this.useDays = useDays;
    }

    public String getMedalInfoType() {
        return medalInfoType;
    }

    public void setMedalInfoType(String medalInfoType) {
        this.medalInfoType = medalInfoType;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomType() {
        return customType;
    }

    public void setCustomType(int customType) {
        this.customType = customType;
    }

    public String getBuyZhuanShi() {
        return buyZhuanShi;
    }

    public void setBuyZhuanShi(String buyZhuanShi) {
        this.buyZhuanShi = buyZhuanShi;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getCzADays() {
        return czADays;
    }

    public void setCzADays(String czADays) {
        this.czADays = czADays;
    }

    public String getCzATotalPriceOneDay() {
        return czATotalPriceOneDay;
    }

    public void setCzATotalPriceOneDay(String czATotalPriceOneDay) {
        this.czATotalPriceOneDay = czATotalPriceOneDay;
    }

    public String getCzBPriceOneBi() {
        return czBPriceOneBi;
    }

    public void setCzBPriceOneBi(String czBPriceOneBi) {
        this.czBPriceOneBi = czBPriceOneBi;
    }

    public String getCzCTotalPrice() {
        return czCTotalPrice;
    }

    public void setCzCTotalPrice(String czCTotalPrice) {
        this.czCTotalPrice = czCTotalPrice;
    }

    public String getCzType() {
        return czType;
    }

    public void setCzType(String czType) {
        this.czType = czType;
    }

    public String getEffectivePerson() {
        return effectivePerson;
    }

    public void setEffectivePerson(String effectivePerson) {
        this.effectivePerson = effectivePerson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTzADays() {
        return tzADays;
    }

    public void setTzADays(String tzADays) {
        this.tzADays = tzADays;
    }

    public String getTzATotalPriceOneDay() {
        return tzATotalPriceOneDay;
    }

    public void setTzATotalPriceOneDay(String tzATotalPriceOneDay) {
        this.tzATotalPriceOneDay = tzATotalPriceOneDay;
    }

    public String getTzBPriceOneBi() {
        return tzBPriceOneBi;
    }

    public void setTzBPriceOneBi(String tzBPriceOneBi) {
        this.tzBPriceOneBi = tzBPriceOneBi;
    }

    public String getTzCTotalPrice() {
        return tzCTotalPrice;
    }

    public void setTzCTotalPrice(String tzCTotalPrice) {
        this.tzCTotalPrice = tzCTotalPrice;
    }

    public String getTzType() {
        return tzType;
    }

    public void setTzType(String tzType) {
        this.tzType = tzType;
    }
}
