package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LiveFilterEntity {
    String game;
    ArrayList<NavigateEntity.GameClassListBean> gameClassListBeanArrayList = new ArrayList<>();
    ArrayList<String> typeNameList = new ArrayList<>();
    ArrayList<String> groupNameList = new ArrayList<>();
    Map<String,ArrayList<String>> allowBettingGroupDataDicForLive = new HashMap<>();

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public ArrayList<NavigateEntity.GameClassListBean> getGameClassListBeanArrayList() {
        return gameClassListBeanArrayList;
    }

    public void setGameClassListBeanArrayList(ArrayList<NavigateEntity.GameClassListBean> gameClassListBeanArrayList) {
        this.gameClassListBeanArrayList = gameClassListBeanArrayList;
    }

    public ArrayList<String> getTypeNameList() {
        return typeNameList;
    }

    public void setTypeNameList(ArrayList<String> typeNameList) {
        this.typeNameList = typeNameList;
    }

    public ArrayList<String> getGroupNameList() {
        return groupNameList;
    }

    public void setGroupNameList(ArrayList<String> groupNameList) {
        this.groupNameList = groupNameList;
    }

    public Map<String, ArrayList<String>> getAllowBettingGroupDataDicForLive() {
        return allowBettingGroupDataDicForLive;
    }

    public void setAllowBettingGroupDataDicForLive(Map<String, ArrayList<String>> allowBettingGroupDataDicForLive) {
        this.allowBettingGroupDataDicForLive = allowBettingGroupDataDicForLive;
    }
}
