package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import java.util.List;

public class ChatUserEntity  {


    /**
     * memberHeadPortraitList : [{"createdDate":1568201858000,"createdUser":"test@qq.com","id":82,"image":"upload/images/20190911/1568201856313.jpg","isDelete":0,"name":Utils.getString(R.string.李沁),"orderNo":"1"},{"createdDate":1568202184000,"createdUser":"test@qq.com","id":83,"image":"upload/images/20190911/1568202182046.jpg","isDelete":0,"name":Utils.getString(R.string.陈晓),"orderNo":"2"},{"createdDate":1568202257000,"createdUser":"test@qq.com","id":84,"image":"upload/images/20190911/1568202255686.jpg","isDelete":0,"name":Utils.getString(R.string.乔振宇),"orderNo":"3"},{"createdDate":1568202297000,"createdUser":"test@qq.com","id":85,"image":"upload/images/20190911/1568202293818.jpg","isDelete":0,"name":Utils.getString(R.string.孙艺珍),"orderNo":"4"},{"createdDate":1568202351000,"createdUser":"test@qq.com","id":86,"image":"upload/images/20190911/1568202349938.jpg","isDelete":0,"name":Utils.getString(R.string.赵丽颖),"orderNo":"5"},{"createdDate":1568202373000,"createdUser":"test@qq.com","id":87,"image":"upload/images/20190911/1568202369992.jpeg","isDelete":0,"name":Utils.getString(R.string.胡歌),"orderNo":"6"},{"createdDate":1568202453000,"createdUser":"test@qq.com","id":88,"image":"upload/images/20190911/1568202451775.jpg","isDelete":0,"name":Utils.getString(R.string.朴信惠),"orderNo":"7"},{"createdDate":1568202509000,"createdUser":"test@qq.com","id":89,"image":"upload/images/20190911/1568202507495.jpg","isDelete":0,"name":Utils.getString(R.string.杨洋),"orderNo":"8"},{"createdDate":1568206647000,"createdUser":"test@qq.com","id":90,"image":"upload/images/20190911/1568206645307.jpg","isDelete":0,"name":Utils.getString(R.string.吴磊),"orderNo":"9"},{"createdDate":1568206689000,"createdUser":"test@qq.com","id":91,"image":"upload/images/20190911/1568206981860.jpg","isDelete":0,"lastModifiedDate":1568206984000,"lastModifiedUser":"test@qq.com","name":Utils.getString(R.string.韩佳人),"orderNo":"10"},{"createdDate":1568206759000,"createdUser":"test@qq.com","id":92,"image":"upload/images/20190911/1568206757838.jpg","isDelete":0,"name":Utils.getString(R.string.谢霆锋),"orderNo":"11"},{"createdDate":1568206829000,"createdUser":"test@qq.com","id":93,"image":"upload/images/20190911/1568206827572.jpg","isDelete":0,"lastModifiedDate":1568206853000,"lastModifiedUser":"test@qq.com","name":Utils.getString(R.string.张学友),"orderNo":"12"},{"createdDate":1568206882000,"createdUser":"test@qq.com","id":94,"image":"upload/images/20190911/1568206880594.jpg","isDelete":0,"name":Utils.getString(R.string.朱茵),"orderNo":"13"},{"createdDate":1568206925000,"createdUser":"test@qq.com","id":95,"image":"upload/images/20190911/1568206920562.png","isDelete":0,"name":Utils.getString(R.string.林志颖),"orderNo":"14"},{"createdDate":1568207038000,"createdUser":"test@qq.com","id":96,"image":"upload/images/20190911/1568207036597.jpg","isDelete":0,"name":Utils.getString(R.string.陈冠希),"orderNo":"15"},{"createdDate":1568207070000,"createdUser":"test@qq.com","id":97,"image":"upload/images/20190911/1568207065844.jpg","isDelete":0,"name":Utils.getString(R.string.陈乔恩),"orderNo":"16"},{"createdDate":1568207217000,"createdUser":"test@qq.com","id":98,"image":"upload/images/20190911/1568207230090.jpg","isDelete":0,"lastModifiedDate":1568207231000,"lastModifiedUser":"test@qq.com","name":Utils.getString(R.string.王祖贤),"orderNo":"17"},{"createdDate":1568207282000,"createdUser":"test@qq.com","id":99,"image":"upload/images/20190911/1568207280832.jpg","isDelete":0,"name":Utils.getString(R.string.薛之谦),"orderNo":"18"},{"createdDate":1568207438000,"createdUser":"test@qq.com","id":100,"image":"upload/images/20190911/1568207436103.jpg","isDelete":0,"lastModifiedDate":1581737764000,"lastModifiedUser":"xbgoogle","name":Utils.getString(R.string.张天爱),"orderNo":"20"},{"createdDate":1568207512000,"createdUser":"test@qq.com","id":102,"image":"upload/images/20190911/1568207508264.jpg","isDelete":0,"lastModifiedDate":1581737751000,"lastModifiedUser":"xbgoogle","name":Utils.getString(R.string.刘诗诗),"orderNo":"21"}]
     * message : 获取用户头像列表成功
     * status : success
     */

    private String message;
    private String status;
    private List<MemberHeadPortraitListBean> memberHeadPortraitList;

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

    public List<MemberHeadPortraitListBean> getMemberHeadPortraitList() {
        return memberHeadPortraitList;
    }

    public void setMemberHeadPortraitList(List<MemberHeadPortraitListBean> memberHeadPortraitList) {
        this.memberHeadPortraitList = memberHeadPortraitList;
    }

    public static class MemberHeadPortraitListBean {
        /**
         * createdDate : 1568201858000
         * createdUser : test@qq.com
         * id : 82
         * image : upload/images/20190911/1568201856313.jpg
         * isDelete : 0
         * name : 李沁
         * orderNo : 1
         * lastModifiedDate : 1568206984000
         * lastModifiedUser : test@qq.com
         */

        private long createdDate;
        private String createdUser;
        private long id;
        private String image;
        private int isDelete;
        private String name;
        private String orderNo;
        private long lastModifiedDate;
        private String lastModifiedUser;

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

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }
    }
}
