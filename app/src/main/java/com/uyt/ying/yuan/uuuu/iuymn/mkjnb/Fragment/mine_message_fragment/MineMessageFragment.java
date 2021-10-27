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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.MessageRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MineMessageFragment extends BaseFragment implements MessageRecycleAdapter.OnRecyclerViewItemClickListener {
    private ArrayList<MessageModel> messageModelArrayList=new ArrayList<>();//公告数据
    private RecyclerView recyclerView;//公告列表
    private MessageRecycleAdapter messageRecycleAdapter;//公告列表适配器
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
        View view =LayoutInflater.from(getContext()).inflate(R.layout.mine_message_fragment,container,false);
        bindView(view);
        initRecycle();
        getMessage(pageNum+"",pageSiZe+"",false,false,true);
        initRefresh();
        return view;
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getMessage(1+"",pageSiZe+"",false,false,true);
    }

    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        loadingLinear=view.findViewById(R.id.loading_linear);
        recyclerView=view.findViewById(R.id.message_recycle);
        noDataLinear=view.findViewById(R.id.no_data_linear);
        refreshLayout=view.findViewById(R.id.message_refresh);
    }
    /*
    recycleView配置
    */
    private void initRecycle() {
        messageRecycleAdapter = new MessageRecycleAdapter(messageModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(messageRecycleAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        messageRecycleAdapter.setOnItemClickListener(this);
    }
    /*
    recylceView 点击事件(点击查看消息详情)
    */
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(),NoticeMessageDetailsActivity.class);
        Long letterUnreadNum = SharePreferencesUtil.getLong(getContext(), "letterUnreadNum", 0l);
        intent.putExtra("time",messageModelArrayList.get(position).getTime());
        intent.putExtra("title",messageModelArrayList.get(position).getTitle());
        intent.putExtra("content",messageModelArrayList.get(position).getWebContent());
        intent.putExtra("id",messageModelArrayList.get(position).getId());
        intent.putExtra("fromMessage",true);
        if(messageModelArrayList.get(position).getIsRead()==0l){
            letterUnreadNum--;
            if(letterUnreadNum<=0l){
                letterUnreadNum=0l;
            }
            messageModelArrayList.get(position).setIsRead(1l);
            messageRecycleAdapter.notifyDataSetChanged();
        }
        SharePreferencesUtil.putLong(getContext(),"letterUnreadNum",letterUnreadNum);
        startActivity(intent);
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
                getMessage(pageNum+"",pageSiZe+"",true,false,false);
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
                getMessage(pageNum+"",pageSiZe+"",false,true,false);
            }
        });
    }

    /**
     * 请求消息列表数据
     * @param pageNo  请求的页数
     * @param pageSize 每次请求的数据size
     * @param isLoad 是否是上拉刷新时在调用(上拉刷新时,messageModelArrayList不用clear,请求结果为空时,需要将refreshLayout初始化)
     * @param isRefresh   是否是上拉刷线时调用
     * @param showLoad 是否是普通请求时在调用(下拉刷新和上拉加载不用弹出progressDialog)
     */
    private void getMessage(String pageNo, String pageSize,  final boolean isLoad,boolean isRefresh,boolean showLoad) {
        if(showLoad){
            noDataLinear.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        final HashMap<String, Object> data = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 1l);
        data.put("pageNo",pageNo);
        data.put("pageSize",pageSize);
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_02me, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getContext());
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray extensionMessageLetterList = jsonObject.getJSONArray("extensionMessageLetterList");
                int size = extensionMessageLetterList.size();
//                dataSize=size;
                if(!isLoad){
                    messageModelArrayList.clear();
                    if(size==0){
                        noDataLinear.setVisibility(View.VISIBLE);
                    }else{
                        noDataLinear.setVisibility(View.GONE);
                    }
                    if(isRefresh){
                        refreshLayout.finishRefresh();
                    }
                }else {
                    if (size==0){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        refreshLayout.finishLoadMore();
                    }
                }
                for (int i=0;i<extensionMessageLetterList.size();i++) {
                    JSONObject jsonObject1 = extensionMessageLetterList.getJSONObject(i);
                    String contentTxt = jsonObject1.getString("contentTxt");//公告内容
                    String createdDate = jsonObject1.getString("createdDate");//时间
                    String title = jsonObject1.getString("title");//标题
                    Long id = jsonObject1.getLong("id");
                    Long isRead = jsonObject1.getLong("isRead");
                    String content1 = jsonObject1.getString("content");
                    messageModelArrayList.add(new MessageModel(title,contentTxt,createdDate,id,isRead,content1));
                }
                messageRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getContext());
                ErrorUtil.showErrorLayout(MineMessageFragment.this,recyclerView,errorLinear,reloadTv);
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

