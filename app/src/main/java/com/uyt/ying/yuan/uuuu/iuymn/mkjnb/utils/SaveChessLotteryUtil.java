/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentChessModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SaveChessLotteryUtil {
    SharedPreferenceHelperImpl sp=new SharedPreferenceHelperImpl();

    public  void saveChessLotteryHistory(String inputText) {
        String longHistory = sp.getChessLotteryHistory();  //获取之前保存的历史记录
        String[] tmpHistory = longHistory.split(Utils.getString(R.string.啊哈哈)); //逗号截取 保存在数组中
        List<String> historyList = new ArrayList<String>(Arrays.asList(tmpHistory)); //将改数组转换成ArrayList
        //longHistory为空时,逗号截取之后historyList size为1,会有一个空字符,判断longHistory不为空,保证第一次能正常储存
        if (historyList.size() > 0&& StringMyUtil.isNotEmpty(longHistory)) {
            //1.移除之前重复添加的元素
            for (int i = 0; i < historyList.size(); i++) {
                String object = historyList.get(i);
                if(inputText.contains("lastLotteryQiShu")){
                    NavigateEntity.GameClassListBean gameClassListBean = JSONObject.parseObject(inputText, NavigateEntity.GameClassListBean.class);
                    if(object.contains("lastLotteryQiShu")){
                        NavigateEntity.GameClassListBean gameClassBeanHistory = JSONObject.parseObject(object, NavigateEntity.GameClassListBean.class);
                        int game = gameClassBeanHistory.getGame();
                        int type_id = gameClassBeanHistory.getType_id();
                        if(game==gameClassListBean.getGame()&&type_id==gameClassListBean.getType_id()){
                            historyList.remove(i);
                            break;
                        }
                    }
                }else {
                    ShoppingContentChessModel.ChessEntity.DataBean dataBean = JSONObject.parseObject(inputText, ShoppingContentChessModel.ChessEntity.DataBean.class);
                    if(!object.contains("lastLotteryQiShu")){
                        ShoppingContentChessModel.ChessEntity.DataBean dataBeanHistory = JSONObject.parseObject(object, ShoppingContentChessModel.ChessEntity.DataBean.class);
                        int game = dataBeanHistory.getGame();
                        String type_id = dataBeanHistory.getType_id();
                        if(game==dataBean.getGame()&&type_id.equals(dataBean.getType_id())){
                            historyList.remove(i);
                            break;
                        }
                    }
                }
            }
            historyList.add(0, inputText); //将新输入的文字添加集合的第0位
            if (historyList.size() > 6) {
                historyList.remove(historyList.size() - 1); //最多保存8条搜索记录 删除最早搜索的那一项
            }
            //逗号拼接
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < historyList.size(); i++) {
                sb.append(historyList.get(i) + Utils.getString(R.string.啊哈哈));
            }
            //保存到sp
            sp.setChessLotteryHistory(sb.toString());
        } else {
            //之前未添加过
            sp.setChessLotteryHistory(inputText + Utils.getString(R.string.啊哈哈));
        }
    }
    //获取搜索记录
    public  List<String> getChessLotteryHistory(){
        String longHistory = sp.getChessLotteryHistory();
        String[] tmpHistory = longHistory.split(Utils.getString(R.string.啊哈哈)); //split后长度为1有一个空串对象
        List<String> historyList = new ArrayList<String>(Arrays.asList(tmpHistory));
        if (historyList.size() == 1 && StringMyUtil.isEmptyString(longHistory)) { //如果没有搜索记录，split之后第0位是个空串的情况下
            historyList.clear();  //清空集合
        }
        return historyList;
    }

}
