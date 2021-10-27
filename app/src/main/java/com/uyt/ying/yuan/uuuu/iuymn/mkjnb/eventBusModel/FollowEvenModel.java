package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel;

public class FollowEvenModel {
    String id;
    boolean isFollow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public FollowEvenModel(String id, boolean isFollow) {
        this.id = id;
        this.isFollow = isFollow;
    }
}
