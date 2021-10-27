/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SaveLiveSearchUtil {
    SharedPreferenceHelperImpl sp=new SharedPreferenceHelperImpl();
    public  void saveSearchHistory(String inputText) {
        String longHistory = sp.getLiveSearchCache();  //获取之前保存的历史记录
        String[] tmpHistory = longHistory.split(",,"); //逗号截取 保存在数组中
        List<String> historyList = new ArrayList<String>(Arrays.asList(tmpHistory)); //将改数组转换成ArrayList
        //longHistory为空时,逗号截取之后historyList size为1,会有一个空字符,判断longHistory不为空,保证第一次能正常储存
        if (historyList.size() > 0&& StringMyUtil.isNotEmpty(longHistory)) {
            //1.移除之前重复添加的元素
            for (int i = 0; i < historyList.size(); i++) {
                if (inputText.equals(historyList.get(i))) {
                    historyList.remove(i);
                    break;
                }
            }
            historyList.add(0, inputText); //将新输入的文字添加集合的第0位
            if (historyList.size() > 8) {
                historyList.remove(historyList.size() - 1); //最多保存8条搜索记录 删除最早搜索的那一项
            }
            //逗号拼接
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < historyList.size(); i++) {
                sb.append(historyList.get(i) + ",,");
            }
            //保存到sp
            sp.setLiveSearchCache(sb.toString());
        } else {
            //之前未添加过
            sp.setLiveSearchCache(inputText + ",,");
        }
    }
    //获取搜索记录
    public  List<String> getSearchHistory(){
        String longHistory = sp.getLiveSearchCache();
        String[] tmpHistory = longHistory.split(",,"); //split后长度为1有一个空串对象
        List<String> historyList = new ArrayList<String>(Arrays.asList(tmpHistory));
        if (historyList.size() == 1 && StringMyUtil.isEmptyString(longHistory)) { //如果没有搜索记录，split之后第0位是个空串的情况下
            historyList.clear();  //清空集合
        }
        return historyList;
    }
}
