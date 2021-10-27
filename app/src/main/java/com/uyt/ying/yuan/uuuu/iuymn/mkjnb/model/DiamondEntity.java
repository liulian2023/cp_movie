package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class DiamondEntity {

    /**
     * gainZhuanShiConditionsList : [{"createdDate":1608967963000,"createdUser":"qixi001","gainZhuanShi":1,"gainZhuanShiByGift":{"medalInfo0Id":1342055173215883265,"medalInfo0Image":"upload/images/20201231/1609381028194.png","medalInfo0Name":Utils.getString(R.string.凤舞九天),"medalInfo1Id":1342719100669005825,"medalInfo1Image":"upload/images/20201226/1608964134807.png","medalInfo1Name":Utils.getString(R.string.骑士),"medalInfo2Id":1342729239673311232,"medalInfo2Image":"upload/images/20201231/1609378608417.png","medalInfo2Name":Utils.getString(R.string.崭露头角),"type0Days":1,"type1Days":1,"type2Days":1},"id":23,"isDelete":0,"lastModifiedDate":1608968131000,"lastModifiedUser":"qixi001","orderCodePreByType":"TZ","price":1,"type":2,"userGainSuccess":1},{"createdDate":1608967986000,"createdUser":"qixi001","gainZhuanShi":2,"gainZhuanShiByGift":{"medalInfo0Id":1342055415772483585,"medalInfo0Image":"upload/images/20201231/1609380927795.png","medalInfo0Name":Utils.getString(R.string.布加迪威龙),"medalInfo1Id":1342720521393016832,"medalInfo1Image":"upload/images/20201226/1608964464923.png","medalInfo1Name":Utils.getString(R.string.伯爵),"medalInfo2Id":1,"medalInfo2Image":"upload/images/20201231/1609379042465.png","medalInfo2Name":Utils.getString(R.string.推广新秀),"type0Days":2,"type1Days":3,"type2Days":2},"id":24,"isDelete":0,"lastModifiedDate":1608968168000,"lastModifiedUser":"qixi001","orderCodePreByType":"TZ","price":2,"type":2,"userGainSuccess":1},{"createdDate":1608967996000,"createdUser":"qixi001","gainZhuanShi":3,"gainZhuanShiByGift":{"medalInfo0Id":1342056466009427969,"medalInfo0Image":"upload/images/20201231/1609380996011.png","medalInfo0Name":Utils.getString(R.string.千里马),"medalInfo1Id":1342722507286908928,"medalInfo1Image":"upload/images/20201226/1608964943757.png","medalInfo1Name":Utils.getString(R.string.公爵),"medalInfo2Id":2,"medalInfo2Image":"upload/images/20201231/1609379026345.png","medalInfo2Name":Utils.getString(R.string.推广至尊),"type0Days":3,"type1Days":3,"type2Days":3},"id":25,"isDelete":0,"lastModifiedDate":1609382313000,"lastModifiedUser":"xbgoogle","orderCodePreByType":"TZ","price":3,"type":2,"userGainSuccess":1}]
     * price : 22
     * message : success
     * status : success
     */

    private String price;
    private String message;
    private String status;
    /**
     * createdDate : 1608967963000
     * createdUser : qixi001
     * gainZhuanShi : 1
     * gainZhuanShiByGift : {"medalInfo0Id":1342055173215883265,"medalInfo0Image":"upload/images/20201231/1609381028194.png","medalInfo0Name":Utils.getString(R.string.凤舞九天),"medalInfo1Id":1342719100669005825,"medalInfo1Image":"upload/images/20201226/1608964134807.png","medalInfo1Name":Utils.getString(R.string.骑士),"medalInfo2Id":1342729239673311232,"medalInfo2Image":"upload/images/20201231/1609378608417.png","medalInfo2Name":Utils.getString(R.string.崭露头角),"type0Days":1,"type1Days":1,"type2Days":1}
     * id : 23
     * isDelete : 0
     * lastModifiedDate : 1608968131000
     * lastModifiedUser : qixi001
     * orderCodePreByType : TZ
     * price : 1
     * type : 2
     * userGainSuccess : 1
     */

    private List<GainZhuanShiConditionsListBean> gainZhuanShiConditionsList;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GainZhuanShiConditionsListBean> getGainZhuanShiConditionsList() {
        return gainZhuanShiConditionsList;
    }

    public void setGainZhuanShiConditionsList(List<GainZhuanShiConditionsListBean> gainZhuanShiConditionsList) {
        this.gainZhuanShiConditionsList = gainZhuanShiConditionsList;
    }

    public static class GainZhuanShiConditionsListBean {
        private String createdDate;
        private String createdUser;
        private String gainZhuanShi;
        /**
         * medalInfo0Id : 1342055173215883265
         * medalInfo0Image : upload/images/20201231/1609381028194.png
         * medalInfo0Name : 凤舞九天
         * medalInfo1Id : 1342719100669005825
         * medalInfo1Image : upload/images/20201226/1608964134807.png
         * medalInfo1Name : 骑士
         * medalInfo2Id : 1342729239673311232
         * medalInfo2Image : upload/images/20201231/1609378608417.png
         * medalInfo2Name : 崭露头角
         * type0Days : 1
         * type1Days : 1
         * type2Days : 1
         */

        private GainZhuanShiByGiftBean gainZhuanShiByGift;
        private String id;
        private String isDelete;
        private String lastModifiedDate;
        private String lastModifiedUser;
        private String orderCodePreByType;
        private String price;
        private String type;
        private String userGainSuccess;

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getGainZhuanShi() {
            return gainZhuanShi;
        }

        public void setGainZhuanShi(String gainZhuanShi) {
            this.gainZhuanShi = gainZhuanShi;
        }

        public GainZhuanShiByGiftBean getGainZhuanShiByGift() {
            return gainZhuanShiByGift;
        }

        public void setGainZhuanShiByGift(GainZhuanShiByGiftBean gainZhuanShiByGift) {
            this.gainZhuanShiByGift = gainZhuanShiByGift;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public String getOrderCodePreByType() {
            return orderCodePreByType;
        }

        public void setOrderCodePreByType(String orderCodePreByType) {
            this.orderCodePreByType = orderCodePreByType;
        }

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

        public String getUserGainSuccess() {
            return userGainSuccess;
        }

        public void setUserGainSuccess(String userGainSuccess) {
            this.userGainSuccess = userGainSuccess;
        }

        public static class GainZhuanShiByGiftBean {
            private String medalInfo0Id;
            private String medalInfo0Image;
            private String medalInfo0Name;
            private String medalInfo1Id;
            private String medalInfo1Image;
            private String medalInfo1Name;
            private String medalInfo2Id;
            private String medalInfo2Image;
            private String medalInfo2Name;
            private String type0Days;
            private String type1Days;
            private String type2Days;
            private String medalInfo0Status;
            private String medalInfo1Status;
            private String medalInfo2Status;

            public String getMedalInfo0Status() {
                return medalInfo0Status;
            }

            public void setMedalInfo0Status(String medalInfo0Status) {
                this.medalInfo0Status = medalInfo0Status;
            }

            public String getMedalInfo1Status() {
                return medalInfo1Status;
            }

            public void setMedalInfo1Status(String medalInfo1Status) {
                this.medalInfo1Status = medalInfo1Status;
            }

            public String getMedalInfo2Status() {
                return medalInfo2Status;
            }

            public void setMedalInfo2Status(String medalInfo2Status) {
                this.medalInfo2Status = medalInfo2Status;
            }

            public String getMedalInfo0Id() {
                return medalInfo0Id;
            }

            public void setMedalInfo0Id(String medalInfo0Id) {
                this.medalInfo0Id = medalInfo0Id;
            }

            public String getMedalInfo0Image() {
                return medalInfo0Image;
            }

            public void setMedalInfo0Image(String medalInfo0Image) {
                this.medalInfo0Image = medalInfo0Image;
            }

            public String getMedalInfo0Name() {
                return medalInfo0Name;
            }

            public void setMedalInfo0Name(String medalInfo0Name) {
                this.medalInfo0Name = medalInfo0Name;
            }

            public String getMedalInfo1Id() {
                return medalInfo1Id;
            }

            public void setMedalInfo1Id(String medalInfo1Id) {
                this.medalInfo1Id = medalInfo1Id;
            }

            public String getMedalInfo1Image() {
                return medalInfo1Image;
            }

            public void setMedalInfo1Image(String medalInfo1Image) {
                this.medalInfo1Image = medalInfo1Image;
            }

            public String getMedalInfo1Name() {
                return medalInfo1Name;
            }

            public void setMedalInfo1Name(String medalInfo1Name) {
                this.medalInfo1Name = medalInfo1Name;
            }

            public String getMedalInfo2Id() {
                return medalInfo2Id;
            }

            public void setMedalInfo2Id(String medalInfo2Id) {
                this.medalInfo2Id = medalInfo2Id;
            }

            public String getMedalInfo2Image() {
                return medalInfo2Image;
            }

            public void setMedalInfo2Image(String medalInfo2Image) {
                this.medalInfo2Image = medalInfo2Image;
            }

            public String getMedalInfo2Name() {
                return medalInfo2Name;
            }

            public void setMedalInfo2Name(String medalInfo2Name) {
                this.medalInfo2Name = medalInfo2Name;
            }

            public String getType0Days() {
                return type0Days;
            }

            public void setType0Days(String type0Days) {
                this.type0Days = type0Days;
            }

            public String getType1Days() {
                return type1Days;
            }

            public void setType1Days(String type1Days) {
                this.type1Days = type1Days;
            }

            public String getType2Days() {
                return type2Days;
            }

            public void setType2Days(String type2Days) {
                this.type2Days = type2Days;
            }
        }
    }
}
