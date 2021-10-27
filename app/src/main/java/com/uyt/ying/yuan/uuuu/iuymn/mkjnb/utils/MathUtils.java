/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import java.math.BigDecimal;

/**
 * created  by xxxx on 2019/9/24.
 */
public class MathUtils {

    /**
     *
     * @param str
     * @param p  保留小数位 四舍五入
     * @return
     */
    public static BigDecimal Str2BigDecimal(String str,int p){
        BigDecimal bigDecimal = new BigDecimal(str);
        return  bigDecimal.setScale(p, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 精确浮点数保留小数  四舍五入
     * @param f
     * @param p
     * @return
     */
    public static float FloatsetScale(float f,int p){
        BigDecimal bigDecimal = new BigDecimal(f);
        return  bigDecimal.setScale(p, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
