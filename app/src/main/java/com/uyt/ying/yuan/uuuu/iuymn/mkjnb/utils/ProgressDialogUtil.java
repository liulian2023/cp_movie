package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.uyt.ying.yuan.R;

import java.lang.ref.WeakReference;

public class ProgressDialogUtil extends Dialog implements DialogInterface.OnCancelListener {

    private WeakReference<Context> mContextRef = new WeakReference<>(null);
    private volatile static ProgressDialogUtil sDialog;

    private ProgressDialogUtil(Context context, CharSequence message) {
        super(context, R.style.CustomProgressDialog);

        mContextRef = new WeakReference<>(context);

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom_progress, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        if (!TextUtils.isEmpty(message)) {
            tvMessage.setText(message);
        }
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(view, lp);
        setOnCancelListener(this);
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        // 点手机返回键等触发Dialog消失，应该取消正在进行的网络请求等
//        Context context = mContextRef.get();
//        if (context != null) {
//
//            MyHttpClient.cancelRequests(context);
//        }
    }

    public static void show(Context context) {
        show(context, "loading...");
    }

    public static void show(Context context, CharSequence message) {
        show(context, message, true);
    }

    public static void show(Context context, CharSequence message, boolean cancelable) {
        if (sDialog != null && sDialog.isShowing()) {
            sDialog.dismiss();
        }

        if (context == null || !(context instanceof Activity)) {
            return;
        }

        sDialog = new ProgressDialogUtil(context, message);
        sDialog.setCancelable(cancelable);

        if (sDialog != null && !sDialog.isShowing() &&(Activity)context!=null&& !((Activity) context).isFinishing()) {
            sDialog.show();
        }
    }

    public static void stop(Context context) {
        if(context instanceof MyApplication&&sDialog != null && sDialog.isShowing()){
            sDialog.dismiss();
        }
        else if (sDialog != null && sDialog.isShowing()&&(Activity)context!=null&&!((Activity)context).isFinishing()) {
            sDialog.dismiss();
        }
        sDialog = null;
    }
    public static void darkenBackground(Activity activity,Float bgcolor) {
        if(activity==null){
            return;
        }
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgcolor;
            if(bgcolor==1f){
                //恢复屏幕亮度时需要移除flag,否则在切换activity时会有短暂黑屏
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }else {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
            activity.getWindow().setAttributes(lp);

    }



}
