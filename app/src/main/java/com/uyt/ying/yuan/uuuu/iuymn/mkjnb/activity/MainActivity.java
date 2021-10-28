package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.AtyFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.HomeTwoFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.LiveTabFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.MineFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.ShoppingFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.ShoppingTwoFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.AppUpdateModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.VersionUtils;
import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener;
import com.cretin.www.cretinautoupdatelibrary.interfaces.AppUpdateInfoListener;
import com.cretin.www.cretinautoupdatelibrary.model.DownloadInfo;
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static String TAG="MainActivity";
    public View tabHome;
    public View hideTabHome;
    public LinearLayout tabBuy;
    public LinearLayout tabAty;
    public LinearLayout TabMine;
    public ImageView tabHomeImg;
    public ImageView tabMineImg;
    public ImageView tabBuyImg;
    public ImageView tabAtyImg;
    public TextView tabHomeTv;
    public TextView tabMineTv;
    public TextView tabBuyTv;
    public TextView tabAtyTv;
    public FragmentManager fragmentManager;
    public long firstTime = 0;
    public LinearLayout tab;
    int count=1;
    public int shoppingCount=1;
    public boolean toShopping;
    public boolean forAllLollery;
    public boolean forGouCai;
    public boolean isCreate=false;
    public ArrayList<LinearLayout>allTabList=new ArrayList<>();
    public boolean fromLogin;
    public String showPop;
    public boolean connectRy;
    public String token;
    public String cameraPermission = Manifest.permission.CAMERA;
    public String photoPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public int camareRequestCode = 10;//调用相机的请求码
    public int photoRequestCode = 11;//调用相册的调用吗
    int liveClickCount=0;
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();

    Fragment homeFragment;
    HomeTwoFragment homeTwoFragment;
    ShoppingFragment shoppingFragment;
    //    ShoppingTwoFragment shoppingTwoFragment;
    LiveTabFragment liveTabFragment;
    AtyFragment atyFragment;
    MineFragment mineFragment;
    ShoppingTwoFragment shoppingTwoFragment;
    ;
    private String description;
    private int versionCode;
    private String versionName;
    private boolean showAtyPop;
    private String bottomIcon1;
    private String bottomIcon2;
    private String bottomIcon3;
    private String bottomIcon4;
    private String bottomIconHl1;
    private String bottomIconHl2;
    private String bottomIconHl3;
    private String bottomIconHl4;
    private CommonTipPop commonTipPop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.white));
        StatusBarUtil.setDarkMode(this);
        connectRy = SharePreferencesUtil.getBoolean(this, "connectRy", true);
        showPop = SharePreferencesUtil.getString(MainActivity.this, "showPop", "1");
        fromLogin = getIntent().getBooleanExtra("fromLogin", false);
        isCreate = true;
        fragmentManager = getSupportFragmentManager();//获取碎片管理者
        bindView();
        toShopping = getIntent().getBooleanExtra("toShopping", false);//进入mainActivity后是否跳转购彩大厅
        boolean toAtyFragment = getIntent().getBooleanExtra("toAtyFragment", false);//进入mainaActivity后是否跳转活动中心
        showAtyPop = getIntent().getBooleanExtra("showAtyPop", false);//进入mainaActivity后是否弹出公告弹窗
        boolean toMineFragment = getIntent().getBooleanExtra("toMineFragment", false);
        forAllLollery = getIntent().getBooleanExtra("forAllLollery", false);
        forGouCai = getIntent().getBooleanExtra("forGouCai",true);//默认为true,选中购彩大厅(倒计时)
        if (toShopping) {
            tabBuy.performClick();
        } else if (toAtyFragment) {
            tabAty.performClick();
        } else if (toMineFragment) {
            TabMine.performClick();
        } else {
            tabHome.performClick();//模拟点击, 启动activity时, 默认选中第一个tab
        }
        //  getAppVersion();


        //如果进入app时系统维护,application获取token失败,不会连接融云服务器,导致聊天室页面加入融云失败,所以在mainactivity中重新请求token,并且连接融云服务器
        if (!connectRy) {
//            getChatroomInfo();
        }
        getTileImgList(this);//请求头像列表.并存入
//        checkPermissions();
        if (showAtyPop) {
            requestAppUpdate();
        }
//        }

        requestRegisterStatistics();

        requestLevelList();
    }

    private void requestLevelList() {
        HttpApiUtils.CPnormalRequest(this, null,RequestUtil.LEVEL_LIST, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LEVEL_LIST,result);


            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    /**
     * 第三方app安装统计
     */
    private void requestRegisterStatistics() {
        Long user_id = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
        if(user_id!=0l){
            HashMap<String, Object> data = new HashMap<>();
            data.put("installApps", Utils.getInstallApps(MainActivity.this));
            data.put("page",5);
            data.put("minutes", 0);
            data.put("times",0);
            HttpApiUtils.CPnormalRequest(this, null, RequestUtil.WATCH_MINUTES, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    Utils.logE(TAG,"onSuccess: 是否安装微信等app统计成功");
                }

                @Override
                public void onFailed(String msg) {
                    Utils.logE(TAG, "onSuccess: 是否安装微信等app统计失败"+msg);
                }
            });
        }
    }


    public static void getTileImgList(Context context) {
        String firstImgurl = Utils.getFirstImgurl(context);
        HashMap<String, Object> data = new HashMap<>();
        Utils.docking(data, RequestUtil.REQUEST_09rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                String titleList="";
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray memberHeadPortraitList = jsonObject1.getJSONArray("memberHeadPortraitList");
                //将所有的头像url用逗号拼接,聊天室界面取出
                for (int i = 0; i < memberHeadPortraitList.size(); i++) {
                    JSONObject jsonObject = memberHeadPortraitList.getJSONObject(i);
                    String image = jsonObject.getString("image");
                    titleList+=firstImgurl+image+",";
                }
                titleList = titleList.substring(0, titleList.length()-1);
                SharePreferencesUtil.putString(context,"titleImageList",titleList);
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    private void checkPermissions() {
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        Utils.logE(TAG,"init: 权限申请成功");
                        if (showAtyPop) {
                            requestAppUpdate();
                        }
                    } else {
                        Utils.logE(TAG,"init: 权限申请失败");
                        showToast(Utils.getString(R.string.拒绝必须权限后将无法正常使用app));
                    }
                });
    }
    /**
     * 版本更新
     */
    private void requestAppUpdate() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("systemType",2);
        int appVersionCode = (int) VersionUtils.getAppVersionCode(this);
        String appVersionName = VersionUtils.getAppVersionName(this);
        data.put("versionCode", appVersionCode);//本地版本号
        HttpApiUtils.CPnormalRequest(this, null,RequestUtil.APP_VERSION, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                Integer flg = jsonObject1.getInteger("flg");
                if(flg==1){//有新版本时flg为1  flg为0时,数据结构完全不一样, 不能解析(后台不改,没办法)
                    JSONObject jsonObject = jsonObject1.getJSONObject("appVersion");
                    versionCode = jsonObject.getInteger("versionCode");
                    versionName = jsonObject.getString("versionName");
                    String isForce = jsonObject.getString("isForce");
                    String downLoadUrl = jsonObject.getString("downLoadUrl");
                    Utils.logE(TAG, "downLoadUrl:" + downLoadUrl);
                    //升级提示信息
                    description = jsonObject.getString("description");
                /*
                   当前为最新版本
                 */
                    if(versionCode==appVersionCode){
                        Utils.logE(TAG, "onSuccess: 当前是最新版本" );
                    }else {
                        //下载地址为apk后缀,直接下载
                        if(StringMyUtil.isNotEmpty(downLoadUrl)&&downLoadUrl.endsWith("apk")){
                            AppUpdateModel appUploadModel = new AppUpdateModel();
                            appUploadModel.setAutoUpdateBackground(false);
                            appUploadModel.setCheckFileMD5(false);
                            appUploadModel.setSourceTypeVaule(TypeConfig.DATA_SOURCE_TYPE_MODEL);
                            appUploadModel.setUiTypeValue(TypeConfig.UI_THEME_L);
                            if (isForce.equals("1")) {//强制更新
                                appUploadModel.setForceUpdate(true);
                            } else {//非强制更新
                                appUploadModel.setForceUpdate(false);
                            }
                            try {
                                if (!downLoadUrl.startsWith("http")) {
                                    downLoadUrl= Utils.getFirstImgurl(MainActivity.this)+downLoadUrl;
                                }
                                startUpdateApp(appUploadModel,downLoadUrl);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            //下载地址为外链链接,跳转外部浏览器
                            if(StringMyUtil.isNotEmpty(downLoadUrl)){
                                String finalDownLoadUrl = downLoadUrl;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(isForce.equals("1")){
                                            showAppUpdateDia(finalDownLoadUrl, false);

                                        }else {
                                            showAppUpdateDia(finalDownLoadUrl, true);
                                        }

                                    }
                                },300);

                            }
                        }

                    }
                }else {
                    Utils.logE(TAG,"onSuccess: 当前是最新版本");
                }
            }


            @Override
            public void onFailed(String msg) {
            }
        });
    }

    /**
     *
     * @param finalDownLoadUrl
     * @param cancelable 点击外部是否消失
     */
    private void showAppUpdateDia(String finalDownLoadUrl, boolean  cancelable) {
        AlertDialog isExit = new AlertDialog.Builder(MainActivity.this)
                .setPositiveButton(Utils.getString(R.string.立即更新),null)
                .create();
        isExit.setCancelable(cancelable);
        isExit.setTitle(Utils.getString(R.string.更新提示));
        isExit.setMessage(Utils.getString(R.string.app有新版本更新内容如下n) + description);
        isExit.show();
        isExit.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteUtils.openBrowser(MainActivity.this, finalDownLoadUrl);
            }
        });
        if(cancelable){
            isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }

    }


    private void startUpdateApp(AppUpdateModel appUploadModel,String downloadUrl) throws IOException {
        DownloadInfo downloadInfo = new DownloadInfo().setApkUrl(downloadUrl)
                //EC42E7E1FE580F1E187C8E10355A13BB0
//                .setFileSize(getContentLength(downloadUrl))
                .setProdVersionCode(versionCode)
                .setProdVersionName(versionName)
                .setForceUpdateFlag(appUploadModel.isForceUpdate() ? 1 : 0)
                .setUpdateLog(description);
        AppUpdateUtils appUpdateUtils = AppUpdateUtils.getInstance();
        appUpdateUtils.getUpdateConfig().setUiThemeType(appUploadModel.getUiTypeValue());
        //打开文件MD5校验
        appUpdateUtils.getUpdateConfig().setNeedFileMD5Check(appUploadModel.isCheckFileMD5());
        appUpdateUtils.getUpdateConfig().setDataSourceType(appUploadModel.getSourceTypeVaule());
        //开启或者关闭后台静默下载功能
        appUpdateUtils.getUpdateConfig().setAutoDownloadBackground(appUploadModel.isAutoUpdateBackground());
        if (appUploadModel.isAutoUpdateBackground()) {
            //开启静默下载的时候推荐关闭通知栏进度提示
            appUpdateUtils.getUpdateConfig().setShowNotification(false);
        } else {
            appUpdateUtils.getUpdateConfig().setShowNotification(true);
        }
        appUpdateUtils
                .addAppUpdateInfoListener(new AppUpdateInfoListener() {
                    @Override
                    public void isLatestVersion(boolean isLatest) {
                        Utils.logE("HHHHHHHHHHHHHHH", "isLatest:" + isLatest);
                    }
                })
                .addAppDownloadListener(new AppDownloadListener() {
                    @Override
                    public void downloading(int progress) {
                        Utils.logE("HHHHHHHHHHHHHHH", "progress:" + progress);
                    }

                    @Override
                    public void downloadFail(String msg) {
                        Utils.logE("HHHHHHHHHHHHHHH", "msg:" + msg);
                    }

                    @Override
                    public void downloadComplete(String path) {
                        Utils.logE("HHHHHHHHHHHHHHH", "path:" + path);

                    }

                    @Override
                    public void downloadStart() {
                        Utils.logE("HHHHHHHHHHHHHHH", "start");
                    }

                    @Override
                    public void reDownload() {
                        Utils.logE("HHHHHHHHHHHHHHH", "reDownload");
                    }

                    @Override
                    public void pause() {
                        Utils.logE("HHHHHHHHHHHHHHH", "pause");
                    }
                })
                .checkUpdate(downloadInfo);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        for (int j = 0; j < allTabList.size(); j++) {
            allTabList.get(j).setClickable(true);
        }

    }



    @Override
    protected void init() {
    }
    /*
    获取前端域名
     */
    public void initFrontUrl() {
        HashMap<String, Object> data = new HashMap<>();
        Utils.docking(data, RequestUtil.REQUEST_10004, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                String frontUrl = jsonObject.getString("frontUrl");
                SharePreferencesUtil.putString(MainActivity.this,CommonStr.FRONT_URL,frontUrl);
                try {
                    URL url = new URL(frontUrl);
                    String protocol = url.getProtocol();
                    String host = url.getHost();
                    String domain=protocol+"://"+host;
                    SharePreferencesUtil.putString(MainActivity.this,"domain",domain);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    /*
    获取图片前缀
     */
    public void iniImageDomain() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "1");
        Utils.docking(map, RequestUtil.REQUEST_10005, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                String FirstImageUrl = jsonObject.getString("imageUrl");
                SharePreferencesUtil.putString(MainActivity.this, "FirstImageUrl", FirstImageUrl);
            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }





    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听返回键，点击两次退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {
                showToast(Utils.getString(R.string.再按一次退出程序));
                firstTime = secondTime;
            } else {
                ActivityUtil.getInstance().exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 绑定组件和事件
     */
    public void bindView() {
            tabHome = findViewById(R.id.tab_home_two);
            tabHomeImg = findViewById(R.id.home_tab__img_two);
            tabHomeTv = findViewById(R.id.home_tab__tv_two);
            hideTabHome=findViewById(R.id.tab_home);
        hideTabHome.setVisibility(View.GONE);
        tabBuy = findViewById(R.id.tab_buy);
        tabAty = findViewById(R.id.tab_aty);
        TabMine = findViewById(R.id.tab_mine);
        allTabList.add((LinearLayout) tabHome);
        allTabList.add(tabBuy);
        allTabList.add(tabAty);
        allTabList.add(TabMine);
        tabMineImg = findViewById(R.id.tab_mine_img);
        tabBuyImg = findViewById(R.id.tab_buy_img);
        tabAtyImg = findViewById(R.id.tab_aty_img);

        tabMineTv = findViewById(R.id.tab_mine_tv);
        tabBuyTv = findViewById(R.id.tab_buy_tv);
        tabAtyTv = findViewById(R.id.tab_aty_tv);
//        actionBar = findViewById(R.id.action_bar);
        tab = findViewById(R.id.linearLayout_tab);
        tabHome.setOnClickListener(this);
        tabBuy.setOnClickListener(this);
        tabAty.setOnClickListener(this);
        TabMine.setOnClickListener(this);
        bottomIcon1 = Utils.getHomeLogo("bottomIcon1");
        bottomIcon2 = Utils.getHomeLogo("bottomIcon2");
        bottomIcon3 = Utils.getHomeLogo("bottomIcon3");
        bottomIcon4 = Utils.getHomeLogo("bottomIcon4");
        bottomIconHl1 = Utils.getHomeLogo("bottomIconHl1");
        bottomIconHl2 = Utils.getHomeLogo("bottomIconHl2");
        bottomIconHl3 = Utils.getHomeLogo("bottomIconHl3");
        bottomIconHl4 = Utils.getHomeLogo("bottomIconHl4");

        if(StringMyUtil.isNotEmpty(bottomIcon1)){
            GlideLoadViewUtil.LoadNormalView(this,bottomIcon1,tabHomeImg);
        }
        if(StringMyUtil.isNotEmpty(bottomIcon2)){
            GlideLoadViewUtil.LoadNormalView(this,bottomIcon2,tabBuyImg);
        }
        if(StringMyUtil.isNotEmpty(bottomIcon3)){
            GlideLoadViewUtil.LoadNormalView(this,bottomIcon3,tabAtyImg);
        }
        if(StringMyUtil.isNotEmpty(bottomIcon4)){
            GlideLoadViewUtil.LoadNormalView(this,bottomIcon4,tabMineImg);
        }

    }
    public  void initBottomClick(LinearLayout linearLayout){
        for (int i = 0; i < allTabList.size(); i++) {
            if(allTabList.get(i).getId()==linearLayout.getId()){
                allTabList.get(i).setClickable(false);
            }else {
                allTabList.get(i).setClickable(true);
            }
        }


    }
    private void  hideAllFragment(FragmentTransaction transaction){
        if(null!=homeFragment){transaction.hide(homeFragment);}
        if(null!=homeTwoFragment){transaction.hide(homeTwoFragment);}
        if(null!=shoppingFragment){transaction.hide(shoppingFragment);}
        if(null!=atyFragment){transaction.hide(atyFragment);}
        if(null!= mineFragment){transaction.hide(mineFragment);}
//        if(null!=shoppingTwoFragment){transaction.hide(shoppingTwoFragment);}
        if(null!=liveTabFragment){transaction.hide(liveTabFragment);}
        if(null!=shoppingTwoFragment){transaction.hide(shoppingTwoFragment);}
    }
    @Override
    public void onClick(View v) {
        boolean isLogin = SharePreferencesUtil.getBoolean(MainActivity.this, "isLogin", false);//是否有登录
        FragmentTransaction transaction = fragmentManager.beginTransaction();//获取碎片事物
        switch (v.getId()) {
            /*
            底部导航栏点击事件处理
             */
            case R.id.tab_home:
            case R.id.tab_home_two:
                initTabHome();
                SharePreferencesUtil.putInt(MainActivity.this,"HomeCount",count);
                forAllLollery=false;//每次回到首页都将forAllLollery的值设为false(避免点击一次更多彩票后,为true,导致后面每次切换购彩大厅时,都是默认选中全部彩票)
                hideAllFragment(transaction);
                if(homeFragment==null){
                        homeFragment=new HomeTwoFragment();
                    transaction.add(R.id.fragment_content,homeFragment,"homeFragment");
                }else {
                    transaction.show(homeFragment);
                }
                if(count==1&&fromLogin&&showPop.equals("0")){//登录成功跳转而来且第一次点击,将fromLoginOneTIme传入,用于登录成功后,点击homeTab时判断是否需要弹出活动pop
                    Bundle bundle = new Bundle();;
                    bundle.putBoolean("fromLogin",true);
                    homeFragment.setArguments(bundle);
                }
                initBottomClick((LinearLayout) tabHome);
                count++;//用于homeFragenmt中是否要弹出活动公告(count==1时,且从开屏图页面进入时弹出)
                break;
            case R.id.tab_buy:
                if(liveClickCount==0){
                    tabBuyClick(true);
                }else {
                    tabBuyClick(false);
                }
                liveClickCount++;
                if(Utils.isNotFastClick()){
                    requestAppStatistics(1);
                }
                break;
            case R.id.tab_aty:
                tabAtyClick();
//                requestAppStatistics(3);
                break;
            case R.id.tab_mine:
                if (isLogin) {
                    hideAllFragment(transaction);
                    initTabMine();
                    if(null== mineFragment){
                        mineFragment = new MineFragment();
                        transaction.add(R.id.fragment_content, mineFragment);
                    }else {
                        transaction.show(mineFragment);
                    }
                } else {
                    LoginIntentUtil.toLogin(MainActivity.this);
                }
                initBottomClick(TabMine);
                if(Utils.isNotFastClick()){
                    requestAppStatistics(4);
                }
                break;

            default:
                break;
        }
        transaction.commit();//提交
    }

    public void tabAtyClick() {
        boolean isLogin = SharePreferencesUtil.getBoolean(MainActivity.this, "isLogin", false);//是否有登录
        FragmentTransaction transaction = fragmentManager.beginTransaction();//获取碎片事物
//        if(isLogin){
        hideAllFragment(transaction);
        initTabAty();
/*                    if(null==atyFragment){
                atyFragment = new AtyFragment();
                transaction.add(R.id.fragment_content,atyFragment);
            }else {
                transaction.show(atyFragment);
            }*/
        if(null == shoppingTwoFragment){
            shoppingTwoFragment = ShoppingTwoFragment.newInstance(false,true);
            transaction.add(R.id.fragment_content,shoppingTwoFragment);
        }else {
            transaction.show(shoppingTwoFragment);
        }
/*        }else {
            IntentUtil.toLogin(MainActivity.this);
        }*/
        transaction.commit();
        initBottomClick(tabAty);
    }

    private void requestAppStatistics(int page){
        if(SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)!=0) {
            HashMap<String, Object> data = new HashMap<>();

            data.put("page", page);//1 直播页面 2游戏页面 3 活动页面 4 会员中心 5 直播观看次数 6 直播观看时长 7 app安装统计
            data.put("installApps", Utils.getInstallApps(MainActivity.this));

            HttpApiUtils.CPnormalRequest(this, null, RequestUtil.WATCH_MINUTES, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    Utils.logE(TAG, "onSuccess: 用户行为统计成功 page ="+ page );
                }

                @Override
                public void onFailed(String msg) {
                    Utils.logE(TAG, "onSuccess: 用户行为统计失败page ="+ page +msg );
                }
            });
        }
    }
    /**
     * 游戏tab点击
     * @param toRecommend 跳转时是否默认选中推荐
     */
    public void tabBuyClick(boolean toRecommend) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();//获取碎片事物
        hideAllFragment(transaction);
        initTabBuy();
        if(null==liveTabFragment){
            liveTabFragment=  LiveTabFragment.newInstance(toRecommend);
            transaction.add(R.id.fragment_content,liveTabFragment);
        }else {
            //每次点击直播的更多按钮, 都切换到推荐
            if(toRecommend){
                liveTabFragment=  LiveTabFragment.newInstance(true);
                transaction.add(R.id.fragment_content,liveTabFragment);
            }else {
                transaction.show(liveTabFragment);
            }
        }
        transaction.commit();

        initBottomClick(tabBuy);
    }

    /*
     底部导航栏点击时，actionBar对应的位置和属性
     */
    public void initTabMine() {
        tab.setVisibility(View.VISIBLE);
        initIvStatus(bottomIcon1, tabHomeImg, R.drawable.shouye_wdl);
        initIvStatus(bottomIcon2, tabBuyImg, R.drawable.zhibo_wdl);
        initIvStatus(bottomIcon3, tabAtyImg, R.drawable.youxi_wdl);
        initIvStatus(bottomIconHl4, tabMineImg, R.drawable.wode_dl);
        tabHomeTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabMineTv.setTextColor(Color.parseColor("#FF574E"));
        tabAtyTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabBuyTv.setTextColor(Color.parseColor("#A9A9A9"));
    }


    public void initTabAty() {
        tab.setVisibility(View.VISIBLE);
        initIvStatus(bottomIcon1, tabHomeImg, R.drawable.shouye_wdl);
        initIvStatus(bottomIcon2, tabBuyImg, R.drawable.zhibo_wdl);
        initIvStatus(bottomIconHl3, tabAtyImg, R.drawable.youxi_dl);
        initIvStatus(bottomIcon4, tabMineImg, R.drawable.wode_wdl);
        tabHomeTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabMineTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabAtyTv.setTextColor(Color.parseColor("#FF574E"));
        tabBuyTv.setTextColor(Color.parseColor("#A9A9A9"));
    }

    public void initTabBuy() {
        tab.setVisibility(View.VISIBLE);
        initIvStatus(bottomIcon1, tabHomeImg, R.drawable.shouye_wdl);
        initIvStatus(bottomIconHl2, tabBuyImg, R.drawable.zhibo_dl);
        initIvStatus(bottomIcon4, tabMineImg, R.drawable.wode_wdl);
        initIvStatus(bottomIcon3, tabAtyImg, R.drawable.youxi_wdl);
        tabHomeTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabMineTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabAtyTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabBuyTv.setTextColor(Color.parseColor("#FF574E"));
    }

    public void initTabHome() {
        tab.setVisibility(View.VISIBLE);
        initIvStatus(bottomIconHl1, tabHomeImg, R.drawable.shouye_dl);
        initIvStatus(bottomIcon2, tabBuyImg, R.drawable.zhibo_wdl);
        initIvStatus(bottomIcon3, tabAtyImg, R.drawable.youxi_wdl);
        initIvStatus(bottomIcon4, tabMineImg, R.drawable.wode_wdl);
        tabHomeTv.setTextColor(Color.parseColor("#FF574E"));
        tabMineTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabAtyTv.setTextColor(Color.parseColor("#A9A9A9"));
        tabBuyTv.setTextColor(Color.parseColor("#A9A9A9"));
    }
    private void initIvStatus(String imageUrl, ImageView imageView, int defaultRes) {
        if (StringMyUtil.isEmptyString(imageUrl)) {
            imageView.setImageResource(defaultRes);
        } else {
            GlideLoadViewUtil.LoadNormalView(this, imageUrl, imageView);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProgressDialogUtil.stop(MainActivity.this);

    }
    @Override
    protected void onResume() {
        if(isCreate){
//            iniImageDomain();//获取图片前缀
            initFrontUrl();//获取前端接口域名
            SharePreferencesUtil.putString(this,"deviceCode","145158033844");//唯一设备号(后台dock中设置的,连接前端接口需要用到)
            String deviceCode = Settings.System.getString(this.getContentResolver(), Settings.System.ANDROID_ID);
            SharePreferencesUtil.putString(this,"androidDeviceCode",deviceCode);//唯一设备号(android 设备号,上传图片到阿里oss需要用于创建文件夹)
            sp.setDeviceCode(deviceCode);
        }
        isCreate=false;

        super.onResume();
    }

    @Override
    public void onNetChange(boolean netWorkState) {
    }


}
