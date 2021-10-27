package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class InviteCodeEntity {
    private String inviteCode;
    private static InviteCodeEntity inviteCodeEntity;
    public static InviteCodeEntity getInstance(){
        if(inviteCodeEntity==null){
            inviteCodeEntity = new InviteCodeEntity();
        }
    return inviteCodeEntity;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
