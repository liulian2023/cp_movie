package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class HomeGridView extends CommonModel implements Serializable  {
       private String ImageUrl;
       private String lottoeryName;
       private String count;
       private int typeId;
       private String isStart;
       private String isopenOffice;
       private int game;
       private String id;
       boolean isXian=false;
    ChessEntity.DataBean chessDataBean;

    public ChessEntity.DataBean getChessDataBean() {
        return chessDataBean;
    }

    public void setChessDataBean(ChessEntity.DataBean chessDataBean) {
        this.chessDataBean = chessDataBean;
    }

    public boolean isXian() {
        return isXian;
    }

    public void setXian(boolean xian) {
        isXian = xian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HomeGridView(String imageUrl, String lottoeryName, String count, int typeId, String isStart, String isopenOffice, int game) {
        this. ImageUrl = imageUrl;
        this.lottoeryName = lottoeryName;
        this.count = count;
        this.typeId = typeId;
        this.isStart = isStart;
        this.isopenOffice = isopenOffice;
        this.game = game;
    }

    public int getTypeId() {
        return typeId;
    }

    public HomeGridView() {
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getIsopenOffice() {
        return isopenOffice;
    }

    public void setIsopenOffice(String isopenOffice) {
        this.isopenOffice = isopenOffice;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLottoeryName() {
        return lottoeryName;
    }

    public void setLottoeryName(String lottoeryName) {
        this.lottoeryName = lottoeryName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public  static class ChessEntity extends CommonModel {


        /**
         * data : [{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":70,"image":"upload/images/20200429/1588159045287.png","isDelete":0,"isHot":1,"isStart":1,"lastModifiedDate":1588224032000,"lastModifiedUser":"pt12345","listsort":0,"status":1,"type_id":"8130","typename":Utils.getString(R.string.?????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":35,"image":"upload/images/20200428/1588078935564.png","isDelete":0,"isHot":1,"isStart":1,"lastModifiedDate":1588224080000,"lastModifiedUser":"xbgoogle","listsort":1,"status":1,"type_id":"600","typename":Utils.getString(R.string.21???),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":31,"image":"upload/images/20200428/1588078917022.png","isDelete":0,"isHot":0,"isStart":0,"lastModifiedDate":1588224073000,"lastModifiedUser":"xbgoogle","listsort":2,"status":1,"type_id":"230","typename":Utils.getString(R.string.???????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":34,"image":"upload/images/20200428/1588078689257.png","isDelete":0,"isHot":1,"isStart":1,"lastModifiedDate":1588224011000,"lastModifiedUser":"pt12345","listsort":3,"status":1,"type_id":"510","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":36,"image":"upload/images/20200428/1588078962863.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134436000,"lastModifiedUser":"xbgoogle","listsort":5,"status":1,"type_id":"610","typename":Utils.getString(R.string.?????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":37,"image":"upload/images/20200428/1588078980265.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588133613000,"lastModifiedUser":"xbgoogle","listsort":6,"status":1,"type_id":"620","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":38,"image":"upload/images/20200428/1588079020603.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134445000,"lastModifiedUser":"xbgoogle","listsort":7,"status":1,"type_id":"630","typename":Utils.getString(R.string.?????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":39,"image":"upload/images/20200428/1588079058966.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134452000,"lastModifiedUser":"xbgoogle","listsort":8,"status":1,"type_id":"650","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":40,"image":"upload/images/20200428/1588079078400.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134461000,"lastModifiedUser":"xbgoogle","listsort":9,"status":1,"type_id":"720","typename":Utils.getString(R.string.?????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":41,"image":"upload/images/20200428/1588079103803.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134467000,"lastModifiedUser":"xbgoogle","listsort":10,"status":1,"type_id":"730","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":42,"image":"upload/images/20200428/1588079118657.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134474000,"lastModifiedUser":"xbgoogle","listsort":11,"status":1,"type_id":"740","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":43,"image":"upload/images/20200428/1588079137516.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134481000,"lastModifiedUser":"xbgoogle","listsort":12,"status":1,"type_id":"830","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":44,"image":"upload/images/20200429/1588122884495.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134488000,"lastModifiedUser":"xbgoogle","listsort":13,"status":1,"type_id":"860","typename":Utils.getString(R.string.??????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":45,"image":"upload/images/20200429/1588122906152.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134495000,"lastModifiedUser":"xbgoogle","listsort":14,"status":1,"type_id":"870","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":47,"image":"upload/images/20200429/1588122925637.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134501000,"lastModifiedUser":"xbgoogle","listsort":15,"status":1,"type_id":"890","typename":Utils.getString(R.string.??????????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":48,"image":"upload/images/20200429/1588122959361.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134507000,"lastModifiedUser":"xbgoogle","listsort":16,"status":1,"type_id":"900","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":49,"image":"upload/images/20200429/1588122990029.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134513000,"lastModifiedUser":"xbgoogle","listsort":17,"status":1,"type_id":"910","typename":Utils.getString(R.string.?????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":50,"image":"upload/images/20200429/1588123009331.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588134520000,"lastModifiedUser":"xbgoogle","listsort":18,"status":1,"type_id":"920","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":51,"image":"upload/images/20200429/1588123027565.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158601000,"lastModifiedUser":"xbgoogle","listsort":19,"status":1,"type_id":"930","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":56,"image":"upload/images/20200429/1588123050937.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158609000,"lastModifiedUser":"xbgoogle","listsort":20,"status":1,"type_id":"1370","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":57,"image":"upload/images/20200429/1588123071824.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158616000,"lastModifiedUser":"xbgoogle","listsort":21,"status":1,"type_id":"1660","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":58,"image":"upload/images/20200429/1588123091695.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158623000,"lastModifiedUser":"xbgoogle","listsort":22,"status":1,"type_id":"1690","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":60,"image":"upload/images/20200429/1588123115700.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158631000,"lastModifiedUser":"xbgoogle","listsort":23,"status":1,"type_id":"1850","typename":Utils.getString(R.string.??????????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":61,"image":"upload/images/20200429/1588123140549.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158637000,"lastModifiedUser":"xbgoogle","listsort":24,"status":1,"type_id":"1860","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":64,"image":"upload/images/20200429/1588123158160.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158643000,"lastModifiedUser":"xbgoogle","listsort":25,"status":1,"type_id":"1940","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":65,"image":"upload/images/20200429/1588123183506.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158650000,"lastModifiedUser":"xbgoogle","listsort":26,"status":1,"type_id":"1950","typename":Utils.getString(R.string.???????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":66,"image":"upload/images/20200429/1588123197968.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158656000,"lastModifiedUser":"xbgoogle","listsort":27,"status":1,"type_id":"1960","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":67,"image":"upload/images/20200429/1588123217477.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158662000,"lastModifiedUser":"xbgoogle","listsort":28,"status":1,"type_id":"1970","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":68,"image":"upload/images/20200429/1588123602132.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158672000,"lastModifiedUser":"xbgoogle","listsort":29,"status":1,"type_id":"1980","typename":Utils.getString(R.string.????????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":69,"image":"upload/images/20200429/1588123245280.png","isDelete":0,"isHot":0,"isStart":1,"lastModifiedDate":1588158680000,"lastModifiedUser":"xbgoogle","listsort":30,"status":1,"type_id":"1990","typename":Utils.getString(R.string.?????????),"version":0},{"createdDate":1587519929000,"createdUser":"pg","game":50,"id":1,"image":"upload/images/20200428/1588078896308.png","isDelete":0,"isHot":0,"isStart":0,"lastModifiedDate":1588224005000,"lastModifiedUser":"pt12345","listsort":31,"status":1,"type_id":"220","typename":Utils.getString(R.string.?????????),"version":0}]
         * message : success
         * status : success
         */

        private String message;
        private String status;
        private List<HomeGridView.ChessEntity.DataBean> data;

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
             * createdDate : 1587519929000
             * createdUser : pg
             * game : 50
             * id : 70
             * image : upload/images/20200429/1588159045287.png
             * isDelete : 0
             * isHot : 1
             * isStart : 1
             * lastModifiedDate : 1588224032000
             * lastModifiedUser : pt12345
             * listsort : 0
             * status : 1
             * type_id : 8130
             * typename : ?????????
             * version : 0
             */
            private boolean isHistory;
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
            private int status;
            private String type_id;
            private String typename;
            private int version;

            public boolean isHistory() {
                return isHistory;
            }

            public void setHistory(boolean history) {
                isHistory = history;
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

}
