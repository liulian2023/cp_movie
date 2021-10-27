package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message;

import android.os.Parcel;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

            /*
                自定义分享投注消息
            */


/*
添加注解
 value: 即 ObjectName,是自定义消息的唯一标识(根据value判断发送和接受到的消息通过哪种方式解析)
      flag:用来定义消息的可操作状态 :
      MessageTag.NONE	为空值，不表示任何意义，发送的自定义消息不会在会话页面和会话列表中展示。
      MessageTag.ISCOUNTED	表示客户端收到消息后，要进行未读消息计数（未读消息数增加 1），所有内容型消息都应该设置此值。非内容类消息暂不支持消息计数。
      MessageTag.ISPERSISTED	表示客户端收到消息后，要进行存储，并在之后可以通过接口查询，存储后会在会话界面中显示。
 */
@MessageTag(value = "s:bet",flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)


public class LiveShareBetMessage extends MessageContent {
    private String userId;
    private String nickname =""; //昵称
    private String pointGrade;//会员等级
    private String type_id ="";//彩票type_id
    private String rule_id ="";//投注id
    private String lotteryqishu;//彩票期数
    private String game ="";//彩票game
    private String typename ="";//彩票typename
    private String lotmoney ="";//下注金额
    private String groupname ="";//组名
    private String name ="";//投注内容
    private String zkCode = SharePreferencesUtil.getString(MyApplication.getInstance(),"zkCode","");//平台标识
    private String userInfoJson; //昵称




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
            jsonObj.put("type_id",type_id );
            jsonObj.put("rule_id",rule_id );
            jsonObj.put("lotteryqishu",lotteryqishu );
            jsonObj.put("game",game );
            jsonObj.put("typename",typename );
            jsonObj.put("lotmoney", lotmoney);
            jsonObj.put("groupname",groupname );
            jsonObj.put("name", name);
            jsonObj.put("userId", userId);
            jsonObj.put("zkCode", zkCode);
            jsonObj.put("userInfoJson", userInfoJson);
        } catch (JSONException e) {
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
        ParcelUtils.writeToParcel(dest, type_id);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, rule_id);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, lotteryqishu);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, game);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, typename);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, lotmoney);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, groupname);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, name);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, userId);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, zkCode);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, userInfoJson);//该类为工具类，对消息中属性进行序列化
    }

    /**
     * 覆盖父类的 MessageContent(byte[] data) 构造方法，该方法将对收到的消息进行解析，先由 byte 转成 json 字符串，再将 json 中内容取出赋值给消息属性。
     * @param data 接收到的消息byte[]数组
     */
    public LiveShareBetMessage(byte[] data) {
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
            if(jsonObj.has("type_id")){
                type_id = jsonObj.optString("type_id");
            }
            if(jsonObj.has("rule_id")){
                rule_id = jsonObj.optString("rule_id");
            }
            if(jsonObj.has("lotteryqishu")){
                lotteryqishu = jsonObj.optString("lotteryqishu");
            }
            if(jsonObj.has("game")){
                game = jsonObj.optString("game");
            }
            if(jsonObj.has("typename")){
                typename = jsonObj.optString("typename");
            }
            if(jsonObj.has("lotmoney")){
                lotmoney = jsonObj.optString("lotmoney");
            }
            if(jsonObj.has("groupname")){
                groupname = jsonObj.optString("groupname");
            }
            if(jsonObj.has("name")){
                name = jsonObj.optString("name");
            }
            if(jsonObj.has("userId")){
                userId = jsonObj.optString("userId");
            }
            if(jsonObj.has("zkCode")){
                zkCode = jsonObj.optString("zkCode");
            }
            if(jsonObj.has("userInfoJson")){
                userInfoJson = jsonObj.optString("userInfoJson");
            }

        } catch (JSONException e) {

        }

    }

    // Parcelable中 给消息赋值。
    public LiveShareBetMessage(Parcel in) {
        nickname= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        pointGrade= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        type_id= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        rule_id= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        lotteryqishu= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        game= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        typename= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        lotmoney = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        groupname= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        name = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        userId = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        zkCode = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        userInfoJson = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性



        //这里可继续增加你消息的属性

    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<LiveShareBetMessage> CREATOR = new Creator<LiveShareBetMessage>() {

        @Override
        public LiveShareBetMessage createFromParcel(Parcel source) {
            return new LiveShareBetMessage(source);
        }

        @Override
        public LiveShareBetMessage[] newArray(int size) {
            return new LiveShareBetMessage[size];
        }
    };

    public LiveShareBetMessage(String nickname, String pointGrade, String type_id, String rule_id, String lotteryqishu, String game, String typename, String lotmoney, String groupname, String name, String userId) {
        this.nickname = nickname;
        this.pointGrade = pointGrade;
        this.type_id = type_id;
        this.rule_id = rule_id;
        this.lotteryqishu = lotteryqishu;
        this.game = game;
        this.typename = typename;
        this.lotmoney = lotmoney;
        this.groupname = groupname;
        this.name = name;
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

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getRule_id() {
        return rule_id;
    }

    public void setRule_id(String rule_id) {
        this.rule_id = rule_id;
    }

    public String getLotteryqishu() {
        return lotteryqishu;
    }

    public void setLotteryqishu(String lotteryqishu) {
        this.lotteryqishu = lotteryqishu;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getLotmoney() {
        return lotmoney;
    }

    public void setLotmoney(String lotmoney) {
        this.lotmoney = lotmoney;
    }



    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<LiveShareBetMessage> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "RongShareBetMessage{" +
                "nickname='" + nickname + '\'' +
                ", pointGrade='" + pointGrade + '\'' +
                ", type_id='" + type_id + '\'' +
                ", rule_id='" + rule_id + '\'' +
                ", lotteryqishu='" + lotteryqishu + '\'' +
                ", game='" + game + '\'' +
                ", typename='" + typename + '\'' +
                ", lotmoney='" + lotmoney + '\'' +
                ", groupname='" + groupname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getZkCode() {
        return zkCode;
    }

    public void setZkCode(String zkCode) {
        this.zkCode = zkCode;
    }

    public String getUserInfoJson() {
        return userInfoJson;
    }

    public void setUserInfoJson(String userInfoJson) {
        this.userInfoJson = userInfoJson;
    }
}
