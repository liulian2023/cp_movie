package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.EditPanel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.CloseLiveMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.ForbiddenMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveExitAndJoinMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveFollowMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveGiftMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveNormalRedMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveRedMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveRewardMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveShareBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveSystemBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveTextMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.RoomManageMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.SwichMoneyMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveSystemMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity.ConnectionStatusListene;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManagerTypeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UsingEquipmentEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const.ANCHOR_ROOM_BEAN;

public class RongLibUtils {
    public static String TAG = "RongLibUtils";
    /**
     * 事件代码
     */
    public static final int MESSAGE_ARRIVED = 0;
    public static final int MESSAGE_SENT = 1;

    /**
     * 事件错误代码
     */
    public static final int MESSAGE_SEND_ERROR = -1;
    /**
     * 事件监听者列表
     */
    private static ArrayList<Handler> eventHandlerList = new ArrayList<>();


    public static void initRongYun(String appKey) {
        //融云全局初始化 (只需初始化一次)
       RongIMClient.init(MyApplication.getInstance(), appKey);
        //消息接受监听
        RongIMClient.setOnReceiveMessageListener(onReceiveMessageListener);
        //连接状态监听
        RongIMClient.setConnectionStatusListener(new ConnectionStatusListene(MyApplication.getInstance()));
        try {
            RongIMClient.registerMessageType(LiveTextMessage.class);
            RongIMClient.registerMessageType(LiveShareBetMessage.class);
            RongIMClient.registerMessageType(LiveRedMessage.class);
            RongIMClient.registerMessageType(LiveGiftMessage.class);
            RongIMClient.registerMessageType(LiveExitAndJoinMessage.class);
            RongIMClient.registerMessageType(LiveFollowMessage.class);
            RongIMClient.registerMessageType(ForbiddenMessage.class);
            RongIMClient.registerMessageType(RoomManageMessage.class);
            RongIMClient.registerMessageType(SwichMoneyMessage.class);
            RongIMClient.registerMessageType(CloseLiveMessage.class);
            RongIMClient.registerMessageType(LiveSystemMessage.class);
            RongIMClient.registerMessageType(LiveNormalRedMessage.class);
            RongIMClient.registerMessageType(LiveSystemBetMessage.class);
            RongIMClient.registerMessageType(LiveRewardMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connectRongYun(String token) {

        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Utils.logE(TAG, "rongLog:  连接融云成功");
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                //消息数据库打开，可以进入到主页面
                Utils.logE(TAG, "rongLog:  连接融云失败"+connectionErrorCode.getValue());
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
            //消息数据库打开，可以进入到主页面
            }
        });
    }
    public static void connectRongYun(Context context,String token, String categoryId, String area,int pageNo,LiveEntity.AnchorMemberRoomListBean AnchorRoomBean) {

        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Utils.logE(TAG, "rongLog:  连接融云成功");
                ActivityUtil.getInstance().finishActivity(LiveActivity.class);
                Intent intent = new Intent();
                intent.setClass(context, LiveActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ANCHOR_ROOM_BEAN,AnchorRoomBean);
                bundle.putString("categoryId",categoryId);
                bundle.putString("area",area);
                bundle.putInt("pageNo",pageNo);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                //消息数据库打开，可以进入到主页面
                Utils.logE(TAG, "rongLog:  连接融云失败"+connectionErrorCode.getValue());
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
                //消息数据库打开，可以进入到主页面
            }
        });

    }

    /**
     * 添加IMLib 事件接收者。
     *
     * @param handler
     */
    public static void addEventHandler(Handler handler) {
        if(handler!=null){
            if (!eventHandlerList.contains(handler)) {
                eventHandlerList.add(handler);
            }
        }
    }

    /**
     * 消息接收监听者
     */
    private static RongIMClient.OnReceiveMessageListener onReceiveMessageListener = new RongIMClient.OnReceiveMessageListener() {
        @Override
        public boolean onReceived(Message message, int i) {
            handleEvent(MESSAGE_ARRIVED, message);
            return false;
        }
    };

    /**
     * 移除IMLib 事件接收者。
     *
     * @param handler
     */
    public static void removeEventHandler(Handler handler) {
        eventHandlerList.remove(handler);
        handler.removeCallbacksAndMessages(null);
    }


    /**
     * 断开与融云服务器的连接，并且不再接收 Push 消息。
     */
    public void logout() {
        RongIMClient.getInstance().logout();
    }

    /**
     * 加入聊天室。如果聊天室不存在，sdk 会创建聊天室并加入，如果已存在，则直接加入。加入聊天室时，可以选择拉取聊天室消息数目。
     *
     * @param
     * @param defMessageCount 默认开始时拉取的历史记录条数
     * @param currentRoomId   聊天室id
     * @param callback        状态回调
     */
    public static void joinChatRoom(int defMessageCount, String currentRoomId, final RongIMClient.OperationCallback callback) {
        RongIMClient.getInstance().joinChatRoom(currentRoomId, defMessageCount, callback);
    }

    /**
     * 退出聊天室，不在接收其消息。
     */
    public static void quitChatRoom(String currentRoomId, final RongIMClient.OperationCallback callback) {
        RongIMClient.getInstance().quitChatRoom(currentRoomId, callback);
    }

    /**
     * 向当前聊天室发送消息。
     * </p>
     * <strong>注意：</strong>此函数为异步函数，发送结果将通过handler事件返回。
     *
     * @param msgContent 消息对象
     */
    public static void sendMessage(String currentRoomId, final MessageContent msgContent) {

        Message msg = Message.obtain(currentRoomId, Conversation.ConversationType.CHATROOM, msgContent);
        //没有获取到聊天室id
        if (StringMyUtil.isEmptyString(currentRoomId)) {
            ToastUtil.showToast(Utils.getString(R.string.直播间初始化失败请退出重试));
            return;
        }
        RongIMClient.getInstance().sendMessage(msg, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
            }

            @Override
            public void onSuccess(Message message) {
                //发送弹幕消息记录当前时间, 下次发送时需要判断时候超过发言间隔时间限制
                if(msgContent instanceof LiveTextMessage){
                    EditPanel.lastSendTime = new Date().getTime();
                }
                handleEvent(MESSAGE_SENT, message);
                Utils.logE(TAG, "rongLog 消息发送成功  id="+ currentRoomId);
              if(message.getContent() instanceof  LiveExitAndJoinMessage){
                  LiveExitAndJoinMessage liveExitAndJoinMessage = (LiveExitAndJoinMessage) message.getContent();
                  String status = liveExitAndJoinMessage.getStatus();
                  if(status.equals("1")){

                      RongLibUtils.quitChatRoom(currentRoomId, new RongIMClient.OperationCallback() {
                          @Override
                          public void onSuccess() {
                              Utils.logE(TAG, "rongLog onSuccess:  退出聊天室成功 + roomId:" + currentRoomId);
                          }

                          @Override
                          public void onError(RongIMClient.ErrorCode errorCode) {
                              Utils.logE(TAG,"rongLog  onError:  退出聊天室失败 + roomId: " + currentRoomId);
                          }
                      });
                  }
              }

            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                handleEvent(MESSAGE_SEND_ERROR, errorCode.getValue(), 0, message);

                Utils.logE(TAG, "rongLog 消息发送失败   id ="+ currentRoomId + "  code=  " + errorCode.getValue());
            }
        });
    }

    private static void handleEvent(int what, Object obj) {
        for (int i = 0; i < eventHandlerList.size(); i++) {
            Handler handler = eventHandlerList.get(i);
            android.os.Message m = android.os.Message.obtain();
            m.what = what;
            m.obj = obj;
            if(handler!=null){
                handler.sendMessage(m);
            }
        }
    }

    private static void handleEvent(int what, int arg1, int arg2, Object obj) {
        for (int i = 0; i < eventHandlerList.size(); i++) {
            Handler handler = eventHandlerList.get(i);
            android.os.Message m = android.os.Message.obtain();
            m.what = what;
            m.arg1 = arg1;
            m.arg2 = arg2;
            m.obj = obj;
            handler.sendMessage(m);
        }
    }

    public static String setUserInfo(Context context, ManagerTypeEntity managerTypeEntity) {
        String userNickName = SharePreferencesUtil.getString(context, "userNickName", "");
        int pointGrade = SharePreferencesUtil.getInt(context, CommonStr.GRADE, 0);
        long user_id = SharePreferencesUtil.getLong(context, "user_id", 0l);
        HashMap<String, Object> userInfoMap = new HashMap<>();
        UsingEquipmentEntity usingEquipmentEntity = Utils.getUsingEquipmentEntity();

        setEquipmentInfo(userInfoMap, usingEquipmentEntity);
        userInfoMap.put("userId", user_id);
        userInfoMap.put("name", userNickName);
        userInfoMap.put("level",pointGrade);
        userInfoMap.put("portrait", Utils.checkImageUrl(SharePreferencesUtil.getString(MyApplication.getInstance(),"image","")));
        userInfoMap.put("platUserId",userNickName);
        String managerType="";
        if(null == managerTypeEntity){
            managerType="0";
        }else {
            String isSuperRoomManager = managerTypeEntity.getIsSuperRoomManager();
            if(isSuperRoomManager.equals("1")){
                managerType="2";
            }else {
                String isRoomManager = managerTypeEntity.getIsRoomManager();
                if(isRoomManager.equals("1")){
                    managerType="3";
                }else {
                    managerType="0";
                }
            }
        }
        userInfoMap.put("managerType",managerType);  //  managerType 1主播  2.超管  3 房管
        String userInfoJson = JSONObject.toJSONString(userInfoMap);
        return userInfoJson;
    }

    private static void setEquipmentInfo(HashMap<String, Object> userInfoMap, UsingEquipmentEntity usingEquipmentEntity) {
        if(usingEquipmentEntity!=null){
            List<UsingEquipmentEntity.MedalInfoType1Bean> medalInfoType_1 = usingEquipmentEntity.getMedalInfoType_1();
            if(medalInfoType_1!=null&&medalInfoType_1.size()!=0){
                UsingEquipmentEntity.MedalInfoType1Bean medalInfoType1Bean = medalInfoType_1.get(0);
                userInfoMap.put("medalIcon", Utils.checkImageUrl(medalInfoType1Bean.getImage()));//勋章
            }else {
                userInfoMap.put("medalIcon", "");//勋章
            }
            List<UsingEquipmentEntity.MedalInfoType0Bean> medalInfoType_0 = usingEquipmentEntity.getMedalInfoType_0();
            if(medalInfoType_0!=null&&medalInfoType_0.size()!=0){
                UsingEquipmentEntity.MedalInfoType0Bean medalInfoType0Bean = medalInfoType_0.get(0);
                userInfoMap.put("mountSVGA", Utils.checkImageUrl(medalInfoType0Bean.getTxImage()));//  坐骑特效地址
                userInfoMap.put("mountName",medalInfoType0Bean.getName());//坐骑名
            }else {
                userInfoMap.put("mountSVGA", "");//坐骑
                userInfoMap.put("mountName","");//坐骑名
            }
            List<UsingEquipmentEntity.MedalInfoType2Bean> medalInfoType_2 = usingEquipmentEntity.getMedalInfoType_2();
            String titleIcon="";
            if(medalInfoType_2!=null&&medalInfoType_2.size()!=0){
                for (int i = 0; i < medalInfoType_2.size(); i++) {
                    UsingEquipmentEntity.MedalInfoType2Bean medalInfoType2Bean = medalInfoType_2.get(i);
                    String image = medalInfoType2Bean.getImage();
                    image= Utils.checkImageUrl(image);
                    titleIcon+=image+",";
                }
                titleIcon = titleIcon.substring(0,titleIcon.length()-1);
                userInfoMap.put("titleIcon", titleIcon);//称号牌
            }else {
                userInfoMap.put("titleIcon", "");//称号牌
            }
            UsingEquipmentEntity.SysGradeBean sysGrade = usingEquipmentEntity.getSysGrade();
            if(sysGrade!=null){
                String image = sysGrade.getImage();
                if(StringMyUtil.isEmptyString(image)){
                    userInfoMap.put("levelIcon","");//等级icon
                }else {
                    userInfoMap.put("levelIcon",Utils.checkImageUrl(image));//等级icon
                }
                String txImage = sysGrade.getTxImage();
                if(StringMyUtil.isEmptyString(txImage)){

                    userInfoMap.put("levelSVGA", "");//等级特效
                }else {
                    userInfoMap.put("levelSVGA", Utils.checkImageUrl(txImage));//等级特效
                }
            }else {
                userInfoMap.put("levelIcon", "");//等级icon
                userInfoMap.put("levelSVGA", "");//等级特效
            }
        }else {
            userInfoMap.put("levelIcon", "");//等级icon
            userInfoMap.put("levelSVGA", "");//等级特效
            userInfoMap.put("titleIcon", "");//称号牌
            userInfoMap.put("mountSVGA", "");//坐骑
            userInfoMap.put("medalIcon", "");//勋章
        }
    }
}
