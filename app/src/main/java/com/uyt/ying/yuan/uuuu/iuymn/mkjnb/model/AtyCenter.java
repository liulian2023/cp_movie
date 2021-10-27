package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

/**
 * 活动中心recycleView数据适配
 */
public class AtyCenter implements Serializable {
    int imageId;
    String title;
    String content;
    String imageUrl;
    int what;
    long noticeId;
    String contentMore;
    String link;

    String themetype;

    public String getThemetype() {
        return themetype;
    }

    public void setThemetype(String themetype) {
        this.themetype = themetype;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContentMore() {
        return contentMore;
    }

    public void setContentMore(String contentMore) {
        this.contentMore = contentMore;
    }

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }

    public AtyCenter(int imageId, String title, String content, String imageUrl, int what, long noticeId,String contentMore) {
        this.imageId = imageId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.what = what;
        this.noticeId = noticeId;
        this.contentMore = contentMore;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
