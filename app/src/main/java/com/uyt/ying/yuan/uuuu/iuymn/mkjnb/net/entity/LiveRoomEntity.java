
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import com.uyt.ying.rxhttp.net.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class LiveRoomEntity extends BaseEntity {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * movie : {"cpId":37,"name":Utils.getString(R.string.距离200米),"isCollection":0,"id":322,"classificationId":1,"moviePhoto":"http://s.c439e6.cn/20200201163341_24ef7f9c86f35773dca68f5669079f3c?imageView2/2/w/600/h/600","networkUrl":"rtmp://txdown1.kcd12.cn/live/1872169_1581064126?txSecret=e253d827a617931127ec2628848a60ed&txTime=5E3D3171"}
         * classificationName : 樱花雨
         * classificationId : 1
         */

        private MovieBean movie;
        private String classificationName;
        private int classificationId;

        public MovieBean getMovie() {
            return movie;
        }

        public void setMovie(MovieBean movie) {
            this.movie = movie;
        }

        public String getClassificationName() {
            return classificationName;
        }

        public void setClassificationName(String classificationName) {
            this.classificationName = classificationName;
        }

        public int getClassificationId() {
            return classificationId;
        }

        public void setClassificationId(int classificationId) {
            this.classificationId = classificationId;
        }

        public static class MovieBean implements Serializable {
            /**
             * cpId : 37
             * name : 距离200米
             * isCollection : 0
             * id : 322
             * classificationId : 1
             * moviePhoto : http://s.c439e6.cn/20200201163341_24ef7f9c86f35773dca68f5669079f3c?imageView2/2/w/600/h/600
             * networkUrl : rtmp://txdown1.kcd12.cn/live/1872169_1581064126?txSecret=e253d827a617931127ec2628848a60ed&txTime=5E3D3171
             */

            private long cpId;
            private String name;
            private int isCollection;
            private long id;
            private int classificationId;
            private String moviePhoto;
            private String networkUrl;

            public long getCpId() {
                return cpId;
            }

            public void setCpId(long cpId) {
                this.cpId = cpId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getClassificationId() {
                return classificationId;
            }

            public void setClassificationId(int classificationId) {
                this.classificationId = classificationId;
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
    }
}
