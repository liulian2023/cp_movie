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
     * ????????????
     */
    public static final int MESSAGE_ARRIVED = 0;
    public static final int MESSAGE_SENT = 1;

    /**
     * ??????????????????
     */
    public static final int MESSAGE_SEND_ERROR = -1;
    /**
     * ?????????????????????
     */
    private static ArrayList<Handler> eventHandlerList = new ArrayList<>();


    public static void initRongYun(String appKey) {
        //????????????????????? (?????????????????????)
       RongIMClient.init(MyApplication.getInstance(), appKey);
        //??????????????????
        RongIMClient.setOnReceiveMessageListener(onReceiveMessageListener);
        //??????????????????
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
                Utils.logE(TAG, "rongLog:  ??????????????????");
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                //????????????????????????????????????????????????
                Utils.logE(TAG, "rongLog:  ??????????????????"+connectionErrorCode.getValue());
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
            //????????????????????????????????????????????????
            }
        });
    }
    public static void connectRongYun(Context context,String token, String categoryId, String area,int pageNo,LiveEntity.AnchorMemberRoomListBean AnchorRoomBean) {

        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Utils.logE(TAG, "rongLog:  ??????????????????");
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
                //????????????????????????????????????????????????
                Utils.logE(TAG, "rongLog:  ??????????????????"+connectionErrorCode.getValue());
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
                //????????????????????????????????????????????????
            }
        });

    }

    /**
     * ??????IMLib ??????????????????
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
     * ?????????????????????
     */
    private static RongIMClient.OnReceiveMessageListener onReceiveMessageListener = new RongIMClient.OnReceiveMessageListener() {
        @Override
        public boolean onReceived(Message message, int i) {
            handleEvent(MESSAGE_ARRIVED, message);
            return false;
        }
    };

    /**
     * ??????IMLib ??????????????????
     *
     * @param handler
     */
    public static void removeEventHandler(Handler handler) {
        eventHandlerList.remove(handler);
        handler.removeCallbacksAndMessages(null);
    }


    /**
     * ?????????????????????????????????????????????????????? Push ?????????
     */
    public void logout() {
        RongIMClient.getInstance().logout();
    }

    /**
     * ?????????????????????????????????????????????sdk ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param
     * @param defMessageCount ??????????????????????????????????????????
     * @param currentRoomId   ?????????id
     * @param callback        ????????????
     */
    public static void joinChatRoom(int defMessageCount, String currentRoomId, final RongIMClient.OperationCallback callback) {
        RongIMClient.getInstance().joinChatRoom(currentRoomId, defMessageCount, callback);
    }

    /**
     * ??????????????????????????????????????????
     */
    public static void quitChatRoom(String currentRoomId, final RongIMClient.OperationCallback callback) {
        RongIMClient.getInstance().quitChatRoom(currentRoomId, callback);
    }

    /**
     * ?????????????????????????????????
     * </p>
     * <strong>?????????</strong>????????????????????????????????????????????????handler???????????????
     *
     * @param msgContent ????????????
     */
    public static void sendMessage(String currentRoomId, final MessageContent msgContent) {

        Message msg = Message.obtain(currentRoomId, Conversation.ConversationType.CHATROOM, msgContent);
        //????????????????????????id
        if (StringMyUtil.isEmptyString(currentRoomId)) {
            ToastUtil.showToast(Utils.getString(R.string.???????????????????????????????????????));
            return;
        }
        RongIMClient.getInstance().sendMessage(msg, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
            }

            @Override
            public void onSuccess(Message message) {
                //????????????????????????????????????, ???????????????????????????????????????????????????????????????
                if(msgContent instanceof LiveTextMessage){
                    EditPanel.lastSendTime = new Date().getTime();
                }
                handleEvent(MESSAGE_SENT, message);
                Utils.logE(TAG, "rongLog ??????????????????  id="+ currentRoomId);
              if(message.getContent() instanceof  LiveExitAndJoinMessage){
                  LiveExitAndJoinMessage liveExitAndJoinMessage = (LiveExitAndJoinMessage) message.getContent();
                  String status = liveExitAndJoinMessage.getStatus();
                  if(status.equals("1")){

                      RongLibUtils.quitChatRoom(currentRoomId, new RongIMClient.OperationCallback() {
                          @Override
                          public void onSuccess() {
                              Utils.logE(TAG, "rongLog onSuccess:  ????????????????????? + roomId:" + currentRoomId);
                          }

                          @Override
                          public void onError(RongIMClient.ErrorCode errorCode) {
                              Utils.logE(TAG,"rongLog  onError:  ????????????????????? + roomId: " + currentRoomId);
                          }
                      });
                  }
              }

            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                handleEvent(MESSAGE_SEND_ERROR, errorCode.getValue(), 0, message);

                Utils.logE(TAG, "rongLog ??????????????????   id ="+ currentRoomId + "  code=  " + errorCode.getValue());
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
        userInfoMap.put("managerType",managerType);  //  managerType 1??????  2.??????  3 ??????
        String userInfoJson = JSONObject.toJSONString(userInfoMap);
        return userInfoJson;
    }

    private static void setEquipmentInfo(HashMap<String, Object> userInfoMap, UsingEquipmentEntity usingEquipmentEntity) {
        if(usingEquipmentEntity!=null){
            List<UsingEquipmentEntity.MedalInfoType1Bean> medalInfoType_1 = usingEquipmentEntity.getMedalInfoType_1();
            if(medalInfoType_1!=null&&medalInfoType_1.size()!=0){
                UsingEquipmentEntity.MedalInfoType1Bean medalInfoType1Bean = medalInfoType_1.get(0);
                userInfoMap.put("medalIcon", Utils.checkImageUrl(medalInfoType1Bean.getImage()));//??????
            }else {
                userInfoMap.put("medalIcon", "");//??????
            }
            List<UsingEquipmentEntity.MedalInfoType0Bean> medalInfoType_0 = usingEquipmentEntity.getMedalInfoType_0();
            if(medalInfoType_0!=null&&medalInfoType_0.size()!=0){
                UsingEquipmentEntity.MedalInfoType0Bean medalInfoType0Bean = medalInfoType_0.get(0);
                userInfoMap.put("mountSVGA", Utils.checkImageUrl(medalInfoType0Bean.getTxImage()));//  ??????????????????
                userInfoMap.put("mountName",medalInfoType0Bean.getName());//?????????
            }else {
                userInfoMap.put("mountSVGA", "");//??????
                userInfoMap.put("mountName","");//?????????
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
                userInfoMap.put("titleIcon", titleIcon);//?????????
            }else {
                userInfoMap.put("titleIcon", "");//?????????
            }
            UsingEquipmentEntity.SysGradeBean sysGrade = usingEquipmentEntity.getSysGrade();
            if(sysGrade!=null){
                String image = sysGrade.getImage();
                if(StringMyUtil.isEmptyString(image)){
                    userInfoMap.put("levelIcon","");//??????icon
                }else {
                    userInfoMap.put("levelIcon",Utils.checkImageUrl(image));//??????icon
                }
                String txImage = sysGrade.getTxImage();
                if(StringMyUtil.isEmptyString(txImage)){

                    userInfoMap.put("levelSVGA", "");//????????????
                }else {
                    userInfoMap.put("levelSVGA", Utils.checkImageUrl(txImage));//????????????
                }
            }else {
                userInfoMap.put("levelIcon", "");//??????icon
                userInfoMap.put("levelSVGA", "");//????????????
            }
        }else {
            userInfoMap.put("levelIcon", "");//??????icon
            userInfoMap.put("levelSVGA", "");//????????????
            userInfoMap.put("titleIcon", "");//?????????
            userInfoMap.put("mountSVGA", "");//??????
            userInfoMap.put("medalIcon", "");//??????
        }
    }
}
