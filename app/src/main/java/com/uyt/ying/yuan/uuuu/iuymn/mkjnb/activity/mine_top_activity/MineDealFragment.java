package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter.MineDealRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.MineDealAllEven;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineDealMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
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
import java.util.Map;
        /*
        交易记录fragment(refreshLayout+recycleView)
         */

public class MineDealFragment extends BaseFragment /* implements TabLayout.BaseOnTabSelectedListener, View.OnClickListener*/ {
    private ArrayList<MineDealMedol> mineDealMedolArrayList = new ArrayList<>();//交易记录数据
    private RecyclerView recyclerViewDeal;//交易详情recycleView,(主页面recycleView)
    private MineDealRecycleAdapter mineDealRecycleAdapter;//交易明细 recycleView
    private RefreshLayout refreshLayout;//下拉刷新 上拉加载 控件
//    private TextView nothing; //请求数据为空时,顶部显示的textView
    private LinearLayout nothing; //请求数据为空时,顶部显示的textView
    private  Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 1l);

    private int pageNumAll =1;
    private int pageNumIn =1;
    private int pageNumOut =1;
    private int pageSizeAll=15;
    private int pageSizeOut=15;
    private int pageSizeIn=15;

    private Date startAll;
    private Date endAll;
    private String typeAll;
    private Date startOut;
    private Date endOut;
    private String typeOut;
    private Date startIn;
    private Date endIn;
    private String typeIn;
    private int pagenum;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reLoadTv;
    private String tabName;
    private TextView total_price_tv;
    private LinearLayout total_price_linear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mine_deal,container,false);
        bindView(view);
        initView();
        refreshLoadMoreListener();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//注册eventBus
        }
        return view;
    }

    private void refreshLoadMoreListener() {
        final String tabName = getArguments().getString("type");
        this .tabName=tabName;
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            //下拉刷新
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                if (tabName.equals(Utils.getString(R.string.所有类型))) {
                    getDataAll(user_id, pagenum, pageSizeAll, startAll, endAll, typeAll,false,true,false);
                } else if (tabName.equals(Utils.getString(R.string.提现明细))) {
                    getDataOut(user_id, pagenum, pageSizeAll, startOut, endOut, typeOut,false,true,false);
                } else if (tabName.equals(Utils.getString(R.string.充值明细))) {
                    getDataIn(user_id, pagenum, pageSizeAll, startIn, endIn, typeIn,false,true,false);
                }
                refreshLayout.finishRefresh();
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (tabName.equals(Utils.getString(R.string.所有类型))) {
                    pageNumAll++;
                    getDataAll(user_id,pageNumAll,pageSizeAll,startAll,endAll, typeAll,true,false,false);
//                    getDataOut(user_id, pagenumOut, pageSizeOut, startOut, endOut, stutasOut, true);


                } else if (tabName.equals(Utils.getString(R.string.提现明细))) {
                    pageNumOut++;
                    getDataOut(user_id,pageNumOut,pageSizeOut,startOut,endOut,typeOut,true,false,false);

                } else  {
                    pageNumIn++;//每次调用pageNum加1,在请求其他数据的时候,将其初始化
                    getDataIn(user_id,pageNumIn,pageSizeIn,startIn,endIn, typeIn,true,false,false);

                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getEventBusData(MineDealAllEven mineDealAllEven){
        String tabName = getArguments().getString("type");
        long user_id = mineDealAllEven.getUser_id();
        pagenum = mineDealAllEven.getPagenumAll();
        int pageSizeAll = mineDealAllEven.getPageSizeAll();
        startAll = mineDealAllEven.getStartAll();
        endAll = mineDealAllEven.getEndAll();
        typeAll = mineDealAllEven.getTypeAll();
        startOut=mineDealAllEven.getStartOut();
        endOut=mineDealAllEven.getEndOut();
        typeOut=mineDealAllEven.getTypeOut();
        startIn=mineDealAllEven.getStartIn();
        endIn=mineDealAllEven.getEndIn();
        typeIn=mineDealAllEven.getTypeIn();
        boolean showLoad = mineDealAllEven.isShowLoad();
        if(tabName .equals(Utils.getString(R.string.所有类型))){
        getDataAll(user_id, pagenum, pageSizeAll, startAll, endAll, typeAll,false,false,showLoad);
        }else if(tabName.equals(Utils.getString(R.string.提现明细))){
         getDataOut(user_id, pagenum, pageSizeAll, startOut, endOut, typeOut,false,false,showLoad);
        }else{
            getDataIn(user_id, pagenum, pageSizeAll, startIn, endIn, typeIn,false,false,showLoad);
        }

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        if (tabName.equals(Utils.getString(R.string.所有类型))) {
            getDataAll(user_id, 1, pageSizeAll, startAll, endAll, typeAll,false,false,true);
        } else if (tabName.equals(Utils.getString(R.string.提现明细))) {
            getDataOut(user_id, 1, pageSizeAll, startOut, endOut, typeOut,false,false,true);
        } else if (tabName.equals(Utils.getString(R.string.充值明细))) {
            getDataIn(user_id, 1, pageSizeAll, startIn, endIn, typeIn,false,false,
                    true);
        }
    }

    private void bindView(View view) {
        total_price_tv=view.findViewById(R.id.total_price_tv);
        total_price_linear=view.findViewById(R.id.total_price_linear);
        errorLinear=view.findViewById(R.id.error_linear);
        reLoadTv=view.findViewById(R.id.reload_tv);
        loadingLinear=view.findViewById(R.id.loading_linear);
        //交易明细主recycle
        recyclerViewDeal = view.findViewById(R.id.mine_deal_recycle);
        nothing = view.findViewById(R.id.nodata_linear);//没有数据时显示的控件
        refreshLayout = view.findViewById(R.id.refreshLayout);

        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//数据未满一页时,禁用上拉加载
    }
//
    public void initView() {
        /*
        交易明细主recycleView相关
         */
        mineDealRecycleAdapter = new MineDealRecycleAdapter(mineDealMedolArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDeal.setLayoutManager(linearLayoutManager);
        recyclerViewDeal.setAdapter(mineDealRecycleAdapter);
        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 1l);
    }

    /**
     * 获取所有类型的数据
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param startDate
     * @param endDate
     * @param
     * @param type       type为请求数据的类型 并用于控制交易金额的字体颜色, 在recycleView的item点击事件中获得
     * @param isLoadMore 是否为加载更多时调用,(用于判断是否需要清空数据)
     */

    private void getDataAll(Long userId, final int pageNum, int pageSize, Date startDate, Date endDate,  String type, final boolean isLoadMore,boolean isRefresh,boolean showLoad) {
//        if (showProgress) {
//            ProgressDialogUtil.show(getContext());
//        }
        initTotalPriceLinear();
        if (selectorViewPagerItem(0)) return;
        ErrorUtil.hideErrorLayout(recyclerViewDeal,errorLinear);
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
        }
        HashMap<String, Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        data.put("user_id", userId);
        data.put("pageNo", pageNum);
        data.put("pageSize", pageSize);
        data.put("startDate", start);
        data.put("endDate", end);
//        data.put("nickname", nickName);
        data.put("type", type);
        Utils.docking(data, RequestUtil.REQUEST_20rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                BigDecimal totalPrice = jsonObject1.getBigDecimal("totalPrice");
                total_price_tv.setText(totalPrice+Utils.getString(R.string.元));
                boolean b = total_price_linear.getVisibility() == View.VISIBLE;
                JSONArray balanceRecordList = jsonObject1.getJSONArray("balanceRecordList");
                int size = balanceRecordList.size();//得到的数据容器size
                Long totalSize = jsonObject1.getLong("totalSize");
                ProgressDialogUtil.stop(getActivity());
                if (!isLoadMore) {
                    mineDealMedolArrayList.clear();
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

//                EventBus.getDefault().post(new DataSizeModel(size));
//                loadAllSize = size;
//                loadMoreList.clear();
                for (int i = 0; i < balanceRecordList.size(); i++) {
                    JSONObject jsonObject = balanceRecordList.getJSONObject(i);
                    String createdDate = jsonObject.getString("createdDate");//时间
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    String remark = jsonObject.getString("remark");//类型(要显示的)
                    Long type1 = jsonObject.getLong("type");//筛选的类型
                    String typeName = jsonObject.getString("typeName");
                    mineDealMedolArrayList.add(new MineDealMedol(price, createdDate, type1, remark, "1", "",typeName));
                }
                mineDealRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
//                ProgressDialogUtil.stop(getActivity());
                ErrorUtil.showErrorLayout(MineDealFragment.this,recyclerViewDeal,errorLinear,reLoadTv);
                if (isLoadMore){
                    pageNumAll--;
                    refreshLayout.finishLoadMore(false);
                }
                if(isRefresh){

                    refreshLayout.finishRefresh(false);
                }
            }
        });
    }


    /**
     * 提现明细数据相关
     *
     * @param userId    用户id
     * @param pageNum   页数
     * @param pageSize  每页的数据条数
     * @param startDate 请求数据的时间范围(开始)
     * @param endDate   请求数据的时间范围(结束)
     * @param status    请求数据的状态  ""为全部类型 -1为提现失败 0为申请中 1为提现成功
     */
    private void getDataOut(Long userId, final int pageNum, int pageSize, Date startDate, Date endDate, String status, final boolean isLoadMore,boolean isRefresh,boolean showLoad) {
        initTotalPriceLinear();
        if (selectorViewPagerItem(1)) return;
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(recyclerViewDeal,errorLinear);
        Map<String, Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        if (StringMyUtil.isEmptyString(status)) {
            data.put("user_id", userId);
            data.put("pageNo", pageNum);
            data.put("pageSize", pageSize);
            data.put("startDate", start);
            data.put("endDate", end);
        } else {
            data.put("user_id", userId);
            data.put("pageNo", pageNum);
            data.put("pageSize", pageSize);
            data.put("startDate", start);
            data.put("endDate", end);
            data.put("status", status);
        }
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            System.out.println(Utils.getString(R.string.map参数 : )+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
        Utils.docking(data, RequestUtil.REQUEST_wt7, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
//                ProgressDialogUtil.stop(getActivity());
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray memberDrawList = jsonObject1.getJSONArray("memberDrawList");
                int size = memberDrawList.size();//得到的数据容器size
                Long totalSize = jsonObject1.getLong("totalSize");
                if(!isLoadMore){
                    mineDealMedolArrayList.clear();
                    pageNumOut=1;
                    if (size == 0) {
                        nothing.setVisibility(View.VISIBLE);
                    } else {
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
                for (int i = 0; i < memberDrawList.size(); i++) {
                    JSONObject jsonObject = memberDrawList.getJSONObject(i);
                    String createdDate = jsonObject.getString("createdDate");//时间
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    String status1 = jsonObject.getString("status");//状态 -2拒绝申请并退回金额-1申请不通过0申请中1待打款2已打款
                    Long type = jsonObject.getLong("type");//type;//类型0余额提现1佣金提现
                    String typeName = jsonObject.getString("typeName");
                    String remark = jsonObject.getString("remark");
                    mineDealMedolArrayList.add(new MineDealMedol(price, createdDate, type, remark, "2", status1,typeName));
                }
                mineDealRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop(getActivity());
                loadingLinear.setVisibility(View.GONE);
                showToast(messageHead.getInfo());
                ErrorUtil.showErrorLayout(MineDealFragment.this,recyclerViewDeal,errorLinear,reLoadTv);
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
     * 充值明细数据相关
     *
     * @param userId    用户id
     * @param pageNum   页数
     * @param pageSize  每页的数据条数
     * @param startDate 请求数据的时间范围(开始)
     * @param endDate   请求数据的时间范围(结束)
     * @param status    请求数据的状态 0充值中 1充值成功 2充值失败
     */

    private void getDataIn(Long userId, int pageNum, int pageSize, Date startDate, Date endDate, String status, final boolean isLoadMore,boolean isRefresh,boolean showLoad) {
//        if (showProgress) {
//            ProgressDialogUtil.show(getContext());
//        }
        initTotalPriceLinear();
        if (selectorViewPagerItem(2)) return;
        if(showLoad){

            loadingLinear.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(recyclerViewDeal,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        if (StringMyUtil.isEmptyString(status)) {//status 为空时,不能传status 否则请求不到数据
            data.put("user_id", userId);
            data.put("pageNo", pageNum);
            data.put("pageSize", pageSize);
            data.put("startDate", start);
            data.put("endDate", end);
        } else {
            data.put("user_id", userId);
            data.put("pageNo", pageNum);
            data.put("pageSize", pageSize);
            data.put("startDate", start);
            data.put("endDate", end);
            data.put("status", status);
        }
        Utils.docking(data, RequestUtil.REQUEST_wt6, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
//                ProgressDialogUtil.stop(getActivity());
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray rechargeRecordList = jsonObject1.getJSONArray("rechargeRecordList");
                int size = rechargeRecordList.size();//得到的数据容器size
                Long totalSize = jsonObject1.getLong("totalSize");
                if (!isLoadMore) {
                    mineDealMedolArrayList.clear();
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
                for (int i = 0; i < rechargeRecordList.size(); i++) {
                    JSONObject jsonObject = rechargeRecordList.getJSONObject(i);
                    String createdDate = jsonObject.getString("createdDate");//时间
                    Long type = jsonObject.getLong("type");//类型/充值类型 0银行 1微信 2支付宝 3后台充值-余额 4代理充值(代理佣金余额充值给下级) 8(price<0 人工扣款-佣金  price>0 人工入款-佣金)后台充值-佣金 9百乐汇 else 在线充值
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    String status1 = jsonObject.getString("status");//status;//状态0充值中1充值成功2充值失败
                    String bankName = jsonObject.getString("bankName");
                    String typeName = jsonObject.getString("typeName");
                    String remark = jsonObject.getString("remark");
                    MineDealMedol mineDealMedol = new MineDealMedol(price, createdDate, type, remark, "3", status1, typeName);
                    mineDealMedol.setBankkName(bankName);
                    mineDealMedolArrayList.add(mineDealMedol);

                }
                mineDealRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
//                ProgressDialogUtil.stop(getActivity());
                ErrorUtil.showErrorLayout(MineDealFragment.this,recyclerViewDeal,errorLinear,reLoadTv);
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

    private boolean selectorViewPagerItem(int index) {
        MineDealActivity mineDealActivity = (MineDealActivity) getActivity();
        int currentItem = mineDealActivity.viewPager.getCurrentItem();
        if(currentItem!=index){
            return true;
        }
        return false;
    }

    private void initTotalPriceLinear() {
        MineDealActivity mineDealActivity = (MineDealActivity) getActivity();
        int currentItem = mineDealActivity.viewPager.getCurrentItem();
        if(currentItem==0&& StringMyUtil.isNotEmpty(typeAll)){
            total_price_linear.setVisibility(View.VISIBLE);
        }else {
            total_price_linear.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消eventBus注册
    }
}
