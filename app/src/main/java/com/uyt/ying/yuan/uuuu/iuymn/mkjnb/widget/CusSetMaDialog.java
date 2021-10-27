package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class CusSetMaDialog extends Dialog {
    public CusSetMaDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{

        private View mLayout;
        private TextView mTitle;
        private TextView mMessage;
        private Button mNo;
        private Button mYes;
        private EditText mEditText;

        private CusSetMaDialog mDialog;

        private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
        private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

        public interface onYesOnclickListener {
             void onYesClick(View v,int value);
        }

        public interface onNoOnclickListener {
             void onNoClick(View v);
        }

        public Builder(Context context ){
            mDialog = new CusSetMaDialog(context,R.style.Theme_AppCompat_Dialog);
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mLayout = layoutInflater.inflate(R.layout.dialog_setma,null);
            mTitle = mLayout.findViewById(R.id.title);
            mMessage = mLayout.findViewById(R.id.message);
            mEditText = mLayout.findViewById(R.id.et_value);
            mNo = mLayout.findViewById(R.id.no);
            mYes = mLayout.findViewById(R.id.yes);
        }

        public Builder setTitle(@NonNull String title) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setMessage(@NonNull String message) {
            mMessage.setText(message);
            mMessage.setVisibility(View.VISIBLE);
            return this;
        }

        /**
         * Set text and listener for button no
         */
        public Builder setNo(@NonNull String text, onNoOnclickListener listener) {
            mNo.setText(text);
            noOnclickListener = listener;
            return this;
        }
        /**
         * Set text and listener for button yes
         */
        public Builder setYes(@NonNull String text, onYesOnclickListener listener) {
            mYes.setText(text);
            yesOnclickListener = listener;
            return this;
        }
        //type 1 筹码  2 礼物
        public CusSetMaDialog create(int type) {

            mNo.setOnClickListener(v -> {
                if (noOnclickListener!=null){
                    noOnclickListener.onNoClick(v);
                    mDialog.dismiss();
                }
            });
            mYes.setOnClickListener(v -> {
                if (yesOnclickListener!=null&& !TextUtils.isEmpty(mEditText.getText().toString())){
                    int value = Integer.parseInt(mEditText.getText().toString());
                    if (value>=1&&value<10000){
                        yesOnclickListener.onYesClick(v,value);
                        mDialog.dismiss();
                    }else {
                        if(type==1){
                            ToastUtil.showToast(Utils.getString(R.string.输入的筹码超出范围));
                        }else {
                            ToastUtil.showToast(Utils.getString(R.string.输入的礼物数量超出范围));
                        }
                    }
                }
            });
            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);                //User can click back to close dialog_info
            mDialog.setCanceledOnTouchOutside(false);   //User can not click outside area to close dialog_info
            return mDialog;
        }

    }
}
