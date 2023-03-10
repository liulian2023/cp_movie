package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow.CommisionPlanPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommissionPlanModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.QRCodeUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SavePhoto;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import pub.devrel.easypermissions.EasyPermissions;

public class LiveQrCodePop extends BasePopupWindow2 implements EasyPermissions.PermissionCallbacks {
    ImageView cover_iv;
    ImageView close_iv;
    ImageView qr_code_iv;
    TextView invite_code_tv;
    TextView download_url_tv;
    ImageView change_code_iv;
    Button save_qr_code_btn;
    LiveFragment fragment;
    ConstraintLayout save_constraintLayout;
    LiveEntity.AnchorMemberRoomListBean mLiveData;
    private String[] PERMISSIONS={"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private int REQUEST_PERMISSONS_CODE=1;
    public CommisionPlanPop commisionPlanPop;

    public LiveQrCodePop(Context context, boolean focusable,LiveFragment fragment, LiveEntity.AnchorMemberRoomListBean mLiveData) {
        super(context, focusable);
        this.fragment =fragment;
        this.mLiveData =mLiveData;
        initData();
        requestCodeList();
    }

    private void initData() {
        if(mLiveData!=null){
            if(mLiveData.getIsPrivate()==0){
//                GlideLoadViewUtil.FLoadNormalView(fragment, Utils.CPImageUrlCheck(mContext,mLiveData.getCover()),cover_iv);
                GlideLoadViewUtil.FLoadNormalView(fragment, Utils.CPImageUrlCheck(mContext,mLiveData.getCover()),cover_iv);
            }else {
//                GlideLoadViewUtil.FLoadNormalView(fragment, Utils.checkLiveImageUrl(mLiveData.getCover()),cover_iv);
                GlideLoadViewUtil.FLoadNormalView(fragment, Utils.checkLiveImageUrl(mLiveData.getCover()),cover_iv);
            }

        }
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.live_qr_code_pop_layout,null );
        cover_iv = rootView.findViewById(R.id.cover_iv);
        close_iv = rootView.findViewById(R.id.close_iv);
        qr_code_iv = rootView.findViewById(R.id.qr_code_iv);
        invite_code_tv = rootView.findViewById(R.id.invite_code_tv);
        download_url_tv = rootView.findViewById(R.id.download_url_tv);
        change_code_iv = rootView.findViewById(R.id.change_code_iv);
        save_qr_code_btn = rootView.findViewById(R.id.save_qr_code_btn);
        save_constraintLayout = rootView.findViewById(R.id.save_constraintLayout);
        if(SharePreferencesUtil.getString(MyApplication.getInstance(),"isInfiniteAgent","").equals("1")){
            change_code_iv.setVisibility(View.INVISIBLE);
        }else {
            change_code_iv.setVisibility(View.VISIBLE);
        }
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveQrCodePop.this.dismiss();
            }
        });
        save_qr_code_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkReadPermission();
            }
        });
        /*
        ???????????????pop
         */
        change_code_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null==commisionPlanPop){
                    commisionPlanPop=  new CommisionPlanPop(mContext,fragment);
                }
                commisionPlanPop.showAtLocation(fragment.giftView, Gravity.BOTTOM,0,0);
                commisionPlanPop.setDisMissListener(new CommisionPlanPop.DisMissListener() {
                    @Override
                    public void onDisMissListener(CommissionPlanModel.CodeListBean codeListBean) {
                        if(codeListBean!=null){
                            updateUi(codeListBean);
                        }
                    }
                });
            }
        });
    }
    private void requestCodeList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",8);
        data.put("isagent",1);
        HttpApiUtils.CPnormalRequest(fragment.getActivity(),fragment, RequestUtil.REQUEST_FINDCODE,data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                Map map = JSONObject.parseObject(result, Map.class);
//                CommissionPlanModel commissionPlanModel = JSONObject.parseObject(JSONObject.toJSONString(map), CommissionPlanModel.class);
                Gson gson = new GsonBuilder().serializeNulls().create();
                CommissionPlanModel commissionPlanModel = gson.fromJson(result, CommissionPlanModel.class);
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
     * ??????ui?????????
     * @param codeListBean
     */
    private void updateUi(CommissionPlanModel.CodeListBean codeListBean) {
        String appDownUrl = Utils.getRandomAppDownloadUrl();

        String useWxTgQrCode = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.WE_CHAT_INVITE_QR_CODE, "0");//0??????1???(???????????????????????????)
        String wxTgQrCodeDesc = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.WE_CHAT_INVITE_QR_CODE_CONTENT, "");//???????????????????????????
        String appDownloadUrl = Utils.getRandomAppDownloadUrl();
        String inviteCodeShareUrl = appDownloadUrl + "?code=" + codeListBean.getInviteCode();
        if(useWxTgQrCode.equals("0")){
            initQrCode(inviteCodeShareUrl);//??????????????????
        }else {
            String appName = SharePreferencesUtil.getString(MyApplication.getInstance(), "appName", "");
//            String qrCodeContent = String.format("https://rd.wechat.com/qrcode/confirm?block_type=101&content=%sAPP???????????????\n%s\n??????????????????????????????????????????????????????????????????%s&lang=zh_CN&scene=4",appName,inviteCodeShareUrl,wxTgQrCodeDesc);
            String qrCodeContent = "https://rd.wechat.com/qrcode/confirm?block_type=101&content="+appName+Utils.getString(R.string.APP????????????)+" \n"+inviteCodeShareUrl+"\n"+Utils.getString(R.string.?????????????????????????????????????????????????????????)+wxTgQrCodeDesc+"&lang=zh_CN&scene=4";
            initQrCode(qrCodeContent);
        }
        invite_code_tv.setText(codeListBean.getInviteCode()); //???????????????????????????
        download_url_tv.setText(appDownUrl.replace("http://","").replace("https://",""));
    }

    /**
     * ???????????????
     * @param inviteCodeShareUrl ????????????
     */
    private void initQrCode(String inviteCodeShareUrl) {
        Bitmap qrImage = QRCodeUtil.createQRImage(inviteCodeShareUrl,  200,200, ErrorCorrectionLevel.Q);
        qr_code_iv.setImageBitmap(qrImage);
    }
    private void checkReadPermission() {
        //???????????????,???????????????
        if(EasyPermissions.hasPermissions(mContext,PERMISSIONS)){
            saveQrCode();
        }else {
            //????????????,????????????
            EasyPermissions.requestPermissions(fragment,Utils.getString(R.string.??????????????????????????????),REQUEST_PERMISSONS_CODE,PERMISSIONS);
        }
    }
    /*
???????????????
*/
    private void saveQrCode() {
        try {
            SavePhoto savePhoto = new SavePhoto(mContext);
                savePhoto.SaveBitmapFromView(save_constraintLayout);
            ToastUtil.showToast(Utils.getString(R.string.?????????????????????));
//            setResult(RESULT_OK);
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
       ToastUtil. showToast(Utils.getString(R.string.?????????????????????????????????????????????????????????????????????));
        if (EasyPermissions.somePermissionPermanentlyDenied(fragment, perms)) {
            //????????????????????????????????????'????????????'???????????????????????????????????????????????????dialog?????????
        }
    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==commisionPlanPop.TO_ADD_INVETICODE&&resultCode==RESULT_OK){
            commisionPlanPop.requestCodeList(false,false);
        }
    }*/





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
