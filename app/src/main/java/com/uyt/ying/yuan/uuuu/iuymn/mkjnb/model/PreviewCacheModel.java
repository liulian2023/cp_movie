package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class PreviewCacheModel {


    List<DataBean>dataBeanList;


    public List<DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }


    public static class DataBean{
        String url;
        long date;
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }
    }
}
