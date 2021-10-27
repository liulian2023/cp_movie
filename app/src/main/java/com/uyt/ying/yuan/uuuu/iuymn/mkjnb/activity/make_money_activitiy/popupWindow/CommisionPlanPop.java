package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.AddCommisionPlanActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money.CommisionPlanAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommissionPlanModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class CommisionPlanPop extends BasePopupWindow {
    private  Fragment fragment;
    private  View view;
    Activity activity;
    RecyclerView recyclerView;
    SmartRefreshLayout refreshLayout;
    RelativeLayout addRelativeLayout;
    CommisionPlanAdapter commisionPlanAdapter;
    ConstraintLayout loadingLinear;
    LinearLayout nodataLinear;
    LinearLayout errorLinear;
    TextView reloadTv;
    ArrayList<CommissionPlanModel.CodeListBean> codeListBeanArrayList =  new ArrayList<>();
    int pageNum=1;
    CommissionPlanModel.CodeListBean codeListBean;
    public static int TO_ADD_INVETICODE=1;

    public CommisionPlanPop(Context context, Activity activity) {
        super(context);
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.commision_plan_pop, null);
        initView();
        initReceycle();
        initPop();
    }
    public CommisionPlanPop(Context context, Fragment fragment) {
        super(context);
        this.fragment = fragment;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.commision_plan_pop, null);
        initView();
        initReceycle();
        initPop();
    }
    @Override
    public void errorRefresh() {
        super.errorRefresh();
        pageNum=1;
        requestCodeList(false,false);
    }

    private void initPop() {
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        ProgressDialogUtil.darkenBackground(activity==null?fragment.getActivity():activity,0.7f);
        this.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(activity==null?fragment.getActivity():activity,1f);
                for (int i = 0; i < codeListBeanArrayList.size(); i++) {
                    CommissionPlanModel.CodeListBean codeListBean = codeListBeanArrayList.get(i);
                    if(codeListBean.getInCheck()==1){
                        CommisionPlanPop.this.codeListBean = codeListBean;
                        break;
                    }
                }
                if(disMissListener!=null){
                    disMissListener.onDisMissListener(codeListBean);
                }
            }
        });
    }

    private void initReceycle() {
        commisionPlanAdapter = new CommisionPlanAdapter(codeListBeanArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity==null?fragment.getContext():activity));
        recyclerView.setAdapter(commisionPlanAdapter);
        requestCodeList(false,false);
        commisionPlanAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommissionPlanModel.CodeListBean codeListBean = codeListBeanArrayList.get(position);
                switch (view.getId()){
                    case R.id.show_more_tv:
                        ArrayList<String> rateList = new ArrayList<>();
                        rateList.add(codeListBean.getK3Rate()+"");
                        rateList.add(codeListBean.getSscaiRate()+"");
                        rateList.add(codeListBean.getHappy8Rate()+"");
                        rateList.add(codeListBean.getXuanwuRate()+"");
                        rateList.add(codeListBean.getFarmRate()+"");
                        rateList.add(codeListBean.getRaceRate()+"");
                        rateList.add(codeListBean.getSixRate()+"");
                        rateList.add(codeListBean.getDanRate()+"");
                        rateList.add(codeListBean.getHappytenRate()+"");
                        new ShowReturnPop(activity==null?fragment.getContext():activity,activity==null?fragment.getActivity():activity, rateList);
                        break;
                        default:
                            break;
                }
            }
        });
    }

    public void requestCodeList(boolean isLoadMore,boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",pageNum);
        data.put("pageSize",8);
        data.put("isagent",1);
        HttpApiUtils.cpPopShowLoadRequest(activity==null?fragment.getActivity():activity, this, RequestUtil.REQUEST_FINDCODE, data, loadingLinear, errorLinear, reloadTv, refreshLayout, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
//                CommissionPlanModel commissionPlanModel = JSONObject.parseObject(result, CommissionPlanModel.class);
                Gson gson = new GsonBuilder().serializeNulls().create();
                CommissionPlanModel commissionPlanModel = gson.fromJson(result, CommissionPlanModel.class);
                List<CommissionPlanModel.CodeListBean> codeList = commissionPlanModel.getCodeList();
                int size = codeList.size();
                RefreshUtil.success(pageNum,refreshLayout,loadingLinear,nodataLinear,size,isLoadMore,isRefresh,codeListBeanArrayList);
                codeListBeanArrayList.addAll(codeList);
                boolean haveSelected=false;
                for (int i = 0; i < codeListBeanArrayList.size(); i++) {
                    if(codeListBeanArrayList.get(i).getInCheck()==1){
                        haveSelected=true;
                        break;
                    }
                }
                if(!haveSelected&&codeListBeanArrayList.size()!=0){
                    codeListBeanArrayList.get(0).setInCheck(1);
                }
                commisionPlanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,isLoadMore,pageNum,refreshLayout);
            }
        });
    }

    private void initView() {
        loadingLinear=view.findViewById(R.id.loading_linear);
        nodataLinear=view.findViewById(R.id.nodata_linear);
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        loadingLinear=view.findViewById(R.id.loading_linear);
        recyclerView = view.findViewById(R.id.commision_plan_recycle);
        refreshLayout=view.findViewById(R.id.commision_plan_refresh);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setRefreshHeader(new ClassicsHeader(activity==null?fragment.getContext():activity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(activity==null?fragment.getContext():activity));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                requestCodeList(true,false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=0;
                requestCodeList(false,true);
            }
        });
        addRelativeLayout=view.findViewById(R.id.add_plan_relativeLayout);
        addRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 增加方案
                if(activity!=null){
                    activity.startActivityForResult(new Intent(activity, AddCommisionPlanActivity.class),TO_ADD_INVETICODE);
                }else {
                    fragment.startActivityForResult(new Intent(fragment.getContext(), AddCommisionPlanActivity.class),TO_ADD_INVETICODE);

                }
            }
        });
    }
    public  interface  DisMissListener{
        void onDisMissListener(CommissionPlanModel.CodeListBean codeListBean);
    }
    DisMissListener disMissListener;

    public DisMissListener getDisMissListener() {
        return disMissListener;
    }

    public void setDisMissListener(DisMissListener disMissListener) {
        this.disMissListener = disMissListener;
    }
}
