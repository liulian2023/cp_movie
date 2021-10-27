package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.MineFollowAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class MineFollowActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;

    private ConstraintLayout loadingLinear;
    private LinearLayout nodataLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;

    private MineFollowAdapter mineFollowAdapter;
    private ArrayList<LiveEntity.AnchorFollowListBean> liveBeanArrayList = new ArrayList<>();
    private int pageNum=1;
    private int pageSize = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_follow);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.我的关注));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        if(EventBus.getDefault().isRegistered(this)){
           EventBus.getDefault().register(this);
        }
        bindView();
        initRefresh();
        initRecycle();
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateIsFollow(FollowEvenModel followEvenModel){
        for (int i = 0; i < liveBeanArrayList.size(); i++) {
            LiveEntity.AnchorFollowListBean anchorFollowListBean = liveBeanArrayList.get(i);
            if((anchorFollowListBean.getAnchorMemberId()+"").equals(followEvenModel.getId())){
                if(followEvenModel.isFollow()){
                    anchorFollowListBean.setIsFollow(1);
                }else {
                    anchorFollowListBean.setIsFollow(0);
                }
                break;
            }
        }
        mineFollowAdapter.notifyDataSetChanged();
    }

    private void initRecycle() {
        mineFollowAdapter = new MineFollowAdapter(liveBeanArrayList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mineFollowAdapter);
        mineFollowAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LiveEntity.AnchorFollowListBean anchorFollowListBean = liveBeanArrayList.get(position);
                String anchorMemberId = anchorFollowListBean.getAnchorMemberId();
                switch (view.getId()){
                    //点击关注/取消关注
                    case R.id. mine_follow_isFollow_iv:
                        if(anchorFollowListBean.getIsFollow()==1){//当前为关注状态,点击时请求取消关注
                            HashMap<String, Object> data = new HashMap<>();
                            data.put("anchorMemberId",anchorMemberId);
                            HttpApiUtils.CPnormalRequest(MineFollowActivity.this, null,RequestUtil.CANCEL_FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
                                @Override
                                public void onSuccess(String result, Headers headers) {
                                    anchorFollowListBean.setIsFollow(0);
                                    mineFollowAdapter.notifyDataSetChanged();
                                    showToast(Utils.getString(R.string.取消关注成功));
                                }

                                @Override
                                public void onFailed(String msg) {

                                }
                            });
                        }else {//当前为未关注状态, 点击请求关注
                            HashMap<String, Object> data = new HashMap<>();
                            data.put("anchorMemberId",anchorMemberId);
                            HttpApiUtils.CPnormalRequest(MineFollowActivity.this,null, RequestUtil.FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
                                @Override
                                public void onSuccess(String result, Headers headers) {
                                    anchorFollowListBean.setIsFollow(1);
                                    mineFollowAdapter.notifyDataSetChanged();
                                    showToast(Utils.getString(R.string.关注成功));
                                }

                                @Override
                                public void onFailed(String msg) {

                                }
                            });

                        }

                        break;
                        //进入直播间
                    case R.id.mine_follow_itemview:
                        if(anchorFollowListBean.getStatus().equals("0")){
                            showToast(Utils.getString(R.string.主播已下播));
                        }else {
                            int curPage = ((position+1) + pageSize-1)/pageSize;
                            RouteUtils.start2LiveActivity(MineFollowActivity.this,anchorFollowListBean,"-1", CommonStr.AREA_DEFAULT_VALUE,curPage);
                        }
                        break;
                        default:
                            break;
                }
            }
        });
        requestFollowList(false,false);
    }

    private void requestFollowList(boolean isLoadMore,boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", SharePreferencesUtil.getLong(this,"user_id",0l));
        data.put("pageNo",pageNum);
        data.put("pageSize",pageSize);
        HttpApiUtils.cpShowLoadRequest(this, null, RequestUtil.MINE_FOLLOW, data, loadingLinear, errorLinear, reloadTv, (View) refreshLayout, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                LiveEntity liveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.AnchorFollowListBean> anchorFollowListBeanList = liveEntity.getAnchorFollowList();
                int size = anchorFollowListBeanList.size();
                RefreshUtil.success(pageNum,refreshLayout,loadingLinear,nodataLinear, size,isLoadMore,isRefresh,liveBeanArrayList);
                liveBeanArrayList.addAll(anchorFollowListBeanList);
                mineFollowAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,isLoadMore,pageNum,refreshLayout);
            }
        });
    }

    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                requestFollowList(true,false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                requestFollowList(false,true);
            }
        });
    }

    private void bindView() {
        recyclerView=findViewById(R.id.mine_follow_recycle);
        refreshLayout=findViewById(R.id.mine_follow_refresh);
        loadingLinear=findViewById(R.id.loading_linear);
        nodataLinear = findViewById(R.id.nodata_linear);
        errorLinear = findViewById(R.id.error_linear);
        reloadTv = findViewById(R.id.reload_tv);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_bar_return:
                finish();
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
