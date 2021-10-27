package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveShoppingActivity;
import com.google.android.material.tabs.TabLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildBetAndDealAllPupopwindowAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter.MineBetRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.MineBetEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildBetAndDealAllPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
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



public class MineBetFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout mtab;
    private LinearLayout chooseLinear;//筛选按钮
    private LinearLayout wrapLinear;//popwindoew 定位元素
    private View inflateAll;
    private PopupWindow popupWindowAll;
    private RadioButton todayAll;
    private RadioButton yestodayAll;
    private RadioButton sevenAll;
    private Button chooseAll;
    private RecyclerView recyclerViewPup;
    private LayoutInflater layoutInflater;//布局管理器
    private ChildBetAndDealAllPupopwindowAdapter childBetAndDealAllPupopwindowAdapter;//筛选栏数据适配器
    private ArrayList<ChildBetAndDealAllPop> childBetAndDealAllPops =new ArrayList<>();//所有类型筛选popupwindow 数据
    private int  PopPosition;
    private RecyclerView mRecycle;//投注详情recycleView
    private MineBetRecycleAdapter mineBetRecycleAdapter;//投注详情适配器
    private ArrayList<MineBetModel> mineBetModelArrayList =new ArrayList<>();
    private int dataSize;
    private LinearLayout nothingLinear;
    private TextView toBet;//没有数据时的 Utils.getString(R.string.去投注)按钮

    private Date startDate;
    private Date endDate;
    private int pageNum=1;
    private int pageSize=15;
    private int type;
    private String remark;

    private RefreshLayout refreshLayout;
    private TextView chooseText;//点击筛选后,把文字设置为选中的彩种名
    private LinearLayout zongjiLinear;//中奖总计linear
    private TextView bonusPrice;//中奖金额总计
    private TextView amountPrice;//投注金额总计
    private String  ordercode;//注单号
    private String perordercode;
    private BigDecimal bonusAll=BigDecimal.ZERO;
    private BigDecimal amountAll=BigDecimal.ZERO;
    private TextView back;

    /*
    投注页面携带的数据
     */
    private String fromShopping;//投注页面携带的彩票名,跳转到此activity后,筛选栏要默认显示该彩票名
    private String type_id;//跳转到此activity后默认请求该彩票投注记录需要用到的参数
    private String game;//跳转到此activity后默认请求该彩票投注记录需要用到的参数
    private boolean isShopping=false;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reLoadTV;
    private String tabName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_mine_bet_fragment,container,false);
//        fromShopping = getIntent().getStringExtra("fromShopping");
//        type_id = getIntent().getStringExtra("type_id");
//        game = getIntent().getStringExtra("game");
        bindView(view);
        initView();
        refreshListener();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//注册eventBus
        }
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMineBet (MineBetEvenModel mineBetEvenModel){
        //startDate,endDate,pageNum+"",pageSize+"",2,type_id,game,remark,true
        endDate= mineBetEvenModel.getEndDate();
        startDate=mineBetEvenModel.getStartDate();
        type=mineBetEvenModel.getType();
        type_id=mineBetEvenModel.getTypeId();
        game=mineBetEvenModel.getGame();
        remark=mineBetEvenModel.getRemark();
        /*
        viewPager预加载功能会导致tab选中时,默认加载当前tab前后一个fragment的数据,如果这里不做判断的话,每次选中tab时,都会以当前tab传入的remake来请求数据, 导致tab切换后,前后fragemnt的数据相同,重新请求数据后会闪烁
        例如: 进入mineBetActivity默认请求的是全部类型的投注记录, 此时remake为"Utils.getString(R.string.(空), 不对remake做判断处理时,默认会将)Utils.getString(R.string.(空)作为前后两个fragment数据请求的参数,
              此时)已中奖Utils.getString(R.string.fragemnt中的数据预加载为remake为空时的数据(即跟)全部Utils.getString(R.string.fragemnt中数据一致),()滑动到 )已中奖Utils.getString(R.string. 对应的fragemnt下时, 再重新请求remake为)中奖"的数据,
              请求数据完成后,更换数据会导致闪烁
         */

        String tabName = getArguments().getString("tabName");
        this.tabName=tabName;
        /*
        根据判断请求fragment对应的数据
         */
        if(tabName.equals(Utils.getString(R.string.全部))){
            remark="";
        }else if(tabName.equals(Utils.getString(R.string.已中奖))){
            remark=Utils.getString(R.string.中奖);
        }else  if(tabName.equals(Utils.getString(R.string.未中奖))){
            remark=Utils.getString(R.string.未中);
        }else if(tabName.equals(Utils.getString(R.string.待开奖))){
            remark=Utils.getString(R.string.等待开奖);
        }else {
            remark=Utils.getString(R.string.撤单);
        }
        getMineBet(startDate,endDate,pageNum,pageSize,type,type_id,game,remark,false,false,mineBetEvenModel.isShowLoad());
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        if(tabName.equals(Utils.getString(R.string.全部))){
            remark="";
        }else if(tabName.equals(Utils.getString(R.string.已中奖))){
            remark=Utils.getString(R.string.中奖);
        }else  if(tabName.equals(Utils.getString(R.string.未中奖))){
            remark=Utils.getString(R.string.未中);
        }else if(tabName.equals(Utils.getString(R.string.待开奖))){
            remark=Utils.getString(R.string.等待开奖);
        }else {
            remark=Utils.getString(R.string.撤单);
        }
        getMineBet(startDate,endDate,pageNum,pageSize,type,type_id,game,remark,false,false,true);
    }

    private void refreshListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                pageNum=1;
//               initIsShopping();
                getMineBet(startDate,endDate,pageNum,pageSize,type,type_id,game,remark,false,true,false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
//              initIsShopping();
                getMineBet(startDate,endDate,pageNum,pageSize,type,type_id,game,remark,true,false,false);

            }
        });
    }

    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reLoadTV=view.findViewById(R.id.reload_tv);
        loadingLinear=view.findViewById(R.id.bet_loading_linear);
        /*
        投注详情主recycleView
         */
        mRecycle=view.findViewById(R.id.mine_bet_recycle);
        nothingLinear=view.findViewById(R.id.nothing_linear);
        toBet=view.findViewById(R.id.to_bet);
        toBet.setOnClickListener(this);
        /*
        下拉刷新
         */
        refreshLayout=view.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//数据未满一页时,禁用上拉加载
        refreshLayout.setEnableAutoLoadMore(false

        );
        /*
        金额总计
         */
        amountPrice=view.findViewById(R.id.amount_price);
        bonusPrice=view.findViewById(R.id.bonus_price);
        zongjiLinear=view.findViewById(R.id.zongji_linear);

    }


    public void initView() {
        /*
        投注详情主recycleView
         */
        mineBetRecycleAdapter=new MineBetRecycleAdapter(mineBetModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycle.setLayoutManager(linearLayoutManager);
        mRecycle.setAdapter(mineBetRecycleAdapter);
        mineBetRecycleAdapter.setOnItemClickListener(new MineBetRecycleAdapter.OnRecyclerViewItemClickListener() {
            /*
            点击跳转到订单详情
             */
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MineBetMoreActivity.class);
                intent.putExtra("perordercode",mineBetModelArrayList.get(position).getPerordercode());
//                startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
               if(requestCode==1){
                   for (int i = 0; i < mineBetModelArrayList.size(); i++) {
                       MineBetModel mineBetModel = mineBetModelArrayList.get(i);
                       String perordercode = mineBetModel.getPerordercode();
                       if(perordercode.equals(data.getExtras().getString("perordercode"))){
//                           mineBetModelArrayList.remove(i);
                           mineBetModel.setRemark(Utils.getString(R.string.撤单));
//                           mineBetModelArrayList.add(i,mineBetModel);
                           break;
                       }
                   }
                   mineBetRecycleAdapter.notifyDataSetChanged();
               }
               break;
        }
    }

    /**
     * 请求投注列表数据
     * @param startDate  开始时间
     * @param endDate  结束时间
     * @param pageNo   分页的页数
     * @param pageSize 每一页的数据size
     * @param type   请求类型 0未结算1已结算2全部
     * @param typeId  彩票type_id
     * @param game   彩票game
     * @param remark 订单类型  已中奖 未中奖 等待开奖 撤单 (传中文)
     * @param isLoad 是否是上拉加载更多时调用(用于判断是否要清空数据)
     */
    private void getMineBet(Date startDate, Date endDate, int pageNo, int pageSize, int type,  String typeId, String game, String remark, final boolean isLoad,boolean isRefesh,boolean showLoad) {
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nothingLinear.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(mRecycle,errorLinear);
        Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
        HashMap<String, Object> dataBet = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        if(typeId.equals("0")){
            typeId="";
        }if(game.equals("0")){
            game="";
        }
        dataBet.put("token",SharePreferencesUtil.getString(getActivity(),"token",""));
        dataBet.put("user_id",user_id);
        dataBet.put("startDate",start);
        dataBet.put("endDate",end);
        dataBet.put("pageNo",pageNo);
        dataBet.put("pageSize",pageSize);
        dataBet.put("type",2);//0未结算1已结算2全部
        dataBet.put("type_id",typeId);//彩种类型
        dataBet.put("game",game);//大彩种类型
        dataBet.put("remark",remark);//订单类型 已中奖 未中奖 等待开奖 撤单 (传中文)
        dataBet.put("groupByOrderCode","0");// 1 大单显示   0 详单显示
        Utils.docking(dataBet, RequestUtil.REQUEST_11r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
//                PopupWindowUtil.disMiss(MineBetAcitivity.this);
//                ProgressDialogUtil.stop(getContext());

                amountAll=BigDecimal.ZERO;
                bonusAll=BigDecimal.ZERO;
                JSONObject jsonObject = JSONObject.parseObject(content);
                Long totalsize = jsonObject.getLong("totalsize");
                JSONArray betorderlist = jsonObject.getJSONArray("betorderlist");
                int size = betorderlist.size();
                if(!isLoad){
                    mineBetModelArrayList.clear();
                    pageNum=1;
                    if(size==0){
                        nothingLinear.setVisibility(View.VISIBLE);
                    }else {
                        nothingLinear.setVisibility(View.GONE);
                    }
                    if(isRefesh){
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
                for (int i=0;i<betorderlist.size();i++) {
                    JSONObject jsonObject1 = betorderlist.getJSONObject(i);
                    BigDecimal amount = jsonObject1.getBigDecimal("amount");//投注金额
                    String createdDate = jsonObject1.getString("createdDate");//投注时间
                    BigDecimal bonus = jsonObject1.getBigDecimal("bonus");//中奖金额
                    String lotteryqishu = jsonObject1.getString("lotteryqishu");//彩票期数
                    String perordercode = jsonObject1.getString("perordercode");//注单号
                    String rulename = jsonObject1.getString("rulename");//投注类别
                    String remark = jsonObject1.getString("remark");//状态(中奖 未中 等待开奖)
                    String groupname = jsonObject1.getString("groupname");//投注类别
                    String typename = jsonObject1.getString("typename");//彩票名
                    mineBetModelArrayList.add(new MineBetModel(typename,lotteryqishu,createdDate,bonus,amount,groupname,rulename,remark,perordercode));
                }
                for (int i = 0; i < mineBetModelArrayList.size(); i++) {
                    BigDecimal amount = mineBetModelArrayList.get(i).getAmount();
                    BigDecimal bonus = mineBetModelArrayList.get(i).getBonus();
                    if(!mineBetModelArrayList.get(i).getRemark().equals(Utils.getString(R.string.撤单))){
                        amountAll=amount.add(amountAll);
                        bonusAll=bonus.add(bonusAll);
                    }
                }
                amountPrice.setText(Utils.getString(R.string.下注冒号)+amountAll+Utils.getString(R.string.元));
                bonusPrice.setText(Utils.getString(R.string.中奖冒号)+bonusAll+Utils.getString(R.string.元));
                mineBetRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop(getContext());
                loadingLinear.setVisibility(View.GONE);
                showToast(messageHead.getInfo());
                ErrorUtil.showErrorLayout(MineBetFragment.this,mRecycle,errorLinear,reLoadTV);
                if(isRefesh){
                    refreshLayout.finishRefresh(false);
                }
                if(isLoad){
                        pageNum--;
                    refreshLayout.finishLoadMore(false);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_bet://没有数据点击去投注跳转到购彩大厅
/*                ProgressDialogUtil.show(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("toShopping",true);
                intent.putExtra("forGouCai",true);
                startActivity(intent);
                ProgressDialogUtil.stop(getActivity());*/
                startActivity(new Intent(getContext(), LiveShoppingActivity.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
