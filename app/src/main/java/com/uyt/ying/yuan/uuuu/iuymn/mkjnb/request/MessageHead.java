package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;


import com.alibaba.fastjson.JSONObject;

/**
 * MessageHead.java类文件描述说明.
 * 
 * @author aoki
 * @since JDK 1.6
 * @see
 */
public class MessageHead {
    private String code = WebServiceConstant.CODE_DEFAULT;//操作状态码
    private String info = WebServiceConstant.INFO_DEFAULT;//操作状态描述
    private String timestamp;//请求时间
    private String version;
    private String token;
    private String httpurl;
    private JSONObject data;
    
    public MessageHead(){
    }
    
    public void setMessageHead(String code, String info){
        this.code = code;
        this.info = info;
    }

//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }


    public JSONObject getData() {

        return data==null?new JSONObject():data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getHttpurl() {
		return httpurl;
	}

	public void setHttpurl(String httpurl) {
		this.httpurl = httpurl;
	}

	public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    private boolean isSuccess(){
        return WebServiceConstant.CODE_DEFAULT.equalsIgnoreCase(code);
    }
}
