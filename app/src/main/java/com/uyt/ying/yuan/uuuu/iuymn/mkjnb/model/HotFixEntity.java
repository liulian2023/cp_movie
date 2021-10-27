package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class HotFixEntity {


    /**
     * flg : 1
     * appVersion : {"downLoadUrl":"upload/images/20200821/1598013948826.jar","lastModifiedDate":1598013954000,"isDelete":0,"description":Utils.getString(R.string.安卓热更新开始了，祝您中奖！),"versionName":"1.0.6","versionCode":6,"createdDate":1554875858000,"systemType":22,"lastModifiedUser":"sys","id":3,"isForce":0,"createdUser":"ccc"}
     * message : 获取版本号成功
     * status : success
     */

    private int flg;
    private AppVersionBean appVersion;
    private String message;
    private String status;

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    public AppVersionBean getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersionBean appVersion) {
        this.appVersion = appVersion;
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

    public static class AppVersionBean {
        /**
         * downLoadUrl : upload/images/20200821/1598013948826.jar
         * lastModifiedDate : 1598013954000
         * isDelete : 0
         * description : 安卓热更新开始了，祝您中奖！
         * versionName : 1.0.6
         * versionCode : 6
         * createdDate : 1554875858000
         * systemType : 22
         * lastModifiedUser : sys
         * id : 3
         * isForce : 0
         * createdUser : ccc
         */

        private String downLoadUrl;
        private long lastModifiedDate;
        private int isDelete;
        private String description;
        private String versionName;
        private int versionCode;
        private long createdDate;
        private int systemType;
        private String lastModifiedUser;
        private String id;
        private int isForce;
        private String createdUser;

        public String getDownLoadUrl() {
            return downLoadUrl;
        }

        public void setDownLoadUrl(String downLoadUrl) {
            this.downLoadUrl = downLoadUrl;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public int getSystemType() {
            return systemType;
        }

        public void setSystemType(int systemType) {
            this.systemType = systemType;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }
    }
}
