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
    private LinearLayout chooseLinear;//????????????
    private LinearLayout wrapLinear;//popwindoew ????????????
    private View inflateAll;
    private PopupWindow popupWindowAll;
    private RadioButton todayAll;
    private RadioButton yestodayAll;
    private RadioButton sevenAll;
    private Button chooseAll;
    private RecyclerView recyclerViewPup;
    private LayoutInflater layoutInflater;//???????????????
    private ChildBetAndDealAllPupopwindowAdapter childBetAndDealAllPupopwindowAdapter;//????????????????????????
    private ArrayList<ChildBetAndDealAllPop> childBetAndDealAllPops =new ArrayList<>();//??????????????????popupwindow ??????
    private int  PopPosition;
    private RecyclerView mRecycle;//????????????recycleView
    private MineBetRecycleAdapter mineBetRecycleAdapter;//?????????????????????
    private ArrayList<MineBetModel> mineBetModelArrayList =new ArrayList<>();
    private int dataSize;
    private LinearLayout nothingLinear;
    private TextView toBet;//?????????????????? Utils.getString(R.string.?????????)??????

    private Date startDate;
    private Date endDate;
    private int pageNum=1;
    private int pageSize=15;
    private int type;
    private String remark;

    private RefreshLayout refreshLayout;
    private TextView chooseText;//???????????????,????????????????????????????????????
    private LinearLayout zongjiLinear;//????????????linear
    private TextView bonusPrice;//??????????????????
    private TextView amountPrice;//??????????????????
    private String  ordercode;//?????????
    private String perordercode;
    private BigDecimal bonusAll=BigDecimal.ZERO;
    private BigDecimal amountAll=BigDecimal.ZERO;
    private TextView back;

    /*
    ???????????????????????????
     */
    private String fromShopping;//??????????????????????????????,????????????activity???,????????????????????????????????????
    private String type_id;//????????????activity?????????????????????????????????????????????????????????
    private String game;//????????????activity?????????????????????????????????????????????????????????
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
            EventBus.getDefault().register(this);//??????eventBus
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
        viewPager????????????????????????tab?????????,??????????????????tab????????????fragment?????????,??????????????????????????????,????????????tab???,???????????????tab?????????remake???????????????, ??????tab?????????,??????fragemnt???????????????,??????????????????????????????
        ??????: ??????mineBetActivity?????????????????????????????????????????????, ??????remake???"Utils.getString(R.string.(???), ??????remake??????????????????,????????????)Utils.getString(R.string.(???)??????????????????fragment?????????????????????,
              ??????)?????????Utils.getString(R.string.fragemnt????????????????????????remake??????????????????(??????)??????Utils.getString(R.string.fragemnt???????????????),()????????? )?????????Utils.getString(R.string. ?????????fragemnt??????, ???????????????remake???)??????"?????????,
              ?????????????????????,???????????????????????????
         */

        String tabName = getArguments().getString("tabName");
        this.tabName=tabName;
        /*
        ??????????????????fragment???????????????
         */
        if(tabName.equals(Utils.getString(R.string.??????))){
            remark="";
        }else if(tabName.equals(Utils.getString(R.string.?????????))){
            remark=Utils.getString(R.string.??????);
        }else  if(tabName.equals(Utils.getString(R.string.?????????))){
            remark=Utils.getString(R.string.??????);
        }else if(tabName.equals(Utils.getString(R.string.?????????))){
            remark=Utils.getString(R.string.????????????);
        }else {
            remark=Utils.getString(R.string.??????);
        }
        getMineBet(startDate,endDate,pageNum,pageSize,type,type_id,game,remark,false,false,mineBetEvenModel.isShowLoad());
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        if(tabName.equals(Utils.getString(R.string.??????))){
            remark="";
        }else if(tabName.equals(Utils.getString(R.string.?????????))){
            remark=Utils.getString(R.string.??????);
        }else  if(tabName.equals(Utils.getString(R.string.?????????))){
            remark=Utils.getString(R.string.??????);
        }else if(tabName.equals(Utils.getString(R.string.?????????))){
            remark=Utils.getString(R.string.????????????);
        }else {
            remark=Utils.getString(R.string.??????);
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
        ???????????????recycleView
         */
        mRecycle=view.findViewById(R.id.mine_bet_recycle);
        nothingLinear=view.findViewById(R.id.nothing_linear);
        toBet=view.findViewById(R.id.to_bet);
        toBet.setOnClickListener(this);
        /*
        ????????????
         */
        refreshLayout=view.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        //?????? Footer ??? ????????????
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//?????????????????????,??????????????????
        refreshLayout.setEnableAutoLoadMore(false

        );
        /*
        ????????????
         */
        amountPrice=view.findViewById(R.id.amount_price);
        bonusPrice=view.findViewById(R.id.bonus_price);
        zongjiLinear=view.findViewById(R.id.zongji_linear);

    }


    public void initView() {
        /*
        ???????????????recycleView
         */
        mineBetRecycleAdapter=new MineBetRecycleAdapter(mineBetModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycle.setLayoutManager(linearLayoutManager);
        mRecycle.setAdapter(mineBetRecycleAdapter);
        mineBetRecycleAdapter.setOnItemClickListener(new MineBetRecycleAdapter.OnRecyclerViewItemClickListener() {
            /*
            ???????????????????????????
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
                           mineBetModel.setRemark(Utils.getString(R.string.??????));
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
     * ????????????????????????
     * @param startDate  ????????????
     * @param endDate  ????????????
     * @param pageNo   ???????????????
     * @param pageSize ??????????????????size
     * @param type   ???????????? 0?????????1?????????2??????
     * @param typeId  ??????type_id
     * @param game   ??????game
     * @param remark ????????????  ????????? ????????? ???????????? ?????? (?????????)
     * @param isLoad ????????????????????????????????????(?????????????????????????????????)
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
        dataBet.put("type",2);//0?????????1?????????2??????
        dataBet.put("type_id",typeId);//????????????
        dataBet.put("game",game);//???????????????
        dataBet.put("remark",remark);//???????????? ????????? ????????? ???????????? ?????? (?????????)
        dataBet.put("groupByOrderCode","0");// 1 ????????????   0 ????????????
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
                    BigDecimal amount = jsonObject1.getBigDecimal("amount");//????????????
                    String createdDate = jsonObject1.getString("createdDate");//????????????
                    BigDecimal bonus = jsonObject1.getBigDecimal("bonus");//????????????
                    String lotteryqishu = jsonObject1.getString("lotteryqishu");//????????????
                    String perordercode = jsonObject1.getString("perordercode");//?????????
                    String rulename = jsonObject1.getString("rulename");//????????????
                    String remark = jsonObject1.getString("remark");//??????(?????? ?????? ????????????)
                    String groupname = jsonObject1.getString("groupname");//????????????
                    String typename = jsonObject1.getString("typename");//?????????
                    mineBetModelArrayList.add(new MineBetModel(typename,lotteryqishu,createdDate,bonus,amount,groupname,rulename,remark,perordercode));
                }
                for (int i = 0; i < mineBetModelArrayList.size(); i++) {
                    BigDecimal amount = mineBetModelArrayList.get(i).getAmount();
                    BigDecimal bonus = mineBetModelArrayList.get(i).getBonus();
                    if(!mineBetModelArrayList.get(i).getRemark().equals(Utils.getString(R.string.??????))){
                        amountAll=amount.add(amountAll);
                        bonusAll=bonus.add(bonusAll);
                    }
                }
                amountPrice.setText(Utils.getString(R.string.????????????)+amountAll+Utils.getString(R.string.???));
                bonusPrice.setText(Utils.getString(R.string.????????????)+bonusAll+Utils.getString(R.string.???));
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
            case R.id.to_bet://????????????????????????????????????????????????
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
