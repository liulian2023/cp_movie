package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.today_win_lose;

import androidx.annotation.NonNull;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter.TodayWinLoseAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TodayWinLoseModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
public class TodayWinLoseActivity extends BaseActivity implements View.OnClickListener {
    private TextView actionBarReturnTv;
    private TextView actionBarCenterTv;
    private TextView winloseAmountTvLeft;
    private TextView winloseAmountTvRight;
    private TextView betCountTv;
    private RecyclerView mRecycle;
    private TodayWinLoseAdapter todayWinLoseAdapter;
    private ArrayList<TodayWinLoseModel> todayWinLoseModelArrayList=new ArrayList<>();

    private int pageNum=1;
    private int pageSize=20;
    private int game;
    private int type_id;
//    private BigDecimal allAmount=BigDecimal.ZERO;
    private RefreshLayout refreshLayout;
    private LinearLayout nodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_win_lose);
        game= getIntent().getIntExtra("game",1);
        type_id= getIntent().getIntExtra("type_id",1);
        bindView();
        initRecycle();
        initRefresh();
        requestBetList(false);
    }

    @Override
    protected void init() {

    }

    private void initRefresh() {
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
//        refreshLayout.setEnableAutoLoadMore(false);//取消自动加载更多
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                requestBetList(false);
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                requestBetList(true);
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void initRecycle() {
        mRecycle=findViewById(R.id.today_win_lose_recycle);
        todayWinLoseAdapter=new TodayWinLoseAdapter(todayWinLoseModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycle.setLayoutManager(linearLayoutManager);
        mRecycle.setAdapter(todayWinLoseAdapter);


    }

    private void requestBetList(final boolean isloadMore) {
        HashMap<String, Object> data = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(new Date());
        String end = simpleDateFormat.format(new Date());
        data.put("user_id",user_id);
        data.put("startDate",start);
        data.put("endDate",end);
        data.put("pageNo",pageNum);
        data.put("pageSize",pageSize);
        data.put("type",1);//0未结算1已结算2全部
        data.put("type_id",type_id);//彩种类型
        data.put("game",game);//大彩种类型
        data.put("groupByOrderCode","0");// 1 大单显示   0 详单显示
        Utils.docking(data, RequestUtil.REQUEST_11r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                String totalsize1 = jsonObject1.getString("totalsize");//总笔数
                BigDecimal sumMoney = jsonObject1.getBigDecimal("sumMoney");//总输赢
                betCountTv.setText(Utils.getString(R.string.总笔数)+totalsize1);
                winloseAmountTvRight.setText(Utils.getString(R.string.总输赢)+sumMoney);
                winloseAmountTvLeft.setText(Utils.getString(R.string.总输赢)+sumMoney);//左侧占位置的textview 保持和右侧总输赢的内容一样
                JSONArray betorderlist = jsonObject1.getJSONArray("betorderlist");
                int size = betorderlist.size();
                Long totalsize = jsonObject1.getLong("totalsize");
                if(!isloadMore){
                    todayWinLoseModelArrayList.clear();
                    pageNum=1;
                    refreshLayout.resetNoMoreData();
                    if(size==0){
                        nodata.setVisibility(View.VISIBLE);
                    }else {
                        nodata.setVisibility(View.GONE);
                    }
                }
                if(size==0){
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                for (int i = 0; i < size; i++) {
                    JSONObject jsonObject = betorderlist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    BigDecimal amount = jsonObject.getBigDecimal("amount");//投注金额
                    BigDecimal bonus = jsonObject.getBigDecimal("bonus");//中奖金额
                    BigDecimal money = jsonObject.getBigDecimal("money");//输赢金额
                    todayWinLoseModelArrayList.add(new TodayWinLoseModel(lotteryqishu,amount,bonus,money));
                }
                //不需要累加总笔数和总输赢(进入页面直接显示总输赢)
               /* allAmount=BigDecimal.ZERO;
                for (int i = 0; i < todayWinLoseModelArrayList.size(); i++) {
                    BigDecimal winLoseAmount = todayWinLoseModelArrayList.get(i).getWinLoseAmount();
                    allAmount=winLoseAmount.add(allAmount);
                }
                betCountTv.setText(Utils.getString(R.string.总笔数:)+todayWinLoseModelArrayList.size()+"");
                winloseAmountTvRight.setText(Utils.getString(R.string.总输赢)+allAmount+"");
                winloseAmountTvLeft.setText(Utils.getString(R.string.总输赢)+allAmount+"");
               */
                todayWinLoseAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }

    private void bindView() {
        actionBarReturnTv=findViewById(R.id.action_bar_return);
        actionBarReturnTv.setOnClickListener(this);
        actionBarCenterTv=findViewById(R.id.action_bar_text);
        actionBarCenterTv.setText(Utils.getString(R.string.今日已结));
        winloseAmountTvLeft=findViewById(R.id.winlose_left);
        winloseAmountTvRight=findViewById(R.id.winlose_right);
        betCountTv=findViewById(R.id.bet_count);
        refreshLayout=findViewById(R.id.refresh);
        nodata=findViewById(R.id.nodata_linear);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
                break;
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
