package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import com.uyt.ying.rxhttp.net.model.BaseEntity;

public class PackEntity extends BaseEntity {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int packId;
        boolean isLock;
        private String msg;
        private String money;

        public int getPackId() {
            return packId;
        }

        public void setPackId(int packId) {
            this.packId = packId;
        }

        public boolean isLock() {
            return isLock;
        }

        public void setLock(boolean lock) {
            isLock = lock;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

}
