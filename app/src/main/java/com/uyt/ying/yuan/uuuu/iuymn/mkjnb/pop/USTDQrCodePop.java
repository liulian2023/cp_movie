package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RechargeModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.QRCodeUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SavePhoto;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.ParseException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class USTDQrCodePop  extends BasePopupWindow2 implements EasyPermissions.PermissionCallbacks{
    ImageView qr_code_iv;
    ImageView close_iv;
    RechargeActivity rechargeActivity;
    RechargeModel.BankAllListBean.RechargeBankListBean rechargeBankListBean;
    ConstraintLayout save_constraintLayout;
    private String[] PERMISSIONS={"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private int REQUEST_PERMISSONS_CODE=1;

    public USTDQrCodePop(Context context, boolean focusable, RechargeActivity rechargeActivity, RechargeModel.BankAllListBean.RechargeBankListBean rechargeBankListBean) {
        super(context, focusable);
        this.rechargeActivity = rechargeActivity;
        this.rechargeBankListBean = rechargeBankListBean;
        initQrCode(rechargeBankListBean.getAccount());
    }
    

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.ustd_qr_code_pop_layout,null );
        qr_code_iv = rootView.findViewById(R.id.qr_code_iv);
        close_iv = rootView.findViewById(R.id.close_iv);
        save_constraintLayout = rootView.findViewById(R.id.save_constraintLayout);
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USTDQrCodePop.this.dismiss();
            }
        });
        qr_code_iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                checkReadPermission();
                return false;
            }
        });

    }



    /**
     * 二维码生成
     * @param inviteCodeShareUrl 邀请链接
     */
    private void initQrCode(String inviteCodeShareUrl) {
        Bitmap qrImage = QRCodeUtil.createQRImage(inviteCodeShareUrl,  200,200, ErrorCorrectionLevel.L);
        qr_code_iv.setImageBitmap(qrImage);
    }
    private void checkReadPermission() {
        //已经有权限,保存二维码
        if(EasyPermissions.hasPermissions(mContext,PERMISSIONS)){
            saveQrCode();
        }else {
            //没有权限,申请权限
            EasyPermissions.requestPermissions(rechargeActivity, Utils.getString(R.string.需要获取您的读写权限),REQUEST_PERMISSONS_CODE,PERMISSIONS);
        }
    }
    /*
保存二维码
*/
    private void saveQrCode() {
        try {
            SavePhoto savePhoto = new SavePhoto(mContext);
            savePhoto.SaveBitmapFromView(save_constraintLayout);
            ToastUtil.showToast(Utils.getString(R.string.二维码保存成功));
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
        ToastUtil. showToast(Utils.getString(R.string.为了您的使用体验请同意相关权限否则功能无法实现));
        if (EasyPermissions.somePermissionPermanentlyDenied(rechargeActivity, perms)) {
            //在权限弹窗中，用户勾选了'不在提示'且拒绝权限的情况触发，可以进行相关dialog提示。
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
