package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class LiveGiftSVGAEntity {
    String url;
    LiveMessageModel liveMessageModel;

    public LiveGiftSVGAEntity(String url, LiveMessageModel liveMessageModel) {
        this.url = url;
        this.liveMessageModel = liveMessageModel;
    }

    public LiveMessageModel getLiveMessageModel() {
        return liveMessageModel;
    }

    public void setLiveMessageModel(LiveMessageModel liveMessageModel) {
        this.liveMessageModel = liveMessageModel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
