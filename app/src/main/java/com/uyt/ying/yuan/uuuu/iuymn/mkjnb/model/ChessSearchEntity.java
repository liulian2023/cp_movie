package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class ChessSearchEntity extends CommonModel {
    /**
     * data : [{"createdDate":1581074897000,"createdUser":"xbgoogle","game":4,"id":111,"image":"upload/images/20200302/1583129853904.png","isDelete":0,"isHot":1,"isStart":1,"lastModifiedDate":1588509604000,"lastModifiedUser":"sys","listsort":0,"remark":Utils.getString(R.string.1分钟1期),"status":1,"type_id":"3","typename":Utils.getString(R.string.1分六合彩),"version":408},{"createdDate":1513330453000,"createdUser":"sys","game":4,"id":15,"image":"upload/images/20200302/1583126565719.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588482085000,"lastModifiedUser":"xbgoogle","listsort":17,"remark":Utils.getString(R.string.每周3期),"status":1,"type_id":"1","typename":Utils.getString(R.string.香港六合彩),"version":1492},{"createdDate":1534931922000,"createdUser":"sys","game":4,"id":40,"image":"upload/images/20200302/1583126595473.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588509603000,"lastModifiedUser":"sys","listsort":18,"remark":Utils.getString(R.string.10分钟1期),"status":1,"type_id":"2","typename":Utils.getString(R.string.10分六合彩),"version":1472}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    private List<DataBean> data;

    
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends CommonModel {
        /**
         * createdDate : 1581074897000
         * createdUser : xbgoogle
         * game : 4
         * id : 111
         * image : upload/images/20200302/1583129853904.png
         * isDelete : 0
         * isHot : 1
         * isStart : 1
         * lastModifiedDate : 1588509604000
         * lastModifiedUser : sys
         * listsort : 0
         * remark : 1分钟1期
         * status : 1
         * type_id : 3
         * typename : 1分六合彩
         * version : 408
         */
        private int isopenOffice;

        public int getIsopenOffice() {
            return isopenOffice;
        }

        public void setIsopenOffice(int isopenOffice) {
            this.isopenOffice = isopenOffice;
        }

        private long createdDate;
        private String createdUser;
        private int game;
        private String id;
        private String image;
        private int isDelete;
        private int isHot;
        private int isStart;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private int listsort;
        private String remark;
        private int status;
        private String type_id;
        private String typename;
        private int version;
        boolean xian;

        public boolean isXian() {
            return xian;
        }

        public void setXian(boolean xian) {
            this.xian = xian;
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

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
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

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsStart() {
            return isStart;
        }

        public void setIsStart(int isStart) {
            this.isStart = isStart;
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

        public int getListsort() {
            return listsort;
        }

        public void setListsort(int listsort) {
            this.listsort = listsort;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
