
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage.RongImageMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage.RongRedMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage.RongShareBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage.RongShareRewardMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage.RongTextMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage.RongVideoMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChatAllMessageMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MyRongMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TouXiangUtil;

import java.text.SimpleDateFormat;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

/**
 * 接受消息的监听()
 */
public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    Context context;

    public MyReceiveMessageListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onReceived(Message message, int i) {
        MessageContent content = message.getContent();
        //消息类型的判断以及数据的添加
        String objectName = message.getObjectName();
        MyRongMessage myRongMessage = new MyRongMessage(objectName, content);
        initSwich(myRongMessage );
        return true;
    }


    private void initSwich(MyRongMessage message) {
        String objectName = message.getObjectName();
        MessageContent content1 = message.getContent();
        if(objectName.equals("s:text")){ //objectName为s:text有两种情况: 1. 自定义文本消息  2.后台发送的自定义消息,包含 更新计划,今日盈亏,投注记录, 但是是通过自定义文本消息的形式发送的,所以需要判断是否是后台传入的数据
            RongTextMessage myTextMag = (RongTextMessage) content1;//强转为自定义的消息类
            String myTextMagContent = myTextMag.getContent();//自定义消息类的content字段
            if (myTextMagContent.contains("isBack")) {//包含isBack字段,说明是后台发送的,(如果有用户发包含isBack的内容,那就....有时间再优化)
                //后台发送的消息
                JSONObject jsonObject = JSONObject.parseObject(myTextMagContent);
                String isBack = jsonObject.getString("isBack");//后台发送的消息类型
                if (isBack.equals("s:record")) {
                    //后台发送的今日盈亏
                    message.setObjectName("s:record");
                    String pointGrade = jsonObject.getString(CommonStr.GRADE);//会员等级
                    String lotteryTotalPrice = jsonObject.getString("lotteryTotalPrice");//中奖金额
                    String profitAndLoss = jsonObject.getString("profitAndLoss");//盈亏金额
                    String bettingPrice = jsonObject.getString("bettingPrice");//投注金额
                    String nickname = jsonObject.getString("nickname");//会员nickname
                    String image = jsonObject.getString("image");//会员nickname
                    String userId = jsonObject.getString("userId");//会员nickname
                    String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);

                    ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                    chatAllMessageMedol.setObjectName("s:record");
                    chatAllMessageMedol.setPointGrade(pointGrade);
                    chatAllMessageMedol.setLotteryTotalPrice(lotteryTotalPrice);
                    chatAllMessageMedol.setProfitAndLoss(profitAndLoss);
                    chatAllMessageMedol.setBettingPrice(bettingPrice);
                    chatAllMessageMedol.setNickname(nickname);
                    chatAllMessageMedol.setImage(otherTouXiang);
                    chatAllMessageMedol.setUserId(userId);
                } else if (isBack.equals("s:shareBet")) {
                    //后台发送的投注记录
                    message.setObjectName("s:shareBet");
                    String rule_id = jsonObject.getString("rule_id");
                    String pointGrade = jsonObject.getString(CommonStr.GRADE);
                    String game = jsonObject.getString("game");
                    String type_id = jsonObject.getString("type_id");
                    String lotmoney = jsonObject.getString("lotmoney");
                    String nickname = jsonObject.getString("nickname");
                    String name = jsonObject.getString("name");
                    String lotteryqishu = jsonObject.getString("lotteryqishu");
                    String groupname = jsonObject.getString("groupname");
                    String typename = jsonObject.getString("typename");
                    String isOfficial = jsonObject.getString("isOfficial");
                    String userId = jsonObject.getString("userId");
                    String image = jsonObject.getString("image");//会员nickname
                    String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);
                    ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                    chatAllMessageMedol.setObjectName("s:shareBet");
                    chatAllMessageMedol.setRule_id(rule_id);
                    chatAllMessageMedol.setPointGrade(pointGrade);
                    chatAllMessageMedol.setGame(game);
                    chatAllMessageMedol.setType_id(type_id);
                    chatAllMessageMedol.setLotmoney(lotmoney);
                    chatAllMessageMedol.setNickname(nickname);
                    chatAllMessageMedol.setName(name);
                    chatAllMessageMedol.setLotteryqishu(lotteryqishu);
                    chatAllMessageMedol.setGroupname(groupname);
                    chatAllMessageMedol.setTypename(typename);
                    chatAllMessageMedol.setIsOfficial(isOfficial);
                    chatAllMessageMedol.setImage(otherTouXiang);
                    chatAllMessageMedol.setUserId(userId);
                } else if (isBack.equals("s:update")) {
                    //后台发的更新计划
                    message.setObjectName("s:update");
                    String content2 = jsonObject.getString("content");

                    ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                    chatAllMessageMedol.setObjectName("s:update");
                    chatAllMessageMedol.setContent(content2);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
                }
            }
            else {
                //自定义文本消息(非后台)
                ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                chatAllMessageMedol.setObjectName("s:text");
                chatAllMessageMedol.setContent(myTextMag.getContent());
                chatAllMessageMedol.setNickname(myTextMag.getNickname());
                chatAllMessageMedol.setPointGrade(myTextMag.getPointGrade());
                String image = myTextMag.getImage();
                String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);
                chatAllMessageMedol.setImage(otherTouXiang);
                chatAllMessageMedol.setUserId(myTextMag.getUserId());
            }
        }else{//其他消息(红包 投注 盈亏)
            if(objectName.equals("s:record")){
                RongShareRewardMessage rongShareRewardMessage=(RongShareRewardMessage)content1;
                ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                chatAllMessageMedol.setObjectName("s:record");
                chatAllMessageMedol.setNickname(rongShareRewardMessage.getNickname());
                chatAllMessageMedol.setPointGrade(rongShareRewardMessage.getPointGrade());
                chatAllMessageMedol.setBettingPrice(rongShareRewardMessage.getBettingPrice());
                chatAllMessageMedol.setLotteryTotalPrice(rongShareRewardMessage.getLotteryTotalPrice());
                chatAllMessageMedol.setProfitAndLoss(rongShareRewardMessage.getProfitAndLoss());
                String image = rongShareRewardMessage.getImage();
                String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);
                chatAllMessageMedol.setImage(otherTouXiang);
                chatAllMessageMedol.setUserId(rongShareRewardMessage.getUserId());
            }else  if(objectName.equals("s:shareBet")){
                RongShareBetMessage rongShareBetMessage=(RongShareBetMessage)content1;
                ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                chatAllMessageMedol.setObjectName("s:shareBet");

                chatAllMessageMedol.setRule_id(rongShareBetMessage.getRule_id());
                chatAllMessageMedol.setPointGrade(rongShareBetMessage.getPointGrade());
                chatAllMessageMedol.setGame(rongShareBetMessage.getGame());
                chatAllMessageMedol.setType_id(rongShareBetMessage.getType_id());
                chatAllMessageMedol.setLotmoney(rongShareBetMessage.getLotmoney());
                chatAllMessageMedol.setNickname(rongShareBetMessage.getNickname());
                chatAllMessageMedol.setName(rongShareBetMessage.getName());
                chatAllMessageMedol.setLotteryqishu(rongShareBetMessage.getLotteryqishu());
                chatAllMessageMedol.setGroupname(rongShareBetMessage.getGroupname());
                chatAllMessageMedol.setTypename(rongShareBetMessage.getTypename());
                chatAllMessageMedol.setIsOfficial(rongShareBetMessage.getIsOfficial());
                String image = rongShareBetMessage.getImage();
                String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);
                chatAllMessageMedol.setImage(otherTouXiang);
                chatAllMessageMedol.setUserId(rongShareBetMessage.getUserId());
            }


            //图片消息
            else if(objectName.equals("s:image")){
                RongImageMessage rongImageMessage=(RongImageMessage)content1;

                ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                chatAllMessageMedol.setObjectName("s:image");
                chatAllMessageMedol.setNickname(rongImageMessage.getNickname());
                chatAllMessageMedol.setPointGrade(rongImageMessage.getPointGrade());
                chatAllMessageMedol.setUserId(rongImageMessage.getUserId());
                String image = rongImageMessage.getImage();
                String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);
                chatAllMessageMedol.setImage(otherTouXiang);
                chatAllMessageMedol.setImageUrl(rongImageMessage.getImageUrl());
            }

            else if(objectName.equals("s:sendRed")) { //红包
                RongRedMessage rongRedMessage=(RongRedMessage)content1;
                ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                chatAllMessageMedol.setObjectName("s:sendRed");
                chatAllMessageMedol.setNickname(rongRedMessage.getNickname());
                chatAllMessageMedol.setPointGrade(rongRedMessage.getPointGrade());
                chatAllMessageMedol.setContent(rongRedMessage.getContent());
                chatAllMessageMedol.setRedId(rongRedMessage.getRedId());
                String image = rongRedMessage.getImage();
                String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);
                chatAllMessageMedol.setImage(otherTouXiang);
                chatAllMessageMedol.setUserId(rongRedMessage.getUserId());
            }
            //小视频
            else if(objectName.equals("s:video")){
                RongVideoMessage rongVideoMessage=( RongVideoMessage )content1;
                ChatAllMessageMedol chatAllMessageMedol = new ChatAllMessageMedol();
                chatAllMessageMedol.setObjectName("s:video");
                chatAllMessageMedol.setNickname(rongVideoMessage.getNickname());
                chatAllMessageMedol.setPointGrade(rongVideoMessage.getPointGrade());
                chatAllMessageMedol.setUserId(rongVideoMessage.getUserId());
                String image = rongVideoMessage.getImage();
                String otherTouXiang = TouXiangUtil.getOtherTouXiang(context, image);
                chatAllMessageMedol.setImage(otherTouXiang);
                chatAllMessageMedol.setImageUrl(rongVideoMessage.getImageUrl());
                chatAllMessageMedol.setVideoUrl(rongVideoMessage.getVideoUrl());
            }

        }
    }
}
