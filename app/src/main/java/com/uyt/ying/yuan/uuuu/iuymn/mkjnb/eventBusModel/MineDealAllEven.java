package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel;

import java.util.Date;

public class MineDealAllEven {
//    getDataAll(user_id, pagenumAll, pageSizeAll, startAll, endAll, "", typeAll, false, false);
    long user_id;
    int pagenumAll;
    int pageSizeAll;
    Date startAll;
    Date endAll;
    String typeAll;
    Date startOut;
    Date endOut;
    String typeOut;
    Date startIn;
    Date endIn;
    String typeIn;
    boolean isLoadMore;
    boolean showLoad;

    public boolean isShowLoad() {
        return showLoad;
    }

    public void setShowLoad(boolean showLoad) {
        this.showLoad = showLoad;
    }

    public MineDealAllEven(long user_id, int pagenumAll, int pageSizeAll, Date startAll, Date endAll, String typeAll, Date startOut, Date endOut, String typeOut, Date startIn, Date endIn, String typeIn, boolean isLoadMore,boolean showLoad) {
        this.user_id = user_id;
        this.pagenumAll = pagenumAll;
        this.pageSizeAll = pageSizeAll;
        this.startAll = startAll;
        this.endAll = endAll;
        this.typeAll = typeAll;
        this.startOut = startOut;
        this.endOut = endOut;
        this.typeOut = typeOut;
        this.startIn = startIn;
        this.endIn = endIn;
        this.typeIn = typeIn;
        this.isLoadMore = isLoadMore;
        this.showLoad = showLoad;
    }

    public Date getStartOut() {

        return startOut;
    }

    public void setStartOut(Date startOut) {
        this.startOut = startOut;
    }

    public Date getEndOut() {
        return endOut;
    }

    public void setEndOut(Date endOut) {
        this.endOut = endOut;
    }

    public String getTypeOut() {
        return typeOut;
    }

    public void setTypeOut(String typeOut) {
        this.typeOut = typeOut;
    }

    public Date getStartIn() {
        return startIn;
    }

    public void setStartIn(Date startIn) {
        this.startIn = startIn;
    }

    public Date getEndIn() {
        return endIn;
    }

    public void setEndIn(Date endIn) {
        this.endIn = endIn;
    }

    public String getTypeIn() {
        return typeIn;
    }

    public void setTypeIn(String typeIn) {
        this.typeIn = typeIn;
    }

    public long getUser_id() {

        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getPagenumAll() {
        return pagenumAll;
    }

    public void setPagenumAll(int pagenumAll) {
        this.pagenumAll = pagenumAll;
    }

    public int getPageSizeAll() {
        return pageSizeAll;
    }

    public void setPageSizeAll(int pageSizeAll) {
        this.pageSizeAll = pageSizeAll;
    }

    public Date getStartAll() {
        return startAll;
    }

    public void setStartAll(Date startAll) {
        this.startAll = startAll;
    }

    public Date getEndAll() {
        return endAll;
    }

    public void setEndAll(Date endAll) {
        this.endAll = endAll;
    }

    public String getTypeAll() {
        return typeAll;
    }

    public void setTypeAll(String typeAll) {
        this.typeAll = typeAll;
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }
}
