package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class HomeAtyEntity {

    /**
     * extensionNoticeInfoList : [{"content":Utils.getString(R.string.<p>猫咪直播推荐您使用[银行卡转账]进行充值！安全、稳定、快速、优惠、笔笔充值赠送2%存款优惠！最高送8888888！！！<\/p>),"contentTxt":Utils.getString(R.string.猫咪直播推荐您使用[银行卡转账]进行充值！安全、稳定、快速、优惠、笔笔充值赠送2%存款优惠！最高送8888888！！！),"createdDate":1606917139000,"createdUser":Utils.getString(R.string.系统发送),"id":146,"index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1606958223000,"lastModifiedUser":"sys","link":"","size":0,"sort":2,"status":1,"theme":Utils.getString(R.string.甜蜜蜜),"themetype":0,"type":3},{"content":"<p><span style=\"color: #333333; font-family: Arial, sans-serif; font-size: 13px; background-color: #ffffff;\Utils.getString(R.string.>孔<\/span><span style=\)color: #f73131; font-family: Arial, sans-serif; font-size: 13px; background-color: #ffffff;\Utils.getString(R.string.>夫子<\/span><span style=\)color: #333333; font-family: Arial, sans-serif; font-size: 13px; background-color: #ffffff;\Utils.getString(R.string.>旧书网是国内领先的古旧书交易平台,汇集全国各地13000家网上书店,50000家书摊,展示多达9000万种书籍;大量极具收藏价值的古旧珍本(明清、民国古籍善本,珍品期刊...<\/span><\/p>),"contentTxt":Utils.getString(R.string.孔夫子旧书网是国内领先的古旧书交易平台,汇集全国各地13000家网上书店,50000家书摊,展示多达9000万种书籍;大量极具收藏价值的古旧珍本(明清、民国古籍善本,珍品期刊...),"createdDate":1606957818000,"createdUser":Utils.getString(R.string.系统发送),"id":148,"index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1606958218000,"lastModifiedUser":"sys","link":"","size":0,"sort":3,"status":1,"theme":"bbbbbbbbbb","themetype":0,"type":3}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    /**
     * content : <p>猫咪直播推荐您使用[银行卡转账]进行充值！安全、稳定、快速、优惠、笔笔充值赠送2%存款优惠！最高送8888888！！！</p>
     * contentTxt : 猫咪直播推荐您使用[银行卡转账]进行充值！安全、稳定、快速、优惠、笔笔充值赠送2%存款优惠！最高送8888888！！！
     * createdDate : 1606917139000
     * createdUser : 系统发送
     * id : 146
     * index : 0
     * isDelete : 0
     * isRed : 0
     * lastModifiedDate : 1606958223000
     * lastModifiedUser : sys
     * link : 
     * size : 0
     * sort : 2
     * status : 1
     * theme : 甜蜜蜜
     * themetype : 0
     * type : 3
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
        private String contentTxt;
        private String createdDate;
        private String createdUser;
        private String id;
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

        public String getContentTxt() {
            return contentTxt;
        }

        public void setContentTxt(String contentTxt) {
            this.contentTxt = contentTxt;
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
