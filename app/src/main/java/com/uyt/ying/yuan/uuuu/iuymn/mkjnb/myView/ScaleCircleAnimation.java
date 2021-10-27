
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;


public class ScaleCircleAnimation {
    private  float radius;
    private int leftX,rightX;
    private int topY,bottomY;


    public ScaleCircleAnimation(int leftX,int rightX,int topY,int bottomY,float radius){
        this.leftX=leftX;
        this.rightX=rightX;
        this.topY=topY;
        this.bottomY=bottomY;
        this.radius=radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getLeftX() {
        return leftX;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }

    public int getRightX() {
        return rightX;
    }

    public void setRightX(int rightX) {
        this.rightX = rightX;
    }

    public int getTopY() {
        return topY;
    }

    public void setTopY(int topY) {
        this.topY = topY;
    }

    public int getBottomY() {
        return bottomY;
    }

    public void setBottomY(int bottomY) {
        this.bottomY = bottomY;
    }
}
