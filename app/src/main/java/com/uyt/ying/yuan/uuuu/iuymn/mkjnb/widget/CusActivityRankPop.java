package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.PackType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.ActivityRankAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ActivityRankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class CusActivityRankPop extends BasePopupWindow {

    private Activity mActivity;
    private Fragment mContext;

    private final View view;
    private TextView tv_activityrank_msg;
    private RecyclerView mRecyclerView;
    private ActivityRankAdapter mAdapter;
    private LiveFragmentViewModel mViewModel;
    private List<ActivityRankEntity.DataBean> mDataList = new ArrayList<>();

    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private LinearLayout nodataLinear;
    private TextView reloadTv;
    private   RongcloudHBParameter hbParameter;


    /**
     * 查看更多回调
     */
    public interface OnMoreClickListener {
        void onMoreClick(PackType type);
    }

    private OnMoreClickListener mOnMoreClickListener;

    public void setOnMoreClickListener(OnMoreClickListener listener) {
        this.mOnMoreClickListener = listener;
    }

    public CusActivityRankPop(Activity mActivity, Fragment mContext) {
        super(mActivity);
        this.mActivity = mActivity;
        this.mContext = mContext;
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_activityrank, null);
        mViewModel = ViewModelProviders.of(mContext).get(LiveFragmentViewModel.class);
        initView();
        initPopWindow();
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        initData();
    }


    private void initView() {
        reloadTv = view.findViewById(R.id.reload_tv);
        loadingLinear = view.findViewById(R.id.loading_linear);
        nodataLinear=view.findViewById(R.id.nodata_linear);
        errorLinear=view.findViewById(R.id.error_linear);
        mRecyclerView = view.findViewById(R.id.recy_activityrank);
        mAdapter = new ActivityRankAdapter(R.layout.item_activityrank_recy, mDataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(16));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        tv_activityrank_msg = view.findViewById(R.id.tv_activityrank_msg);
        /**
         * recy_item 查看更多点击
         */
        mAdapter.addChildClickViewIds(R.id.tv_item_activityrank_more);
        mAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            if (view.getId() == R.id.tv_item_activityrank_more) {
            //    ToastUtils.showToast(Utils.getString(R.string.活动榜单Item查看更多点击:) + position);
                ActivityRankEntity.DataBean item = (ActivityRankEntity.DataBean) adapter.getItem(position);
                int activityType = item.getActivityType();
                if (activityType == PackType.QY.getValue()) {
                    mOnMoreClickListener.onMoreClick(PackType.QY);
                } else if (activityType == PackType.TJ.getValue()) {
                    mOnMoreClickListener.onMoreClick(PackType.TJ);
                } else if (activityType == PackType.ZX.getValue()) {
                    mOnMoreClickListener.onMoreClick(PackType.ZX);
                }
            }
        });

/*      *//**
         * 观察rankLiveData
         *//*
        mViewModel.getRankLiveData().observe(mContext, (ActivityRankEntity entity) -> {
            mDataList.clear();
            mDataList.addAll(entity.getData());
            mAdapter.notifyDataSetChanged();
        });*/
    }

    public void initData() {
        if(hbParameter==null){
            requestAtyStatus();
        }else {
            handlerJson();
        }
    }

    private void requestAtyStatus() {
        HttpApiUtils.cpPopShowLoadRequest(mActivity, this, RequestUtil.HB_PARAMETER, new HashMap<String, Object>(), loadingLinear, errorLinear, reloadTv, mRecyclerView, false, false, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                hbParameter = JSONObject.parseObject(result, RongcloudHBParameter.class);
                RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
                int zxHBSwitch = rongcloudHBParameter.getZxHBSwitch();
//                if(zxHBSwitch==1){
                    String zxHBGameClassIds = rongcloudHBParameter.getZxHBGameClassIds();
                    HbGameClassModel instance = HbGameClassModel.getInstance();
                    instance.setGameIdListStr(zxHBSwitch==1?zxHBGameClassIds:"");
                    instance.setHbParameter(hbParameter);
                    EventBus.getDefault().postSticky(instance);
//                }
                handlerJson();

            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void handlerJson() {
        mDataList.clear();
        ActivityRankEntity rankEntity = new ActivityRankEntity();
        RongcloudHBParameter.RongcloudHBParameterBean parameter = hbParameter.getRongcloudHBParameter();
        //switch == 1  开关打开
        int quYueHBSwitch = parameter.getQuYueHBSwitch();
        int tjHBSwitch = parameter.getTjHBSwitch();
        int zxHBSwitch = parameter.getZxHBSwitch();

        if (quYueHBSwitch == 1) {
            //趣约
            ActivityRankEntity.DataBean dataBean = new ActivityRankEntity.DataBean();
            dataBean.setSwitch(1);
            dataBean.setImageResId(R.drawable.ic_activity_qy);
            dataBean.setActivityType(PackType.QY.getValue());
            dataBean.setContent(hbParameter);
            mDataList.add(dataBean);
        }
        if (tjHBSwitch == 1) {
            //天降
            ActivityRankEntity.DataBean dataBean = new ActivityRankEntity.DataBean();
            dataBean.setSwitch(1);
            dataBean.setImageResId(R.drawable.ic_activity_tj);
            dataBean.setActivityType(PackType.TJ.getValue());
            dataBean.setContent(hbParameter);
            mDataList.add(dataBean);
        }
        if (zxHBSwitch == 1) {
            //专享
            ActivityRankEntity.DataBean dataBean = new ActivityRankEntity.DataBean();
            dataBean.setSwitch(1);
            dataBean = new ActivityRankEntity.DataBean();
            dataBean.setImageResId(R.drawable.ic_activity_zx);
            dataBean.setActivityType(PackType.ZX.getValue());
            dataBean.setContent(hbParameter);
            mDataList.add(dataBean);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initPopWindow() {
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.pop_bottom_to_top_150);
        //    ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        //  this.setBackgroundDrawable(dw);
        backgroundAlpha(mActivity, 0.5f);//0.0-1.0


        this.setOnDismissListener(() -> {
            backgroundAlpha(mActivity, 1f);
        });
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
