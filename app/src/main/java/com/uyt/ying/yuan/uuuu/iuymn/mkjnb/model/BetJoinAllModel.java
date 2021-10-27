package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class BetJoinAllModel {

    /**
     * 倍数列表   筹码列表
     */
    private List<BetJoinTimeModel> betJoinTimeModelList;
    private List<BetJoinMaModel> betJoinMaModelList;

    public List<BetJoinTimeModel> getBetJoinTimeModelList() {
        return betJoinTimeModelList;
    }

    public void setBetJoinTimeModelList(List<BetJoinTimeModel> betJoinTimeModelList) {
        this.betJoinTimeModelList = betJoinTimeModelList;
    }

    public List<BetJoinMaModel> getBetJoinMaModelList() {
        return betJoinMaModelList;
    }

    public void setBetJoinMaModelList(List<BetJoinMaModel> betJoinMaModelList) {
        this.betJoinMaModelList = betJoinMaModelList;
    }

    public static class BetJoinTimeModel{
        private int times;
        private boolean isSelect;


        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public static class BetJoinMaModel{
        private int danjia;     //筹码大小
        private boolean isSelect;  //是否选中
        private boolean isCurrent;   //是否点击确认后成为当前筹码
        private int index;  //index 位置

        public int getDanjia() {
            return danjia;
        }

        public void setDanjia(int danjia) {
            this.danjia = danjia;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public boolean isCurrent() {
            return isCurrent;
        }

        public void setCurrent(boolean current) {
            isCurrent = current;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }



}
