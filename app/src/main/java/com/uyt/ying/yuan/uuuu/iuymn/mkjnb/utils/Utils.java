package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveShareBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.VisitorSafeCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateSingleEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.liveActivityEntity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.uyt.ying.rxhttp.net.utils.CommonModule;
import com.hjq.toast.ToastUtils;
import com.uyt.ying.rxhttp.net.model.FlagEventModel;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LevelModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UsingEquipmentEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.CallBack;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.Base64Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.cf.msc.sdk.AppVest;
import com.cf.msc.sdk.SecurityConnection;
import com.umeng.analytics.MobclickAgent;


import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import anet.channel.util.StringUtils;
import io.rong.imlib.RongIMClient;
import okhttp3.Headers;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.uyt.ying.yuan.BuildConfig.API_HOST1;

public class Utils {
    static  String TAG="HttpLogger--> Docking";
    static SharedPreferenceHelperImpl mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();
    public static String TEST ="";
    // ???????????????????????????????????????????????????800??????
    private static final int MIN_CLICK_DELAY_TIME = 800;
    private static long lastClickTime;

    public static HashMap<String, Long> urlTime = new HashMap<>();
    public static ArrayList<String> gameTypeIdList = new ArrayList<>();

    public static boolean fileIsExists(String strFile) {
        try {
            File sdCardDir = Environment.getExternalStorageDirectory();//??????SDCard??????
            File file = getParent();
            File f = new File(file, strFile + ".txt");
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public static boolean deleteNormalFile(File file) {
        // ????????????????????????????????????????????????????????????????????????????????????
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println(Utils.getString(R.string.??????????????????) + fileName + Utils.getString(R.string.?????????));
                return true;
            } else {
//                System.out.println(Utils.getString(R.string.??????????????????) + fileName + Utils.getString(R.string.?????????));
                return false;
            }
        } else {
//            System.out.println(Utils.getString(R.string.???????????????????????????) + fileName + Utils.getString(R.string.????????????));
            return false;
        }
    }
    //?????????sd???
    public static boolean saveFileData(String obj, String fileName) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = getParent();
            FileOutputStream fos = null;
            try {
                File sdFile = new File(file, fileName + ".txt");
                fos = new FileOutputStream(sdFile);
                fos.write(obj.getBytes());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) fos.close();
//                    System.out.println("savaData success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public static double change(double a){
        return a * Math.PI  / 180;
    }
    @NonNull
    private static File getParent() {

        File sdCardDir = Environment.getExternalStorageDirectory();//??????SDCard??????

        String absolutePath = sdCardDir.getAbsolutePath();
        File file = new File(absolutePath + "/color");
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
        return file;
    }


    /**
     * ??????????????????
     *
     * @param fileName ??????????????????????????????
     * @return ??????????????????????????????true???????????????false
     */
    public static boolean deleteFile(String fileName) {
        File parent = getParent();
        File file = new File(parent.getAbsolutePath() + "/" + fileName + ".txt");
        // ????????????????????????????????????????????????????????????????????????????????????
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println(Utils.getString(R.string.??????????????????) + fileName + Utils.getString(R.string.?????????));
                return true;
            } else {
//                System.out.println(Utils.getString(R.string.??????????????????) + fileName + Utils.getString(R.string.?????????));
                return false;
            }
        } else {
//            System.out.println(Utils.getString(R.string.???????????????????????????) + fileName + Utils.getString(R.string.????????????));
            return false;
        }
    }


    public static String getFileData(String path) {
        StringBuffer buffer = new StringBuffer();
        try {
            File file = getParent();
            FileInputStream fis = new FileInputStream(file.getAbsolutePath() + "/" + path + ".txt");
            InputStreamReader isr = new InputStreamReader(fis);//????????????Unicode,UTF-8,ASCII,GB2312,Big5
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char) ch);
            }
            in.close();
        } catch (IOException e) {

        }
        return buffer.toString();
    }





    /***
     *
     * @param data
     * @param url
     * @param pageNo  ???1?????????,-1??????????????????,??????????????????:??????????????????return
     * @param iLoaderListener
     */
    public static void docking(final Map<String, Object> data, final String url, final int pageNo, final DockingUtil.ILoaderListener iLoaderListener) {
        String path = url.hashCode() + "";
//        String domain = SharePreferencesUtil.getString(MyApplication.getInstance(), "domain", "");
        String token = SharePreferencesUtil.getString(MyApplication.getInstance(), "token", "");
        Long user_id1 = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);;
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        String domain = sp.getNewBaseUrl();
        domain = StringMyUtil.isEmptyString(domain)? BuildConfig.API_HOST1:domain;

        if (!StringMyUtil.isEmptyString(domain) && !data.containsKey("domain")) {
            data.put("domain", domain);
        }
        if(!data.containsKey("loginType")){
            data.put("loginType",1);
        }
        data.put("source", 2);
        if (!StringMyUtil.isEmptyString(token) && !data.containsKey("token") && !url.equals("/member/login") && !url.equals(RequestUtil.REQUEST_l2)) {
            data.put("token", token);
            data.put("tokenInfo", token);
        }
        if (user_id1 != 0l && !url.equals("/member/login") && !url.equals(RequestUtil.REQUEST_l2)) {
            data.put("user_id", user_id1);
        }
        if(!data.containsKey("deviceNumber")){
            data.put("deviceNumber", AESUtil.encrypt(DeviceUtils.getUniqueId(MyApplication.getInstance())));//?????????
        }
        if(!data.containsKey("appVersionName")){
            data.put("appVersionName", VersionUtils.getAppVersionName(MyApplication.getInstance()));
        }
        if(!data.containsKey("mobileSystemVersion")){
            data.put("mobileSystemVersion", SystemUtil.getSystemVersion());
        }
        if(!data.containsKey("mobileBrandModels")){
            data.put("mobileBrandModels", SystemUtil.getSystemModel());
        }
        if(!data.containsKey("mobileBrand")){
            data.put("mobileBrand",SystemUtil.getDeviceBrand());
        }
        if (data != null && !data.isEmpty()) {
            path += data.toString().hashCode();
        }

        //    aLong 1560241979505  fileLastModified 1560241955000
        Long versionNewest = null;
        final Handler handler = new Handler();
        if (pageNo == 1 || pageNo == -1) {
            //TODO ?????????
            final String fileData = getFileData(path);
            long fileLastModified = getFileLastModified(path);
//            Date date = new Date(fileLastModified);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String format = simpleDateFormat.format(date);

//            System.out.println("getFileData=  " + url + ">" + fileData);
            if (StringMyUtil.isNotEmpty(fileData)) {
                iLoaderListener.success(fileData);
                if (pageNo == -1) {
                    Long aLong = urlTime.get("cache_" + url);
                    if (aLong == null || aLong.longValue() < fileLastModified) {
                        String key = getCacheKey(data);
                        versionNewest = urlTime.get(key);
                        JSONObject jsonObject = JSONObject.parseObject(fileData);
                        int version = jsonObject.getIntValue("version");
                        long user_id = jsonObject.getLongValue("user_id");
                        String userId = data.get("user_id") + "";
                        if ((version + "").equalsIgnoreCase(versionNewest + "") && (user_id + "").equalsIgnoreCase(userId)) {
                            return;
                        }
                    }
                }
            }
        }
        final String finalPath = path;
        new Thread() {
            @Override
            public void run() {
                try {
                    //TODO ????????????h
                    if ((data.get("user_id") + "").equals("0")) {
                        data.remove("user_id");
                    }
                    final String resultData = DockingUtil.docking(data, url);
                    JSONObject mJsonObject = null;
                    Utils.logE(TAG, url+">>>\n"+resultData );
                    final JSONObject jsonObject = JSONObject.parseObject(resultData);
                    final String success = jsonObject.getString("status");
                    final String message = jsonObject.getString("message");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if ("success".equals(success)) {
                                //TODO  ???1????????????
                                iLoaderListener.success(resultData);

                                if (pageNo == 1 || pageNo == -1) {
                                    String path = url.hashCode() + "";
                                    if (data != null && !data.isEmpty()) {
                                        path += data.toString().hashCode();
                                    }
                                    boolean saveFileData = saveFileData(resultData, path);
//                                    System.out.println("saveFileData=" + url + ">" + saveFileData);
                                }
                            } else {
//                                Activity topActivity = ActivityStack.getInstance().getTopActivity();
                                Activity topActivity = MyActivityManager.getInstance().getCurrentActivity();
                                MessageHead messageHead = new MessageHead();
                                Utils.logE(TAG,messageHead.toString()+">>>>"+url);
                                if (message==null) {
                                    messageHead.setInfo("error");
                                } else {
                                    messageHead.setInfo(message);
                                }
                                messageHead.setData(jsonObject);
                                iLoaderListener.failed(messageHead);
                                MobclickAgent.reportError(CommonModule.getAppContext(),message);
                                Utils.logE(TAG, messageHead.getInfo()+">>>>"+url );
                                String info = messageHead.getInfo();
                                if (StringMyUtil.isNotEmpty(info)&&info.equals("timeout")) {
//                                    showToast( Utils.getString(R.string.????????????,?????????));
                                }else {
                                    showToast(info);
                                }

                                JSONObject headData = messageHead.getData();
                                String flag = headData.getString("flag");
                                //????????????
                                if (!StringMyUtil.isEmptyString(flag)) {
                                    if (flag.equals("1")) {//flag???1,??????????????????,????????????loginActivity
                                        ClearCache.clearCache(MyApplication.getInstance());
                                        EventBus.getDefault().post(new FlagEventModel(1));
                                    } else if (flag.equals("2")) {
                                        //????????????
                                        EventBus.getDefault().post(new FlagEventModel(2));
                                    }else if(flag.equals("11")){
                                        EventBus.getDefault().post(new FlagEventModel(11));
                                    }

                                }
                            }


                        }
                    });
                } catch (final Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //TODO ??????

                            MessageHead messageHead = new MessageHead();
                            String localizedMessage = e.getLocalizedMessage();
                            Utils.logE(TAG, "??????:"+localizedMessage +url);
                            if (localizedMessage !=null&& localizedMessage.length()>10){
                                if(localizedMessage.contains("timeout")||localizedMessage.contains("after")){
//                                    messageHead.setInfo(Utils.getString(R.string.????????????));
                                    messageHead.setInfo("");
                                }else if(localizedMessage.contains("")&&!localizedMessage.contains("after")){
//                                    messageHead.setInfo(Utils.getString(R.string.????????????,???????????????));
                                    messageHead.setInfo("");
                                }else {
                                    messageHead.setInfo(Utils.getString(R.string.??????));
                                }
                            }else {
                                messageHead.setInfo(localizedMessage);
                            }
                            iLoaderListener.failed(messageHead);
                            showToast(messageHead.getInfo());
                            JSONObject headData = messageHead.getData();
//                            Activity topActivity = ActivityStack.getInstance().getTopActivity();
                            Activity topActivity = MyActivityManager.getInstance().getCurrentActivity();
                            String info = messageHead.getInfo();
                            Utils.logE(TAG,messageHead.getHttpurl()+"   >>>"+messageHead.getInfo());
                            //????????????
                            String flag = headData.getString("flag");
                            if (!StringMyUtil.isEmptyString(flag)) {
                                if (flag.equals("1")) {//flag???1,??????????????????,????????????loginActivity
                                    ClearCache.clearCache(MyApplication.getInstance());;
                                    if (topActivity != null && !topActivity.isFinishing()) {
                                        LoginIntentUtil.toLogin(topActivity,1,true);
                                    }
                                } else if (flag.equals("2")) {
                                    //????????????
                                    EventBus.getDefault().post(new FlagEventModel(2));
                                }else if(flag.equals("11")){
                                    EventBus.getDefault().post(new FlagEventModel(11));
                                }
                            }
                        }
                    });

                }
            }
        }.start();
    }

    private static void showToast( String s) {
        if(StringMyUtil.isEmptyString(s)||s.equalsIgnoreCase("timeout")||s.contains(Utils.getString(R.string.??????????????????))){
            return;
        }
        ToastUtils.setGravity(Gravity.CENTER,0,0);
        ToastUtils.show(s);
    }

    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0.00" + "M";//???????????????,???????????????0.3Byte???????????????,??????????????????0.00M
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    @NonNull
    private static String getCacheKey(Map<String, Object> data) {
        String game = data.get("game") + "";
        String type_id = data.get("type_id") + "";
        return "cache_" + game + type_id;
    }

    private static long getFileLastModified(String path) {
        File file = getParent();
        File fis = new File(file.getAbsolutePath() + "/" + path + ".txt");
        if (fis == null) {
            return System.currentTimeMillis();
        }
        long timeMillis = fis.lastModified();

        return timeMillis;
    }

    public static String getFirstImgurl(Context context) {
        String firstImageUrl = SharePreferencesUtil.getString(context, "FirstImageUrl", "");
        return firstImageUrl;

    }    public static String getLiveFirstImgurl(Context context) {
        String firstImageUrl = SharePreferencesUtil.getString(context, "liveDomain", "");
        if(!firstImageUrl.endsWith("/")){
            return firstImageUrl+"/";
        }
        return firstImageUrl;
    }

    public static void requestPermissions(Activity activity) {
        if (activity != null) {
            ActivityCompat.requestPermissions(activity, new String[]{android
                    .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    /*
  ????????????????????????map(key: url  value: ????????? )
 */
    public static void setUrlTime(Context context, String url) {
        String key = "cache_" + url;
        long currentTimeMillis = System.currentTimeMillis();
        Utils.urlTime.put(key, currentTimeMillis);
        SharePreferencesUtil.putLong(context, key, currentTimeMillis);
    }
    public static void setXmlImage(ImageView imageView,String url) {
        url = Utils.checkImageUrl(url);
        Activity activity = (Activity) imageView.getContext();
        boolean destroyed = activity.isDestroyed();
        boolean finishing = activity.isFinishing();
        if(imageView.getContext()!=null&&!finishing&&!destroyed){
            Glide.with(MyApplication.getInstance())
                    .load("android/xml/"+url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }
    public static void iniUrlTime(Context context) {
        String[] urls = new String[]{RequestUtil.REQUEST_01dh, RequestUtil.REQUEST_01dhnew};
        for (String url : urls) {
            String key = "cache_" + url;
            Long aLong = SharePreferencesUtil.getLong(context, key, System.currentTimeMillis());
            Utils.urlTime.put(key, aLong);
        }
    }

    /**
     * ??????????????????????????? dp ????????? ????????? px(??????)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * ??????????????????????????? px(??????) ????????? ????????? dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //?????????????????????
    public static int LengthNum(int num) {
        int count = 0; //??????
        while (num >= 1) {
            num = num / 10;
            count++;
        }
        return count;
    }

    //??????????????????  ??????   x??????
    public static Animation getAnimation(int x) {
        Animation rotateAnimation = new RotateAnimation(0f, x, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(0);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDetachWallpaper(true);

        return rotateAnimation;
    }

    //???????????????JAVA???????????????
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /***
     *
     * @param context
     * @param content
     */
    public static void initGameClassVersion(Context context, String content) {
        JSONObject jsonObject = JSONObject.parseObject(content);
        Long navigateVersion = jsonObject.getLong("navigateVersion");//???????????????
        Long giftListVersion = jsonObject.getLong("giftListVersion");//???????????????
        //??????????????????????????????   ----- start  -----------
        JSONArray gameClassVersions = jsonObject.getJSONArray("gameClassVersions");
        for (int i = 0; i < gameClassVersions.size(); i++) {
            JSONObject gameClass = gameClassVersions.getJSONObject(i);
            int game = gameClass.getIntValue("game");
            int type_id = gameClass.getIntValue("type_id");
            long version = gameClass.getLongValue("version");
            String key = CommonStr.LOTTERY_VERSION+ game + type_id;
            Long cacheValue = SharePreferencesUtil.getLong(MyApplication.getInstance(), key, 0l);
            if(cacheValue == 0l || cacheValue!=version){
                //todo ??????????????????????????????
                SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.LOTTERY_RULE+ game+type_id);
            }
            if(!gameTypeIdList.contains(key)){
                gameTypeIdList.add(key);
            }
            SharePreferencesUtil.putLong(MyApplication.getInstance(),key,version);
            //??????????????????????????????   ----- end  -----------

        }

        //??????????????????
        Long giftListCacheVersion = SharePreferencesUtil.getLong(MyApplication.getInstance(), CommonStr.GIFT_LIST_VERSION, 0l);
        if(giftListVersion.longValue() == 0l ||giftListVersion.longValue()!= giftListCacheVersion.longValue() ){
            SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.GIFT_LIST_CACHE);
        }
        SharePreferencesUtil.putLong(MyApplication.getInstance(),CommonStr.GIFT_LIST_VERSION,giftListVersion);
    }

    public static HashMap<String, Object> getNavigateListMap(int isHot) {
        HashMap<String, Object> datalottery = new HashMap<>();
        datalottery.put("isHot", "");
        datalottery.put("game", 0);//???????????????
        datalottery.put("type_id", 0);//???????????????
        datalottery.put("user_id", 0);//???????????????
        return datalottery;
    }


    public static View getContentView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
    public static String getImageUrl(String url,String domainImg){
        if(StringMyUtil.isNotEmpty(url)&&!url.startsWith("http")){
            url=domainImg+url;
        }
        return url;
    }


    public static String ImageUrlCheck(String img_url){
        if (StringMyUtil.isNotEmpty(img_url)&&!img_url.startsWith("http")&&!img_url.startsWith("https")){
            img_url = mSharedPreferenceHelperImpl.getImageDomin()+img_url;
        }
        return img_url;
    }
    public static String CPImageUrlCheck(Context context,String img_url){
        if (StringMyUtil.isNotEmpty(img_url)&&!img_url.startsWith("http")&&!img_url.startsWith("https")){
            String firstImageUrl = SharePreferencesUtil.getString(context, "FirstImageUrl", "");
            img_url = firstImageUrl+img_url;
        }
        return img_url;
    }


    public static void hideSoftKeyBoard(Activity activity) {
        if(activity!=null&&!activity.isFinishing()){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                if (activity.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
    }

    /**
     * ??????????????? (??????activity???????????????view???,??????????????????view???freagment??? ??????activity.getCurrentFocus()??????, ?????????fragment??????????????????????????????editText??????)
     * @param activity
     * @param
     */
    public static void hideSoftKeyBoard(Activity activity ,EditText editText) {
        if(activity!=null&&!activity.isFinishing()){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                if (editText.getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }

    }


    public static void showSoftInputFromWindow(Activity activity, EditText  editText){
        if(null!=activity&&!activity.isFinishing()){
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager)activity. getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        }
    }



    public static int intgetWinndowWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        Class c;
        int width=0;
        try {
            c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            int WIDTH = dm.widthPixels;
            width=WIDTH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return width;
    }
    public static int intgetWinndowHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        Class c;
        int height=0;
        try {
            c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            int HEIGHT = dm.heightPixels;
            height=HEIGHT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }

    /*
???????????? ??????????????????
 */
    public static void openAPK(String fileSavePath){
/*        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://"+fileSavePath),"application/vnd.android.package-archive");
        MyApplication.getInstance().startActivity(intent);
        */

    /*    Intent intent = new Intent(Intent.ACTION_VIEW);

        //2. ?????? category
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        //?????? flag ,???????????????????????????????????????????????????????????????????????????????????????
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //3. ?????? data ??? type
        intent.setDataAndType(Uri.fromFile(new File(fileSavePath)),"application/vnd.android.package-archive");

        //3. ?????? data ??? type (?????????????????????)
        //intent.setDataAndType(Uri.parse("file://" + targetFile.getPath()),"application/vnd.android.package-archive");

        //4. ?????? activity
        MyApplication.getInstance().  startActivity(intent);*/

        //???????????????????????????
        Uri uri = Uri.fromFile(new File(fileSavePath));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //???????????????AndroidN?????????????????????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MyApplication.getInstance(), BuildConfig.APPLICATION_ID+".fileProvider", new File(fileSavePath));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            //??????????????????????????????Context???startActivity??????????????????????????????????????????task
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        MyApplication.getInstance().  startActivity(intent);
    }


    public static int dp2px(float dpValue) {
        return (int)(0.5F + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * ??????bitmap?????????, ???????????????
     * @param bitmap
     * @param newWidth ??????????????????(?????????dp)
     * @param newHeight
     * @return
     */
    public static Bitmap getNewBitmap(Bitmap bitmap, int newWidth , int newHeight){
        // ?????????????????????.
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // ??????????????????. (????????????px)
        float scaleWidth = ((float) dp2px(newWidth)) / width;
        float scaleHeight = ((float) dp2px(newHeight)) / height;
        // ?????????????????????matrix??????.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // ??????????????????.
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBitmap;
    }
    public static <T> T initEntity(String result, Class<T> tClass, CallBack callBack) {
        try {
            Context mContext=MyApplication.getInstance();
            JSONObject json = JSONObject.parseObject(result);
            JSONObject jsonHead = JSONObject.parseObject(json.getString("head"));
            if ("00".equals(jsonHead.getString("code"))) {
                if (json.getString("data") != null) {
                    String resultData = Base64Utils.decodeBase64String(json.getString("data"));//?????????????????????
                    String timestamp = jsonHead.getString("timestamp");//???????????????
                    Long oldTime = SharePreferencesUtil.getLong(mContext, "shijiancha", 0l);
//                    long nowTime = System.currentTimeMillis() - Long.parseLong(timestamp);
                    long nowTime = new Date().getTime() - Long.parseLong(timestamp);
                    if (oldTime == 0) {//???????????????(?????????????????????0???????????????,?????????)
                        SharePreferencesUtil.putLong(mContext, "shijiancha", nowTime);
                    } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                        SharePreferencesUtil.putLong(mContext, "shijiancha", nowTime);
                    } else {
                        SharePreferencesUtil.putLong(mContext, "shijiancha", oldTime);
                    }
                    Object o = JSONObject.parseObject(resultData, tClass.forName(tClass.getName()));
                    /**
                     * ??????????????????
                     */
                    callBack.Success(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void initResult(String url,String result, CallBack callBack) {
        try {
            Context mContext=MyApplication.getInstance();
            JSONObject json = JSONObject.parseObject(result);
            JSONObject jsonHead = JSONObject.parseObject(json.getString("head"));
            if ("00".equals(jsonHead.getString("code"))) {
                if (json.getString("data") != null) {
                    String resultData = Base64Utils.decodeBase64String(json.getString("data"));//?????????????????????
                    String timestamp = jsonHead.getString("timestamp");//???????????????
                    Long oldTime = SharePreferencesUtil.getLong(mContext, "shijiancha", 0l);
//                    long nowTime = System.currentTimeMillis() - Long.parseLong(timestamp);
                    long nowTime = new Date().getTime() - Long.parseLong(timestamp);
                    if (oldTime == 0) {//???????????????(?????????????????????0???????????????,?????????)
                        SharePreferencesUtil.putLong(mContext, "shijiancha", nowTime);
                    } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                        SharePreferencesUtil.putLong(mContext, "shijiancha", nowTime);
                    } else {
                        SharePreferencesUtil.putLong(mContext, "shijiancha", oldTime);
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String initOldCpData(String url,String result) {
        String resultData = "";
        if (!StringMyUtil.isEmptyString(result)) {
            if (result.contains("500 Internal Server Error")) {
                resultData = "";
            } else {
                try {
                    JSONObject json = JSONObject.parseObject(result);
                    JSONObject jsonHead = JSONObject.parseObject(json.getString("head"));
                    if ("00".equals(jsonHead.getString("code"))) {
                        if (json.getString("data") != null) {
                            resultData = Base64Utils.decodeBase64String(json.getString("data"));//?????????????????????
                            String timestamp = jsonHead.getString("timestamp");//???????????????
                            //               Utils.saveFileData(timestamp + "", "time");//???????????????
                            Context context = MyApplication.getInstance();
                            Long oldTime = SharePreferencesUtil.getLong(context, "shijiancha", 0l);
//                            long nowTime = System.currentTimeMillis() - Long.parseLong(timestamp);
                            long nowTime = new Date().getTime() - Long.parseLong(timestamp);
                            if (oldTime == 0) {//???????????????(?????????????????????0???????????????,?????????)
                                SharePreferencesUtil.putLong(context, "shijiancha", nowTime);
                            } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                                SharePreferencesUtil.putLong(context, "shijiancha", nowTime);
                            } else {
                                SharePreferencesUtil.putLong(context, "shijiancha", oldTime);
                            }
                        }
                    } else {
                        /* resultData = "";*/
                        resultData = Base64Utils.decodeBase64String(json.getString("data"));//?????????????????????
                    }//21740
                }catch (Exception e){
                    e.printStackTrace();
                    Utils.logE(TAG,e.getMessage());
                }

            }
        } else {
            resultData = "";
        }
        Utils.logE(TAG,  url+">>>>"+result);
        return resultData;

    }
    public static String checkImageUrl(String url){
        if(StringMyUtil.isEmptyString(url)||url.startsWith("http")){
            return url;
        }
        url=getFirstImgurl(MyApplication.getInstance())+url;
        return url;
    }
    public static String checkLiveImageUrl(String url){
        if(StringMyUtil.isEmptyString(url)||url.startsWith("http")){
            return url;
        }
        url=getLiveFirstImgurl(MyApplication.getInstance())+url;
        return url;
    }

    public static RongcloudHBParameter hbParameter;
    public static void setRongcloudHBParameter(RongcloudHBParameter hbParameter1) {
        hbParameter = hbParameter1;
    }

    public static RongcloudHBParameter getRongcloudHBParameter() {
        return hbParameter;
    }

    public static String get2BigDecimal(String num){
        return new BigDecimal(num).setScale(2,BigDecimal.ROUND_HALF_UP)+"";
    }

    public static void saveLotteryHistory(int game,int type_id) {
        SaveChessLotteryUtil saveChessLotteryUtil = new SaveChessLotteryUtil();
        String navigateList = SharePreferencesUtil.getString(MyApplication.getInstance(), "navigateList", "");
        NavigateEntity navigateEntity = JSONObject.parseObject(navigateList, NavigateEntity.class);
        if(navigateEntity == null){
            return;
        }
        List<NavigateEntity.GameClassListBean> gameClassList = navigateEntity.getGameClassList();
        for (int i = 0; i < gameClassList.size(); i++) {
            NavigateEntity.GameClassListBean gameClassListBean = gameClassList.get(i);
            if(gameClassListBean.getGame()==game&&gameClassListBean.getType_id()==type_id){
                String toJSONString = JSONObject.toJSONString(gameClassListBean);
                saveChessLotteryUtil.saveChessLotteryHistory(toJSONString);
            }
        }
    }

    public static boolean checkAccount(String input) {
        int length = input .length();
        if (length<6||length > 11) {
            return false;
        }
        //6-11???????????????????????????
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,11}$");
        boolean matches = pattern.matcher(input).matches();
        //????????????
        Pattern pattern2 = Pattern.compile("^[a-zA-Z][0-9A-Za-z]*$");
        boolean matches2 = pattern2.matcher(input).matches();
        boolean result = matches && matches2;
        return result;

    }
    public static boolean checkPsw(String input) {
        int length = (input + "").length();
        if(length <6||length>16){
            return false;
        }
        //6-16???????????????????????????
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        boolean matches = pattern.matcher(input).matches();
        return matches;

    }
    public static boolean isAllNum(String input) {
        Pattern pattern = Pattern.compile("[0-9]*");
        boolean matches = pattern.matcher(input).matches();
        return matches;

    }
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
    public static boolean isNotFastClick() {
        return !isFastClick();
    }

    public static RongIMClient.ConnectionStatusListener.ConnectionStatus  getRongConnection(){
        RongIMClient.ConnectionStatusListener.ConnectionStatus currentConnectionStatus = RongIMClient.getInstance().getCurrentConnectionStatus();
        return currentConnectionStatus;
    }
    /*    public static int getLevelDrawble(int level) {
            if(level>=30){
                level=29;
            }
            if(level<=0){
                level=1;
            }
            int drawType;
            int[] levelIcon = Const.getLevelIcon(MyApplication.getInstance());
            drawType = levelIcon[level - 1];
            return drawType;
        }*/
    public static long getMineUserId(){
        return SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l);
    }
    public static String getLevelUrl(int grade){
        String string = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LEVEL_LIST, "");
        if(StringMyUtil.isNotEmpty(string)){
            LevelModel levelModel = JSONObject.parseObject(string, LevelModel.class);
            List<LevelModel.SysGradeListBean> sysGradeList = levelModel.getSysGradeList();
            for (int i = 0; i < sysGradeList.size(); i++) {
                LevelModel.SysGradeListBean sysGradeListBean = sysGradeList.get(i);
                int pointGrade = sysGradeListBean.getPointGrade();
                if(grade-1 == pointGrade){
                    return sysGradeListBean.getImage();
                }
            }
        }

        return "";
    }

    public static void webViewLoadColorStr(String content, WebView webView,String color){
        String wrapStr   = "<html> \n" +
                "<head> \n" +
                "<style type=\"text/css\"> \n" +
                "body {font-size:13px;}\n" +
                "</style> \n" +
                "</head> \n" +
                "<body>" +
                "<script type='text/javascript'>" +
                "window.onload = function(){\n" +
                "var $img = document.getElementsByTagName('img');\n" +
                "for(var p in  $img){\n" +
                " $img[p].style.width = '100%%';\n" +
                "$img[p].style.height ='auto'\n" +
                "}\n" +
                "}" +
//                "</script><span style='color:#FF6D23;'>" +
                "</script><span style='color:"+color+";'>" +
                content+"</span>"
                + "</body>" +
                "</html>";
        webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+wrapStr,"text/html", "utf-8",null);
    }
    public static void webViewLoadStr(String content, WebView webView ){
        String wrapStr   = "<html> \n" +
                "<head> \n" +
                "<style type=\"text/css\"> \n" +
                "body {font-size:13px;}\n" +
                "</style> \n" +
                "</head> \n" +
                "<body>" +
                "<script type='text/javascript'>" +
                "window.onload = function(){\n" +
                "var $img = document.getElementsByTagName('img');\n" +
                "for(var p in  $img){\n" +
                " $img[p].style.width = '100%%';\n" +
                "$img[p].style.height ='auto'\n" +
                "}\n" +
                "}" +
                "</script>" +
                content
                + "</body>" +
                "</html>";
        webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+wrapStr,"text/html", "utf-8",null);
    }
    public static void copyStr(String label, String content){
        ClipboardManager clipboardManager= (ClipboardManager) MyApplication.getInstance().getSystemService(CLIPBOARD_SERVICE);//?????????clipboardManager??????
        ClipData bankCardNumData=  ClipData.newPlainText(label, content);//??????????????????????????????  newPlainText
        clipboardManager.setPrimaryClip(bankCardNumData);
        showToast( Utils.getString(R.string.????????????));
    }

    public static String  getInstallApps(Context  context){
        ArrayList<String> appList = new ArrayList<>();
        Map<String, String> appMap = new HashMap<>();
        appMap.put("QQ","com.tencent.mobileqq");
        appMap.put("wechat","com.tencent.mm");
        appMap.put("alipay","com.eg.android.AlipayGphone");
        appMap.put("tiktok","com.ss.android.ugc.aweme");
        for (String key : appMap.keySet()) {
            boolean installApp = isInstallApp(context,appMap.get(key));
            if(installApp){
                appList.add(key);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<appList.size(); i++){
            sb.append(appList.get(i)).append(",");
        }
        if(StringMyUtil.isEmptyString(sb)){
            return "";
        }
        return  sb.substring(0, sb.length() - 1);
    }

    public static boolean isInstallApp(Context context,String packageName) {
        PackageManager manager = context.getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(0);
        for (int i = 0; i < pkgList.size(); i++) {
            PackageInfo pI = pkgList.get(i);
            if (pI.packageName.equalsIgnoreCase(packageName)){
                return true ;
            };
        }
        return false;
    }
    public static String getRandomAppDownloadUrl(){
        String appDownUrl = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.APP_DOWN_URL, "");
        List<String> appDownloadUrlList = Arrays.asList(appDownUrl.split(","));
        if(appDownloadUrlList.size()==0){
            return "";
        }
        int randomIndex = new Random().nextInt(appDownloadUrlList.size());
        String s = appDownloadUrlList.get(randomIndex);
        if(!s.endsWith("/")){
            s+="/";
        }
        return s;
    }

    /**
     * ?????????????????????????????????????????????
     * @param editText
     * @param count ??????????????????
     * @param s onTextChanged????????????CharSequence
     */
    public static void limitEtvBigDecimal(EditText editText ,int count,CharSequence s){
        //????????????????????????
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > count) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + count+1);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }
        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }


    public static UserInfoEntity getUserInfoEntity() {
        UserInfoEntity userInfoEntity=null;
        String userInfo = SharePreferencesUtil.getString(MyApplication.getInstance(), "userInfo", "");
        if(StringMyUtil.isNotEmpty(userInfo)){
            userInfoEntity = JSONObject.parseObject(userInfo, UserInfoEntity.class);
        }
        return userInfoEntity;
    }

    public static void RequestUsingEquipment(Activity activity, Fragment fragment){
        HashMap<String, Object> data = new HashMap<>();
        HttpApiUtils.CPnormalRequest(activity, fragment, RequestUtil.USING_EQUIPMENT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(MyApplication.getInstance(), CommonStr.USING_EQUIPMENT,result);

            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    public static String getApiHost() {
        String apiHost;
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if (sp.getAppVestFlag() == 1) {
            SecurityConnection conn = AppVest.getServerIPAndPort("www.tiantian.com", 1002);
            apiHost = String.format("http://%s:%s/web/ws/", conn.getServerIp(), conn.getServerPort());
        } else {
            apiHost = API_HOST1;
        }
        return apiHost;
    }
    public static UsingEquipmentEntity getUsingEquipmentEntity() {
        String s = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.USING_EQUIPMENT, "");
        if(StringMyUtil.isEmptyString(s)){
            return null;
        }
        UsingEquipmentEntity usingEquipmentEntity = JSONObject.parseObject(s, UsingEquipmentEntity.class);
        return usingEquipmentEntity;
    }



    public  interface AccessTxtListener{
        void success(String content);
        void fail(int code);

    }
    AccessTxtListener accessTxtListener;

    public void setAccessTxtListener(AccessTxtListener accessTxtListener) {
        this.accessTxtListener = accessTxtListener;
    }
    public static void logE(String tag,String msg){
        if(BuildConfig.DEBUG){
          Log.e(tag,msg);
        }
    }

    /**
     * ???????????????????????????????????????????????????????????????????????????
     * @author fenggaopan 2015???7???21??? ??????9:49:40
     * @param cardNum ????????????????????????
     * @return ??????????????????
     */
    public static boolean judgeContainsStr(String cardNum) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m=Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }
    public static void main(String[] args) {

        String test = "abc123\uD83D\uDE0B";
        // ??????????????????
        for (int i = 0; i < test.length(); i++) {
            char item = test.charAt(i);
            System.out.println(String.valueOf(item));
        }
    }

    public static boolean containsEmoji(String source) {
        boolean isEmoji =false;
        if(source.length()==1){
            char hs = source.charAt(0);
            isEmoji=  isEmojiCharacter(hs);
        }

        return isEmoji;
    }

    /**
     * ?????????emoji??????
     * @param codePoint
     * @return
     */
    public static boolean isEmojiCharacter(char codePoint) {
        if ((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)))
            return false;
        return true;
    }

    /**
     * ??????emoji ?????? ??????????????????????????????
     * @param source
     * @return
     */
    public  static  String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    public static String getHomeLogo(String key){
        String homeLogo;
        String homeLogos = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.HOME_LOGO, "");
        if(StringMyUtil.isEmptyString(homeLogos)){
            homeLogo = "";
        }else {
            JSONObject jsonObject = JSONObject.parseObject(homeLogos);
            homeLogo= jsonObject.getString(key);
        }
        return StringMyUtil.isEmptyString(homeLogo)?"":homeLogo;
    }
    public static int getWidth(Context context){
        int mScreenWidth = ScreenUtils.getWight(context);
        return (mScreenWidth-ScreenUtils.dip2px(context,8)-ScreenUtils.dip2px(context, 8))/2;
    }
    public static  liveActivityEntity getLiveActivityArray(){
        String activityJsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.EXTENSTION_MOTICEINFO10, "");
        if(StringMyUtil.isEmptyString(activityJsonStr)){
            return null;
        }
        liveActivityEntity liveActivityEntity = JSONObject.parseObject(activityJsonStr, liveActivityEntity.class);
        return liveActivityEntity;
    }
    public static String getString(int resId){
        return MyApplication.getInstance().getString(resId);
    }

    public static boolean isInt(String content) {
        if(TextUtils.isEmpty(content)){
            return false;
        }
        boolean isInt = true;
        try {
            Integer.parseInt(content);
        } catch (Exception e) {
            isInt = false;
        }
        if(!isInt){
            return false;
        }
        return true;
    }
    public static boolean isNotInt(String content) {
        return !isInt(content);
    }
    public static boolean isJson(String content) {
        if(TextUtils.isEmpty(content)){
            return false;
        }
        boolean isJsonObject = true;

        try {
            JSONObject.parseObject(content);
        } catch (Exception e) {
            isJsonObject = false;
        }

        if(!isJsonObject ){ //??????json??????
            return false;
        }
        return true;
    }

    public static String filtrationChineseEnglishNumberEmoji(String res) {
        ArrayList<String> array = new ArrayList<>();
        String temp = res;
        for (int i = 0; i < res.length(); i++) {
            if (temp.length() == 0) {
                break;
            }
            int offset = temp.offsetByCodePoints(0, temp.codePointCount(0, 1));
            String substring = temp.substring(0, offset);
            temp = temp.substring(offset);
            array.add(substring);
        }
        String result="";
        boolean containsLetter = Utils.judgeContainsStr(res);
        int limitCharNum = 0;
        for (int i = 0; i < array.size(); i++) {
            String valueOf = array.get(i);
            boolean isLimitChar = CommonStr.kChatRoomLimitStrings.contains(valueOf);
            if(isLimitChar){
                limitCharNum++;
            }
            if(!((isLimitChar && containsLetter && limitCharNum > 4) || (isLimitChar && !containsLetter && limitCharNum > 7)) &&
                    (CommonStr.fuhao.contains(valueOf)|| ((isChineseEnglishNumber(valueOf)||hasEmoji(valueOf))  && !CommonStr.kChatRoomForbiddenStrings.contains(valueOf))||valueOf.equals(" ")||valueOf.equals("???"))){
                result+=valueOf;
            }else {
                result+="*";
            }
        }
        return result;
    }


    /**
     * ??????????????? ?????? ??????
     * @param res
     * @return
     */
    public static boolean isChineseEnglishNumber(String res) {
        boolean flog = false;
        Matcher m = Pattern.compile("^[a-zA-Z0-9\\\\u4E00-\\u9FFF]$").matcher(res);
        if(m.matches()){
            flog = true;
        }
        return flog;
    }
    public static boolean hasEmoji(String content){

        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }



    public static void initSkipVisitorSafeCenterPop(Context context,Activity activity){
           CommonTipPop  commonTipPop = new CommonTipPop(context,activity,Utils.getString(R.string.????????????),Utils.getString(R.string.?????????????????????????????????),Utils.getString(R.string.????????????));
            commonTipPop.setOnClickLintener(new CommonTipPop.OnClickLintener() {
                @Override
                public void onSureClick(View view) {
                    //????????????????????????
                    context.startActivity(new Intent(context, VisitorSafeCenterActivity.class));
                    commonTipPop.dismiss();
                }
            });

        if(!commonTipPop.isShowing()){
            commonTipPop.showAtLocation(Utils.getContentView(activity), Gravity.CENTER,0,0);
            ProgressDialogUtil.darkenBackground(activity,0.7f);
        }
    }
    public static boolean passwordIsEmpty(){
        String password = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.USER_PASSWORD, "");
        boolean isEmpty = StringMyUtil.isEmptyString(password);
        return isEmpty;
    }
    public static boolean isLong(String content) {
        if(StringMyUtil.isEmptyString(content)){
            return false;
        }
        boolean isLong = true;
        try {
            Long.parseLong(content);
        } catch (Exception e) {
            isLong = false;
        }
        if(!isLong){
            return false;
        }
        return true;
    }
    public static boolean isNotLong(String content) {
        return !isLong(content);
    }


    public static boolean isDouble(String content) {
        if(StringMyUtil.isEmptyString(content)){
            return false;
        }
        boolean isDouble = true;
        try {
            Double.parseDouble(content);
        } catch (Exception e) {
            isDouble = false;
        }
        if(!isDouble){
            return false;
        }
        return true;
    }

    public static boolean isNotDouble(String content) {
        return  !isDouble(content);
    }
    public static boolean filterBetMessage(LiveShareBetMessage liveShareBetMessage) {
        boolean isPass = false;
        boolean haveTypeName = false;
        String typename = liveShareBetMessage.getTypename();
        NavigateEntity navigateEntity = NavigateSingleEntity.newInstance().getNavigateEntity();
        if(navigateEntity!=null){
            List<NavigateEntity.GameClassListBean> gameClassList = navigateEntity.getGameClassList();
            if(gameClassList!=null && gameClassList.size()!=0){
                for (int i = 0; i < gameClassList.size(); i++) {
                    NavigateEntity.GameClassListBean gameClassListBean = gameClassList.get(i);
                    if(typename.equals(gameClassListBean.getTypename())){
                        haveTypeName =true;
                        break;
                    }
                }
            }
            if(haveTypeName){
                String groupname = liveShareBetMessage.getGroupname();
                List<String> nameList = Arrays.asList(liveShareBetMessage.getName().split(","));
                if(liveShareBetMessage.getGame().equals("1")){
                    //??????
                    if(groupname.equals(Utils.getString(R.string.??????)) || groupname.equals(Utils.getString(R.string.??????)) || groupname.equals(Utils.getString(R.string.??????))){
                        if(groupname.equals(Utils.getString(R.string.??????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.??????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals("1") || name.equals("2") || name.equals("3") || name.equals("4") || name.equals("5") || name.equals("6") ){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.??????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals("1_1") || name.equals("2_2") || name.equals("3_3") || name.equals("4_4") || name.equals("5_5") || name.equals("6_6") ){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                    }
                }else if(liveShareBetMessage.getGame().equals("2")){
                    //?????????
                    if(groupname.equals(Utils.getString(R.string.???????????????)) || groupname.equals(Utils.getString(R.string.???????????????)) || groupname.equals(Utils.getString(R.string.??????)) ){
                        if(groupname.equals(Utils.getString(R.string.???????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }

                        if(groupname.equals(Utils.getString(R.string.???????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.??????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                    }
                }else if(liveShareBetMessage.getGame().equals("3")){
                    //??????
                    if(groupname.equals(Utils.getString(R.string.????????????)) || groupname.equals(Utils.getString(R.string.????????????)) || groupname.equals(Utils.getString(R.string.?????????)) ){
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals("1") || name.equals("2") || name.equals("3") || name.equals("4") || name.equals("5") || name.equals("6")  || name.equals("7") || name.equals("8") ){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.?????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                    }
                }else if(liveShareBetMessage.getGame().equals("4")){
                    //?????????
                    if(groupname.equals(Utils.getString(R.string.????????????)) || groupname.equals(Utils.getString(R.string.????????????)) || groupname.equals(Utils.getString(R.string.????????????))  || groupname.equals(Utils.getString(R.string.????????????)) ){
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???))  || name.equals(Utils.getString(R.string.???))  || name.equals(Utils.getString(R.string.???))   || name.equals(Utils.getString(R.string.???))  || name.equals(Utils.getString(R.string.???))  || name.equals(Utils.getString(R.string.???))  || name.equals(Utils.getString(R.string.???))  || name.equals(Utils.getString(R.string.???))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                    }
                }else if(liveShareBetMessage.getGame().equals("5")){
                    //pc??????
                    if(groupname.equals(Utils.getString(R.string.????????????)) || groupname.equals(Utils.getString(R.string.??????)) || groupname.equals(Utils.getString(R.string.??????)) ){
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.??????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.??????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????)) || name.equals(Utils.getString(R.string.??????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                    }
                }else if(liveShareBetMessage.getGame().equals("9")){
                    if(groupname.equals(Utils.getString(R.string.???????????????)) || groupname.equals(Utils.getString(R.string.????????????)) || groupname.equals(Utils.getString(R.string.??????)) ){
                        if(groupname.equals(Utils.getString(R.string.???????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.????????????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????)) || name.equals(Utils.getString(R.string.?????????))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                        if(groupname.equals(Utils.getString(R.string.??????))){
                            int passCount = 0;
                            for (int i = 0; i < nameList.size(); i++) {
                                String name = nameList.get(i);
                                if(name.equals(Utils.getString(R.string.???)) || name.equals(Utils.getString(R.string.???))){
                                    passCount++;
                                }
                            }
                            if(passCount == nameList.size()){
                                isPass = true;
                            }
                        }
                    }
                }

            }
            List<String> betAmountList = Arrays.asList(liveShareBetMessage.getLotmoney().split(","));
            double betAmount = 0f;
            for (int i = 0; i < betAmountList.size(); i++) {
                betAmount+=Double.parseDouble(betAmountList.get(i));
            }
            if(betAmount<=12000){
                isPass = true;
            }
        }

        return  isPass;
    }

    /**
     * ????????????
     * @param min
     * @param max
     * @return
     */
    public static BigDecimal getRandomRewardPriceWeenMinAndMax(BigDecimal min, BigDecimal max){
        float minF = min.floatValue();
        float maxF = max.floatValue();
        //???????????????
        BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);

        //????????????????????????????????????????????????????????????
        return db.setScale(2,BigDecimal.ROUND_DOWN);
    }
}

