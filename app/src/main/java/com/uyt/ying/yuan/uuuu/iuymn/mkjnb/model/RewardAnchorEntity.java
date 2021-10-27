package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.Objects;

public class RewardAnchorEntity {
    String price;
    boolean isSelector =false;

    public RewardAnchorEntity(String price) {
        this.price = price;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public void setSelector(boolean selector) {
        isSelector = selector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RewardAnchorEntity that = (RewardAnchorEntity) o;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
