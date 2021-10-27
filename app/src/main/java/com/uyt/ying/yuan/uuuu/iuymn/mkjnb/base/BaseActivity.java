package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;

import android.view.Gravity;
import android.view.KeyEvent;

import com.hjq.toast.ToastUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.receiver.NetWorkStateReceiver;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MemoryLeakUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

/**
 * BaseActivity是所有Activity的基类，把一些公共的方法放到里面，如基础样式设置，权限封装，网络状态监听等
 * <p>
 *
 */
public abstract class BaseActivity extends MySupportActivity   implements NetWorkStateReceiver.NetChangeListener{

//    public static NetWorkStateReceiver.NetChangeListener netEvent;// 网络状态改变监听事件
    //网络状态监听库
     NetWorkStateReceiver netWorkStateReceiver;
    public static NetWorkStateChange netEvent;

    private static long clickGapTime = 0;

    protected KProgressHUD mKProgressHUD;

    //  public static final int CLICK_GAP_RESPONSE = 700;//700ms内不响应

    public static class NetWorkStateChange implements NetWorkStateReceiver.NetChangeListener{
         WeakReference<BaseActivity>baseActivityWeakReference;

        public WeakReference<BaseActivity> getBaseActivityWeakReference() {
            return baseActivityWeakReference;
        }

        public void setBaseActivityWeakReference(WeakReference<BaseActivity> baseActivityWeakReference) {
            this.baseActivityWeakReference = baseActivityWeakReference;
        }

        public NetWorkStateChange(WeakReference<BaseActivity> baseActivityWeakReference) {
            this.baseActivityWeakReference = baseActivityWeakReference;
        }

        @Override
        public void onNetChange(boolean netWorkState) {

        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // 添加到Activity工具类
        ActivityUtil.getInstance().addActivity(this);
//        ProgressDialogUtil.show(this);
        // 执行初始化方法
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        init();
        // 初始化netEvent
        netEvent= new NetWorkStateChange(new WeakReference<>(this));
    }



    // 抽象 - 初始化方法，可以对数据进行初始化
    protected abstract void init();



    //请求列表数据失败时,重新加载
    public void errorRefresh(){

    }


    public void showLoading() {
        if(mKProgressHUD==null){
            mKProgressHUD = KProgressHUD.create(this);
        }
        if(!mKProgressHUD.isShowing()){
            mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();

        }

    }
    public void showLoading(String tipContent) {
        mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setLabel(tipContent)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
    public void showLoadingNoAlpha() {
        mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
            /*    .setDimAmount(1f)*/
                .show();
    }
    public void showLoadingNoAlpha(String tipContent) {
        mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                /*    .setDimAmount(1f)*/
                .show();
    }
    public void closeLoading() {
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());


        Utils.logE("",Utils.getString(R.string.注册));
        MobclickAgent.onResume(this);
        if (MyApplication.isBackNoPermission()) {
            MyApplication.setBackNoPermission(false);
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            isExit.setTitle(Utils.getString(R.string.温馨提示));
            isExit.setMessage(Utils.getString(R.string.是否要开启聊天室悬浮球?));
            isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                }
            });
            isExit.show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 移除Activity
            ActivityUtil.getInstance().removeActivity(this);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showToast(String str){
        if(StringMyUtil.isEmptyString(str)||str.equalsIgnoreCase("timeout")||str.contains(Utils.getString(R.string.接口请求频繁))){
            return;
        }
        ToastUtils.setGravity(Gravity.CENTER,0,0);
        ToastUtils.show(str);
    }

    /**
     * 防止点击过快
     * @param CLICK_GAP_RESPONSE   设置的时长  700毫秒
     * @return
     */
    protected boolean clickGapFilter(int CLICK_GAP_RESPONSE){
        long currentTimeMillis = System.currentTimeMillis();
        if(currentTimeMillis-clickGapTime< CLICK_GAP_RESPONSE){
            return false;
        }
        clickGapTime = currentTimeMillis;
        return true;
    }
    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除Activity
        ActivityUtil.getInstance().removeActivity(this);
        unregisterReceiver(netWorkStateReceiver);
        Utils.logE("",Utils.getString(R.string.注销));
        ProgressDialogUtil.stop(this);
//        ActivityStack.getInstance().remove(this);
        MemoryLeakUtil.fixLeak(this);
        closeLoading();

    }
    /**
     * 网络状态改变时间监听
     *
//     * @param netWorkState true有网络，false无网络
     */
/*    @Override
    public void onNetChange(boolean netWorkState) {

    }*/
    public void hideDialog(){
        ProgressDialogUtil.stop(this);
    }
}