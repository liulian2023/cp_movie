package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage;

import android.os.Parcel;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

            /*
                自定义更新计划消息
            */


/*
添加注解
 value: 即 ObjectName,是自定义消息的唯一标识(根据value判断发送和接受到的消息通过哪种方式解析)
 flag:用来定义消息的可操作状态 :
      MessageTag.NONE	为空值，不表示任何意义，发送的自定义消息不会在会话页面和会话列表中展示。
      MessageTag.ISCOUNTED	表示客户端收到消息后，要进行未读消息计数（未读消息数增加 1），所有内容型消息都应该设置此值。非内容类消息暂不支持消息计数。
      MessageTag.ISPERSISTED	表示客户端收到消息后，要进行存储，并在之后可以通过接口查询，存储后会在会话界面中显示。
 */
@MessageTag(value = "s:update",flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)


public class RongUpdateMessage extends MessageContent {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 实现 encode() 方法，该方法的功能是将消息属性封装成 json 串，再将 json 串转成 byte 数组，该方法会在发消息时调用
     * @return 消息属性的byte[]数组
     */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("content",content );

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


        ParcelUtils.writeToParcel(dest, content);//该类为工具类，对消息中属性进行序列化

    }

    /**
     * 覆盖父类的 MessageContent(byte[] data) 构造方法，该方法将对收到的消息进行解析，先由 byte 转成 json 字符串，再将 json 中内容取出赋值给消息属性。
     * @param data 接收到的消息byte[]数组
     */
    public RongUpdateMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
        }
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            //赋值给RongTextMessage对应的属性
            if (jsonObj.has("content")){
                content = jsonObj.optString("content");
            }
        } catch (JSONException e) {

        }

    }

    // Parcelable中 给消息赋值。
    public RongUpdateMessage(Parcel in) {
        content= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性



        //这里可继续增加你消息的属性

    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<RongUpdateMessage> CREATOR = new Creator<RongUpdateMessage>() {

        @Override
        public RongUpdateMessage createFromParcel(Parcel source) {
            return new RongUpdateMessage(source);
        }

        @Override
        public RongUpdateMessage[] newArray(int size) {
            return new RongUpdateMessage[size];
        }
    };

    @Override
    public String toString() {
        return "RongUpdateMessage{" +
                "content='" + content + '\'' +
                '}';
    }

    public RongUpdateMessage(String content) {
        this.content = content;
    }
}
