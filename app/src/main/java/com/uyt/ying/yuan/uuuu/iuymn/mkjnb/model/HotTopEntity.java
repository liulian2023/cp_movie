package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class HotTopEntity {

    /**
     * extensionNoticeInfoList : [{"content":"","createdDate":1625536849000,"createdUser":Utils.getString(R.string.系统发送),"id":198,"image":"upload/images/20210706/1625536846640.png","index":0,"isDelete":0,"isRed":0,"link":"","liveShowPage":3,"size":0,"sort":0,"status":1,"theme":Utils.getString(R.string.推广赚钱),"themetype":0,"type":11},{"content":"","createdDate":1625482067000,"createdUser":Utils.getString(R.string.系统发送),"id":197,"image":"upload/images/20210705/1625483509161.png","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1625483566000,"lastModifiedUser":"nangua","link":"http://wwwbaidu.com","liveShowPage":2,"size":0,"sort":0,"status":1,"theme":Utils.getString(R.string.在线客服),"themetype":0,"type":11},{"content":"{\"id\":79,\"game\":51,\"type_id\":\"510\",\"image\":\"upload/images/20201217/1608171259089.png\",\"typename\":\Utils.getString(R.string.激光炮捕鱼\)}","createdDate":1625479886000,"createdUser":Utils.getString(R.string.系统发送),"id":194,"image":"","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1625534066000,"lastModifiedUser":"nangua","link":"","liveShowPage":1,"size":0,"sort":1,"status":1,"theme":Utils.getString(R.string.热门游戏1),"themetype":0,"type":11},{"content":"{\"id\":79,\"game\":51,\"type_id\":\"510\",\"image\":\"upload/images/20201217/1608171259089.png\",\"typename\":\Utils.getString(R.string.激光炮捕鱼\)}","createdDate":1625481525000,"createdUser":Utils.getString(R.string.系统发送),"id":195,"image":"","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1625483574000,"lastModifiedUser":"nangua","link":"","liveShowPage":1,"size":0,"sort":2,"status":1,"theme":Utils.getString(R.string.热门游戏2),"themetype":0,"type":11},{"content":"{\"id\":32,\"game\":1,\"type_id\":2,\"image\":\"upload/images/20200302/1583126811420.png\",\"typename\":\Utils.getString(R.string.一分快三\)}","createdDate":1625481974000,"createdUser":Utils.getString(R.string.系统发送),"id":196,"image":"","index":0,"isDelete":0,"isRed":0,"lastModifiedDate":1625483574000,"lastModifiedUser":"nangua","link":"","liveShowPage":1,"size":0,"sort":3,"status":1,"theme":Utils.getString(R.string.一分快三),"themetype":0,"type":11}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    /**
     * content :
     * createdDate : 1625536849000
     * createdUser : 系统发送
     * id : 198
     * image : upload/images/20210706/1625536846640.png
     * index : 0
     * isDelete : 0
     * isRed : 0
     * link :
     * liveShowPage : 3
     * size : 0
     * sort : 0
     * status : 1
     * theme : 推广赚钱
     * themetype : 0
     * type : 11
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
        private long createdDate;
        private String createdUser;
        private int id;
        private String image;
        private int index;
        private int isDelete;
        private int isRed;
        private String link;
        private int liveShowPage;
        private int size;
        private int sort;
        private int status;
        private String theme;
        private int themetype;
        private int type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsRed() {
            return isRed;
        }

        public void setIsRed(int isRed) {
            this.isRed = isRed;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getLiveShowPage() {
            return liveShowPage;
        }

        public void setLiveShowPage(int liveShowPage) {
            this.liveShowPage = liveShowPage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public int getThemetype() {
            return themetype;
        }

        public void setThemetype(int themetype) {
            this.themetype = themetype;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
