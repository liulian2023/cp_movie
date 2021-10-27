package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import com.uyt.ying.rxhttp.net.model.BaseEntity;

import java.util.List;

public class GiftEntity extends BaseEntity {


    /**
     * giftLists : [{"createdDate":1581138925000,"createdUser":"sys","giftAmount":123,"giftIntroduction":"33333","giftLogo":"uploadimages202002081581138647621.jpg","giftTitle":"222222","id":2,"isDelete":0,"specialEffects":"uploadimages202002081581138920092.gif"},{"createdDate":1581245572000,"createdUser":"sys","giftAmount":555,"giftIntroduction":"das","giftLogo":"uploadimages202002091581245544929.gif","giftTitle":"3333","id":3,"isDelete":0,"specialEffects":"uploadimages202002091581245557621.svga"}]
     * status : success
     */

    private List<GiftListsBean> giftLists;



    public List<GiftListsBean> getGiftLists() {
        return giftLists;
    }

    public void setGiftLists(List<GiftListsBean> giftLists) {
        this.giftLists = giftLists;
    }

    public static class GiftListsBean {
        /**
         * createdDate : 1581138925000
         * createdUser : sys
         * giftAmount : 123.0
         * giftIntroduction : 33333
         * giftLogo : uploadimages202002081581138647621.jpg
         * giftTitle : 222222
         * id : 2
         * isDelete : 0
         * specialEffects : uploadimages202002081581138920092.gif
         *
         */

        private long createdDate;
        private String createdUser;
        private String giftAmount;
        private String giftIntroduction;
        private String giftLogo;
        private String giftTitle;
        private long id;
        private int isDelete;
        private String specialEffects;
        private String gear;
        private String gearTime;
        private boolean isSecect = false;
        private boolean isGz=false;

        public String getGear() {
            return gear;
        }

        public void setGear(String gear) {
            this.gear = gear;
        }

        public String getGearTime() {
            return gearTime;
        }

        public void setGearTime(String gearTime) {
            this.gearTime = gearTime;
        }

        public boolean isGz() {
            return isGz;
        }

        public void setGz(boolean gz) {
            isGz = gz;
        }

        public boolean isSecect() {
            return isSecect;
        }

        public void setSecect(boolean secect) {
            this.isSecect = secect;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getGiftAmount() {
            return giftAmount;
        }

        public void setGiftAmount(String giftAmount) {
            this.giftAmount = giftAmount;
        }

        public String getGiftIntroduction() {
            return giftIntroduction;
        }

        public void setGiftIntroduction(String giftIntroduction) {
            this.giftIntroduction = giftIntroduction;
        }

        public String getGiftLogo() {
            return giftLogo;
        }

        public void setGiftLogo(String giftLogo) {
            this.giftLogo = giftLogo;
        }

        public String getGiftTitle() {
            return giftTitle;
        }

        public void setGiftTitle(String giftTitle) {
            this.giftTitle = giftTitle;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getSpecialEffects() {
            return specialEffects;
        }

        public void setSpecialEffects(String specialEffects) {
            this.specialEffects = specialEffects;
        }
    }
}
