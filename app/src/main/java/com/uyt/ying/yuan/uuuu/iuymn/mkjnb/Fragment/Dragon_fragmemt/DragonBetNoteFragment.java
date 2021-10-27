
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.Dragon_fragmemt;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveShoppingActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineBetAcitivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineBetMoreActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter.LongDragonBetNoteRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class DragonBetNoteFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecycle;
    private LongDragonBetNoteRecycleAdapter mineBetRecycleAdapter;//投注详情适配器
    private ArrayList<MineBetModel> mineBetModelArrayList =new ArrayList<>();
    private TextView showMoreTv;
    private RefreshLayout refreshLayout;
    private LinearLayout noDataLinear;
    private   View footView;
    private ConstraintLayout loadingLinear;
    private TextView toBetTv;
    private LinearLayout errorLinear;
    private TextView reloadTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chang_long_bet_note, container, false);
        noDataLinear=view.findViewById(R.id.nothing_linear);
        loadingLinear=view.findViewById(R.id.loading_linear);
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        initRefresh(view);
        initRecycle(view);
        return view;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        refreshLayout.autoRefresh();
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
/*        Date start = DateUtil.getDateBefore(new Date(), 6);//得到7天前的当前时间
        Calendar todayCalendar = DateUtil.getTodayCalendar(start);//得到7天前时间的Calendar对象
        Date startTime = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
        Date endTime = new Date();//当前时间为结束时间*/
        Date startTime = new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
        Date endTime =  new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
        getMineBet(startTime,endTime,1,20,true );
    }

    private void initRefresh(View view) {
        toBetTv=view.findViewById(R.id.to_bet);
        toBetTv.setOnClickListener(this);
        refreshLayout=view.findViewById(R.id.refresh);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
/*                Date start = DateUtil.getDateBefore(new Date(), 6);//得到7天前的当前时间
                Calendar todayCalendar = DateUtil.getTodayCalendar(start);//得到7天前时间的Calendar对象
                Date startTime = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
                Date endTime = new Date();//当前时间为结束时间*/
                Date startTime = new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
                Date endTime =  new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
                getMineBet(startTime,endTime,1,20,false );

            }
        });
    }

    private void initRecycle(View view) {
        mRecycle=view.findViewById(R.id.changlong_bet_note_recycle);
        mineBetRecycleAdapter=new LongDragonBetNoteRecycleAdapter(mineBetModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecycle.setLayoutManager(linearLayoutManager);
        mRecycle.setAdapter(mineBetRecycleAdapter);
        mineBetRecycleAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), MineBetMoreActivity.class);
                intent.putExtra("perordercode",mineBetModelArrayList.get(position).getPerordercode());
//                startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
/*        Date start = DateUtil.getDateBefore(new Date(), 6);//得到7天前的当前时间
        Calendar todayCalendar = DateUtil.getTodayCalendar(start);//得到7天前时间的Calendar对象
        Date startTime = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
        Date endTime = new Date();//当前时间为结束时间*/
        Date startTime = new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
        Date endTime =  new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
        footView = LayoutInflater.from(view.getContext()).inflate(R.layout.changlong_recycle_foot, null);
        mineBetRecycleAdapter.addFooterView(footView);
        showMoreTv=footView.findViewById(R.id.changlong_show_more);
        //请求投注记录
        showMoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MineBetAcitivity.class));
            }
        });
        getMineBet(startTime,endTime,1,20 ,true);
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
     */
    private void getMineBet(Date startDate, Date endDate, int pageNo, int pageSize,boolean showLoad) {
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            noDataLinear.setVisibility(View.GONE);
            footView.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(mRecycle,errorLinear);
        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
        HashMap<String, Object> dataBet = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
//        if(typeId.equals("0")){
//            typeId="";
//        }
        if(user_id!=0l){
            dataBet.put("user_id",user_id);
        }
        dataBet.put("startDate",start);
        dataBet.put("endDate",end);
        dataBet.put("pageNo",pageNo);
        dataBet.put("pageSize",pageSize);
        dataBet.put("type",2);//0未结算1已结算2全部
        dataBet.put("remark","");//订单类型 已中奖 未中奖 等待开奖 撤单 (传中文)
        dataBet.put("groupByOrderCode","0");// 1 大单显示   0 详单显示
        Utils.docking(dataBet, RequestUtil.REQUEST_11r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                mineBetModelArrayList.clear();
                JSONObject jsonObject = JSONObject.parseObject(content);
                Long totalsize = jsonObject.getLong("totalsize");
                JSONArray betorderlist = jsonObject.getJSONArray("betorderlist");
                int size = betorderlist.size();
                if(betorderlist.size()==0){
                    footView.setVisibility(View.GONE);
                    noDataLinear.setVisibility(View.VISIBLE);
                }else {
                    footView.setVisibility(View.VISIBLE);
                    noDataLinear.setVisibility(View.GONE);
                }
                refreshLayout.finishRefresh();
//                dataSize=size;
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
                }
                mineBetRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getContext());
                refreshLayout.finishRefresh(false);
                ErrorUtil.showErrorLayout(DragonBetNoteFragment.this,mRecycle,errorLinear,reloadTv);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_bet:
                if(LoginIntentUtil.isLogin(getContext())){
       /*             ProgressDialogUtil.show(getActivity());
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("toShopping",true);
                    intent.putExtra("forGouCai",true);
                    startActivity(intent);
                    ProgressDialogUtil.stop(getActivity());*/
                   startActivity(new Intent(getContext(), LiveShoppingActivity.class));
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }
                break;
                default:
                    break;
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Date start = DateUtil.getDateBefore(new Date(), 7);//得到7天前的当前时间
//        Calendar todayCalendar = DateUtil.getTodayCalendar(start);//得到7天前时间的Calendar对象
//        Date startTime = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
//        Date endTime = new Date();//当前时间为结束时间
//        getMineBet(startTime,endTime,1,20 );
//    }
}
