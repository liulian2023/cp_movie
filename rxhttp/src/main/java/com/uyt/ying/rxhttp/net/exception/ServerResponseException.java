package com.uyt.ying.rxhttp.net.exception;

import com.uyt.ying.rxhttp.net.common.ErrorCode;

/**
 * created  by xxxx on 2019/11/12.
 */
public class ServerResponseException extends RuntimeException {

    private int errorCode;
    private String errorMsg;

    public ServerResponseException(int errorCode,String errorMsg) {
        super(ErrorCode.getErrorMessage(errorCode, errorMsg), new Throwable(errorMsg));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

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
