package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import com.uyt.ying.rxhttp.net.model.BaseEntity;

import java.util.List;

public class ChanelEntity extends BaseEntity {


    /**
     * data : {"movies":[{"cpId":46,"id":1251,"movieName":Utils.getString(R.string.xh小可爱),"moviePhoto":"http://static.j6s0f.cn/20200102200939_10d02c5c1abdcf13d37fa24c5937667e?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/2343032_1580610381?txSecret=15db97f3232afed0ee2ff2eeb13847dd&txTime=5E3636DC"},{"cpId":37,"id":1252,"movieName":Utils.getString(R.string.唇色九九),"moviePhoto":"http://static.j6s0f.cn/20200201174938_e3c55477c64f3c1a78e57a4c91c3e377?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/3042103_1580608215?txSecret=c48f5797963ca85b8aa7f2791e43aea3&txTime=5E3636DC"},{"cpId":54,"id":1250,"movieName":Utils.getString(R.string.sky君君),"moviePhoto":"http://zb.4d0qa.cn/FvabrX4Yoqc06Ctz8wXeNKrmRiyW?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.xiwanjic.com/live/5146220_1580603941?txSecret=09f192da5a441791b9f602f6be4f9b0c&txTime=5E361A25"},{"cpId":62,"id":1248,"movieName":Utils.getString(R.string.sky君君),"moviePhoto":"http://s.c439e6.cn/20190814011047_4857f797441cea882bc445ddc4519726?imageView2/2/w/600/h/600","networkUrl":"rtmp://txdown1.kcd12.cn/live/322617_1580603522?txSecret=6db23e0ed3673f5af7d3bb25a1aa7dae&txTime=5E3636E2"},{"cpId":56,"id":1249,"movieName":Utils.getString(R.string.FH大奶戴戴@),"moviePhoto":"http://static.j6s0f.cn/20200125054938_4223d85d4e63b65b74c95de9a4c541a4?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/2377128_1580596859?txSecret=88c2786f2836167ac49fd99401ff6f5b&txTime=5E3636DC"},{"cpId":54,"id":1236,"movieName":Utils.getString(R.string.VIP请叫我小花),"moviePhoto":"http://s.c439e6.cn/20200201215802_b0a1d76d383099426d777ad5e65070f1?imageView2/2/w/600/h/600","networkUrl":"rtmp://txdown1.kcd12.cn/live/3381716_1580611169?txSecret=2d03d7af9a6d143f6032e781090858b0&txTime=5E3636E2"},{"cpId":21,"id":1231,"movieName":Utils.getString(R.string.mt 恩熙),"moviePhoto":"http://zb.4d0qa.cn/FlRZAMqIdULx11Ezg9F0Ava7vMBN?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.xiwanjic.com/live/5503881_1580589644?txSecret=9ae81fe9196eeec877b2a29781f12c49&txTime=5E35E24C"},{"cpId":53,"id":1228,"movieName":Utils.getString(R.string.mt恩意),"moviePhoto":"http://static.j6s0f.cn/20200103221702_cff4e3ff424984cb9373f1424617f101?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/2347211_1580563498?txSecret=41c69f7a28533c4295442460d61a3294&txTime=5E3636DC"},{"cpId":42,"id":1217,"movieName":Utils.getString(R.string.户外树林啪啪),"moviePhoto":"http://s.c439e6.cn/20191127193640_7fc47745e3906f9cba42f085632f8c1d?imageView2/2/w/600/h/600","networkUrl":"rtmp://txdown1.kcd12.cn/live/1560319_1580590750?txSecret=167e6c9130eb5ec29824a608855295f3&txTime=5E3636E2"},{"cpId":21,"id":1213,"movieName":Utils.getString(R.string.花郎君3311),"moviePhoto":"http://zb.xcmzb.xyz/default_avatar.png","networkUrl":"rtmp://pull.xiwanjic.com/live/5201796_1580558064?txSecret=b9bfb5ce84ae2d109eda281317d8832f&txTime=5E34BE30"}],"movieCount":304,"classification":[{"id":1,"name":Utils.getString(R.string.樱花雨)},{"id":2,"name":Utils.getString(R.string.云上飞)},{"id":3,"name":Utils.getString(R.string.红浪漫)}],"chooseClassificationId":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * movies : [{"cpId":46,"id":1251,"movieName":Utils.getString(R.string.xh小可爱),"moviePhoto":"http://static.j6s0f.cn/20200102200939_10d02c5c1abdcf13d37fa24c5937667e?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/2343032_1580610381?txSecret=15db97f3232afed0ee2ff2eeb13847dd&txTime=5E3636DC"},{"cpId":37,"id":1252,"movieName":Utils.getString(R.string.唇色九九),"moviePhoto":"http://static.j6s0f.cn/20200201174938_e3c55477c64f3c1a78e57a4c91c3e377?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/3042103_1580608215?txSecret=c48f5797963ca85b8aa7f2791e43aea3&txTime=5E3636DC"},{"cpId":54,"id":1250,"movieName":Utils.getString(R.string.sky君君),"moviePhoto":"http://zb.4d0qa.cn/FvabrX4Yoqc06Ctz8wXeNKrmRiyW?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.xiwanjic.com/live/5146220_1580603941?txSecret=09f192da5a441791b9f602f6be4f9b0c&txTime=5E361A25"},{"cpId":62,"id":1248,"movieName":Utils.getString(R.string.sky君君),"moviePhoto":"http://s.c439e6.cn/20190814011047_4857f797441cea882bc445ddc4519726?imageView2/2/w/600/h/600","networkUrl":"rtmp://txdown1.kcd12.cn/live/322617_1580603522?txSecret=6db23e0ed3673f5af7d3bb25a1aa7dae&txTime=5E3636E2"},{"cpId":56,"id":1249,"movieName":Utils.getString(R.string.FH大奶戴戴@),"moviePhoto":"http://static.j6s0f.cn/20200125054938_4223d85d4e63b65b74c95de9a4c541a4?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/2377128_1580596859?txSecret=88c2786f2836167ac49fd99401ff6f5b&txTime=5E3636DC"},{"cpId":54,"id":1236,"movieName":Utils.getString(R.string.VIP请叫我小花),"moviePhoto":"http://s.c439e6.cn/20200201215802_b0a1d76d383099426d777ad5e65070f1?imageView2/2/w/600/h/600","networkUrl":"rtmp://txdown1.kcd12.cn/live/3381716_1580611169?txSecret=2d03d7af9a6d143f6032e781090858b0&txTime=5E3636E2"},{"cpId":21,"id":1231,"movieName":Utils.getString(R.string.mt 恩熙),"moviePhoto":"http://zb.4d0qa.cn/FlRZAMqIdULx11Ezg9F0Ava7vMBN?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.xiwanjic.com/live/5503881_1580589644?txSecret=9ae81fe9196eeec877b2a29781f12c49&txTime=5E35E24C"},{"cpId":53,"id":1228,"movieName":Utils.getString(R.string.mt恩意),"moviePhoto":"http://static.j6s0f.cn/20200103221702_cff4e3ff424984cb9373f1424617f101?imageView2/2/w/600/h/600","networkUrl":"rtmp://pull.purikura.com.cn/live/2347211_1580563498?txSecret=41c69f7a28533c4295442460d61a3294&txTime=5E3636DC"},{"cpId":42,"id":1217,"movieName":Utils.getString(R.string.户外树林啪啪),"moviePhoto":"http://s.c439e6.cn/20191127193640_7fc47745e3906f9cba42f085632f8c1d?imageView2/2/w/600/h/600","networkUrl":"rtmp://txdown1.kcd12.cn/live/1560319_1580590750?txSecret=167e6c9130eb5ec29824a608855295f3&txTime=5E3636E2"},{"cpId":21,"id":1213,"movieName":Utils.getString(R.string.花郎君3311),"moviePhoto":"http://zb.xcmzb.xyz/default_avatar.png","networkUrl":"rtmp://pull.xiwanjic.com/live/5201796_1580558064?txSecret=b9bfb5ce84ae2d109eda281317d8832f&txTime=5E34BE30"}]
         * movieCount : 304
         * classification : [{"id":1,"name":Utils.getString(R.string.樱花雨)},{"id":2,"name":Utils.getString(R.string.云上飞)},{"id":3,"name":Utils.getString(R.string.红浪漫)}]
         * chooseClassificationId : 0
         */

        private int movieCount;
        private int chooseClassificationId;
        private List<MoviesBean> movies;
        private List<ClassificationBean> classification;

        public int getMovieCount() {
            return movieCount;
        }

        public void setMovieCount(int movieCount) {
            this.movieCount = movieCount;
        }

        public int getChooseClassificationId() {
            return chooseClassificationId;
        }

        public void setChooseClassificationId(int chooseClassificationId) {
            this.chooseClassificationId = chooseClassificationId;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public List<ClassificationBean> getClassification() {
            return classification;
        }

        public void setClassification(List<ClassificationBean> classification) {
            this.classification = classification;
        }

        public static class MoviesBean {
            /**
             * cpId : 46
             * id : 1251
             * movieName : xh小可爱
             * moviePhoto : http://static.j6s0f.cn/20200102200939_10d02c5c1abdcf13d37fa24c5937667e?imageView2/2/w/600/h/600
             * networkUrl : rtmp://pull.purikura.com.cn/live/2343032_1580610381?txSecret=15db97f3232afed0ee2ff2eeb13847dd&txTime=5E3636DC
             */

            private long cpId;
            private long id;
            private String movieName;
            private String moviePhoto;
            private String networkUrl;

            public long getCpId() {
                return cpId;
            }

            public void setCpId(long cpId) {
                this.cpId = cpId;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getMovieName() {
                return movieName;
            }

            public void setMovieName(String movieName) {
                this.movieName = movieName;
            }

            public String getMoviePhoto() {
                return moviePhoto;
            }

            public void setMoviePhoto(String moviePhoto) {
                this.moviePhoto = moviePhoto;
            }

            public String getNetworkUrl() {
                return networkUrl;
            }

            public void setNetworkUrl(String networkUrl) {
                this.networkUrl = networkUrl;
            }
        }

        public static class ClassificationBean {
            /**
             * id : 1
             * name : 樱花雨
             */

            private long id;
            private String name;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
