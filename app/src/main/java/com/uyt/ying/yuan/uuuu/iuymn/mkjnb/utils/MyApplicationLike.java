/*


package com.cambodia.zhanbang.xunbo.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.request.target.ViewTarget;
import com.cambodia.zhanbang.xunbo.BuildConfig;
import com.cambodia.zhanbang.xunbo.R;
import com.cambodia.zhanbang.xunbo.RongYunMessage.RongImageMessage;
import com.cambodia.zhanbang.xunbo.RongYunMessage.RongRedMessage;
import com.cambodia.zhanbang.xunbo.RongYunMessage.RongShareBetMessage;
import com.cambodia.zhanbang.xunbo.RongYunMessage.RongShareRewardMessage;
import com.cambodia.zhanbang.xunbo.RongYunMessage.LiveTextMessage;
import com.cambodia.zhanbang.xunbo.RongYunMessage.RongVideoMessage;
import com.cambodia.zhanbang.xunbo.activity.MainActivity;
import com.cambodia.zhanbang.xunbo.activity.chatRoomActivity.ConnectionStatusListene;
import com.cambodia.zhanbang.xunbo.request.DockingUtil;
import com.cambodia.zhanbang.xunbo.request.MessageHead;
import com.cambodia.zhanbang.xunbo.request.OkHttp3Utils;
import com.cambodia.zhanbang.xunbo.request.RequestUtil;
import com.cambodia.zhanbang.xunbo.service.AboutPersonService;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import android.support.multidex.MultiDex;

import org.json.JSONException;

import io.rong.imkit.RongIM;
import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.RongIMClient;
import okio.Buffer;

import static android.content.ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN;
import static com.cambodia.zhanbang.xunbo.request.CommonUtil.cerStr;

public class MyApplicationLike extends DefaultApplicationLike {

    private static final String TAG = "OnUILifecycleListener";
    String token;
    // Starts as true in order to be notified on first launch
    private boolean isBackground = true;
    //是否是从webViewActivity界面返回，并且没有浮窗权限
    private static boolean isBackNoPermission;
    private static Context instance;

    public MyApplicationLike(Application application, int tinkerFlags,
                             boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                             long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化hotfix
        initHotFix();
        initUMENG();
        initRongyun();
        //设置全局app时区
        initGlobalTimeZone();
        ViewTarget.setTagId(R.id.tag_glide);//glide给iamgeView设置tag
        listenForForeground();
        listenForScreenTurningOff();
        //每次启动app,覆盖时间差
        SharePreferencesUtil.putLong(getApplication(), "shijiancha", 0l);
    //    OkHttp3Utils.getInstance().setCertificates(new BufferedInputStream(new Buffer().writeUtf8(cerStr).inputStream()));
        instance = getApplication();
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        Beta.installTinker(this);

    }

    //热更新具体初始化回调
    public void initHotFix() {
        */
/*
         * true表示app启动自动初始化升级模块;
         * false不会自动初始化;
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false，
         * 在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
         *//*

        Beta.autoInit = true;
        */
/*
         * true表示初始化时自动检查升级;
         * false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         *//*



        */
/*
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         *//*

        Beta.upgradeCheckPeriod = 60 * 1000;

        */
/*
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         *//*

        Beta.initDelay = 1000;

        Beta.autoCheckUpgrade = true;
        Beta.largeIconId = R.drawable.ic_launch;
        Beta.smallIconId = R.drawable.ic_launch;
        Beta.defaultBannerId = R.drawable.ic_launch;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        // 设置是否开启热更新能力，默认为true
        */
/**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         *//*

        Beta.showInterruptedStrategy = false;

        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        */
/*
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         *//*

        Beta.canShowUpgradeActs.add(MainActivity.class);
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
            }

            @Override
            public void onDownloadSuccess(String msg) {
            }

            @Override
            public void onDownloadFailure(String msg) {

            }

            @Override
            public void onApplySuccess(String msg) {
            }

            @Override
            public void onApplyFailure(String msg) {
            }

            @Override
            public void onPatchRollback() {
            }
        };

        //监听安装包下载状态
        Beta.downloadListener = new DownloadListener() {
            @Override
            public void onReceive(DownloadTask downloadTask) {
                Log.d(TAG, "downloadListener receive apk file");
            }

            @Override
            public void onCompleted(DownloadTask downloadTask) {
                Log.d(TAG, "downloadListener download apk file success");
            }

            @Override
            public void onFailed(DownloadTask downloadTask, int i, String s) {
                Log.d(TAG, "downloadListener download apk file fail");
            }
        };

        //监听APP升级状态
        Beta.upgradeStateListener = new UpgradeStateListener() {
            @Override
            public void onUpgradeFailed(boolean b) {
                Log.d(TAG, "upgradeStateListener upgrade fail");
            }

            @Override
            public void onUpgradeSuccess(boolean b) {
                Log.d(TAG, "upgradeStateListener upgrade success");
            }

            @Override
            public void onUpgradeNoVersion(boolean b) {
                Log.d(TAG, "upgradeStateListener upgrade has no new version");
            }

            @Override
            public void onUpgrading(boolean b) {
                Log.d(TAG, "upgradeStateListener upgrading");
            }

            @Override
            public void onDownloadCompleted(boolean b) {
                Log.d(TAG, "upgradeStateListener download apk file success");
            }
        };


        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(getApplication(), false);
        // 多渠道需求塞入
        //    String channel = WalleChannelReader.getChannel(getApplication());
        Bugly.setAppChannel(getApplication(), BuildConfig.FLAVOR);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), BuildConfig.BUGLY_ID, false);
    }

    //具体初始化友盟统计
    public void initUMENG() {
        //    UMConfigure.init(this,  UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.init(getApplication(), "5d5c5f013fc195c4a600087b", BuildConfig.FLAVOR, UMConfigure.DEVICE_TYPE_PHONE, null);
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        // 打开统计SDK调试模式
        UMConfigure.setLogEnabled(true);
    }

    //融云初始化
    public void initRongyun() {
        //融云IM初始化
        RongIM.init(getApplication());
        try {
            RongIMClient.registerMessageType(LiveTextMessage.class);//自定义文本消息
            RongIMClient.registerMessageType(RongImageMessage.class);//自定义图片消息
            RongIMClient.registerMessageType(RongRedMessage.class);//自定义红包消息
            RongIMClient.registerMessageType(RongShareBetMessage.class);//自定义分享注单消息
            RongIMClient.registerMessageType(RongShareRewardMessage.class);//自定义分享盈亏消息
            RongIMClient.registerMessageType(RongVideoMessage.class);//自定义分享视频消息
        } catch (AnnotationNotFoundException e) {
            e.printStackTrace();
        }
        getChatroomInfo();//获取聊天室信息
        ConnectionStatusListene();//连接状态监听
    }

    */
/*
  连接状态监听
   *//*

    public static void ConnectionStatusListene() {
        RongIMClient.setConnectionStatusListener(new ConnectionStatusListene(getInstance()));
    }

    public void getChatroomInfo() {
        Long user_id = SharePreferencesUtil.getLong(getApplication(), "user_id", 0l);
        if (user_id != 0l) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id", user_id);
            Utils.docking(data, RequestUtil.CHATROOMLIST, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) throws JSONException {
                    JSONObject jsonObject1 = JSONObject.parseObject(content);
                    token = jsonObject1.getString("token");
                    SharePreferencesUtil.putString(getApplication(), "chatroomToken", token);
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    handler.sendMessage(obtain);
                }

                @Override
                public void failed(MessageHead messageHead) {
                }
            });
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    connectRongYun(token);//连接融云服务器
                    break;
            }
        }
    };

    */
/**
     * 设置app内全局时区
     *//*

    public void initGlobalTimeZone() {
        TimeZone chinaTimeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(chinaTimeZone);
    }

    public static void connectRongYun(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            */
/**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期需要向 App Server 重新请求一个新的 Token
             *                            2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             *//*

            @Override
            public void onTokenIncorrect() {
                Utils.logE(TAG, Utils.getString(R.string.token >>>> 错误));
            }

            */
/**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             *//*

            @Override
            public void onSuccess(String userid) {
//
                Utils.logE(TAG, Utils.getString(R.string.连接融云成功 >>>>));

            }

            */
/**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             *//*

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Utils.logE(TAG, Utils.getString(R.string. 连接融云失败, >>>> ) + errorCode.getValue());
            }
        });
    }

    public static void joinChatroom(final String chatroomId) {
//        RongIMClient.getInstance().joinExistChatRoom(chatroomId, 50, new RongIMClient.OperationCallback() {//加入已经存在的聊天室,如果聊天室不存在,则加入失败,errorCode为23410
        RongIMClient.getInstance().joinChatRoom(chatroomId, 50, new RongIMClient.OperationCallback() {//加入聊天室,如果聊天室不存在会自动创建并加入
            @Override
            public void onSuccess() {
                Utils.logE(TAG, Utils.getString(R.string.加入聊天室成功));
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                int value = errorCode.getValue();
                if (value == 23410) {
                } else {
                }
                Utils.logE(TAG, Utils.getString(R.string.加入聊天室失败   ) + errorCode + "  " + value + "chatroomId=  " + chatroomId);
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void listenForForeground() {
        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (isBackground) {
                    isBackground = false;
                    notifyForeground();
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
            //...
        });
    }

    private void listenForScreenTurningOff() {
        IntentFilter screenStateFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        getApplication().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                isBackground = true;
                notifyBackground();
            }
        }, screenStateFilter);
    }

    private void notifyForeground() {
        // This is where you can notify listeners, handle session tracking, etc
        Utils.logE("", "notifyForeground");
        Intent intent = new Intent(getApplication(), AboutPersonService.class);
        getApplication().startService(intent);

    }

    private void notifyBackground() {
        // This is where you can notify listeners, handle session tracking, etc
        Utils.logE("", "notifyBackground");
        Intent intent = new Intent(getApplication(), AboutPersonService.class);
        getApplication().stopService(intent);
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Utils.logE("", "onTerminate");
        super.onTerminate();
        Beta.unInit();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Utils.logE("", "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        Utils.logE("", "onTrimMemory");
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            isBackground = true;
            notifyBackground();
        }
        super.onTrimMemory(level);
    }

    public boolean isBackground() {
        return isBackground;
    }

    public static boolean  isBackNoPermission() {
        return isBackNoPermission;
    }

    public static void setBackNoPermission(boolean backNoPermission) {
        isBackNoPermission = backNoPermission;
    }
}
*/
