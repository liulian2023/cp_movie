package com.uyt.ying.rxhttp.net.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 */
public class BaseEntity<T> implements Serializable {
    @SerializedName("status")
    private int errorCode;
    @SerializedName("message")
    private String errorMsg;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
