package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManagerTypeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

public class CusNotePop extends PopupWindow {


    private Activity mActivity;
    private LiveFragment liveFragment;
    private final View view;

    private ImageView iv_openjiang_record;
    private ImageView iv_bet_record;
    private ImageView iv_recharge_center;
    private ImageView iv_station_privateletter;
    private ImageView iv_task_center_iv;
    private ImageView take_diamond_iv;
    private ImageView rorate_iv;
    private ImageView equipment_iv;
    private TextView iv_task_center_tv;
    private TextView forbidden_tv;
    private TextView take_diamond_tv;
    private TextView rorate_tv;
    private TextView equipment_tv;
    private ImageView forbidden_iv;
    ManagerTypeEntity managerTypeEntity;
    public enum CLICKTYPE{
        OPENRESULT,
        BETRESULT,
        RECHARGECENTER,
        PRILETTER,
        TASKCENTER,
        FORBIDDEN,
        DIAMOND,
        TURNTABLE,
        EQUIPMENT
    }

    public interface OnItemClickListener {
        void onItemClick(CLICKTYPE clickType);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public CusNotePop(Activity mActivity, LiveFragment liveFragment, ManagerTypeEntity managerTypeEntity) {
        super(mActivity);
        this.managerTypeEntity = managerTypeEntity;
        this.mActivity = mActivity;
        this.liveFragment = liveFragment;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.note_pop_layout, null);

        initView();
        initPopWindow();

    }


    private void initView(){




        take_diamond_tv = view.findViewById(R.id.take_diamond_tv);
        rorate_tv = view.findViewById(R.id.rorate_tv);
        equipment_tv = view.findViewById(R.id.equipment_tv);
        take_diamond_iv = view.findViewById(R.id.take_diamond_iv);
        rorate_iv = view.findViewById(R.id.rorate_iv);
        equipment_iv = view.findViewById(R.id.equipment_iv);

        iv_openjiang_record = view.findViewById(R.id.iv_openjiang_record);
        iv_bet_record = view.findViewById(R.id.iv_bet_record);
        iv_recharge_center = view.findViewById(R.id.iv_recharge_center);
        iv_station_privateletter = view.findViewById(R.id.iv_station_privateletter);
        iv_task_center_iv = view.findViewById(R.id.iv_task_center_iv);
        iv_task_center_tv = view.findViewById(R.id.iv_task_center_tv);
        forbidden_tv=view.findViewById(R.id.forbidden_tv);
        forbidden_iv=view.findViewById(R.id.forbidden_iv);
        if(managerTypeEntity!=null){
            if(managerTypeEntity.getIsSuperRoomManager().equals("1")||managerTypeEntity.getIsRoomManager().equals("1")){
                forbidden_tv.setVisibility(View.VISIBLE);
                forbidden_iv.setVisibility(View.VISIBLE);
            }else {
                forbidden_tv.setVisibility(View.GONE);
                forbidden_iv.setVisibility(View.GONE);
            }
        }else {
            forbidden_tv.setVisibility(View.GONE);
            forbidden_iv.setVisibility(View.GONE);
        }

        if(liveFragment!=null&& StringMyUtil.isNotEmpty(liveFragment.turntableStatus)){
            setTurntableStatus();
        }
        iv_openjiang_record.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.OPENRESULT));
        iv_bet_record.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.BETRESULT));
        iv_recharge_center.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.RECHARGECENTER));
        iv_station_privateletter.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.PRILETTER));
        iv_task_center_iv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.TASKCENTER));
        iv_task_center_tv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.TASKCENTER));
        forbidden_tv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.FORBIDDEN));
        forbidden_iv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.FORBIDDEN));
        take_diamond_tv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.DIAMOND));
        take_diamond_iv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.DIAMOND));
        rorate_tv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.TURNTABLE));
        rorate_iv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.TURNTABLE));
        equipment_iv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.EQUIPMENT));
        equipment_tv.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.EQUIPMENT));

    }

    public void setTurntableStatus() {
        if(liveFragment.turntableStatus.equals("0")){
            rorate_iv.setVisibility(View.GONE);
            rorate_tv.setVisibility(View.GONE);
        }else {
            rorate_iv.setVisibility(View.VISIBLE);
            rorate_tv.setVisibility(View.VISIBLE);
        }
    }

    /*
        private void OpenActivityRankPop() {
        if (mCusRankPop == null) {
            mCusRankPop = new CusActivityRankPop(_mActivity, this);
        }
        mCusRankPop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        mCusRankPop.backgroundAlpha(_mActivity, 0.5f);
        mCusRankPop.setOnMoreClickListener((PackType type) -> {
            mCusRankPop.dismiss();
            switch (type) {
                case QY:
                    showPackPopType(QY);
                    break;
                case ZX:
                    showPackPopType(ZX);
                    break;
                case TJ:
                    showPackPopType(TJ);
                    break;
            }
        });
    }
     */
    private void initPopWindow(){
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.pop_bottom_to_top_150);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(mActivity, 0.5f);//0.0-1.0
        this.setOnDismissListener(() ->
                backgroundAlpha(mActivity, 1f)
        );
    }

    /**
     * 设置添加屏幕的背景透明度(值越大,透明度越高)
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1f) {
            //恢复屏幕亮度时需要移除flag,否则在切换activity时会有短暂黑屏
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        context.getWindow().setAttributes(lp);
    }



}
