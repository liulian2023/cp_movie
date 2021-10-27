package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow.CommisionPlanPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommissionPlanModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.QRCodeUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SavePhoto;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import pub.devrel.easypermissions.EasyPermissions;

public class MineQRCodeActivity extends BaseActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks {
    @BindView(R.id.qr_code_back_iv)
    ImageView backIv;
    @BindView(R.id.app_name_tv)
    TextView app_name_tv;
    @BindView(R.id.qr_code_iv)
    ImageView qr_code_iv;
    @BindView(R.id.invite_code_tv)
    TextView invite_code_tv;
    @BindView(R.id.down_url_tv)
    TextView down_url_tv;
    @BindView(R.id.commison_plan_iv)
    ImageView commison_plan_iv;
    @BindView(R.id.commison_plan_tv)
    TextView commison_plan_tv;
    @BindView(R.id.save_image_tv)
    TextView save_image_tv;
    @BindView(R.id.copy_url_tv)
    TextView copy_url_tv;
    @BindView(R.id.papularize_tv)
    TextView papularize_tv;
    @BindView(R.id.constraintLayout4)
    ConstraintLayout constraintLayout4;
    private String[] PERMISSIONS={"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private int REQUEST_PERMISSONS_CODE=1;
    CommisionPlanPop commisionPlanPop;
    String inviteShareUrl;
    private String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_qrcode);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.transparent));
        initClick();
        requestCodeList();
        if(SharePreferencesUtil.getString(this,"isInfiniteAgent","").equals("1")){
            constraintLayout4.setVisibility(View.GONE);
        }else {
            constraintLayout4.setVisibility(View.VISIBLE);
        }
    }

    private void initClick() {
         appName = SharePreferencesUtil.getString(MyApplication.getInstance(), "appName", "");
        app_name_tv.setText(appName);
        backIv.setOnClickListener(this);
        commison_plan_tv.setOnClickListener(this);
        commison_plan_iv.setOnClickListener(this);
        save_image_tv.setOnClickListener(this);
        copy_url_tv.setOnClickListener(this);
        papularize_tv.setOnClickListener(this);
    }

    /**
     * 二维码生成
     * @param inviteCodeShareUrl 邀请链接
     */
    private void initQrCode(String inviteCodeShareUrl) {
        Bitmap qrImage = QRCodeUtil.createQRImage(inviteCodeShareUrl, Utils.dip2px(MyApplication.getInstance(),152), Utils.dip2px(MyApplication.getInstance(),152), ErrorCorrectionLevel.Q);
        qr_code_iv.setImageBitmap(qrImage);
    }

    /**
     *请求邀请码列表(取第一个用于生成二维码和邀请链接)
     */
    private void requestCodeList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",8);
        data.put("isagent",1);
        HttpApiUtils.CPnormalRequest(MineQRCodeActivity.this,null,RequestUtil.REQUEST_FINDCODE,data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                CommissionPlanModel commissionPlanModel = gson.fromJson(result, CommissionPlanModel.class);

//                CommissionPlanModel commissionPlanModel = JSONObject.parseObject(result, CommissionPlanModel.class);
                List<CommissionPlanModel.CodeListBean> codeList = commissionPlanModel.getCodeList();
                if(codeList!=null&&codeList.size()!=0){
                    CommissionPlanModel.CodeListBean codeListBean = codeList.get(0);
                    updateUi(codeListBean);
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    /**
     * 页面ui的更新
     * @param codeListBean
     */
    private void updateUi(CommissionPlanModel.CodeListBean codeListBean) {
        String useWxTgQrCode = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.WE_CHAT_INVITE_QR_CODE, "0");//0否；1是(启用微信推广二维码)
        String wxTgQrCodeDesc = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.WE_CHAT_INVITE_QR_CODE_CONTENT, "");//微信推广二维码描述
        String appDownloadUrl = Utils.getRandomAppDownloadUrl();
        String inviteCodeShareUrl = appDownloadUrl + "?code=" + codeListBean.getInviteCode();
        if(useWxTgQrCode.equals("0")){
            initQrCode(inviteCodeShareUrl);//初始化二维码
        }else {
            String qrCodeContent = String.format("https://rd.wechat.com/qrcode/confirm?block_type=101&content=%sAPP下载地址：\n%s\n请将网址复制到浏览器打开（长按即可复制网址）%s&lang=zh_CN&scene=4",appName,inviteCodeShareUrl,wxTgQrCodeDesc);
            initQrCode(qrCodeContent);
        }
        invite_code_tv.setText(codeListBean.getInviteCode()); //二维码下方的邀请码
        commison_plan_tv.setText(codeListBean.getInviteCode());//推广计划中的邀请码
        inviteShareUrl= inviteCodeShareUrl;//直接分享和复制链接需要的邀请码
        down_url_tv.setText(Utils.getString(R.string.app下载地址:)+appDownloadUrl.replace("http://","").replace("https://",""));
    }
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.qr_code_back_iv:
                    finish();
                    break;
                    //推广方案(邀请码列表)
                case R.id.commison_plan_tv:
                case R.id.commison_plan_iv:
                    if(null==commisionPlanPop){
                        commisionPlanPop=  new CommisionPlanPop(MineQRCodeActivity.this,MineQRCodeActivity.this);
                    }
                    commisionPlanPop.showAtLocation(save_image_tv, Gravity.BOTTOM,0,0);
                    commisionPlanPop.setDisMissListener(new CommisionPlanPop.DisMissListener() {
                        @Override
                        public void onDisMissListener(CommissionPlanModel.CodeListBean codeListBean) {
                            if(codeListBean!=null){
                                updateUi(codeListBean);
                            }
                        }
                    });
                    break;
                    //保存海报
                case R.id.save_image_tv:
                    checkReadPermission();
                    break;
                    //复制链接
                case R.id.copy_url_tv:
                    Utils.copyStr("inviteCodeUrl",getIntent().getStringExtra("shareContent")+inviteShareUrl);
                    break;
                //直接推广
                case R.id.papularize_tv:
                    RouteUtils.start2Share(MineQRCodeActivity.this,getIntent().getStringExtra("shareContent")+inviteShareUrl);
                    break;

            }
    }
    private void checkReadPermission() {
        //已经有权限,保存二维码
        if(EasyPermissions.hasPermissions(this,PERMISSIONS)){
            saveQrCode();
        }else {
            //没有权限,申请权限
            EasyPermissions.requestPermissions(this,Utils.getString(R.string.需要获取您的读写权限),REQUEST_PERMISSONS_CODE,PERMISSIONS);
        }
    }
    /*
   保存二维码
    */
    private void saveQrCode() {
                try {
                    SavePhoto savePhoto = new SavePhoto(MineQRCodeActivity.this);
                    savePhoto.SaveBitmapFromView(findViewById(R.id.qr_code_root_view));
                    showToast(Utils.getString(R.string.海报保存成功));
                    setResult(RESULT_OK);
                } catch (ParseException e) {
                    e.printStackTrace();
    }
}

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(requestCode==REQUEST_PERMISSONS_CODE){
            saveQrCode();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showToast(Utils.getString(R.string.为了您的使用体验,请同意相关权限,否则功能无法实现));
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //在权限弹窗中，用户勾选了'不在提示'且拒绝权限的情况触发，可以进行相关dialog提示。
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==commisionPlanPop.TO_ADD_INVETICODE&&resultCode==RESULT_OK){
            commisionPlanPop.requestCodeList(false,false);
        }
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
