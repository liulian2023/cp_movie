package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.HttpResponseCache;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;

import androidx.multidex.MultiDex;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.SplashActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.VisitorSafeCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.uyt.ying.rxhttp.net.model.FlagEventModel;
import com.uyt.ying.rxhttp.net.utils.CommonModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.hjq.toast.ToastUtils;
import com.uyt.ying.rxhttp.net.common.ApiConfig;
import com.uyt.ying.rxhttp.net.common.RxLibConstants;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity.ConnectionStatusListene;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.AppUpdateModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import com.cf.msc.sdk.AppVest;
import com.cf.msc.sdk.SecurityConnection;
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig;
import com.cretin.www.cretinautoupdatelibrary.model.UpdateConfig;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.cretin.www.cretinautoupdatelibrary.utils.SSLUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import io.reactivex.plugins.RxJavaPlugins;
import io.rong.imlib.RongIMClient;
import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.OkHttpClient;


public class MyApplication extends Application {


   /* public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.cambodia.zhanbang.xunbo.utils.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }*/

    private static final String TAG = "MyApplication";
    private boolean isBackground = true;
    //是否是从webViewActivity界面返回，并且没有浮窗权限
    private static boolean isBackNoPermission;
    private static Context instance;
    private CommonTipPop commonTipPop;

    public static Context getInstance() {
        return instance;
    }
    private String token;
    private int appCount=0;
    private boolean isRunInBackground=false;
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white, R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat(Utils.getString(R.string.更新于 %s)));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setPrimaryColorsId(android.R.color.white, R.color.black);//全局设置主题颜色
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        CommonModule.init(this);
        initAPPVest();
//        MultiDex.install(instance);
        initTxWeView();
        initHttpCache();
        //初始化hotfix
//        initBugly();
        initUMENG();
        initRxHttp();
//        downLoadTxt();
        initFragmention();
        //设置全局app时区
        initGlobalTimeZone();
        ViewTarget.setTagId(R.id.tag_glide);//glide给iamgeView设置tag
        listenForForeground();
        listenForScreenTurningOff();

        //每次启动app,覆盖时间差
        SharePreferencesUtil.putLong(this, "shijiancha", 0l);
        //    OkHttp3Utils.getInstance().setCertificates(new BufferedInputStream(new Buffer().writeUtf8(cerStr).inputStream()));
        initRongyun();
        //监听app从转入后台和进入前台的状态
        initBackgroundCallBack();
        initAppUpdate();
        // 以下用来捕获程序崩溃异常  程序崩溃时触发线程
        Thread.setDefaultUncaughtExceptionHandler(new OwnUncaughtExceptionHandler());

        setRxJavaErrorHandler();

        ToastUtils.init(this);


    }

    private void initAPPVest() {
        int res = AppVest.init("1c9627ed0b622eba8a03e73650d26c28", "93naUhnw8N80fQ+fg2V+meaZqx1WCPwoQnQoJpRw29C0r12NtIKW0rr3Bx7SvXASE7bPp4jBt3TYzuv3+BT9iRBDa31tYA7qGTMPH20B9CVJ4htaRheDMZeGtiCO8wi75Nw6PcfsPob6CuDYNXkbUcrA7zDE1oLn0AObvF3SOSPx1BbkQKhixgB0l4zzCqkWeGUxKc/GduABNwSnztVsSJ4SxHMB9FrgTUAQmfuH5Kv9Za2++6syPSuLV0Ln6Kd6srApyym4VWjFWTgr700qrJfU72jHmB6mfX7aibLPFuFw5ksdVHyWgbaTTlZWOUNUPH26amtb5Xy33wGQpUzkynKZgzNFtmDUBHjM800UTflRL/ulQn5Pl8uxS+ufyGbSZunFXQ7BRLMKUungSpzKuG9f9OImvJeknRGVc4owgDEhgFRkBifROQz0IYby72XvWNQD4Acp9ejtdVJaKTiDSl1v2d9bsgqZejVTtOZTsjH4o54RxnVACIy1MTZzq5jplvIe21kAIl93WZGRYiU1XthSMqaS5n6hUIY8Q5IA+ePhdo14QvN/yrlr0/PwK1oW+S3uWZ9J8dlcvrdZW3a2/F7sG00MaxfD8R2gjcIden/L1FDYS/DeN+7T+KF2vW8sdppjE63K1B5qmX48/WhQ2I9vhp8agJgvNg9Le5w8dLMRuBSSjTmqamt5N4tiiQ17MdeBGmToDLU7fNL8V8KGn3wbnf2p7Ca/oTrh7XL6KHPPA++LEPJw4MqyhwlycmIjtJkUoSMzgWxc05rMNRU2qMCwcSOe+RMRiJZyEiwLRneQ3HFTxBnylOPn6bc03sWfboGEktxAqgzGLExnAyOGvNsaPunDV+7AJsawIgS9fbrvcbEnSV9OJQyo00XFohXa+6xfOhrrgevlPrpjKGTIr3yMl2g7n888oSFI/OS02uu26KB9ayYIYe/b2xHjpZaaj/lrAWSOJ02IxfW+8eHt80wZjDTJ+YqbbdvKmlMSsCFJedv5rUUzySgRxvxjhLmCh9Eu+Ybah1YkRKpuTg1IINpSs0/hc/JsdiQLaxvrDlCA8oabLZmkZHYNJ08yMU4PH4a8I1HyGsAivAUs7DBuMThsn3h/3AUo8KcNoLwh8wz1izf90UCcqvZB");
        if (res == -1) { /*初始化失败*/
            Utils.logE(TAG, "appvest init failed");
        }
    }


    /**
 * RxJava2 当取消订阅后(dispose())，RxJava抛出的异常后续无法接收(此时后台线程仍在跑，可能会抛出IO等异常),全部由RxJavaPlugin接收，需要提前设置ErrorHandler
 * 详情：http://engineering.rallyhealth.com/mobile/rxjava/reactive/2017/03/15/migrating-to-rxjava-2.html#Error Handling
*/
private void setRxJavaErrorHandler() {
    RxJavaPlugins.setErrorHandler(throwable -> {
        throwable.printStackTrace();
        Utils.logE("MyApplication", "MyApplication setRxJavaErrorHandler "  + throwable.getMessage());
    });
}


    private void initTxWeView() {
        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);

    }

    /**
     * HttpResponseCache 处理缓存 (SVGA缓存处理)
     */
    private void    initHttpCache() {
        File httpCacheDir=new File(instance.getCacheDir(),"http");
        long httpCacheSize=1024 *1024*1000;
        try {
            HttpResponseCache.install(httpCacheDir,httpCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void initAppUpdate() {
        //如果你想使用okhttp作为下载的载体，那么你需要自己依赖okhttp，更新库不强制依赖okhttp！可以使用如下代码创建一个OkHttpClient 并在UpdateConfig中配置setCustomDownloadConnectionCreator start
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30_000, TimeUnit.SECONDS)
                .readTimeout(30_000, TimeUnit.SECONDS)
                .writeTimeout(30_000, TimeUnit.SECONDS)
                //如果你需要信任所有的证书，可解决根证书不被信任导致无法下载的问题 start
                .sslSocketFactory(SSLUtils.createSSLSocketFactory())
                .hostnameVerifier(new SSLUtils.TrustAllHostnameVerifier())
                //如果你需要信任所有的证书，可解决根证书不被信任导致无法下载的问题 end
                .retryOnConnectionFailure(true);

        //更新库配置
        UpdateConfig updateConfig = new UpdateConfig()
                .setDebug(true)//是否是Debug模式
                .setBaseUrl("http://www.cretinzp.com/system/versioninfo")//当dataSourceType为DATA_SOURCE_TYPE_URL时，配置此接口用于获取更新信息
                .setMethodType(TypeConfig.METHOD_GET)//当dataSourceType为DATA_SOURCE_TYPE_URL时，设置请求的方法
                .setDataSourceType(TypeConfig.DATA_SOURCE_TYPE_URL)//设置获取更新信息的方式
                .setShowNotification(true)//配置更新的过程中是否在通知栏显示进度
                .setNotificationIconRes(R.drawable.ic_launch_1)//配置通知栏显示的图标
                .setUiThemeType(TypeConfig.UI_THEME_K)//配置UI的样式，一种有12种样式可供选择
                .setRequestHeaders(null)//当dataSourceType为DATA_SOURCE_TYPE_URL时，设置请求的请求头
                .setRequestParams(null)//当dataSourceType为DATA_SOURCE_TYPE_URL时，设置请求的请求参数
                .setAutoDownloadBackground(false)//是否需要后台静默下载，如果设置为true，则调用checkUpdate方法之后会直接下载安装，不会弹出更新页面。当你选择UI样式为TypeConfig.UI_THEME_CUSTOM，静默安装失效，您需要在自定义的Activity中自主实现静默下载，使用这种方式的时候建议setShowNotification(false)，这样基本上用户就会对下载无感知了
//                .setCustomActivityClass(CustomActivity.class)//如果你选择的UI样式为TypeConfig.UI_THEME_CUSTOM，那么你需要自定义一个Activity继承自RootActivity，并参照demo实现功能，在此处填写自定义Activity的class
                .setNeedFileMD5Check(false)//是否需要进行文件的MD5检验，如果开启需要提供文件本身正确的MD5校验码，DEMO中提供了获取文件MD5检验码的工具页面，也提供了加密工具类Md5Utils
//                .setCustomDownloadConnectionCreator(new OkHttp3Connection.Creator(builder))//如果你想使用okhttp作为下载的载体，可以使用如下代码创建一个OkHttpClient，并使用demo中提供的OkHttp3Connection构建一个ConnectionCreator传入，在这里可以配置信任所有的证书，可解决根证书不被信任导致无法下载apk的问题
                .setModelClass(new AppUpdateModel());
        //初始化
        AppUpdateUtils.init(this, updateConfig);
    }

    private void initRxHttp() {
        int appVestFlag = SharePreferencesUtil.getInt(instance, RxLibConstants.APPVEST_FLAG, 0);
        if(appVestFlag==1){
            SecurityConnection conn = AppVest.getServerIPAndPort("www.tiantian.com", 1002);
            ArrayMap<String, String> headMap = new ArrayMap<String, String>();
            //retrofit2 工厂类初始化
            ApiConfig build = new ApiConfig.Builder()
//                .setBaseUrl1(BuildConfig.API_HOST1)//BaseUrl，这个地方加入后项目中默认使用该url
                    .setBaseUrl1(String.format("http://%s:%s/web/ws/", conn.getServerIp(), conn.getServerPort()))//BaseUrl，这个地方加入后项目中默认使用该url
                    .setBaseUrl2(BuildConfig.API_HOST2)
                    .setSucceedCode(200)//成功返回码  200
                    .setDefaultTimeout(1000*10)//响应时间，可以不设置，默认为2000毫秒
                    //        .setHeads(headMap)
                    .setOpenHttps(true)
                    .build();
            build.init(this);
        }else {
            ArrayMap<String, String> headMap = new ArrayMap<String, String>();
            //retrofit2 工厂类初始化
            ApiConfig build = new ApiConfig.Builder()
                    .setBaseUrl1(BuildConfig.API_HOST1)//BaseUrl，这个地方加入后项目中默认使用该url
                    .setBaseUrl2(BuildConfig.API_HOST2)
                    .setSucceedCode(200)//成功返回码  200
                    .setDefaultTimeout(1000*10)//响应时间，可以不设置，默认为2000毫秒
                    //        .setHeads(headMap)
                    .setOpenHttps(true)
                    .build();
            build.init(this);
        }
    }

    private void initFragmention(){
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
    }



    //初始化友盟统计
    public void initUMENG() {
        //    UMConfigure.init(this,  UMConfigure.DEVICE_TYPE_PHONE, null);
//        if(!BuildConfig.DEBUG){
        //debug 不初始化友盟
            if(BuildConfig.API_HOST1.contains("https")){
//                initUm("5fa8e6de1c520d3073a38284", "4b0d95305a6fb6879665874883245f95");//旧包名
//                initUm("6051744d6ee47d382b872514", "3953505cb331cd2a19815c16f7034ce9");//新包名
                initUm(BuildConfig.UM_KEY, "3953505cb331cd2a19815c16f7034ce9");

            }else {
                initUm("5f97a83a45b2b751a91cdc23", "edb37f84c04d3c8360577fb38b305fb7");
            }
//    }

    }

    private void initUm(String s2, String edb37f84c04d3c8360577fb38b305fb7) {
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, s2, BuildConfig.FLAVOR, UMConfigure.DEVICE_TYPE_PHONE, edb37f84c04d3c8360577fb38b305fb7);
        ///获取消息推送代理示例  推送不集成
/*        PushAgent pushAgent = PushAgent.getInstance(this);
        pushAgent.setResourcePackageName("com.tiantian.zz.tt");
        pushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Utils.logE(TAG, Utils.getString(R.string.获取deviceToken成功: ) + s);
            }
            @Override
            public void onFailure(String s, String s1) {
                Utils.logE(TAG, Utils.getString(R.string.获取deviceToken失败: ) + s + s1);
            }
        });*/
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        // 打开统计SDK调试模式
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    //融云初始化
    public void initRongyun() {
        String rongYunKey = SharePreferencesUtil.getString(instance, "rongYunKey", "");
        if(StringMyUtil.isNotEmpty(rongYunKey)){
            RongLibUtils.initRongYun(rongYunKey);
        }
        String token = SharePreferencesUtil.getString(instance, "chatroomToken", "");
        if(StringMyUtil.isNotEmpty(token)){
            RongLibUtils.connectRongYun(token);
        }
    }

    /*
  连接状态监听
   */
    public static void ConnectionStatusListene() {
        RongIMClient.setConnectionStatusListener(new ConnectionStatusListene(MyApplication.getInstance()));
    }

    /**
     * 设置app内全局时区
     */
    public void initGlobalTimeZone() {
        TimeZone chinaTimeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(chinaTimeZone);
    }

    private void initBackgroundCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
                if (isRunInBackground) {
                    //应用从后台回到前台 需要做的操作
                    back2App(activity);

                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
                if (appCount == 0) {
                    //应用进入后台 需要做的操作
                    leaveApp(activity);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }
    /**
     * 从后台回到前台需要执行的逻辑
     *
     * @param activity
     */
    private void back2App(Activity activity) {
        isRunInBackground = false;
    }

    /**
     * 离开应用 压入后台或者退出应用
     *
     * @param activity
     */
    private void leaveApp(Activity activity) {
        isRunInBackground = true;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void listenForForeground() {
         registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
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

        });
    }

    private void listenForScreenTurningOff() {
        IntentFilter screenStateFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastReceiver() {
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
 /*       Intent intent = new Intent(this, AboutPersonService.class);
        startService(intent);*/

    }
    //单点登录
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void singleLogin(FlagEventModel flagEventModel) {
        Activity topActivity = ActivityUtil.getInstance().currentActivity();
        int flag = flagEventModel.getFlag();
        if(flag == 1){
            ClearCache.clearCache(MyApplication.getInstance());
            if(topActivity!=null&&!topActivity.isFinishing()&&!topActivity.isDestroyed()){
                LoginIntentUtil.toLogin(topActivity, flag, true);
            }
        }else if(flag == 2){
            if(topActivity!=null&&!topActivity.isFinishing()&&!topActivity.isDestroyed()){
                Intent intent = new Intent(topActivity, SplashActivity.class);
                startActivity(intent);
            }
        }else if(flag == 11){
            //跳转游客登录安全中心界面
            if(topActivity!=null&&!topActivity.isFinishing()&&!topActivity.isDestroyed()){
                if(commonTipPop == null){
                    commonTipPop = new CommonTipPop(topActivity,topActivity,Utils.getString(R.string.温馨提示),Utils.getString(R.string.此操作需要完成安全认证),Utils.getString(R.string.立即认证));
                    commonTipPop.setOnClickLintener(new CommonTipPop.OnClickLintener() {
                        @Override
                        public void onSureClick(View view) {
                            //跳转游客安全中心
                            Intent intent = new Intent(topActivity, VisitorSafeCenterActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                            startActivity(intent);
                            commonTipPop.dismiss();
                        }
                    });
                }
                if(!commonTipPop.isShowing()){
                    commonTipPop.showAtLocation(Utils.getContentView(topActivity), Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(topActivity,0.7f);
                }
            }
        }

    }
    private void notifyBackground() {
        // This is where you can notify listeners, handle session tracking, etc
        Utils.logE("", "notifyBackground");
/*        Intent intent = new Intent(this, AboutPersonService.class);
        stopService(intent);*/
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Utils.logE("", "onTerminate");
        super.onTerminate();
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

    public  static interface JoinSuccess{
        void joinSuccess();
    }
    private static JoinSuccess joinSuccess =null;

    public void setJoinSuccess(JoinSuccess joinSuccess) {
        this.joinSuccess = joinSuccess;
    }

    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }



}

