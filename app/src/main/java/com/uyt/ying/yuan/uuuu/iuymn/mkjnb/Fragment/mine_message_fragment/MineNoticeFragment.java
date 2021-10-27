package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.mine_message_fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.NoticeMessageDetailsActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.NoticeRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NoticeModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MineNoticeFragment extends BaseFragment implements NoticeRecycleAdapter.OnRecyclerViewItemClickListener {
    private ArrayList<NoticeModel>noticeModelArrayList=new ArrayList<>();//公告数据
    private RecyclerView recyclerView;//公告列表
    private NoticeRecycleAdapter noticeRecycleAdapter;//公告列表适配器
    private LinearLayout noDataLinear;//没有数据时的视图
    private RefreshLayout refreshLayout;//刷新控件
    private int pageNum=1;
    private int pageSiZe=15;
    private int dataSize; //判断上拉加载有无数据;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.mine_notice_fragment,container,false);
        bindView(view);
        initRecycle();
        getNotice(pageNum+"",pageSiZe+"",2,false,false,true);
        initRefresh();
        return view;
    }
    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        loadingLinear=view.findViewById(R.id.loading_linear);
        recyclerView=view.findViewById(R.id.notice_recycle);
        noDataLinear=view.findViewById(R.id.no_data_linear);
        refreshLayout=view.findViewById(R.id.notice_refresh);
    }
    /*
    recycleView配置
     */
    private void initRecycle() {
        noticeRecycleAdapter = new NoticeRecycleAdapter(noticeModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(noticeRecycleAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        noticeRecycleAdapter.setOnItemClickListener(this);
    }
            /*
            recylceView 点击事件(点击进去公告详情)
             */
    @Override
    public void onItemClick(View view, int position) {
        // title= intent.getStringExtra("title");
        //        content= intent.getStringExtra("content");
        //        time= intent.getStringExtra("time");
        Intent intent = new Intent(getContext(),NoticeMessageDetailsActivity.class);
        NoticeModel noticeModel = noticeModelArrayList.get(position);
        intent.putExtra("title", noticeModel.getTitle());
        intent.putExtra("content", noticeModel.getWenContent());
        intent.putExtra("time", noticeModel.getTime());
        intent.putExtra("id",noticeModel.getId());
        startActivity(intent);

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getNotice(1+"",pageSiZe+"",2,false,false,true);
    }

    /*
        刷新和加载相关配置
         */
    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getNotice(pageNum+"",pageSiZe+"",2,true,false,false);
//                if(dataSize==0){
//                    refreshLayout.finishLoadMoreWithNoMoreData();
//                }

            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                refreshLayout.resetNoMoreData();
                getNotice(pageNum+"",pageSiZe+"",2,false,true,false);

            }
        });
    }

    /**
     * 请求公告列表数据
     * @param pageNo
     * @param pageSize
     * @param type 类型 0.logo 1轮播图 2消息 3弹出公告 4优惠活动
     * @param isLoad
     */
    private void getNotice(String pageNo, String pageSize, int type, final boolean isLoad,boolean isRefresh,boolean showLoad) {
//        PopupWindowUtil.showShareWindow(getActivity());
        if(showLoad){
//        ProgressDialogUtil.show(getContext());
            loadingLinear.setVisibility(View.VISIBLE);
            noDataLinear.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        final HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",pageNo);
        data.put("pageSize",pageSize);
        data.put("type",type);
        Utils.docking(data, RequestUtil.REQUEST_33r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
//                PopupWindowUtil.disMiss(getActivity());
                ProgressDialogUtil.stop(getContext());
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray extensionNoticeInfoList = jsonObject.getJSONArray("extensionNoticeInfoList");
                int size = extensionNoticeInfoList.size();
//                dataSize=size;
                if(!isLoad){
                    noticeModelArrayList.clear();
                    if(size==0){
                        noDataLinear.setVisibility(View.VISIBLE);
                    }else{
                        noDataLinear.setVisibility(View.GONE);
                    }
                    if(isRefresh){
                        refreshLayout.finishRefresh();
                    }
                }else{
                    if(size==0){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        refreshLayout.finishLoadMore();
                    }
                }
                for (int i=0;i<extensionNoticeInfoList.size();i++) {
                    JSONObject jsonObject1 = extensionNoticeInfoList.getJSONObject(i);
                    String contentTxt = jsonObject1.getString("contentTxt");//公告内容
                    String createdDate = jsonObject1.getString("createdDate");//时间
                    String theme = jsonObject1.getString("theme");//标题
                    Long id = jsonObject1.getLong("id");
                    String content1 = jsonObject1.getString("content");
                    noticeModelArrayList.add(new NoticeModel(theme,contentTxt,createdDate,id,content1));
                }
                noticeRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
//                PopupWindowUtil.disMiss(getActivity());
                ProgressDialogUtil.stop(getContext());
                ErrorUtil.showErrorLayout(MineNoticeFragment.this,recyclerView,errorLinear,reloadTv);
                if(isLoad){
                    pageNum--;
                    refreshLayout.finishLoadMore(false);
                }
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
            }
        });
    }
}
