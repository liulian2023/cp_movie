package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;

public class OpenInviteRedModel extends CommonModel {
    boolean isOpened;
    BigDecimal amount;

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
