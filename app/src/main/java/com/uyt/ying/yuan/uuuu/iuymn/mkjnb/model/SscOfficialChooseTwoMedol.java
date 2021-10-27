

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.ArrayList;

public class SscOfficialChooseTwoMedol extends CommonModel {
    String id;
    String name;
    String odds;
    String parentId;
    String remake;
    ArrayList<SscOfficialChooseMedol>sscOfficialChooseMedolArrayList =new ArrayList<>();//此字段赋值给二级列表对应的三级列表,(创建三级列表适配器时传入)
    int status=0;


    @Override
    public String toString() {
        return "SscOfficialChooseTwoMedol{" +
                "sscOfficialChooseMedolArrayList=" + sscOfficialChooseMedolArrayList +
                '}';
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SscOfficialChooseMedol> getSscOfficialChooseMedolArrayList() {
        return sscOfficialChooseMedolArrayList;
    }

    public void setSscOfficialChooseMedolArrayList(ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedolArrayList) {
        this.sscOfficialChooseMedolArrayList = sscOfficialChooseMedolArrayList;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SscOfficialChooseTwoMedol(String id, String name, String odds, String parentId) {
        this.id = id;
        this.name = name;
        this.odds = odds;
        this.parentId = parentId;
    }
}
