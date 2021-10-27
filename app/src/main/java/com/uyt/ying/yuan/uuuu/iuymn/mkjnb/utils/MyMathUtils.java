/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import java.util.ArrayList;
import java.util.List;

public class MyMathUtils {

    /**
     *
     * @param requMin 最小值
     * @param requMax 最大值
     * @param targetLength 获取随机数个数
     * @return   不相同随机数
     */
    public List<Integer> getRandomNum(int requMin, int requMax, int targetLength) {
        if(requMax-requMin < 1){
            return null;
        }else if(requMax-requMin <targetLength){
            return null;
        }
        int target = targetLength;
        List<Integer> list = new ArrayList<>();

        List<Integer> requList = new ArrayList<>();
        for (int i = requMin; i <= requMax; i++) {
            requList.add(i);
        }

        for (int i = 0; i < targetLength; i++) {

            // 取出一个随机角标
            int r = (int) (Math.random() * requList.size());
            list.add(requList.get(r));

            // 移除已经取过的值
            requList.remove(r);


        }

        return list;
    }

    /**
     *
     * @param requList 指定集合
     * @param targetLength 获取随机数个数
     * @return
     */
    public static List<Long> getRandomNum(List<Long> requList, int targetLength) {
        if(requList.size() < 1){
            return null;
        }else if(requList.size() <targetLength){
            return null;
        }
        List<Long> list = new ArrayList<>();

        for (int i = 0; i < targetLength; i++) {

            // 取出一个随机角标
            int r = (int) (Math.random() * requList.size());
            list.add((Long) requList.get(r));

            // 移除已经取过的值
            requList.remove(r);


        }
        return list;
    }


}
