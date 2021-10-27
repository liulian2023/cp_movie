package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message;

import android.os.Parcel;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
  /*
                自定义礼物消息
            */


/*
添加注解
 value: 即 ObjectName,是自定义消息的唯一标识(根据value判断发送和接受到的消息通过哪种方式解析)
 flag:用来定义消息的可操作状态 :
      MessageTag.NONE	为空值，不表示任何意义，发送的自定义消息不会在会话页面和会话列表中展示。
      MessageTag.ISCOUNTED	表示客户端收到消息后，要进行未读消息计数（未读消息数增加 1），所有内容型消息都应该设置此值。非内容类消息暂不支持消息计数。
      MessageTag.ISPERSISTED	表示客户端收到消息后，要进行存储，并在之后可以通过接口查询，存储后会在会话界面中显示。
 */
@MessageTag(value = "s:gift",flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)

public class LiveGiftMessage extends MessageContent {
    private String userInfoJson; //用户信息
    private String giftId;//礼物id
    private String giftName; //礼物名
    private String giftNum;//礼物个数
    private String giftUrl;//礼物特效地址
    private String giftIcon;//礼物略缩图
    private String giftPrice;//礼物略缩图
    private String zkCode = SharePreferencesUtil.getString(MyApplication.getInstance(),"zkCode","");//平台标识
    private String gear = "0";
    private String gearTime = "0";
    public String getGear() {
        return Utils.isInt(gear)?gear:"1";
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getGearTime() {
        return Utils.isInt(gearTime)?gearTime:"1";
    }

    public void setGearTime(String gearTime) {
        this.gearTime = gearTime;
    }
    public String getZkCode() {
        return zkCode;
    }

    public void setZkCode(String zkCode) {
        this.zkCode = zkCode;
    }

    public String getGiftUrl() {
        return giftUrl;
    }

    public void setGiftUrl(String giftUrl) {
        this.giftUrl = giftUrl;
    }

    public String getGiftIcon() {
        return giftIcon;
    }

    public void setGiftIcon(String giftIcon) {
        this.giftIcon = giftIcon;
    }

    public String getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(String giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum;
    }

    public LiveGiftMessage(String userInfoJson, String giftId, String giftName, String giftNum, String giftUrl, String giftIcon,String giftPrice,String gear,String gearTime) {
        this.userInfoJson = userInfoJson;
        this.giftId = giftId;
        this.giftName = giftName;
        this.giftNum = giftNum;
        this.giftUrl = giftUrl;
        this.giftIcon = giftIcon;
        this.giftPrice = giftPrice;
        this.gear = gear;
        this.gearTime = gearTime;
    }

    /**
     * 实现 encode() 方法，该方法的功能是将消息属性封装成 json 串，再将 json 串转成 byte 数组，该方法会在发消息时调用
     * @return 消息属性的byte[]数组
     */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("userInfoJson",userInfoJson );
            jsonObj.put("giftId",giftId );
            jsonObj.put("giftName",giftName );
            jsonObj.put("giftNum",giftNum );
            jsonObj.put("giftUrl",giftUrl );
            jsonObj.put("giftIcon",giftIcon );
            jsonObj.put("giftPrice",giftPrice );
            jsonObj.put("zkCode",zkCode );
            jsonObj.put("gear",gear );
            jsonObj.put("gearTime",gearTime );
        } catch (JSONException e) {
            Utils.logE("JSONException", e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 描述了包含在 Parcelable 对象排列信息中的特殊对象的类型。
     *
     * @return 一个标志位，表明Parcelable对象特殊对象类型集合的排列。
     */
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * 将类的数据写入外部提供的 Parcel 中。
     *
     * @param dest  对象被写入的 Parcel。
     * @param flags 对象如何被写入的附加标志。
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, userInfoJson);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, giftId);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, giftName);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, giftNum);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, giftUrl);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, giftIcon);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, giftPrice);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, zkCode);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, gear);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, gearTime);//该类为工具类，对消息中属性进行序列
    }

    /**
     * 覆盖父类的 MessageContent(byte[] data) 构造方法，该方法将对收到的消息进行解析，先由 byte 转成 json 字符串，再将 json 中内容取出赋值给消息属性。
     * @param data 接收到的消息byte[]数组
     */
    public LiveGiftMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            //赋值给RongTextMessage对应的属性
            if (jsonObj.has("userInfoJson")){
                userInfoJson = jsonObj.optString("userInfoJson");
            }if(jsonObj.has("giftId")){
                giftId = jsonObj.optString("giftId");
            }
            if(jsonObj.has("giftName")){
                giftName = jsonObj.optString("giftName");
            }
            if(jsonObj.has("giftNum")){
                giftNum = jsonObj.optString("giftNum");
            }
            if(jsonObj.has("giftUrl")){
                giftUrl = jsonObj.optString("giftUrl");
            }
            if(jsonObj.has("giftIcon")){
                giftIcon = jsonObj.optString("giftIcon");
            }
            if(jsonObj.has("giftPrice")){
                giftPrice = jsonObj.optString("giftPrice");
            }
            if(jsonObj.has("zkCode")){
                zkCode = jsonObj.optString("zkCode");
            }
            if(jsonObj.has("gear")){
                gear = jsonObj.optString("gear");
            }
            if(jsonObj.has("gearTime")){
                gearTime = jsonObj.optString("gearTime");
            }
        } catch (JSONException e) {

        }

    }

    // Parcelable中 给消息赋值。
    public LiveGiftMessage(Parcel in) {
        userInfoJson= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        giftId= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        giftName= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        giftNum= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        giftUrl= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        giftIcon= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        giftPrice= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        zkCode= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        gear= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        gearTime= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        //这里可继续增加你消息的属性
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<LiveGiftMessage> CREATOR = new Creator<LiveGiftMessage>() {

        @Override
        public LiveGiftMessage createFromParcel(Parcel source) {
            return new LiveGiftMessage(source);
        }

        @Override
        public LiveGiftMessage[] newArray(int size) {
            return new LiveGiftMessage[size];
        }
    };


    public String getUserInfoJson() {
        return userInfoJson;
    }

    public void setUserInfoJson(String userInfoJson) {
        this.userInfoJson = userInfoJson;
    }

    public String getContent() {
        return giftId;
    }

    public void setContent(String giftId) {
        this.giftId = giftId;
    }

}
