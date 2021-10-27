package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel;

public class CollectEvenModel {
    int movieId;
    boolean isCollect;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public CollectEvenModel(int movieId, boolean isCollect) {
        this.movieId = movieId;
        this.isCollect = isCollect;
    }
}
