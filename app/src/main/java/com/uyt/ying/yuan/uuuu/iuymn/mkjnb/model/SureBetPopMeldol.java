package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.Objects;

public class SureBetPopMeldol extends CommonModel {

    String groupName;
    String betType;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public SureBetPopMeldol(String groupName, String betType) {
        this.groupName = groupName;
        this.betType = betType;
    }

    public SureBetPopMeldol(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SureBetPopMeldol that = (SureBetPopMeldol) o;
        return Objects.equals(groupName, that.groupName) &&
                Objects.equals(betType, that.betType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, betType);
    }
}
