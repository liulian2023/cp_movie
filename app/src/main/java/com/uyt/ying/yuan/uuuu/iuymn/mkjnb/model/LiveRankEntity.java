package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class LiveRankEntity {

    /**
     * list : [{"image":"upload/images/20190911/1568202349938.jpg","poStringGrade":"1","userNickName":Utils.getString(R.string.榴莲蛋糕),"user_id":58,"anchorGift":1.2}]
     * message : 获取贡献榜数据成功
     * status : success
     */

    private String message;
    private String status;
    private List<ListBean> list;
    private String anchorGift;//自身礼物金额

    public void setAnchorGift(String anchorGift) {
        this.anchorGift = anchorGift;
    }

    public String getAnchorGift() {
        return anchorGift;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * image : upload/images/20190911/1568202349938.jpg
         * pointGrade : 1
         * userNickName : 榴莲蛋糕
         * user_id : 58
         * anchorGift : 1.2
         */

        private String image;
        private String pointGrade;
        private String userNickName;
        private String user_id;
        private String anchorGift;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPointGrade() {
            return pointGrade;
        }

        public void setPointGrade(String pointGrade) {
            this.pointGrade = pointGrade;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAnchorGift() {
            return anchorGift;
        }
        public void setAnchorGift(String anchorGift) {
            this.anchorGift = anchorGift;
        }
    }
}
