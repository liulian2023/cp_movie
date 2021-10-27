package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.PaihangActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RankTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.VisitorSafeCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import io.rong.imlib.RongIMClient;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const.ANCHOR_ROOM_BEAN;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const.RANKTYPE;

public class RouteUtils {

    /**
     *跳转直播间
     * @param context
     * @param AnchorRoomBean 点击的直播间dataBean
     * @param categoryId 分类id(可为空)
     * @param pageNo 当前点击的数据处于第几页(可为空)
     */
    public static void start2LiveActivity(Context context,LiveEntity.AnchorMemberRoomListBean AnchorRoomBean,String categoryId,String area,int pageNo){
        RongIMClient.ConnectionStatusListener.ConnectionStatus rongConnection = Utils.getRongConnection();
        if(rongConnection == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED){
            ActivityUtil.getInstance().finishActivity(LiveActivity.class);
            Intent intent = new Intent();
            intent.setClass(context, LiveActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("categoryId",categoryId);
            bundle.putString("area",area);
            bundle.putInt("pageNo",pageNo);
            bundle.putSerializable(ANCHOR_ROOM_BEAN,AnchorRoomBean);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else {
            if(rongConnection ==RongIMClient.ConnectionStatusListener.ConnectionStatus.CONN_USER_BLOCKED){
                ToastUtil.showToast(Utils.getString(R.string.您存在违规行为,已被封禁));
            }else if(rongConnection == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTING){
                ToastUtil.showToast(Utils.getString(R.string.直播间连接中,请稍等));
            }else if(rongConnection == RongIMClient.ConnectionStatusListener.ConnectionStatus.NETWORK_UNAVAILABLE){
                ToastUtil.showToast(Utils.getString(R.string.网络不可用));
            }else if(rongConnection == RongIMClient.ConnectionStatusListener.ConnectionStatus.TOKEN_INCORRECT){
                ToastUtil.showToast(Utils.getString(R.string.直播间token不正确,重新登录或联系客服处理));
            }else if(rongConnection == RongIMClient.ConnectionStatusListener.ConnectionStatus.TIMEOUT){
                connectRong(context, AnchorRoomBean, categoryId, area, pageNo, Utils.getString(R.string.直播间连接超时,正在尝试重新连接,请稍等));
            }else {
                connectRong(context, AnchorRoomBean, categoryId, area, pageNo, Utils.getString(R.string.网络不给力,正在连接直播间,请稍等));
            }
        }

    }

    /**
     * 直播间连接失败.尝试重新连接
     * @param context
     * @param AnchorRoomBean
     * @param categoryId
     * @param area
     * @param pageNo
     * @param tip
     */
    private static void connectRong(Context context, LiveEntity.AnchorMemberRoomListBean AnchorRoomBean, String categoryId, String area, int pageNo, String tip) {
        String token = SharePreferencesUtil.getString(MyApplication.getInstance(), "chatroomToken", "");
        if (StringMyUtil.isNotEmpty(token)) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    String rongYunKey = SharePreferencesUtil.getString(context, "rongYunKey", "");
                    RongLibUtils.initRongYun(rongYunKey);
                    RongLibUtils.connectRongYun(context, token, categoryId, area, pageNo, AnchorRoomBean);
                    ToastUtil.showToast(tip);
                }
            });
        } else {
            ToastUtil.showToast(Utils.getString(R.string.未能成功获取直播间配置信息,请退出APP重试!));
        }
    }

    public static void start2PaihangActivity(Context context, RankTypeEnum rankTypeEnum){
        Intent intent = new Intent();
        intent.setClass(context, PaihangActivity.class);
        intent.putExtra(RANKTYPE,rankTypeEnum);
        context.startActivity(intent);
    }


    //本地分享
    public static void start2Share(Context context,String url) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        //     share_intent.putExtra(Intent.EXTRA_SUBJECT, title);//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, url);//添加分享链接内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, Utils.getString(R.string.选择分享应用));
        context.startActivity(share_intent);
    }

    public static void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            context.startActivity(Intent.createChooser(intent, Utils.getString(R.string.请选择浏览器)));
        } else {
           ToastUtil.showToast(Utils.getString(R.string.链接错误或无浏览器));
        }
    }
    public static void skipVisitorSafeCenter(Context context){
        Intent intent = new Intent(context, VisitorSafeCenterActivity.class);
        intent.putExtra("phone",SharePreferencesUtil.getString(MyApplication.getInstance(),CommonStr.USER_PHONE,""));
        context.startActivity(intent);
    }

}
