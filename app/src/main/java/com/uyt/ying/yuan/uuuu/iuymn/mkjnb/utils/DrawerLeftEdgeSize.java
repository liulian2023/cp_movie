package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;
import androidx.drawerlayout.widget.DrawerLayout;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.EBDrawerLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.XDrawerLayout;

import java.lang.reflect.Field;

public class DrawerLeftEdgeSize {

    public static void setCustomRightEdgeSize(@NonNull XDrawerLayout drawerLayout, float displayWidthPercentage) {
        try {
            // find ViewDragHelper and set it accessible
            Field rightDraggerField = drawerLayout.getClass().getDeclaredField("mRightDragger");
            if (rightDraggerField == null) {
                return;
            }
            rightDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) rightDraggerField.get(drawerLayout);
            // find edgesize and set is accessible
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            // set new edgesize
            int widthPixels = ScreenUtils.getWight(drawerLayout.getContext());
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (widthPixels * displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void setRightEdgeSize(@NonNull EBDrawerLayout drawerLayout, float displayWidthPercentage) {
        try {
            // find ViewDragHelper and set it accessible
            Field rightDraggerField = drawerLayout.getClass().getDeclaredField("mRightDragger");
            if (rightDraggerField == null) {
                return;
            }
            rightDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) rightDraggerField.get(drawerLayout);
            // find edgesize and set is accessible
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            // set new edgesize
            int widthPixels = ScreenUtils.getWight(drawerLayout.getContext());
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (widthPixels * displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setRightEdge (Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField =
                    drawerLayout.getClass().getDeclaredField("mRightDragger");//Right
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);

            // 找到 edgeSizeField 并设置 Accessible 为true
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);

            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

}
