package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class GiftNumEntity {


    private List<GiftNumListBean> giftNumList;

    public List<GiftNumListBean> getGiftNumList() {
        return giftNumList;
    }

    public void setGiftNumList(List<GiftNumListBean> giftNumList) {
        this.giftNumList = giftNumList;
    }

    public static class GiftNumListBean {
        /**
         * num : 0
         * des : 其他数量
         * isSelect : false
         */

        private int num;
        private String des;
        private boolean isSelect;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public boolean isIsSelect() {
            return isSelect;
        }

        public void setIsSelect(boolean isSelect) {
            this.isSelect = isSelect;
        }
    }
}
