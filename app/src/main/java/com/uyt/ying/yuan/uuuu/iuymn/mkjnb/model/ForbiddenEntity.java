package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class ForbiddenEntity {

    /**
     * chatRoomGagList : [{"anchorId":"1277419320856547329","chatroomId":"1277419320856547329662b633b4feb4440bdcd20a893d9854f","createdDate":1596107714000,"createdUser":"sys","endForbiddenDate":1598768235000,"id":13,"isDelete":0,"isForbidden":1,"isRoomManager":0,"lastModifiedDate":1596176235000,"lastModifiedUser":"sys","rcUsId":"58","remark":Utils.getString(R.string.上游主播禁言榴莲蛋糕),"roomManageUserNickName":Utils.getString(R.string.xo家族波浪骚),"startForbiddenDate":1596176235000,"userNickName":Utils.getString(R.string.榴莲蛋糕),"user_id":58}]
     * message : 获取该聊天室禁言用户成功!!!!
     * status : success
     */

    private String message;
    private String status;
    private List<ChatRoomGagListBean> chatRoomGagList;

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

    public List<ChatRoomGagListBean> getChatRoomGagList() {
        return chatRoomGagList;
    }

    public void setChatRoomGagList(List<ChatRoomGagListBean> chatRoomGagList) {
        this.chatRoomGagList = chatRoomGagList;
    }

    public static class ChatRoomGagListBean {
        /**
         * anchorId : 1277419320856547329
         * chatroomId : 1277419320856547329662b633b4feb4440bdcd20a893d9854f
         * createdDate : 1596107714000
         * createdUser : sys
         * endForbiddenDate : 1598768235000
         * id : 13
         * isDelete : 0
         * isForbidden : 1
         * isRoomManager : 0
         * lastModifiedDate : 1596176235000
         * lastModifiedUser : sys
         * rcUsId : 58
         * remark : 上游主播禁言榴莲蛋糕
         * roomManageUserNickName : xo家族波浪骚
         * startForbiddenDate : 1596176235000
         * userNickName : 榴莲蛋糕
         * user_id : 58
         */

        private String anchorId;
        private String chatroomId;
        private String createdDate;
        private String createdUser;
        private String endForbiddenDate;
        private String id;
        private String isDelete;
        private String isForbidden;
        private String isRoomManager;
        private String lastModifiedDate;
        private String lastModifiedUser;
        private String rcUsId;
        private String remark;
        private String roomManageUserNickName;
        private String startForbiddenDate;
        private String userNickName;
        private String user_id;

        public String getAnchorId() {
            return anchorId;
        }

        public void setAnchorId(String anchorId) {
            this.anchorId = anchorId;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
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

        public String getEndForbiddenDate() {
            return endForbiddenDate;
        }

        public void setEndForbiddenDate(String endForbiddenDate) {
            this.endForbiddenDate = endForbiddenDate;
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

        public String getIsForbidden() {
            return isForbidden;
        }

        public void setIsForbidden(String isForbidden) {
            this.isForbidden = isForbidden;
        }

        public String getIsRoomManager() {
            return isRoomManager;
        }

        public void setIsRoomManager(String isRoomManager) {
            this.isRoomManager = isRoomManager;
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

        public String getRcUsId() {
            return rcUsId;
        }

        public void setRcUsId(String rcUsId) {
            this.rcUsId = rcUsId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRoomManageUserNickName() {
            return roomManageUserNickName;
        }

        public void setRoomManageUserNickName(String roomManageUserNickName) {
            this.roomManageUserNickName = roomManageUserNickName;
        }

        public String getStartForbiddenDate() {
            return startForbiddenDate;
        }

        public void setStartForbiddenDate(String startForbiddenDate) {
            this.startForbiddenDate = startForbiddenDate;
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
    }
}
