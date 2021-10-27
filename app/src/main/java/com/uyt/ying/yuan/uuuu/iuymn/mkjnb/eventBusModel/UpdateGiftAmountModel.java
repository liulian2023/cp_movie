package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel;

import java.math.BigDecimal;

public class UpdateGiftAmountModel {
    BigDecimal giftAmount;
    String id;

    public UpdateGiftAmountModel(BigDecimal giftAmount, String id) {
        this.giftAmount = giftAmount;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(BigDecimal giftAmount) {
        this.giftAmount = giftAmount;
    }


}
