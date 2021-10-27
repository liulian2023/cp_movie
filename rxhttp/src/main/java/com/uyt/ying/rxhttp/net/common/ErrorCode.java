
package com.uyt.ying.rxhttp.net.common;


import com.uyt.ying.rxhttp.R;
import com.uyt.ying.rxhttp.net.utils.AppContextUtils;

import androidx.annotation.StringRes;


public class ErrorCode {

    public static final int SUCCESS = 200;

    public static final int REQUEST_FAILED = 500;

    public static final int TOKEN_EXPIRED = 40101;

    public static final int REFRESH_TOKEN_EXPIRED = 40199;

    public static final int SYSTEM_MAINTAIN = 50001;


    public static String getErrorMessage(int errorCode) {
        return getErrorMessage(errorCode, "");
    }

    public static String getErrorMessage(int errorCode, String errorMsg) {
        String message;
        switch (errorCode) {
            case REQUEST_FAILED:
                message =  errorMsg;
                break;
            case TOKEN_EXPIRED:
                message = getString(R.string.token_expired);
                break;
            case REFRESH_TOKEN_EXPIRED:
                message = getString(R.string.refresh_token_expired);
                break;
            case SYSTEM_MAINTAIN:
                message = getString(R.string.system_maintain);
                break;
            default:
                message = getString(R.string.request_error) + errorCode;
                break;
        }
        return message;
    }

    private static String getString(@StringRes int resId) {
        return AppContextUtils.getContext().getString(resId);
    }
}
