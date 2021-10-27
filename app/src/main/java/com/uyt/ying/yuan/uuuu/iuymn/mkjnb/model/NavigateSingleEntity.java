package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class NavigateSingleEntity {
    NavigateEntity navigateEntity;
    public static NavigateSingleEntity navigateSingleEntity=null;

    public static NavigateSingleEntity newInstance(){
        if(navigateSingleEntity == null){
            navigateSingleEntity = new NavigateSingleEntity();
        }
        return navigateSingleEntity;
    }

    public NavigateEntity getNavigateEntity() {
        return navigateEntity;
    }

    public void setNavigateEntity(NavigateEntity navigateEntity) {
        this.navigateEntity = navigateEntity;
    }
}
