package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.graphics.Color;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.LiveRankRecyclerAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveRankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.LiveRankDialogFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Headers;


public class LiveRankFragment extends BaseFragment {
    @BindView(R.id.live_rank_recycler)
    RecyclerView live_rank_recycler;
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    private Unbinder mUnbinder;
    LiveRankRecyclerAdapter liveRankRecyclerAdapter ;
    ArrayList<LiveRankEntity.ListBean>liveRankEntityArrayList = new ArrayList<>();
    LiveRankDialogFragment liveRankDialogFragment;
    int type=0;
    private int position;
    String anchorAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_rank, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        getArgumentsData();
        initRefresh();
        initRecycler();
        return view;
    }

/*    public LiveRankFragment(LiveFragment liveFragment) {
        this.liveFragment = liveFragment;
    }*/

    public LiveRankFragment() {
    }

    private void initRecycler() {
        liveRankRecyclerAdapter = new LiveRankRecyclerAdapter(R.layout.live_rank_recycler_item,liveRankEntityArrayList,this);
        live_rank_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        live_rank_recycler.setAdapter(liveRankRecyclerAdapter);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        requestRankList(false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    private void requestRankList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
            data.put("anchorAccount",anchorAccount);
            data.put("type",type);
            data.put("pageSize",50);
            HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.GIFT_RANK, data, loading_linear, error_linear, reload_tv, (View) refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    LiveRankEntity liveRankEntity = JSONObject.parseObject(result, LiveRankEntity.class);
                    String anchorGift = liveRankEntity.getAnchorGift();//自身礼物金额
                    List<LiveRankEntity.ListBean> list = liveRankEntity.getList();
                    RefreshUtil.success(1,refresh,loading_linear,null,list.size(),false,true,liveRankEntityArrayList);
                    liveRankEntityArrayList.addAll(list);
                    liveRankRecyclerAdapter.notifyDataSetChanged();
                    boolean isInRank=false;
                    LiveRankEntity.ListBean mineBean=new LiveRankEntity.ListBean();
                    mineBean.setAnchorGift(anchorGift);
                    String imageUrl = SharePreferencesUtil.getString(getContext(), "image", "");
                    mineBean.setImage(imageUrl);
                    mineBean.setPointGrade(SharePreferencesUtil.getInt(MyApplication.getInstance(), CommonStr.GRADE,0)+"");
                    mineBean.setUserNickName(SharePreferencesUtil.getString(MyApplication.getInstance(),"userNickName",""));
                    int position=0;
                    for (int i = 0; i < liveRankEntityArrayList.size(); i++) {
                        LiveRankEntity.ListBean listBean = liveRankEntityArrayList.get(i);
                        if(listBean.getUser_id().equals(SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)+"")){
                            isInRank=true;
      /*                    mineBean.setImage(listBean.getImage());
                            mineBean.setUserNickName(listBean.getUserNickName());
                            mineBean.setPointGrade((Integer.parseInt(listBean.getPointGrade()))+1+"");*/
                            position = i;
                            break;
                        }
                    }
                    Fragment parentFragment = getParentFragment();
                    if(parentFragment instanceof LiveRankDialogFragment){
                        liveRankDialogFragment = (LiveRankDialogFragment) parentFragment;
                        if(liveRankDialogFragment!=null){
                            liveRankDialogFragment.initMineInfo(isInRank,position,mineBean);
                        }
                    }
                }

                @Override
                public void onFailed(String msg) {
                    RefreshUtil.fail(isRefresh,false,1,refresh);
                }
            });

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestRankList(false);
    }

    private void initRefresh() {


        refresh.setEnableLoadMoreWhenContentNotFull(false);
        refresh.setEnableLoadMore(false);
        ClassicsHeader classicsHeader = new ClassicsHeader(getContext());
        classicsHeader.setPrimaryColor(ContextCompat.getColor(getContext(),R.color.alpha_70_black));
        classicsHeader.setAccentColor(Color.WHITE);
        refresh.setRefreshHeader(classicsHeader);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestRankList(true);
            }
        });


    }

    private void getArgumentsData() {
         position = getArguments().getInt("position");
        anchorAccount = getArguments().getString("anchorAccount");
        switch (position){
            case 0:
                type=0;
                break;
            case 1:
                type=20;
                break;
            case 2:
                type =2;
                break;
                default:
                break;
        }
    }

    public static LiveRankFragment newInstance(int position,  String anchorAccount){
        LiveRankFragment liveRankFragment = new LiveRankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("anchorAccount",anchorAccount);
        bundle.putInt("position",position);
        liveRankFragment.setArguments(bundle);
        return liveRankFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
