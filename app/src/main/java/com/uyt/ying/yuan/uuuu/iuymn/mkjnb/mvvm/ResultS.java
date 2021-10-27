package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;


import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */

public class ResultS<T> {
    private int code;

    private String msg;

    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }



}
