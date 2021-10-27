/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class BannerData {

    /**
     * extensionNoticeInfoList : [{"content":Utils.getString(R.string.内容为空......),"createdDate":1608441533000,"createdUser":Utils.getString(R.string.系统发送),"id":150,"image":"upload/images/20201220/1608441531524.png","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1608441859000,"lastModifiedUser":"sys","link":"","size":0,"sort":0,"status":1,"theme":Utils.getString(R.string.招聘),"themetype":0,"type":1},{"content":Utils.getString(R.string.内容为空......),"createdDate":1608441638000,"createdUser":Utils.getString(R.string.系统发送),"id":155,"image":"upload/images/20201220/1608441636290.png","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1608441859000,"lastModifiedUser":"sys","link":"","size":0,"sort":1,"status":1,"theme":Utils.getString(R.string.百万红包雨),"themetype":0,"type":1},{"content":Utils.getString(R.string.内容为空......),"createdDate":1608441604000,"createdUser":Utils.getString(R.string.系统发送),"id":153,"image":"upload/images/20201220/1608441602878.png","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1608441926000,"lastModifiedUser":"dizhu888","link":"http://655hd.tv","size":0,"sort":2,"status":1,"theme":Utils.getString(R.string.优惠大厅),"themetype":0,"type":1},{"content":Utils.getString(R.string.内容为空......),"createdDate":1608441583000,"createdUser":Utils.getString(R.string.系统发送),"id":152,"image":"upload/images/20201220/1608441582040.png","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1608441882000,"lastModifiedUser":"sys","link":"","size":0,"sort":3,"status":1,"theme":Utils.getString(R.string.银行卡转账),"themetype":0,"type":1},{"content":Utils.getString(R.string.内容为空......),"createdDate":1608441556000,"createdUser":Utils.getString(R.string.系统发送),"id":151,"image":"upload/images/20201220/1608441553052.png","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1608441882000,"lastModifiedUser":"sys","link":"","size":0,"sort":4,"status":1,"theme":Utils.getString(R.string.开元棋牌),"themetype":0,"type":1},{"content":Utils.getString(R.string.内容为空......),"createdDate":1608441619000,"createdUser":Utils.getString(R.string.系统发送),"id":154,"image":"upload/images/20201220/1608441615707.png","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1608441902000,"lastModifiedUser":"dizhu888","link":"http:ios655.tv","size":0,"sort":5,"status":1,"theme":"IOS","themetype":0,"type":1}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    /**
     * content : 内容为空......
     * createdDate : 1608441533000
     * createdUser : 系统发送
     * id : 150
     * image : upload/images/20201220/1608441531524.png
     * index : 0
     * isDelete : 0
     * isRed : 0
     * lastModifiedDate : 1608441859000
     * lastModifiedUser : sys
     * link : 
     * size : 0
     * sort : 0
     * status : 1
     * theme : 招聘
     * themetype : 0
     * type : 1
     */

    private List<ExtensionNoticeInfoListBean> extensionNoticeInfoList;

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

    public List<ExtensionNoticeInfoListBean> getExtensionNoticeInfoList() {
        return extensionNoticeInfoList;
    }

    public void setExtensionNoticeInfoList(List<ExtensionNoticeInfoListBean> extensionNoticeInfoList) {
        this.extensionNoticeInfoList = extensionNoticeInfoList;
    }

    public static class ExtensionNoticeInfoListBean {
        private String content;
        private String createdDate;
        private String createdUser;
        private String id;
        private String image;
        private String index;
        private String isDelete;
        private String isRed;
        private String lastModifiedDate;
        private String lastModifiedUser;
        private String link;
        private String size;
        private String sort;
        private String status;
        private String theme;
        private String themetype;
        private String type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsRed() {
            return isRed;
        }

        public void setIsRed(String isRed) {
            this.isRed = isRed;
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getThemetype() {
            return themetype;
        }

        public void setThemetype(String themetype) {
            this.themetype = themetype;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
