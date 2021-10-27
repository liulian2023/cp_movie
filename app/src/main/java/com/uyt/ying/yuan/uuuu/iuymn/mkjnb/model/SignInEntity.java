package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.util.ArrayList;
import java.util.List;

public class SignInEntity {

    /**
     * gainZhuanShiConditionsList : [{"createdDate":1609499442000,"createdUser":"nangua","gainJsonByGift":"{\"medalInfo0Id\":1342056261306421248,\"type0Days\":1,\"type1Days\":0,\"type2Days\":0}","gainZhuanShi":1,"gainZhuanShiByGift":{"medalInfo0Id":1342056261306421248,"medalInfo0Image":"upload/images/20201231/1609380915953.png","medalInfo0Name":Utils.getString(R.string.玛莎拉蒂),"medalInfo0Status":1,"type0Days":1,"type1Days":0,"type2Days":0},"id":51,"isDelete":0,"lastModifiedDate":1609567878000,"lastModifiedUser":"qixi","orderCodePreByType":"QD","price":2,"type":4,"userGainSuccess":0},{"createdDate":1609500019000,"createdUser":"qixi001","gainJsonByGift":"{\"medalInfo1Id\":1342722507286908928,\"type0Days\":0,\"type1Days\":1,\"type2Days\":0}","gainZhuanShi":2,"gainZhuanShiByGift":{"medalInfo1Id":1342722507286908928,"medalInfo1Image":"upload/images/20201226/1608964943757.png","medalInfo1Name":Utils.getString(R.string.公爵),"medalInfo1Status":1,"type0Days":0,"type1Days":1,"type2Days":0},"id":52,"isDelete":0,"lastModifiedDate":1609567888000,"lastModifiedUser":"qixi","orderCodePreByType":"QD","price":3,"type":4,"userGainSuccess":0},{"createdDate":1609500024000,"createdUser":"qixi001","gainJsonByGift":"{\"medalInfo2Id\":1344451028464046081,\"type0Days\":0,\"type1Days\":0,\"type2Days\":1}","gainZhuanShi":3,"gainZhuanShiByGift":{"medalInfo2Id":1344451028464046081,"medalInfo2Image":"upload/images/20201231/1609379081581.png","medalInfo2Name":Utils.getString(R.string.魅力天使),"medalInfo2Status":1,"type0Days":0,"type1Days":0,"type2Days":1},"id":53,"isDelete":0,"lastModifiedDate":1609567906000,"lastModifiedUser":"qixi","orderCodePreByType":"QD","price":4,"type":4,"userGainSuccess":0},{"createdDate":1609500029000,"createdUser":"qixi001","gainZhuanShi":4,"id":54,"isDelete":0,"orderCodePreByType":"QD","price":5,"type":4,"userGainSuccess":0},{"createdDate":1609500034000,"createdUser":"qixi001","gainJsonByGift":"{\"medalInfo1Id\":1342719100669005825,\"type0Days\":0,\"type1Days\":2,\"type2Days\":0}","gainZhuanShi":5,"gainZhuanShiByGift":{"medalInfo1Id":1342719100669005825,"medalInfo1Image":"upload/images/20201226/1608964134807.png","medalInfo1Name":Utils.getString(R.string.骑士),"medalInfo1Status":1,"type0Days":0,"type1Days":2,"type2Days":0},"id":55,"isDelete":0,"lastModifiedDate":1609567983000,"lastModifiedUser":"qixi","orderCodePreByType":"QD","price":6,"type":4,"userGainSuccess":0},{"createdDate":1609500038000,"createdUser":"qixi001","gainJsonByGift":"{\"medalInfo1Id\":1342720521393016832,\"type0Days\":1,\"type1Days\":1,\"type2Days\":0}","gainZhuanShi":6,"gainZhuanShiByGift":{"medalInfo1Id":1342720521393016832,"medalInfo1Image":"upload/images/20201226/1608964464923.png","medalInfo1Name":Utils.getString(R.string.伯爵),"medalInfo1Status":1,"type0Days":1,"type1Days":1,"type2Days":0},"id":56,"isDelete":0,"lastModifiedDate":1610024515000,"lastModifiedUser":"qixi","orderCodePreByType":"QD","price":7,"type":4,"userGainSuccess":0},{"createdDate":1609500065000,"createdUser":"qixi001","gainJsonByGift":"{\"medalInfo0Id\":1342055415772483585,\"medalInfo1Id\":1342723274827763712,\"medalInfo2Id\":1344460601694490625,\"type0Days\":1,\"type1Days\":1,\"type2Days\":1}","gainZhuanShi":7,"gainZhuanShiByGift":{"medalInfo0Id":1342055415772483585,"medalInfo0Image":"upload/images/20201231/1609380927795.png","medalInfo0Name":Utils.getString(R.string.布加迪威龙),"medalInfo0Status":1,"medalInfo1Id":1342723274827763712,"medalInfo1Image":"upload/images/20201226/1608965140910.png","medalInfo1Name":Utils.getString(R.string.幻神8),"medalInfo1Status":1,"medalInfo2Id":1344460601694490625,"medalInfo2Image":"upload/images/20201231/1609379342558.png","medalInfo2Name":Utils.getString(R.string.金玉满堂),"medalInfo2Status":1,"type0Days":1,"type1Days":1,"type2Days":1},"id":58,"isDelete":0,"lastModifiedDate":1609568067000,"lastModifiedUser":"qixi","orderCodePreByType":"QD","price":1,"type":4,"userGainSuccess":0}]
     * data : 获取签到列表成功
     * todaySignInFlag : 0
     * message : 获取签到列表成功
     * thisWeekTitle : 01月11日-01月17日
     * status : success
     */

    private String data;
    private String todaySignInFlag;
    private String message;
    private String thisWeekTitle;
    private String status;
    /**
     * createdDate : 1609499442000
     * createdUser : nangua
     * gainJsonByGift : {"medalInfo0Id":1342056261306421248,"type0Days":1,"type1Days":0,"type2Days":0}
     * gainZhuanShi : 1
     * gainZhuanShiByGift : {"medalInfo0Id":1342056261306421248,"medalInfo0Image":"upload/images/20201231/1609380915953.png","medalInfo0Name":Utils.getString(R.string.玛莎拉蒂),"medalInfo0Status":1,"type0Days":1,"type1Days":0,"type2Days":0}
     * id : 51
     * isDelete : 0
     * lastModifiedDate : 1609567878000
     * lastModifiedUser : qixi
     * orderCodePreByType : QD
     * price : 2
     * type : 4
     * userGainSuccess : 0
     */

    private List<GainZhuanShiConditionsListBean> gainZhuanShiConditionsList;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTodaySignInFlag() {
        return todaySignInFlag;
    }

    public void setTodaySignInFlag(String todaySignInFlag) {
        this.todaySignInFlag = todaySignInFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThisWeekTitle() {
        return thisWeekTitle;
    }

    public void setThisWeekTitle(String thisWeekTitle) {
        this.thisWeekTitle = thisWeekTitle;
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
        private String gainJsonByGift;
        private String gainZhuanShi;
        /**
         * medalInfo0Id : 1342056261306421248
         * medalInfo0Image : upload/images/20201231/1609380915953.png
         * medalInfo0Name : 玛莎拉蒂
         * medalInfo0Status : 1
         * type0Days : 1
         * type1Days : 0
         * type2Days : 0
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
        ArrayList<DiamondChildEntity> childList;

        public ArrayList<DiamondChildEntity> getChildList() {
            return childList;
        }

        public void setChildList(ArrayList<DiamondChildEntity> childList) {
            this.childList = childList;
        }

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

        public String getGainJsonByGift() {
            return gainJsonByGift;
        }

        public void setGainJsonByGift(String gainJsonByGift) {
            this.gainJsonByGift = gainJsonByGift;
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
            return StringMyUtil.isEmptyString(price)?"":price;
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

            public String getMedalInfo0Status() {
                return medalInfo0Status;
            }

            public void setMedalInfo0Status(String medalInfo0Status) {
                this.medalInfo0Status = medalInfo0Status;
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
