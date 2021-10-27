package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildDealRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.ChildDealEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildDealMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ChildDealFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<ChildDealMedol> childDealMedolArrayList =new ArrayList<>();//交易记录数据
    private RecyclerView recyclerViewDeal;//交易详情recycleView,(主页面recycleView)
    private ChildDealRecycleAdapter childDealRecycleAdapter;//交易明细 recycleView适配器
    private RefreshLayout  refreshLayout;//下拉刷新 上拉加载 控件
    private LinearLayout nothing; //请求数据为空时,顶部显示的textView
    private Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
    private int pageNumAll =1;
    private int pageNumIn =1;
    private int pageNumOut =1;
    private int pageSize=15;
    private Date startAll;
    private Date endAll;
    private String typeAll;
    private Date startOut;
    private Date endOut;
    private String typeOut;
    private Date startIn;
    private Date endIn;
    private String typeIn;
    private String nickNime;
    private int pagenum;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private String tabName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_deal,container,false);
        bindView(view);
        initView(view);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//注册eventBus
        }
        return view;
    }
    @Subscribe (threadMode = ThreadMode.MAIN,sticky = true)
    public void getEvenBusData(ChildDealEvenModel childDealEvenModel){
        String tabName=getArguments().getString("tabName");
        this.tabName=tabName;
        pagenum = childDealEvenModel.getPagenum();
        int pageSize = childDealEvenModel.getPageSize();
        startAll = childDealEvenModel.getStartAll();
        endAll = childDealEvenModel.getEndAll();
        typeAll = childDealEvenModel.getTypeAll();
        startOut=childDealEvenModel.getStartOut();
        endOut=childDealEvenModel.getEndOut();
        typeOut=childDealEvenModel.getTypeOut();
        startIn =childDealEvenModel.getStartIn();
        endIn=childDealEvenModel.getEndIn();
        typeIn=childDealEvenModel.getTypeIn();
        nickNime = childDealEvenModel.getNickNime();
        boolean showLoad = childDealEvenModel.isShowLoad();
        //解决请求数据时的闪屏问题: 根据选中的tab,初始化status
        if(Utils.getString(R.string.所有类型).equals(tabName)){
            getDataAll(user_id,pagenum,pageSize,startAll,endAll,nickNime,typeAll,false,false,showLoad);
        }else if(Utils.getString(R.string.提现明细).equals(tabName)){
            getDataOut(user_id,pagenum,pageSize,startOut,endOut,nickNime,typeOut,false,false,showLoad);
        }else {
            getDataIn(user_id,pagenum,pageSize,startIn,endIn,nickNime,typeIn,false,false,showLoad);
        }
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        if(Utils.getString(R.string.所有类型).equals(tabName)){
            getDataAll(user_id,1,pageSize,startAll,endAll,nickNime,typeAll,false,false,true);
        }else if(Utils.getString(R.string.提现明细).equals(tabName)){
            getDataOut(user_id,1,pageSize,startOut,endOut,nickNime,typeOut,false,false,true);
        }else {
            getDataIn(user_id,1,pageSize,startIn,endIn,nickNime,typeIn,false,false,true);
        }
    }

    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        nothing=view.findViewById(R.id.nodata_linear);//没有数据时显示的控件
        recyclerViewDeal =view.findViewById(R.id.child_deal_recycle);
        loadingLinear=view.findViewById(R.id.loading_linear);
        loadingLinear.setVisibility(View.VISIBLE);
        /*
        下拉刷新.上拉加载相关控件
         */
        refreshLayout=view.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//数据未满一页时,禁用上拉加载
    }

    private void initView(View view) {
        /*
        交易明细主recycleView相关
         */
        childDealRecycleAdapter =new ChildDealRecycleAdapter(childDealMedolArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDeal.setLayoutManager(linearLayoutManager);
        recyclerViewDeal.setAdapter(childDealRecycleAdapter);
        /*
        下拉刷新 上拉加载的监听相关
         */
        onRefreshLoadMoreListent();
    }
    /*
          下拉刷新 上拉加载的监听相关
           */
    private void onRefreshLoadMoreListent() {
        final String tabName=getArguments().getString("tabName");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            //下拉刷新
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(tabName.equals(Utils.getString(R.string.所有类型))){
                    getDataAll(user_id,pagenum,pageSize,startAll,endAll,nickNime,typeAll,false,true,false);
                }
                else if(tabName.equals(Utils.getString(R.string.提现明细))){
                    getDataOut(user_id,pagenum,pageSize,startOut,endOut,nickNime,typeOut,false,true,false);
                }
                else if(tabName.equals(Utils.getString(R.string.交易明细))){
                    getDataIn(user_id,pagenum,pageSize,startIn,endIn,nickNime,typeIn,false,true,false);
                }

            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (tabName.equals(Utils.getString(R.string.所有类型))) {
                    pageNumAll++;
                    getDataAll(user_id,pageNumAll,pageSize,startAll,endAll,nickNime,typeAll,true,false,false);


                } else if (tabName.equals(Utils.getString(R.string.提现明细))) {
                    pageNumOut++;
                    getDataOut(user_id,pageNumOut,pageSize,startOut,endOut,nickNime,typeOut,true,false,false);

                } else {
                    pageNumIn++;//每次调用pageNum加1,在请求其他数据的时候,将其初始化
                    getDataIn(user_id,pageNumIn,pageSize,startIn,endIn,nickNime,typeIn,true,false,false);

                }
            }
        });
    }

    /**
     * 获取所有类型的数据
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param startDate
     * @param endDate
     * @param nickName
     * @param type  type为请求数据的类型 并用于控制交易金额的字体颜色, 在recycleView的item点击事件中获得
     * @param isLoadMore 是否为加载更多时调用,(用于判断是否需要清空数据)
     */

    private void getDataAll(Long userId, int pageNum, int pageSize, Date startDate, Date endDate, String nickName, String type, final boolean isLoadMore,boolean isRefresh,boolean showLoad){
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(recyclerViewDeal,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        data.put("user_id",userId);
        data.put("pageNo",pageNum);
        data.put("pageSize",pageSize);
        data.put("startDate",start);
        data.put("endDate",end);
        data.put("nickname",nickName);
        data.put("type",type);
        Utils.docking(data, RequestUtil.REQUEST_21rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getContext());
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray childBalanceRecordList = jsonObject1.getJSONArray("childBalanceRecordList");
                int size = childBalanceRecordList.size();//得到的数据容器size
                if(!isLoadMore){
                    childDealMedolArrayList.clear();
                    pageNumAll=1;
                    if(size==0){
                        nothing.setVisibility(View.VISIBLE);
                    }else {
                        nothing.setVisibility(View.GONE);
                    }
                    if(isRefresh){
                        refreshLayout.finishRefresh();
                    }
                }else {
                    if(size==0){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        refreshLayout.finishLoadMore();
                    }
                }
                for (int i=0;i<childBalanceRecordList.size();i++) {
                    JSONObject jsonObject = childBalanceRecordList.getJSONObject(i);
                    String createdDate = jsonObject.getString("createdDate");//时间
                    String user_name = jsonObject.getString("user_name");//用户昵称
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    String remark = jsonObject.getString("remark");//类型(要显示的)
                    Long type1 = jsonObject.getLong("type");//筛选的类型
                    String typename = jsonObject.getString("typename");//筛选的类型名称
                    childDealMedolArrayList.add(new ChildDealMedol(user_name,price,createdDate,type1,typename,remark,"1",""));
                }
                childDealRecycleAdapter.notifyDataSetChanged();
            }
            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(getContext());
                loadingLinear.setVisibility(View.GONE);
                showToast(messageHead.getInfo());
                ErrorUtil.showErrorLayout(ChildDealFragment.this,recyclerViewDeal,errorLinear,reloadTv);
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
                if(isLoadMore){
                    pageNumAll--;
                    refreshLayout.finishLoadMore(false);
                }
            }
        });
    }

    /**
     *  提现明细数据相关
     * @param userId  用户id
     * @param pageNum 页数
     * @param pageSize 每页的数据条数
     * @param startDate 请求数据的时间范围(开始)
     * @param endDate  请求数据的时间范围(结束)
     * @param nickName 指定的下级昵称 默认为空,输入框有输入时,传入输入的值
     * @param status   请求数据的状态  ""为全部类型 -1为提现失败 0为申请中 1为提现成功
     */
    private void getDataOut(Long userId, int pageNum, int pageSize, Date startDate, Date endDate, String nickName, String status, final boolean isLoadMore,boolean isRefresh,boolean showLoad){
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(recyclerViewDeal,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        data.put("user_id",userId);
        data.put("pageNo",pageNum);
        data.put("pageSize",pageSize);
        data.put("startDate",start);
        data.put("endDate",end);
        data.put("nickname",nickName);
        data.put("status",status);
        Utils.docking(data, RequestUtil.REQUEST_16rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getActivity());
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray childDrawRecordList = jsonObject1.getJSONArray("childDrawRecordList");
                int size = childDrawRecordList.size();//得到的数据容器size
                if(!isLoadMore){
                    childDealMedolArrayList.clear();
                    pageNumOut=1;
                    if(size==0){
                        nothing.setVisibility(View.VISIBLE);
                    }else {
                        nothing.setVisibility(View.GONE);
                    }
                    if(isRefresh){
                        refreshLayout.finishRefresh();
                    }
                }else {
                    if(size==0){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        refreshLayout.finishLoadMore();
                    }
                }

//                loadMoreList.clear();
                for (int i=0;i<childDrawRecordList.size();i++) {
                    JSONObject jsonObject = childDrawRecordList.getJSONObject(i);
                    String createdDate = jsonObject.getString("createdDate");//时间
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    String nickname = jsonObject.getString("nickname");//用户名
                    String status1 = jsonObject.getString("status");//状态 -2拒绝申请并退回金额-1申请不通过0申请中1待打款2已打款
                    Long type = jsonObject.getLong("type");//type;//类型0余额提现1佣金提现
                    String typeName = jsonObject.getString("typeName");
                    String remark = jsonObject.getString("remark");
                    childDealMedolArrayList.add(new ChildDealMedol(nickname,price,createdDate,type,typeName,remark,"2",status1));
                }
                childDealRecycleAdapter.notifyDataSetChanged();
            }
            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(getActivity());
                loadingLinear.setVisibility(View.GONE);
                ErrorUtil.showErrorLayout(ChildDealFragment.this,recyclerViewDeal,errorLinear,reloadTv);
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
                if(isLoadMore){
                    pageNumOut--;
                    refreshLayout.finishLoadMore(false);
                }

            }
        });


    }
    /**
     *  充值明细数据相关
     * @param userId  用户id
     * @param pageNum 页数
     * @param pageSize 每页的数据条数
     * @param startDate 请求数据的时间范围(开始)
     * @param endDate  请求数据的时间范围(结束)
     * @param nickName 指定的下级昵称 默认为空,输入框有输入时,传入输入的值
     * @param status   请求数据的状态 0充值中 1充值成功 2充值失败
     */

    private void getDataIn(Long userId, int pageNum, int pageSize, Date startDate, Date endDate, String nickName, String status, final boolean isLoadMore,boolean isRefresh,boolean showLoad) {
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(recyclerViewDeal,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        data.put("user_id",userId);
        data.put("pageNo",pageNum);
        data.put("pageSize",pageSize);
        data.put("startDate",start);
        data.put("endDate",end);
        data.put("nickname",nickName);
        data.put("status",status);
        Utils.docking(data, RequestUtil.REQUEST_17rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getActivity());
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray childRechargeList = jsonObject1.getJSONArray("childRechargeList");
                int size = childRechargeList.size();//得到的数据容器size
                if(!isLoadMore){
                    childDealMedolArrayList.clear();
                    pageNumIn=1;
                    if(size==0){
                        nothing.setVisibility(View.VISIBLE);
                    }else {
                        nothing.setVisibility(View.GONE);
                    }
                    if(isRefresh){
                        refreshLayout.finishRefresh();
                    }
                }else {
                    if(size==0){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        refreshLayout.finishLoadMore();
                    }
                }
                refreshLayout.finishRefresh(true);
                refreshLayout.finishLoadMore(true);
                for (int i=0;i<childRechargeList.size();i++) {
                    JSONObject jsonObject = childRechargeList.getJSONObject(i);
                    String createdDate = jsonObject.getString("createdDate");//时间
                    String bankName = jsonObject.getString("bankName");//银行名
                    Long type = jsonObject.getLong("type");//类型/充值类型 0银行 1微信 2支付宝 3后台充值-余额 4代理充值(代理佣金余额充值给下级) 5后台充值-佣金 6第三方支付
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    String status1 = jsonObject.getString("status");//status;//状态0充值中1充值成功2充值失败
                    String typeName = jsonObject.getString("typeName");
                    String remark = jsonObject.getString("remark");
                    childDealMedolArrayList.add(new ChildDealMedol(bankName,price,createdDate,type,typeName,remark,"3",status1));

                }
                childDealRecycleAdapter.notifyDataSetChanged();
            }
            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(getActivity());
                loadingLinear.setVisibility(View.GONE);
                ErrorUtil.showErrorLayout(ChildDealFragment.this,recyclerViewDeal,errorLinear,reloadTv);
                if(isRefresh){

                    refreshLayout.finishRefresh(false);
                }
                if(isLoadMore){
                    pageNumIn--;
                    refreshLayout.finishLoadMore(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);//取消eventBus注册
        super.onDestroy();
    }
}
