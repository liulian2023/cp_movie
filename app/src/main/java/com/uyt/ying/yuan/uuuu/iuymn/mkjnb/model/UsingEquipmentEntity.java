package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class UsingEquipmentEntity {

    /**
     * message : success
     * medalInfoType_2 : []
     * medalInfoType_1 : []
     * sysGrade : {"createdDate":1609415957000,"createdUser":"qixi","growthIntegral":900,"id":10,"image":"upload/images/20201231/1609415926818.png","isDelete":0,"name":Utils.getString(R.string.小财主),CommonStr.GRADE:6,"speechIntervalSeconds":2,"txImage":""}
     * status : success
     * medalInfoType_0 : [{"image":"upload/images/20201231/1609380977400.png","name":Utils.getString(R.string.奥拓),"type":0}]
     */

    private String message;
    /**
     * createdDate : 1609415957000
     * createdUser : qixi
     * growthIntegral : 900.0
     * id : 10
     * image : upload/images/20201231/1609415926818.png
     * isDelete : 0
     * name : 小财主
     * pointGrade : 6
     * speechIntervalSeconds : 2
     * txImage :
     */

    private SysGradeBean sysGrade;
    private String status;
    private List<MedalInfoType2Bean> medalInfoType_2;
    private List<MedalInfoType1Bean> medalInfoType_1;
    /**
     * image : upload/images/20201231/1609380977400.png
     * name : 奥拓
     * type : 0
     */

    private List<MedalInfoType0Bean> medalInfoType_0;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SysGradeBean getSysGrade() {
        return sysGrade;
    }

    public void setSysGrade(SysGradeBean sysGrade) {
        this.sysGrade = sysGrade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MedalInfoType2Bean> getMedalInfoType_2() {
        return medalInfoType_2;
    }

    public void setMedalInfoType_2(List<MedalInfoType2Bean> medalInfoType_2) {
        this.medalInfoType_2 = medalInfoType_2;
    }

    public List<MedalInfoType1Bean> getMedalInfoType_1() {
        return medalInfoType_1;
    }

    public void setMedalInfoType_1(List<MedalInfoType1Bean> medalInfoType_1) {
        this.medalInfoType_1 = medalInfoType_1;
    }

    public List<MedalInfoType0Bean> getMedalInfoType_0() {
        return medalInfoType_0;
    }

    public void setMedalInfoType_0(List<MedalInfoType0Bean> medalInfoType_0) {
        this.medalInfoType_0 = medalInfoType_0;
    }

    public static class SysGradeBean {
        private long createdDate;
        private String createdUser;
        private String growthIntegral;
        private String id;
        private String image;
        private String isDelete;
        private String name;
        private String pointGrade;
        private String speechIntervalSeconds;
        private String txImage;

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

        public String getGrowthIntegral() {
            return growthIntegral;
        }

        public void setGrowthIntegral(String growthIntegral) {
            this.growthIntegral = growthIntegral;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPointGrade() {
            return pointGrade;
        }

        public void setPointGrade(String pointGrade) {
            this.pointGrade = pointGrade;
        }

        public String getSpeechIntervalSeconds() {
            return speechIntervalSeconds;
        }

        public void setSpeechIntervalSeconds(String speechIntervalSeconds) {
            this.speechIntervalSeconds = speechIntervalSeconds;
        }

        public String getTxImage() {
            return txImage;
        }

        public void setTxImage(String txImage) {
            this.txImage = txImage;
        }
    }

    public static class MedalInfoType0Bean {
        private String image;
        private String name;
        private String type;
        private String txImage;

        public String getImage() {
            return image;
        }

        public String getTxImage() {
            return txImage;
        }

        public void setTxImage(String txImage) {
            this.txImage = txImage;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    public static class MedalInfoType1Bean {
        private String image;
        private String name;
        private String type;
        private String txImage;
        public String getImage() {
            return image;
        }

        public String getTxImage() {
            return txImage;
        }

        public void setTxImage(String txImage) {
            this.txImage = txImage;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    public static class MedalInfoType2Bean {
        private String image;
        private String name;
        private String type;
        private String txImage;

        public String getTxImage() {
            return txImage;
        }

        public void setTxImage(String txImage) {
            this.txImage = txImage;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
