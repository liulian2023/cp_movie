package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import java.util.List;

public class SortEntity  {


    /**
     * rankingList : [{"redPrice":783.04,"nickname":"7a***9","image":"upload/images/20190911/1568206981860.jpg"},{"redPrice":744.9,"nickname":"m9***c","image":"upload/images/20190911/1568207462510.jpg"},{"redPrice":737.4,"nickname":"23***f","image":"upload/images/20190911/1568207436103.jpg"},{"redPrice":728.36,"nickname":"6d***7","image":"upload/images/20190911/1568206757838.jpg"},{"redPrice":720.76,"nickname":"os***3","image":"upload/images/20190911/1568206757838.jpg"},{"redPrice":702.51,"nickname":"ct***g","image":"upload/images/20190911/1568207230090.jpg"},{"redPrice":702.26,"nickname":"n7***w","image":"upload/images/20190911/1568206920562.png"},{"redPrice":699.61,"nickname":"21***5","image":"upload/images/20190911/1568207781513.jpg"},{"redPrice":698.79,"nickname":"97***9","image":"upload/images/20190911/1568206757838.jpg"},{"redPrice":692.9,"nickname":"6o***y","image":"upload/images/20190911/1568207036597.jpg"}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    private List<RankingListBean> rankingList;

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

    public List<RankingListBean> getRankingList() {
        return rankingList;
    }

    public void setRankingList(List<RankingListBean> rankingList) {
        this.rankingList = rankingList;
    }

    public static class RankingListBean {
        /**
         * redPrice : 783.04
         * nickname : 7a***9
         * image : upload/images/20190911/1568206981860.jpg
         */

        private String redPrice;
        private String nickname;
        private String image;

        public String getRedPrice() {
            return redPrice;
        }

        public void setRedPrice(String redPrice) {
            this.redPrice = redPrice;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
