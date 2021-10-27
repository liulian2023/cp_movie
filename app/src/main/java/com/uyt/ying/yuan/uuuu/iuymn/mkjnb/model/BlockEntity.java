package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class BlockEntity {

    /**
     * data : 超管获取拉黑用户/主播列表成功!!!
     * memberKillMemberList : [{"createdDate":1608351879000,"createdUser":"sys","id":1340151029215399936,"isDelete":0,"killedUserId":1331092040821379073,"killedUserName":"new001","lastModifiedDate":1608351985000,"lastModifiedUser":"sys","maStringainStatus":1,"user_id":1335887082782068736,"user_name":"xx1111"},{"createdDate":1608354153000,"createdUser":"sys","id":1340160567545040897,"isDelete":0,"killedUserId":1331172050643062785,"killedUserName":"new003","maStringainStatus":1,"user_id":1335887082782068736,"user_name":"xx1111"},{"createdDate":1608355826000,"createdUser":"sys","id":1340167584649187329,"isDelete":0,"killedAnchorMemberId":16,"killedAnchorUserName":"guanggao3","maStringainStatus":1,"user_id":1335887082782068736,"user_name":"xx1111"}]
     * message : 超管获取拉黑用户/主播列表成功!!!
     * status : success
     */

    private String data;
    private String message;
    private String status;
    /**
     * createdDate : 1608351879000
     * createdUser : sys
     * id : 1340151029215399936
     * isDelete : 0
     * killedUserId : 1331092040821379073
     * killedUserName : new001
     * lastModifiedDate : 1608351985000
     * lastModifiedUser : sys
     * maStringainStatus : 1
     * user_id : 1335887082782068736
     * user_name : xx1111
     */

    private List<MemberKillMemberListBean> memberKillMemberList;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public List<MemberKillMemberListBean> getMemberKillMemberList() {
        return memberKillMemberList;
    }

    public void setMemberKillMemberList(List<MemberKillMemberListBean> memberKillMemberList) {
        this.memberKillMemberList = memberKillMemberList;
    }

    public static class MemberKillMemberListBean {
        private String createdDate;
        private String createdUser;
        private String id;
        private String isDelete;
        private String killedUserId;
        private String killedAnchorMemberId;
        private String killedAnchorAccount;
        private String killedUserNickName;
        private String killedAnchorUserNickName;
        private String lastModifiedDate;
        private String lastModifiedUser;
        private String maStringainStatus;
        private String user_id;
        private String user_name;

        public String getKilledAnchorAccount() {
            return killedAnchorAccount;
        }

        public void setKilledAnchorAccount(String killedAnchorAccount) {
            this.killedAnchorAccount = killedAnchorAccount;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getKilledAnchorMemberId() {
            return killedAnchorMemberId;
        }

        public void setKilledAnchorMemberId(String killedAnchorMemberId) {
            this.killedAnchorMemberId = killedAnchorMemberId;
        }

        public String getKilledAnchorUserNickName() {
            return killedAnchorUserNickName;
        }

        public void setKilledAnchorUserNickName(String killedAnchorUserNickName) {
            this.killedAnchorUserNickName = killedAnchorUserNickName;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
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

        public String getKilledUserId() {
            return killedUserId;
        }

        public void setKilledUserId(String killedUserId) {
            this.killedUserId = killedUserId;
        }

        public String getKilledUserNickName() {
            return killedUserNickName;
        }

        public void setKilledUserNickName(String killedUserNickName) {
            this.killedUserNickName = killedUserNickName;
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

        public String getMaStringainStatus() {
            return maStringainStatus;
        }

        public void setMaStringainStatus(String maStringainStatus) {
            this.maStringainStatus = maStringainStatus;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
