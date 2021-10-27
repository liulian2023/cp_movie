package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.integral_details_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.enum_pakege.IntegralType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money.IntegralDetailAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IntegralModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;


public class TotalCommisionFragment extends BaseFragment {
    private LinearLayout nodataLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private ConstraintLayout loadingLinear;
    private SmartRefreshLayout refreshLayout;
    private TextView num_type_tv;
    private TextView integral_num_tv;
    private RecyclerView recyclerView;
    private IntegralDetailAdapter integralDetailAdapter;
    private ArrayList<IntegralModel.DataBean> dataBeanArrayList  = new ArrayList<>();
    private IntegralType integralType;
    private int pageNum=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total_commision, container, false);

        initFragmentType();
        bindView(view);
        initRecycle();
        initRefresh();
        return view;
    }

    private void initRefresh() {
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                requestIntegral(true,false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=0;
                requestIntegral(false,true);
            }
        });
    }

    private void initFragmentType() {
        int positon = getArguments().getInt("position");
        if(positon==0){
            integralType= IntegralType.TOTAL;
        }else {
            integralType=IntegralType.CONSUMPTION;
        }
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        pageNum=1;
    }

    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        nodataLinear=view.findViewById(R.id.nodata_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        refreshLayout=view.findViewById(R.id.integral_refresh);
        recyclerView=view.findViewById(R.id.integral_recycle);
        loadingLinear=view.findViewById(R.id.loading_linear);
        integral_num_tv=view.findViewById(R.id.integral_num_tv);
        num_type_tv=view.findViewById(R.id.num_type_tv);
        num_type_tv.setText(integralType==IntegralType.TOTAL?Utils.getString(R.string.累计):Utils.getString(R.string.剩余));
    }

    public static TotalCommisionFragment newInstance(int position){
        Bundle bundle = new Bundle();
        TotalCommisionFragment totalCommisionFragment = new TotalCommisionFragment();
        bundle.putInt("position", position);
        totalCommisionFragment.setArguments(bundle);
        return totalCommisionFragment;
    }

    private void initRecycle() {
        integralDetailAdapter = new IntegralDetailAdapter(dataBeanArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(integralDetailAdapter);
        requestIntegral(false,false);
    }
    private void requestIntegral(boolean isLoadMore,boolean isRefresh) {
        //user_id 用户id（必传） type类型（必传1得到2消费）pageNo （必传）pageSize（必传）
        HashMap<String, Object> data = new HashMap<>();
        data.put("type",integralType==IntegralType.TOTAL?1:2);
        data.put("pageNo",pageNum);
        data.put("pageSize",10);
        HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.INTEGRAL_DETAILS, data, loadingLinear, errorLinear, reloadTv, refreshLayout, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                IntegralModel integralModel = JSONObject.parseObject(result, IntegralModel.class);
                List<IntegralModel.DataBean> dataBeanList = integralModel.getData();
                int size = dataBeanList.size();
                RefreshUtil.success(pageNum,refreshLayout,loadingLinear,nodataLinear,size,isLoadMore,isRefresh,dataBeanArrayList);
                dataBeanArrayList.addAll(dataBeanList);
                integralDetailAdapter.notifyDataSetChanged();
                if(integralType==IntegralType.TOTAL){
                    int integralNum=0;
                    for (int i = 0; i < dataBeanArrayList.size(); i++) {
                        IntegralModel.DataBean dataBean = dataBeanArrayList.get(i);
                        int integral = dataBean.getIntegral();
                        integralNum+=integral;
                    }
                    integral_num_tv.setText(integralNum+"");
                }else {
                    integral_num_tv.setText(integralModel.getInvitePoint()+"");
                }
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,isLoadMore,pageNum,refreshLayout);
            }
        });

    }
}
