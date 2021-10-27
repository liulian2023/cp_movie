package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class WinOrLoseModel  implements Serializable {
    String price;
    String type;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WinOrLoseModel(String price, String type) {

        this.price = price;
        this.type = type;
    }
}
