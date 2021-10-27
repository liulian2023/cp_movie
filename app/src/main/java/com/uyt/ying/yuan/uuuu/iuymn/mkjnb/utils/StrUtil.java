/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

    public static boolean isNumer(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isclick(List<IsClickModel> list,String id){
        for (IsClickModel bean:list){
            if (bean.getId().equals(id)){
                return bean.getIsclick();
            }
        }
        return false;
    }

    public static void isclickAdd(List<IsClickModel> list,String id,Boolean b){
        IsClickModel isClickModel = new IsClickModel();
        isClickModel.setId(id);
        isClickModel.setIsclick(b);
        list.add(isClickModel);
    }

    public static int isclickCal(List<IsClickModel> list){
        int num =0;
        for (int i=0;i<list.size();i++){
            if (list.get(i).getIsclick()){
                num++;
            }
        }
        return num;
    }

    public static void isclickReplace(List<IsClickModel> list,String id,Boolean b){
        for (IsClickModel bean:list){
            if (bean.getId().equals(id)){
                bean.setIsclick(b);
            }
        }
    }

    //算组合数
    public static long comb(int m, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return m;
        if (n > m / 2)
            return comb(m, m - n);
        if (n > 1)
            return comb(m - 1, n - 1) + comb(m - 1, n);
        return 0; //通过编译需要，数字无实际意义
    }


    private static StringBuffer sbf= new StringBuffer();
    /**
     * 组合选择（从列表中选择n个组合）
     * @param dataList 待选列表
     * @param n 选择个数
     */
    public static String combinationSelect(String[] dataList, int n) {

        sbf = new StringBuffer();
        String str = combinationSelect(dataList, 0, new String[n], 0);
        return  str;
    }

    /**
     * 组合选择
     * @param dataList 待选列表
     * @param dataIndex 待选开始索引
     * @param resultList 前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */

    private static String combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {


        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            //     TList.addAll(resultList);
            // sbf.append(Arrays.asList(resultList));
            for (int i = 0;i<resultList.length;i++){
                sbf.append(resultList[i]);
                if (i==resultList.length-1){
                    sbf.append("-");
                }else {

                    sbf.append(",");
                }

            }
            //     System.out.println(Arrays.asList(resultList));
            return sbf.toString();

        }

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
        return sbf.toString();
    }

    /**
     * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
     * @param n
     * @return
     */
    public static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    /**
     * 计算排列数，即A(n, m) = n!/(n-m)!
     * @param n
     * @param m
     * @return
     */
    public static long arrangement(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) : 0;
    }

    /**
     * 计算组合数，即C(n, m) = n!/((n-m)! * m!)
     * @param n
     * @param m
     * @return
     */
    public static long combination(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
    }



    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }


}
