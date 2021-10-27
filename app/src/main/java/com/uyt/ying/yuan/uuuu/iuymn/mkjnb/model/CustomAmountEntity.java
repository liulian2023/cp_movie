package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class CustomAmountEntity {
    String amount;
    int status=0;

    public CustomAmountEntity(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
