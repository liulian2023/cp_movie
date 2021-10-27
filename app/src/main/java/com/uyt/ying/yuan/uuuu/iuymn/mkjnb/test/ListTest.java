/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.test;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListTest {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args){

//

        List<LiveEntity.AnchorMemberRoomListBean> dataList = new ArrayList<>();

        for (int i =0;i<5;i++){
            LiveEntity.AnchorMemberRoomListBean bean = new LiveEntity.AnchorMemberRoomListBean();
            bean.setAnchorAccount(""+i);
//            bean.setAnchorMemberId(i);
            bean.setLiveRoomId(""+i);

            dataList.add(bean);
        }
        dataList.get(2).setAnchorAccount("1");
    }

    private static ArrayList<LiveEntity.AnchorMemberRoomListBean> removeDuplicateUser(List<LiveEntity.AnchorMemberRoomListBean> users) {
        Set<LiveEntity.AnchorMemberRoomListBean> set = new TreeSet<LiveEntity.AnchorMemberRoomListBean>(new Comparator<LiveEntity.AnchorMemberRoomListBean>() {
            @Override
            public int compare(LiveEntity.AnchorMemberRoomListBean o1, LiveEntity.AnchorMemberRoomListBean o2) {
                //字符串,则按照asicc码升序排列
                return o1.getAnchorAccount().compareTo(o2.getAnchorAccount());
            }
        });
        set.addAll(users);
        return new ArrayList<>(set);
    }
}
