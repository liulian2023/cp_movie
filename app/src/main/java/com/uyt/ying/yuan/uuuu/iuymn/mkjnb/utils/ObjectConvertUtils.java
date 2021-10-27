/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ObjectConvertUtils {

    /**
     * 把一个List<Object[]>转换成一个List<T>
     * @param
     * @param clz
     * @return
     * @throws
     */
    public static <T> List<T> objectToBean(List<Object[]> objList, Class<T> clz) throws Exception{
        if(objList==null || objList.size()==0) {
            return null;
        }

        Class<?>[] cz = null;
        Constructor<?>[] cons = clz.getConstructors();
        for(Constructor<?> ct : cons) {
            Class<?>[] clazz = ct.getParameterTypes();
            if(objList.get(0).length == clazz.length) {
                cz = clazz;
                break;
            }
        }

        List<T> list = new ArrayList<T>();
        for(Object[] obj : objList) {
            Constructor<T> cr = clz.getConstructor(cz);
            list.add(cr.newInstance(obj));
        }
        return list;
    }



}
