package com.my.xunboplayerlib;

public class PlayEvent {


    public enum PLAYTYPE{
        PREPARE(1,"准备"),
        PLAYING(2,"播放中"),
        ERROR(3,"播放错误"),
        XIABO(4,"通过播放error刷新后url再次播放,仍然error");

        /**
         * 状态值
         */
        private int value;
        /**
         * 类型描述
         */
        private String description;

        PLAYTYPE(int value, String description) {
            this.value = value;
            this.description = description;
        }


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public static PLAYTYPE valueOf(int value){
            for (PLAYTYPE typeEnume:PLAYTYPE.values()){
                if (typeEnume.getValue()==value){
                    return typeEnume;
                }
            }
            return null;
        }
    }

    private int status;
    private String anchorAccount;

    public PlayEvent(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PlayEvent(int status, String anchorAccount) {
        this.status = status;
        this.anchorAccount = anchorAccount;
    }

    public String getAnchorAccount() {
        return anchorAccount;
    }

    public void setAnchorAccount(String anchorAccount) {
        this.anchorAccount = anchorAccount;
    }
}
