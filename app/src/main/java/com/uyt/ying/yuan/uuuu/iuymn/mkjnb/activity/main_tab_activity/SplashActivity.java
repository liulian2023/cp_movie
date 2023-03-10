package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwner;
import com.uyt.ying.yuan.BuildConfig;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.net.common.BaseStringObserver;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.rxhttp.net.utils.RxTransformerUtils;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BaseUrlEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PreviewCacheModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.NetWorkPopwindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.cf.msc.sdk.AppVest;
import com.cf.msc.sdk.SecurityConnection;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Response;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
/*
???????????????
*/
public class SplashActivity extends BaseActivity implements View.OnClickListener {
    private boolean isDefend = false;//???????????????
    ImageView splash_image;
    LinearLayout xtwhLinear;
    TextView whContentTv;
    LinearLayout ll_reload;
    TextView online_kefu_tv;
    LinearLayout skip_linear;
    TextView test_change_url_tv;
    TextView refresh_textview;
    TextView skip_tv;
    SharedPreferenceHelperImpl mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();
    MyHolder myHolder;
    CommonTipPop commonTipPop;
    private String TAG="SplashActivity";
    static  boolean isSkip=false;
    private boolean autoChooseUrl=true;
    protected  int messageQueueIndexId=0;
    private Integer appAdImageDurationSeconds;
    private String appAdImage;
    private  int failCount=0;
    private List<BaseUrlEntity.AppRequestDomainsBean> appRequestDomains;
    private   BaseUrlEntity.AppRequestDomainsBean bestDomainBean;
    private int REQUEST_PERMISSION_SETTING = 1;
    private String kefu = "";


    @Override
    protected void init() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*????????????????????????home??????????????????*/
        if (!this.isTaskRoot()) {
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                    return;
                }
            }
        }
        checkPermissions();
    }
    private void checkPermissions() {
        String[] PERMISSIONS = {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        Utils.logE(TAG,"init: ??????????????????");
                        permissionSuccess();
//        }
                    } else {
                        Utils.logE(TAG, "init: ??????????????????");
                        permissionFail();
                    }
                });
    }

    private void permissionFail() {
        if(!ActivityCompat.shouldShowRequestPermissionRationale(this,WRITE_EXTERNAL_STORAGE)||!ActivityCompat.shouldShowRequestPermissionRationale(this,READ_EXTERNAL_STORAGE)){
            //???????????????????????????
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            isExit.setTitle(Utils.getString(R.string.????????????));
            isExit.setMessage(Utils.getString(R.string.?????????????????????APP?????????????????????????????????????????????????????????));
            isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.??????), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    checkPermissions();
                }
            });
            isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.??????), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                }
            });
            isExit.show();
        }else {
            checkPermissions();
        }
    }

    private void permissionSuccess() {
        downLoadTxt();
        initView();
        String urlList = mSharedPreferenceHelperImpl.getUrlList();
        BaseUrlEntity baseUrlEntity = JSONObject.parseObject(urlList, BaseUrlEntity.class);
        if(baseUrlEntity!=null&&baseUrlEntity.getAppRequestDomains().size()!=0){
            chooseBestUrl(baseUrlEntity);
        } else {
            requestAppStatistics();//??????app??????
            getSystemsData();//??????????????????
            NetWorkPopwindow.initPop();
            requestBaseUrlList();
            deletePreviewCache();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PERMISSION_SETTING){
            //???????????????????????????f
            checkPermissions();
        }
    }

    /**
     * ??????????????????
     * @param baseUrlEntity
     */
    private void chooseBestUrl(BaseUrlEntity baseUrlEntity) {
        KProgressHUD mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setLabel(Utils.getString(R.string.????????????????????????))
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        appRequestDomains = baseUrlEntity.getAppRequestDomains();

        BaseUrlEntity.AppRequestDomainsBean appRequestDomainsBean = new BaseUrlEntity.AppRequestDomainsBean();
        appRequestDomainsBean.setDomain(BuildConfig.API_HOST1);
        appRequestDomains.add(appRequestDomainsBean);


        for (int i = 0; i < appRequestDomains.size(); i++) {
            BaseUrlEntity.AppRequestDomainsBean appRequestDomainsBean1 = appRequestDomains.get(i);
//            Observable<Response<ResponseBody>> compose = new HttpApiImpl(appRequestDomainsBean1.getDomain())
            String apiHost;
            if( mSharedPreferenceHelperImpl.getAppVestFlag()==1){
                SecurityConnection conn = AppVest.getServerIPAndPort("www.tiantian.com", 1002);
                apiHost=  String.format("http://%s:%s/web/ws/", conn.getServerIp(), conn.getServerPort());
            }else {
                apiHost = appRequestDomainsBean1.getDomain();
            }
            Observable<Response<ResponseBody>> compose = new HttpApiImpl(apiHost)
                    .pingTest()
                    .timeout(2000, TimeUnit.MILLISECONDS)
                    .compose(RxTransformerUtils.io_main());
            compose
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner)this)))
                    .subscribe(new BaseStringObserver<ResponseBody>() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            LogUtils.e("onSuccess " + result);
                            try {
                                if(bestDomainBean==null){
                                    bestDomainBean= appRequestDomainsBean1;
                                    mSharedPreferenceHelperImpl.setNewBaseUrl( bestDomainBean.getDomain());
                                    if(mKProgressHUD!=null){
                                        mKProgressHUD.dismiss();
                                    }
                                    Utils.logE(TAG, "onSuccess: ????????????: "+appRequestDomainsBean1.getDomain() );
                                    getSystemsData();
                                    requestAppStatistics();
                                    NetWorkPopwindow.initPop();
                                    requestBaseUrlList();
                                    deletePreviewCache();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            failCount++;
                            if(appRequestDomains!=null&&failCount==appRequestDomains.size()){
                                if(mKProgressHUD!=null){
                                    mKProgressHUD.dismiss();
                                }
                                getSystemsData();//??????????????????
                                requestAppStatistics();//??????app??????
                                NetWorkPopwindow.initPop();
                                requestBaseUrlList();
                                deletePreviewCache();
                            }
                        }
                    });
        }


    }

    private void initView() {
        myHolder=new MyHolder(new WeakReference<>(this));
        mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();
        splash_image=findViewById(R.id.splash_image);
        xtwhLinear=findViewById(R.id.xtwh_linear);
        skip_tv=findViewById(R.id.skip_tv);
        whContentTv=findViewById(R.id.wh_content);
        ll_reload=findViewById(R.id.ll_reload);
        online_kefu_tv=findViewById(R.id.online_kefu_tv);
        skip_linear=findViewById(R.id.skip_linear);
        skip_linear.setOnClickListener(this);
        online_kefu_tv.setOnClickListener(this);
        findViewById(R.id.refresh_tv).setOnClickListener(this);
        findViewById(R.id.tv_reload).setOnClickListener(this);
    }

    private void initInviteCode() {
        ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = cm.getPrimaryClip();
        String inviteCode="";
        if(clipData!=null&&clipData.getItemCount()>0){
            ClipData.Item item = clipData.getItemAt(0);
            if(item!=null&&item.getText()!=null){
                inviteCode = item.getText().toString();
            }
        }
        String substring="";
        if(StringMyUtil.isNotEmpty(inviteCode)&&inviteCode.startsWith("code_")){
            substring = inviteCode.substring(inviteCode.indexOf("_") + 1, inviteCode.length());
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.INVITE_CODE,substring);
//            initFirstInstall();
        }/*else {*/
        // ?????????????????????h5????????????????????????
        HashMap<String, Object> data = new HashMap<>();
        data.put("deviceInfo","1");
        HttpApiUtils.CpRequest(this, null, RequestUtil.CHECK_REGISTER, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String inviteCode = jsonObject.getString("inviteCode");
                if(StringMyUtil.isNotEmpty(inviteCode)){
                    /**
                     * ?????????????????????????????????, ????????????????????????(?????????????????????????????????)
                     */
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.INVITE_CODE,StringMyUtil.isEmptyString(inviteCode)?"":inviteCode);
                }
                initFirstInstall();

             }

            @Override
            public void onFailed(String msg) {
//                String inveteCode = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.INVITE_CODE, "");
//                if(StringMyUtil.isNotEmpty(inveteCode)){
                    initFirstInstall();
//                }
            }
        });

//        }
    }
    /**
     * ????????????
     */
    private void requestAppStatistics() {
        boolean isConnect = false;
        boolean firstInstall = mSharedPreferenceHelperImpl.getFirstInstall();
        if(firstInstall){
            //??????????????? ?????????????????? ???????????????????????????,??????????????????
            initInviteCode();
        }else {
            //?????????????????????????????????????????????????????????
            Long user_id = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
            if(user_id!=0l){
                String rongYunKey = SharePreferencesUtil.getString(MyApplication.getInstance(), "rongYunKey", "");
                String chatroomToken = SharePreferencesUtil.getString(MyApplication.getInstance(), "chatroomToken", "");
                if(StringUtils.isNotEmpty(rongYunKey)&&StringUtils.isNotEmpty(chatroomToken)){
                    initRongyun();
                    isConnect=true;
                }
                boolean finalIsConnect = isConnect;
                HttpApiUtils.CPnormalRequest(this, null,RequestUtil.REQUEST_Chat1, new HashMap<>(), new HttpApiUtils.OnRequestLintener() {
                    @Override
                    public void onSuccess(String result, Headers headers) {
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        String chatRoomId = jsonObject.getString("chatroomId");
                        String token = null;
                        String appKey = null;
                        try {
                            token = AESUtil.decrypt(jsonObject.getString("token"));
                            appKey = AESUtil.decrypt(jsonObject.getString("appKey"));
                            SharePreferencesUtil.putString(MyApplication.getInstance(),"rongYunKey",appKey);
                            SharePreferencesUtil.putString(MyApplication.getInstance(), "chatroomToken", token);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SharePreferencesUtil.putString(MyApplication.getInstance(), "chatroomId", chatRoomId);
                        //?????????????????? 0 ???1 ???
                        String isSuperRoomManager = jsonObject.getString("isSuperRoomManager");
                        SharePreferencesUtil.putString(MyApplication.getInstance(),"isSuperRoomManager",isSuperRoomManager);
                        //??????id
                        String rcUsId = jsonObject.getString("rcUsId");
                        SharePreferencesUtil.putString(MyApplication.getInstance(),"rcUsId",rcUsId);

                        String vipSpeak = jsonObject.getString("vipSpeak");//vip????????????

                        SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.VIP_SPEAK,vipSpeak);
                        if(!finalIsConnect){
//                            initRongyun();
                        }
                    }

                    @Override
                    public void onFailed(String msg) {

                    }
                });
            }
        }
    }

    private void initFirstInstall() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("IsInstall","1");
        data.put("refername", SharePreferencesUtil.getString(MyApplication.getInstance(),CommonStr.INVITE_CODE,""));//??????????????????????????????
        HttpApiUtils.CPnormalRequest(this, null, RequestUtil.REQUEST_Chat1, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                //????????????????????????????????????  ???????????????????????????????????????????????????
                mSharedPreferenceHelperImpl.setFirstInstall(false);
                Utils.logE(TAG, "onSuccess: app??????????????????" );
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }


    //???????????????
    public void initRongyun() {
        String rongYunKey = SharePreferencesUtil.getString(MyApplication.getInstance(), "rongYunKey", "");
        if(StringMyUtil.isNotEmpty(rongYunKey)){
            RongLibUtils.initRongYun(rongYunKey);
        }
        String token = SharePreferencesUtil.getString(MyApplication.getInstance(), "chatroomToken", "");
        if(StringMyUtil.isNotEmpty(token)){
            RongLibUtils.connectRongYun(token);
        }
    }


    /**
     *??????????????????????????????
     */
    private void deletePreviewCache() {
        PreviewCacheModel previewCacheModel;
        List<PreviewCacheModel.DataBean> dataBeanList;
        String cache = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.PREVIEW_CACHE, "");
        if(StringMyUtil.isNotEmpty(cache)){
            previewCacheModel = JSONObject.parseObject(cache, PreviewCacheModel.class);
            dataBeanList = previewCacheModel.getDataBeanList();
        }else {
            previewCacheModel = new PreviewCacheModel();
            dataBeanList = new ArrayList<>();
        }
        Iterator<PreviewCacheModel.DataBean> it = dataBeanList.iterator();
        while(it.hasNext()){
            PreviewCacheModel.DataBean dataBean = it.next();
            long date = dataBean.getDate();
            //????????????????????????????????????
            if(date+(3600*24*1000)<=System.currentTimeMillis()){
                it.remove();
            }
        }
        previewCacheModel.setDataBeanList(dataBeanList);
        SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.PREVIEW_CACHE,JSONObject.toJSONString(previewCacheModel));
    }


    /**
     * ??????????????????????????????
     */
    private void requestBaseUrlList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("flag",2);
        HttpApiUtils.CPnormalRequest(this,null, RequestUtil.BASE_URL_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                mSharedPreferenceHelperImpl.setUrlList(result);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myHolder!=null){
            myHolder.removeCallbacksAndMessages(null);
        }
        myHolder=null;
    }


    private void getVersion() {
        HashMap<String, Object> data = new HashMap<>();
        HttpApiUtils.CPnormalRequest(this,null, RequestUtil.GET_VERSION, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                splash_image.setVisibility(View.VISIBLE);
                xtwhLinear.setVisibility(View.GONE);
                ll_reload.setVisibility(View.GONE);
                Utils.initGameClassVersion(getBaseContext(), result);
               myHolder.sendEmptyMessageDelayed(3,1000);

            }

            @Override
            public void onFailed(String msg) {
                splash_image.setVisibility(View.GONE);
                xtwhLinear.setVisibility(View.GONE);
                ll_reload.setVisibility(View.VISIBLE);
                initChooseBaseUrlPop();
            }
        });

    }

    /**
     * ????????????????????????
     */
    private void getSystemsData(){
        HashMap<String, Object> data = new HashMap<>();
        HttpApiUtils.CpRequest(this, null,RequestUtil.REQUEST_l2, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String province = jsonObject.getString("province");
                province = StringUtils.isEmpty(province)?Utils.getString(R.string.??????):province;//??????????????????
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.PROVINCE,province);
                String recommendLiveCategory = jsonObject.getString("recommendLiveCategory");//??????2????????????????????????
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.HOME_RECOMMEND,recommendLiveCategory);
                String extensionNoticeInfo10 = jsonObject.getString("extensionNoticeInfo10");
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.EXTENSTION_MOTICEINFO10,extensionNoticeInfo10);
                JSONObject sysParameter = jsonObject.getJSONObject("sysParameter");
                JSONObject systemParameterInfo = jsonObject.getJSONObject("systemParameterInfo");
                String need_install_app = systemParameterInfo.getString("NEED_INSTALL_APP");//0???????????????1???????????????
                if(need_install_app.equals("0")){
                    if(StringMyUtil.isEmptyString(Utils.getInstallApps(SplashActivity.this))){
                        KProgressHUD  mKProgressHUD = KProgressHUD.create(SplashActivity.this);
                        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                .setCancellable(false)
                                .setLabel(Utils.getString(R.string.??????????????????))
                                .setAnimationSpeed(2)
                                .setDimAmount(0.5f)
                                .show();
                    }else {
                        checkSuccess(sysParameter, systemParameterInfo);
                    }
                }else {
                    checkSuccess(sysParameter, systemParameterInfo);
                }
            }

            @Override
            public void onFailed(String msg) {
                splash_image.setVisibility(View.GONE);
                xtwhLinear.setVisibility(View.GONE);
                ll_reload.setVisibility(View.VISIBLE);
                initChooseBaseUrlPop();
            }
        });
    }

    private void checkSuccess(JSONObject sysParameter, JSONObject systemParameterInfo) {
        try {
            String defaultKey;
            String defaultSecret;
//            initUm("", "");//?????????
            if(BuildConfig.API_HOST1.contains("https")){
                //????????????
      /*          defaultKey="5fa8e6de1c520d3073a38284";
                defaultSecret="4b0d95305a6fb6879665874883245f95";*/

                defaultKey="6051744d6ee47d382b872514";
                defaultSecret="3953505cb331cd2a19815c16f7034ce9";
            }else {
                //????????????
                defaultKey="5f97a83a45b2b751a91cdc23";
                defaultSecret="edb37f84c04d3c8360577fb38b305fb7";
            }
            if(systemParameterInfo==null){
//                initUMENG(defaultKey,defaultSecret);
            }else {
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.ANCHOR_REWARD_RATE,systemParameterInfo.getString(CommonStr.ANCHOR_REWARD_RATE));//????????????????????????
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.ANCHOR_REWARD_MAX_PRICE,systemParameterInfo.getString(CommonStr.ANCHOR_REWARD_MAX_PRICE));//??????????????????????????????
                String cpTzFyRate = systemParameterInfo.getString("cpTzFyRate");
                SharePreferencesUtil.putString(MyApplication.getInstance(), CommonStr.CP_TZ_FLY_RATE,cpTzFyRate);  //??????????????????????????????????????????
                String um_pushkey_android = AESUtil.decrypt( systemParameterInfo.getString("UM_PUSHKEY_ANDROID"));
                String UMKEY_ANDROID = AESUtil.decrypt(systemParameterInfo.getString("UMKEY_ANDROID"));
//                initUMENG(StringMyUtil.isEmptyString(UMKEY_ANDROID)?defaultKey:UMKEY_ANDROID,StringMyUtil.isEmptyString(um_pushkey_android)?defaultSecret:um_pushkey_android);
                String registernum_by_accountname = AESUtil.decrypt(systemParameterInfo.getString("REGISTERNUM_BY_ACCOUNTNAME"));//?????????????????????????????????
                registernum_by_accountname = StringMyUtil.isEmptyString(registernum_by_accountname)?"0":registernum_by_accountname;
                SharePreferencesUtil.putInt(MyApplication.getInstance(),CommonStr.REGISTERNUM_BY_ACCOUNTNAME,Integer.parseInt(registernum_by_accountname));
                String visitor_login_close = systemParameterInfo.getString(CommonStr.VISITOR_LOGIN_CLOSE);//0??????????????????;1?????????????????????
                String visitor_login_sliding_check = systemParameterInfo.getString(CommonStr.VISITOR_LOGIN_SLIDING_CHECK);//0??????????????????;1??????????????????(??????????????????????????????)
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.VISITOR_LOGIN_CLOSE,visitor_login_close);
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.VISITOR_LOGIN_SLIDING_CHECK,visitor_login_sliding_check);
            }

            String chatRoomSensitiveWord = sysParameter.getString("chatRoomSensitiveWord");//??????????????????
            SharePreferencesUtil.putString(MyApplication.getInstance(),"chatRoomSensitiveWord",chatRoomSensitiveWord);

            String homeLogos = sysParameter.getString("homeLogos");
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.HOME_LOGO,homeLogos);//??????????????????
//                    String pcLogo = sysParameter.getString("pcLogo");//actionBar??????
            String headWord = sysParameter.getString("headWord");//homeFragemnt??????????????????
            String onlineCustomer = sysParameter.getString("onlineCustomer");//??????????????????
            String showLiveOnlineService = sysParameter.getString("showLiveOnlineService");//????????????????????????????????? 0 ?????? 1?????????
            String appSlidingCheck = sysParameter.getString("appSlidingCheck");//????????????????????????
            SharePreferencesUtil.putString(MyApplication.getInstance(),"showLiveOnlineService",showLiveOnlineService);
            Integer sscaiCount = sysParameter.getInteger("sscaiCount");//?????????????????????
            Integer raceCount = sysParameter.getInteger("raceCount");//?????????????????????????????????????????? 0 ??????  1 ?????????
            Integer systemSwitch = sysParameter.getInteger("systemSwitch");//??????
            Integer editNicknameLevel = sysParameter.getInteger("editNicknameLevel");//??????????????????????????????????????????????????????
            Integer isrestore = sysParameter.getInteger("isrestore");//pcdd?????????????????????????????? 0 ???  1???

            String noticeInfo = sysParameter.getString("noticeInfo");//???????????????,?????????????????????
            String isOpenPayPass = sysParameter.getString("isOpenPayPass");
            Integer chatGrade = sysParameter.getInteger("chatGrade");//?????????????????????
            chatGrade= chatGrade == null?1:chatGrade;
            SharePreferencesUtil.putInt(MyApplication.getInstance(),CommonStr.CHAT_GRADE,chatGrade);
            String zkCode = sysParameter.getString("zkCode");//??????????????????
            SharePreferencesUtil.putString(SplashActivity.this,"zkCode",zkCode);
            String mobileNumSwitch = sysParameter.getString("mobileNumSwitch");  //???????????????   0??????   1??????
            String registerSetting = sysParameter.getString("registerSetting");
            String showPop = sysParameter.getString("showPop");//???????????????????????????  0?????? 1?????????
            SharePreferencesUtil.putString(SplashActivity.this,"showPop",showPop);
            String showShopInfoOnlineService = sysParameter.getString("showShopInfoOnlineService");
            SharePreferencesUtil.putString(MyApplication.getInstance(),"showShopInfoOnlineService",showShopInfoOnlineService);//?????????????????????????????????????????? 0??????/??????;1?????????/??????
            String chatRoomAmount = sysParameter.getString("chatRoomAmount");
            String imageUrl = sysParameter.getString("imageUrl");
            SharePreferencesUtil.putString(MyApplication.getInstance(), "FirstImageUrl", imageUrl);
            String isInfiniteAgent = sysParameter.getString("isInfiniteAgent");//0????????????;1???????????????
            SharePreferencesUtil.putString(SplashActivity.this,"isInfiniteAgent",isInfiniteAgent);
            String liveDomain = sysParameter.getString("liveDomain");//?????????????????????
            SharePreferencesUtil.putString(SplashActivity.this,"liveDomain",liveDomain);
            Integer showCodeAmountMyself = sysParameter.getInteger("showCodeAmountMyself");//?????????????????????????????? 0 ?????? 1??????
            Integer showMemberGradeAward = sysParameter.getInteger("showMemberGradeAward");//?????????????????????????????? 0 ?????? 1??????
            SharePreferencesUtil.putInt(SplashActivity.this,"showCodeAmountMyself",showCodeAmountMyself);
            SharePreferencesUtil.putInt(SplashActivity.this,"showMemberGradeAward",showMemberGradeAward);
            String isTestMemberPay = sysParameter.getString("isTestMemberPay");//????????????????????? 1???????????????????????????????????????????????? 0??????????????????
            SharePreferencesUtil.putString(SplashActivity.this,"isTestMemberPay",isTestMemberPay);
            String chatRoomSwitch = sysParameter.getString("chatRoomSwitch");//??????????????? 0?????? 1??????
            String isLoginCustom = sysParameter.getString("isLoginCustom");//?????????????????????????????? 0?????? 1??????
            SharePreferencesUtil.putString(MyApplication.getInstance(),"isLoginCustom",isLoginCustom);
            SharePreferencesUtil.putString(SplashActivity.this,"chatRoomSwitch",chatRoomSwitch);
            String anchorViewNumber = sysParameter.getString("anchorViewNumber");
            String anchorChangeNumber = sysParameter.getString("anchorChangeNumber");
            String appDownUrl = sysParameter.getString("appDownUrl");
            appAdImage = sysParameter.getString("appAdImage");
            appAdImageDurationSeconds = sysParameter.getInteger("appAdImageDurationSeconds");//?????????????????????
            if(StringMyUtil.isNotEmpty(appAdImage)){
                GlideLoadViewUtil.LoadNormalView(SplashActivity.this, Utils.checkImageUrl(appAdImage),splash_image);
            }

            String useWxTgQrCode = sysParameter.getString("useWxTgQrCode");//0??????1???(???????????????????????????)
            String wxTgQrCodeDesc = sysParameter.getString("wxTgQrCodeDesc");//???????????????????????????
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.WE_CHAT_INVITE_QR_CODE,useWxTgQrCode);
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.WE_CHAT_INVITE_QR_CODE_CONTENT,wxTgQrCodeDesc);
            String agentCommissionRechargeToChild = sysParameter.getString("agentCommissionRechargeToChild");//???????????????????????????????????????(0 ?????? 1??????)
            if(StringMyUtil.isEmptyString(agentCommissionRechargeToChild)){
                agentCommissionRechargeToChild="0";
            }
            SharePreferencesUtil.putInt(MyApplication.getInstance(),"editNicknameLevel",editNicknameLevel);
            SharePreferencesUtil.putInt(SplashActivity.this,CommonStr.AGENT_IN_CONE,Integer.parseInt(agentCommissionRechargeToChild));
            SharePreferencesUtil.putString(SplashActivity.this,CommonStr.ANCHOR_VIEW_NUMBER,anchorViewNumber);
            SharePreferencesUtil.putString(SplashActivity.this,CommonStr.ANCHOR_CHANGE_NUMBER,anchorChangeNumber);
            SharePreferencesUtil.putString(SplashActivity.this,CommonStr.APP_DOWN_URL,appDownUrl);
            JSONObject jsonObject1 = JSONObject.parseObject(registerSetting);
            String yqm = jsonObject1.getString("yqm");  //?????????   0 ??????   1??????
            String name = sysParameter.getString("name");
            //?????????????????????????????????????????????????????? (????????????????????????????????????,????????????????????????7???)
            SharePreferencesUtil.putString(SplashActivity.this,"appName",name);
            whContentTv.setText(noticeInfo);
            if (systemSwitch == 1) {
                isDefend = true;
                splash_image.setVisibility(View.GONE);
                xtwhLinear.setVisibility(View.VISIBLE);
                ll_reload.setVisibility(View.GONE);
                skip_tv.setVisibility(View.GONE);
                skip_linear.setVisibility(View.GONE);
            } else {
                isDefend = false;
                splash_image.setVisibility(View.VISIBLE);
                xtwhLinear.setVisibility(View.GONE);
                ll_reload.setVisibility(View.GONE);
                skip_tv.setVisibility(View.VISIBLE);
                skip_linear.setVisibility(View.VISIBLE);
                if(appAdImageDurationSeconds==null){
                    appAdImageDurationSeconds=0;
                }

                if(appAdImageDurationSeconds!=0){
                    myHolder.post(countDownTimeRunnable);
                }



                SharePreferencesUtil.putInt(SplashActivity.this, "sscaiCount", sscaiCount);
                SharePreferencesUtil.putInt(SplashActivity.this, "raceCount", raceCount);
                SharePreferencesUtil.putInt(SplashActivity.this, "isrestore", isrestore);
                SharePreferencesUtil.putString(SplashActivity.this, "pcLogo", sysParameter.getString("pcLogo"));
                SharePreferencesUtil.putString(SplashActivity.this, "headWord", headWord);
                SharePreferencesUtil.putString(SplashActivity.this, "onlineCustomer", onlineCustomer);
                SharePreferencesUtil.putString(SplashActivity.this, "isOpenPayPass", isOpenPayPass);
                SharePreferencesUtil.putString(SplashActivity.this, "mobileNumSwitch", mobileNumSwitch);
                SharePreferencesUtil.putString(SplashActivity.this,"chatRoomAmount",chatRoomAmount);
                SharePreferencesUtil.putString(SplashActivity.this, "yqm", yqm);
                SharePreferencesUtil.putString(SplashActivity.this, "appSlidingCheck", appSlidingCheck);

                Message msg = Message.obtain();
                msg.what = 1;
                myHolder.sendMessage(msg);
            }
        } catch (com.alibaba.fastjson.JSONException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initUMENG(String appKey ,String pushSecret) {
        //    UMConfigure.init(this,  UMConfigure.DEVICE_TYPE_PHONE, null);
//        if(!BuildConfig.DEBUG){
        //debug ??????????????????
        UMConfigure.init(MyApplication.getInstance(), appKey, BuildConfig.FLAVOR, UMConfigure.DEVICE_TYPE_PHONE, pushSecret);
        ///??????????????????????????????
/*        PushAgent pushAgent = PushAgent.getInstance(MyApplication.getInstance());
        pushAgent.setResourcePackageName("com.tiantian.zz.tt");
        pushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Utils.logE(TAG, Utils.getString(R.string.??????deviceToken??????: )+s);
            }

            @Override
            public void onFailure(String s, String s1) {
                Utils.logE(TAG, Utils.getString(R.string.??????deviceToken??????: )+s+">>>"+s1);
            }
        });*/
        // ??????AUTO??????????????????
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        // ????????????SDK????????????
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setCatchUncaughtExceptions(true);
//        }
    }
    Runnable countDownTimeRunnable = new Runnable() {
        @Override
        public void run() {
            if(StringMyUtil.isEmptyString(appAdImage)){
                skip_tv.setVisibility(View.GONE);
                skip_linear.setVisibility(View.GONE);
            }
            if(appAdImageDurationSeconds>0){
                skip_tv.setText(Utils.getString(R.string.??????)+appAdImageDurationSeconds+")S");
                if(skip_tv.getVisibility()!=View.VISIBLE){
                    skip_tv.setVisibility(View.VISIBLE);
                    skip_linear.setVisibility(View.VISIBLE);
                }
            }else {
                skip_tv.setText(Utils.getString(R.string.??????)+"(0)S");
                Message msg = Message.obtain();
                msg.what = 3;
                myHolder.sendMessage(msg);
                myHolder.removeCallbacks(this);
            }
            appAdImageDurationSeconds--;
            myHolder.postDelayed(this,1000);
        }
    };
    /**
     *??????ping???????????????
     * @param appRequestDomainsBean
     * @return
     */
    public long pingSuccessTime(BaseUrlEntity.AppRequestDomainsBean appRequestDomainsBean) {
        String delay = new String();
        Process p = null;
        try {
            String domain = appRequestDomainsBean.getDomain();
            URL url = new URL(domain);
            String host = url.getHost();
//            p = Runtime.getRuntime().exec("ping -c 1 " + host);
            p = Runtime.getRuntime().exec("/system/bin/ping -c 1 " + host);
            Utils.logE(TAG, "ping: "+host );
            BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = new String();
            while ((str = buf.readLine()) != null) {
                if (str.contains("avg")) {
                    int i = str.indexOf("/", 20);
                    int j = str.indexOf(".", i);
                    Utils.logE(TAG, "ping: "+ str.substring(i + 1, j) );
                    delay = str.substring(i + 1, j);
                    return Long.parseLong(StringMyUtil.isEmptyString(delay)?"100000":delay);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Long.parseLong(StringMyUtil.isEmptyString(delay)?"100000":delay);
    }
    /**
     * ????????????pop
     */
    private void initChooseBaseUrlPop() {
        if(commonTipPop== null){
//            commonTipPop = new CommonTipPop(SplashActivity.this,SplashActivity.this,Utils.getString(R.string.????????????),Utils.getString(R.string.??????????????????,?????????????????????),Utils.getString(R.string.????????????));
            commonTipPop = new CommonTipPop(SplashActivity.this,SplashActivity.this,Utils.getString(R.string.????????????),Utils.getString(R.string.?????????????????????????????????????????????)+BuildConfig.OFFICIAL_WEBSITE+Utils.getString(R.string.????????????),Utils.getString(R.string.????????????));
            commonTipPop.setOnClickLintener(new CommonTipPop.OnClickLintener() {
                @Override
                public void onSureClick(View view) {
                    startActivity(new Intent(SplashActivity.this, ChooseBaseUrlAvtivity.class));
                    commonTipPop.dismiss();
                }
            });

            commonTipPop.setOnCancelClickListener(new CommonTipPop.OnCancelClickListener() {
                @Override
                public void onCancelClick(View view) {
                    Intent intent = new Intent(SplashActivity.this, OnLineKeFuActivity.class);
                    String onlineCustomer = SharePreferencesUtil.getString(MyApplication.getInstance(), "onlineCustomer", "");
                    if(StringMyUtil.isEmptyString(onlineCustomer)){
                        intent.putExtra("kefuUrl",kefu);
                    }
                    startActivity(intent);
                }
            });
            commonTipPop.getExit_cancel_tv().setText(Utils.getString(R.string.????????????));
        }
        if(StringMyUtil.isNotEmpty(mSharedPreferenceHelperImpl.getUrlList())){
            commonTipPop.showAtLocation(splash_image, Gravity.CENTER,0,0);
            ProgressDialogUtil.darkenBackground(SplashActivity.this,0.7f);
        }

    }
    static class MyHolder extends Handler{
        WeakReference<SplashActivity> splashActivityWeakReference;

        public MyHolder(WeakReference<SplashActivity> splashActivityWeakReference) {
            this.splashActivityWeakReference = splashActivityWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity splashActivity = splashActivityWeakReference.get();
            if(null!=splashActivity){
                switch (msg.what) {
                    case 1:
                        splashActivity. getVersion();
                        break;
                    case 2:
//                        splashActivity.  getMovieSystems();
                        break;
                    case 3:
                        if(splashActivity.appAdImageDurationSeconds<=0&&!splashActivity.isDefend){//?????????????????????????????????????????????,???????????????????????????????????????
                            skip2MainActivity(splashActivity);
                        }
                        break;
                    default:
                        break;
                }
            }
            super.handleMessage(msg);

        }
    }

    private static void skip2MainActivity(SplashActivity splashActivity) {
        if(!isSkip){
            Intent intent = new Intent(splashActivity, MainActivity.class);//??????MainActivity
            intent.putExtra("showAtyPop", true);//???????????????app?????????????????????
            splashActivity. startActivity(intent);
            splashActivity.myHolder.removeCallbacksAndMessages(null);
        }
        isSkip=true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            ActivityUtil.getInstance().exitSystem();
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.skip_linear:
                if(isDefend){
                    return;
                }
                skip2MainActivity(SplashActivity.this);
                break;
            case R.id.online_kefu_tv:
                startActivity(new Intent(SplashActivity.this,OnLineKeFuActivity.class));
                break;
            default:
                getSystemsData();
                break;
        }

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    private void downLoadTxt(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                accessTxt(new Utils.AccessTxtListener() {
                    @Override
                    public void success(String content) {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        Integer appVestFlag = jsonObject.getInteger("AppVestFlag");
                        appVestFlag = appVestFlag==null?0:appVestFlag;
                        mSharedPreferenceHelperImpl.setAppVestFlag(appVestFlag);
                        Integer maintainFlag = jsonObject.getInteger("maintainFlag");//???????????????flag 0???1???
                        String maintainText = jsonObject.getString("maintainText");//??????????????????????????????
                        String cpapiUrl = jsonObject.getString("cpapiUrl");//??????api,????????????????????????url????????????
                        if(StringMyUtil.isNotEmpty(cpapiUrl)){
                            mSharedPreferenceHelperImpl.setNewBaseUrl(cpapiUrl);
                        }
                        maintainText= StringMyUtil.isEmptyString(maintainText)?Utils.getString(R.string.??????????????????????????????????????????????????????):maintainText;
                        kefu = jsonObject.getString("kefu");//??????????????????????????????????????????
                        if(maintainFlag==1){
                            String finalMaintainText = maintainText;
                            skip_linear.post(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog isExit = new AlertDialog.Builder(SplashActivity.this).create();
                                    isExit.setCancelable(false);
                                    isExit.setTitle(Utils.getString(R.string.????????????));
                                    isExit.setMessage(finalMaintainText);
                                    isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.????????????), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(SplashActivity.this, OnLineKeFuActivity.class);
                                            intent.putExtra("kefuUrl",kefu);
                                            startActivity(intent);
                                        }
                                    });
                                    isExit.show();
                                }
                            });
                        }
                    }
                    @Override
                    public void fail(int code) {
                    }
                });
            }
        }).start();

    }

    /**
     * ??????????????????
     */
    public  void accessTxt(Utils.AccessTxtListener accessTxtListener) {
        String url = "";
        if(BuildConfig.API_HOST1.contains("https")){
//        url= "http://www.xayyjd.com/systemParams.txt";
            try {
                url=AESUtil.decrypt(BuildConfig.DOWNLOAD_TXT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            url= "http://172.18.165.16:8185/upload/systemParams.txt";
        }
        OkHttpClient.Builder clientBuilder= new OkHttpClient().newBuilder();
        clientBuilder.readTimeout(2, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(2, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(2, TimeUnit.SECONDS);
        OkHttpClient okClient = clientBuilder.build();
        if(StringMyUtil.isNotEmpty(url)){
            Request request = new Request.Builder().url(url).build();
            try {
                okhttp3.Response response = okClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String msg = response.body().string();
                    if(accessTxtListener!=null){
                        accessTxtListener.success(msg);
                    }
                } else {
                    accessTxtListener.fail(response.code());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
