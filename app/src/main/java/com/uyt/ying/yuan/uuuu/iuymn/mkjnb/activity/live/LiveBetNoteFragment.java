package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineBetMoreActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter.MineBetRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class LiveBetNoteFragment extends BaseFragment {
    private String type_id;
    private String remark;
    private String game;

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private MineBetRecycleAdapter mineBetRecycleAdapter;
    private ArrayList<MineBetModel> mineBetModelArrayList =new ArrayList<>();
    private ConstraintLayout loadingLinear;
    private LinearLayout nodataLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private int pageNum=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_bet_note, container, false);
        getArgumentsData();
        bindView(view);
        initRecycle();
        initRefresh();
        return view;
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        pageNum=1;
        getMineBet(pageNum,type_id,game,remark,false,false,true);
    }

    private void initRefresh() {
        RefreshUtil.initRefreshLoadMore(new SoftReference<>(getContext()), refreshLayout, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum=1;
                getMineBet(pageNum,type_id,game,remark,false,true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
            pageNum++;
                getMineBet(pageNum,type_id,game,remark,true,false,false);
            }
        });
    }

    private void initRecycle() {
        mineBetRecycleAdapter=new MineBetRecycleAdapter(mineBetModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mineBetRecycleAdapter);
        // 请求投注记录
        getMineBet(pageNum,type_id,game,remark,false,false,true);
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

    private void bindView(View view) {
        recyclerView=view.findViewById(R.id.bet_note_recycle);
        refreshLayout=view.findViewById(R.id.bet_note_refresh);
        loadingLinear=view.findViewById(R.id.loading_linear);
        nodataLinear=view.findViewById(R.id.live_bet_note_nodata_linear);
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv= view.findViewById(R.id.reload_tv);
    }

    private void getArgumentsData() {
        Bundle arguments = getArguments();
        int position = arguments.getInt("position");
        if(position==0){
            remark="";
        }else if(position==1){
            remark=Utils.getString(R.string.中奖);
        }else if(position==2){
            remark=Utils.getString(R.string.未中);
        }else if(position==3){
            remark=Utils.getString(R.string.等待开奖);
        }else {
            remark=Utils.getString(R.string.撤单);
        }
        game=arguments.getString("game");
        type_id=arguments.getString("type_id");
    }

    public static LiveBetNoteFragment newInstance(int position,String type_id,String game){
        LiveBetNoteFragment liveBetNoteFragment = new LiveBetNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putString("type_id",type_id);
        bundle.putString("game",game);
        liveBetNoteFragment.setArguments(bundle);
        return liveBetNoteFragment;
    }

    private void getMineBet( int pageNo, String typeId, String game, String remark, final boolean isLoad, boolean isRefesh, boolean showLoad) {
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nodataLinear.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
        HashMap<String, Object> dataBet = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
        String end = simpleDateFormat.format(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
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
        dataBet.put("pageSize",10);
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

                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray betorderlist = jsonObject.getJSONArray("betorderlist");
                int size = betorderlist.size();
                if(!isLoad){
                    mineBetModelArrayList.clear();
                    pageNum=1;
                    if(size==0){
                        nodataLinear.setVisibility(View.VISIBLE);
                    }else {
                        nodataLinear.setVisibility(View.GONE);
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
                mineBetRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop(getContext());
                loadingLinear.setVisibility(View.GONE);
                ErrorUtil.showErrorLayout(LiveBetNoteFragment.this,recyclerView,errorLinear,reloadTv);
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
}
