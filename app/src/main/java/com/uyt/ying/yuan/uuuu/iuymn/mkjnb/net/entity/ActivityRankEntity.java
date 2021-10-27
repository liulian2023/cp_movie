package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import com.uyt.ying.rxhttp.net.model.BaseEntity;

import java.util.List;

public class ActivityRankEntity extends BaseEntity {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int Switch;
        private String image;
        private RongcloudHBParameter content;
        private int imageResId;
        private int activityType;//1趣约;2天降;3专享红包

        public int getSwitch() {
            return Switch;
        }

        public void setSwitch(int aSwitch) {
            Switch = aSwitch;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public RongcloudHBParameter getContent() {
            return content;
        }

        public void setContent(RongcloudHBParameter content) {
            this.content = content;
        }

        public int getImageResId() {
            return imageResId;
        }

        public void setImageResId(int imageResId) {
            this.imageResId = imageResId;
        }

        public int getActivityType() {
            return activityType;
        }

        public void setActivityType(int activityType) {
            this.activityType = activityType;
        }
    }

}
