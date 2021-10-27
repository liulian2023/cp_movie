package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import java.util.Random;

public class RandomCode {

    //生成四位不重复的验证码
    public static String code(){
        char [] codeSequence={'1','2','3','4','5','6','7','8','9'};
//      char [] codeSequence={'A','B','C','D','E','F','G','H','I','J',
//                'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
//                '1','2','3','4','5','6','7','8','9'};
        Random random =new Random();
        StringBuilder sb=new StringBuilder();//动态字符串，String创建的字符串不能修改
        int count=0;//计数器确定产生的是四位验证码
        while(true){
            //随机产生一个下标，通过下标取出字符数组对应的字符
            char c=codeSequence[random.nextInt(codeSequence.length)];
            //假设取出来的字符在动态字符串中不存在，代表没有重复
            if (sb.indexOf(c+"")==-1) {
                sb.append(c);//追加到动态字符串中
                count++;
                if (count==4) {
                    break;
                }
            }
        }

        return sb.toString();

    }

    public static String allCosde(){
//        char [] codeSequence={'1','2','3','4','5','6','7','8','9'};
      char [] codeSequence={'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                '1','2','3','4','5','6','7','8','9'};
        Random random =new Random();
        StringBuilder sb=new StringBuilder();//动态字符串，String创建的字符串不能修改
        int count=0;//计数器确定产生的是四位验证码
        while(true){
            //随机产生一个下标，通过下标取出字符数组对应的字符
            char c=codeSequence[random.nextInt(codeSequence.length)];
            //假设取出来的字符在动态字符串中不存在，代表没有重复
            if (sb.indexOf(c+"")==-1) {
                sb.append(c);//追加到动态字符串中
                count++;
                if (count==4) {
                    break;
                }
            }
        }

        return sb.toString();

    }

}
