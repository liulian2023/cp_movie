package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage;

import android.os.Parcel;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

            /*
                自定义盈亏消息
            */


/*
添加注解
 value: 即 ObjectName,是自定义消息的唯一标识(根据value判断发送和接受到的消息通过哪种方式解析)
 flag:用来定义消息的可操作状态 :
      MessageTag.NONE	为空值，不表示任何意义，发送的自定义消息不会在会话页面和会话列表中展示。
      MessageTag.ISCOUNTED	表示客户端收到消息后，要进行未读消息计数（未读消息数增加 1），所有内容型消息都应该设置此值。非内容类消息暂不支持消息计数。
      MessageTag.ISPERSISTED	表示客户端收到消息后，要进行存储，并在之后可以通过接口查询，存储后会在会话界面中显示。
 */
@MessageTag(value = "s:record",flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)


public class RongShareRewardMessage extends MessageContent implements Serializable {

    private String nickname; //昵称
    private String pointGrade;//会员等级
    private String image;//图片url
    private String bettingPrice;//投注金额
    private String lotteryTotalPrice;//中奖金额
    private String profitAndLoss;//盈亏
    private String userId;//盈亏

    /**
     * 实现 encode() 方法，该方法的功能是将消息属性封装成 json 串，再将 json 串转成 byte 数组，该方法会在发消息时调用
     * @return 消息属性的byte[]数组
     */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {


            jsonObj.put("nickname",nickname );
            jsonObj.put(CommonStr.GRADE,pointGrade );
            jsonObj.put("image",image );
            jsonObj.put("bettingPrice",bettingPrice );
            jsonObj.put("lotteryTotalPrice",lotteryTotalPrice );
            jsonObj.put("profitAndLoss",profitAndLoss );
            jsonObj.put("userId", userId);

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


        ParcelUtils.writeToParcel(dest, nickname);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, pointGrade);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, image);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, bettingPrice);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, lotteryTotalPrice);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, profitAndLoss);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, userId);//该类为工具类，对消息中属性进行序列化
    }

    /**
     * 覆盖父类的 MessageContent(byte[] data) 构造方法，该方法将对收到的消息进行解析，先由 byte 转成 json 字符串，再将 json 中内容取出赋值给消息属性。
     * @param data 接收到的消息byte[]数组
     */
    public RongShareRewardMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
        }
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            //赋值给RongTextMessage对应的属性
            if (jsonObj.has("nickname")){
                nickname = jsonObj.optString("nickname");
            }if(jsonObj.has(CommonStr.GRADE)){
                pointGrade = jsonObj.optString(CommonStr.GRADE);
            }
            if(jsonObj.has("image")){
                image = jsonObj.optString("image");
            }

            if(jsonObj.has("bettingPrice")){
                bettingPrice = jsonObj.optString("bettingPrice");
            }
            if(jsonObj.has("lotteryTotalPrice")){
                lotteryTotalPrice = jsonObj.optString("lotteryTotalPrice");
            }
            if(jsonObj.has("profitAndLoss")){
                profitAndLoss = jsonObj.optString("profitAndLoss");
            }
            if(jsonObj.has("userId")){
                userId = jsonObj.optString("userId");
            }

        } catch (JSONException e) {

        }

    }

    // Parcelable中 给消息赋值。
    public RongShareRewardMessage(Parcel in) {
        nickname= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        pointGrade= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        image= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        bettingPrice= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        lotteryTotalPrice= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        profitAndLoss= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        userId = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性



        //这里可继续增加你消息的属性

    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<RongShareRewardMessage> CREATOR = new Creator<RongShareRewardMessage>() {

        @Override
        public RongShareRewardMessage createFromParcel(Parcel source) {
            return new RongShareRewardMessage(source);
        }

        @Override
        public RongShareRewardMessage[] newArray(int size) {
            return new RongShareRewardMessage[size];
        }
    };

    @Override
    public String toString() {
        return "RongShareRewardMessage{" +
                "nickname='" + nickname + '\'' +
                ", pointGrade='" + pointGrade + '\'' +
                ", image='" + image + '\'' +
                ", bettingPrice='" + bettingPrice + '\'' +
                ", lotteryTotalPrice='" + lotteryTotalPrice + '\'' +
                ", profitAndLoss='" + profitAndLoss + '\'' +
                '}';
    }

    public RongShareRewardMessage(String nickname, String pointGrade, String image, String bettingPrice, String lotteryTotalPrice, String profitAndLoss,String userId) {
        this.nickname = nickname;
        this.pointGrade = pointGrade;
        this.image = image;
        this.bettingPrice = bettingPrice;
        this.lotteryTotalPrice = lotteryTotalPrice;
        this.profitAndLoss = profitAndLoss;
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPointGrade() {
        return pointGrade;
    }

    public void setPointGrade(String pointGrade) {
        this.pointGrade = pointGrade;
    }

    public String getBettingPrice() {
        return bettingPrice;
    }

    public void setBettingPrice(String bettingPrice) {
        this.bettingPrice = bettingPrice;
    }

    public String getLotteryTotalPrice() {
        return lotteryTotalPrice;
    }

    public void setLotteryTotalPrice(String lotteryTotalPrice) {
        this.lotteryTotalPrice = lotteryTotalPrice;
    }

    public String getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(String profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    public static Creator<RongShareRewardMessage> getCREATOR() {
        return CREATOR;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
