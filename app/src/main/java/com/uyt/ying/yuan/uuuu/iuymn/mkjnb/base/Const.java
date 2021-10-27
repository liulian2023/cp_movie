/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.content.Context;
import android.content.res.TypedArray;

import com.uyt.ying.yuan.R;

public class Const {


    public static final String ERROR_STATE  = "error_state";
    public static final String LOADING_STATE  = "loading_state";
    public static final String COMPLETE_STATE  = "complete_state";



    public static final String[] NumArray = {"1","2","3","4","5","6"};

    public static int[] getShaziArray(Context mContext){
        int[] shaizis;
        TypedArray ar = mContext.getResources().obtainTypedArray(R.array.shaizi);
        int len = ar.length();
        shaizis = new int[len];
        for (int i = 0; i < len; i++)
        {  shaizis[i] = ar.getResourceId(i, 0);}
        ar.recycle();
        return shaizis;
    }
    public static int[] getRaceqiuArray(Context mContext){
        int[] shaizis;
        TypedArray ar = mContext.getResources().obtainTypedArray(R.array.raceQiu);
        int len = ar.length();
        shaizis = new int[len];
        for (int i = 0; i < len; i++)
        {  shaizis[i] = ar.getResourceId(i, 0);}
        ar.recycle();
        return shaizis;
    }
    public static int[] getXuanwuqiuArray(Context mContext){
        int[] shaizis;
        TypedArray ar = mContext.getResources().obtainTypedArray(R.array.xuanwuQiu);
        int len = ar.length();
        shaizis = new int[len];
        for (int i = 0; i < len; i++)
        {  shaizis[i] = ar.getResourceId(i, 0);}
        ar.recycle();
        return shaizis;
    }
/*    public static int[] getLevelIcon(Context mContext){
        int[] levels;
        TypedArray ar = mContext.getResources().obtainTypedArray(R.array.level_icon);
        int len = ar.length();
        levels = new int[len];
        for (int i = 0; i < len; i++)
        {  levels[i] = ar.getResourceId(i, 0);}
        ar.recycle();
        return levels;
    }*/
    public static final String POSITION = "position";
    public static final String RANKTYPE = "RankType";
    public static final String ID = "id";
    public static final String ANCHOR_ROOM_BEAN = "anchor_room_bean";

}
