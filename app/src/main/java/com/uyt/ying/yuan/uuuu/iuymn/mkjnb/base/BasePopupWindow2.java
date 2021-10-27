package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;

public class BasePopupWindow2 extends PopupWindow implements View.OnClickListener {
    public View rootView;
    public Context mContext;
    public LayoutInflater inflater;
    private static final int FULL_SCREEN_FLAG =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
    public BasePopupWindow2(Context context,boolean focusable) {
        super(context);
        mContext=context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView();
        initPop(focusable);
    }
    /**
     *
     * @param focusable pop弹出时是否获取焦点(直播界面没有输入框的pop不需要获取焦点,否则每次弹出pop都会把底部虚拟键唤醒)
     */
    public void initPop(boolean focusable) {
        this.setContentView(rootView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//        this.setFocusable(false);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
//        this.getContentView().setSystemUiVisibility(FULL_SCREEN_FLAG);
//        this.setFocusable(focusable);
        //软键盘不会挡着popupwindow
//        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        this.update();
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
/*        this.getContentView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {

//                BasePopupWindow2.this.getContentView().setSystemUiVisibility(FULL_SCREEN_FLAG);
            }
        });*/
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground((Activity)mContext,1f);
                if(mOnDissmissListener!=null){
                    mOnDissmissListener.onDismiss();
                }
//                Utils.hideSoftKeyBoard((Activity) mContext);

            }
        });
    }


    public void initView() {

    }



    @Override
    public void onClick(View v) {
        if(mOnPopClickListener!=null){
            mOnPopClickListener.onPopClick(v);
        }
    }
    public View getView(int viewId){
        return rootView.findViewById(viewId);
    }
    public interface  OnPopClickListener{
        void onPopClick(View view);
    }
    public   OnPopClickListener mOnPopClickListener;

    public void setOnPopClickListener(OnPopClickListener mOnPopClickListener) {
        this.mOnPopClickListener = mOnPopClickListener;
    }

    public interface  OnRecycleItemClick{
        void onPopItemClick(View view,int  position);
    }
    public   OnRecycleItemClick mOnPopItemClick;

    public void setOnPopItemClick(OnRecycleItemClick mOnPopItemClick) {
        this.mOnPopItemClick = mOnPopItemClick;
    }
    public void setFocusableAndupdate(){
        this.setFocusable(true);
        this.update();
    }
    public interface OnDissmissListener{
        void onDismiss();
    }
   public OnDissmissListener mOnDissmissListener;

    public void setmOnDissmissListener(OnDissmissListener mOnDissmissListener) {
        this.mOnDissmissListener = mOnDissmissListener;
    }

    public void errorRefresh(){
    }
}
