package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter.ChessBetNoteAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessBetEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ReportEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ChooseTimePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class GameNoteActivity extends BaseActivity {
    @BindView(R.id.game_note_back_iv)
    ImageView game_note_back_iv;
    @BindView(R.id.game_note_title_tv)
    TextView game_note_title_tv;
    @BindView(R.id.game_note_time_tv)
    TextView game_note_time_tv;
    @BindView(R.id.game_note_time_iv)
    ImageView game_note_time_iv;
    @BindView(R.id.bet_amount_tv)
    TextView bet_amount_tv;
    @BindView(R.id.zj_amount_tv)
    TextView zj_amount_tv;
    @BindView(R.id.game_note_refresh)
    SmartRefreshLayout game_note_refresh;
    @BindView(R.id.game_note_recycler)
    RecyclerView game_note_recycler;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    String game;
    String time;
    private Date startDate;
    private Date endDate;
    ChessBetNoteAdapter chessBetNoteAdapter;
    ArrayList<ChessBetEntity.TouZhuListBean>chessBetEntityArrayList = new ArrayList<>();
    int pageNo=1;
    ChooseTimePop chooseTimePop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_note);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        ButterKnife.bind(this);
        getIntentData();
        initRecycler();
        requestTotalAmount();
        initRefresh();
        error_linear.setBackgroundColor(Color.parseColor("#F5F5F5"));
        nodata_linear.setBackgroundColor(Color.parseColor("#F5F5F5"));

    }

    private void requestTotalAmount() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> data = new HashMap<>();
        data.put("startDate",simpleDateFormat.format(startDate));
        data.put("endDate",simpleDateFormat.format(endDate));
        data.put("game",game);
        data.put("loginType",1);
        HttpApiUtils.CpRequest(this, null,RequestUtil.GAME_REPORT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ReportEntity reportEntity = JSONObject.parseObject(result, ReportEntity.class);
                List<ReportEntity.GameReportBean> reportEntityGameReport = reportEntity.getGameReport();
                for (int i = 0; i < reportEntityGameReport.size(); i++) {
                    ReportEntity.GameReportBean gameReportBean = reportEntityGameReport.get(i);
                    int selectorGame = gameReportBean.getGame();
                    if((selectorGame+"").equals(game)){
                        bet_amount_tv.setText("¥"+gameReportBean.getTzPrice());
                        zj_amount_tv.setText("¥"+gameReportBean.getZjPrice());
                        break;
                    }
                }
            }
            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void initRefresh() {
        RefreshUtil.initRefreshLoadMore(new SoftReference<>(this), game_note_refresh, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNo=1;
                requestReportList(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNo++;
                 requestReportList(false,true);

            }
        });
    }

    private void getIntentData() {
        game= getIntent().getStringExtra("game");
        time= getIntent().getStringExtra("time");
        if(time.equals(CommonStr.TODAY)){
            initTodayDate();
        }else if(time.equals(CommonStr.YESTERDAY)){
            initYesterdayDate();
        }else {
            initWeekDate();
        }
    }

    private void initRecycler() {
        chessBetNoteAdapter= new ChessBetNoteAdapter(chessBetEntityArrayList,this);
        game_note_recycler.setLayoutManager(new LinearLayoutManager(this));
        game_note_recycler.setAdapter(chessBetNoteAdapter);
        chessBetNoteAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ChessBetEntity.TouZhuListBean touZhuListBean = chessBetEntityArrayList.get(position);
                ChessBetMoreActivity.startAty(GameNoteActivity.this,touZhuListBean);
            }
        });
        requestReportList(false,false);
    }

    private void requestReportList(boolean isRefresh,boolean isLoadMore) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> data = new HashMap<>();
        data.put("startDate",     simpleDateFormat.format(startDate));
        data.put("endDate",     simpleDateFormat.format(endDate));
        data.put("game",game);
        data.put("pageNo",pageNo);
        data.put("pageSize",15);
        data.put("loginType",1);
        HttpApiUtils.cpShowLoadRequest(this, null, RequestUtil.CHESS_BET_LIST, data, loading_linear, error_linear, reload_tv, game_note_refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ChessBetEntity chessBetEntity = JSONObject.parseObject(result, ChessBetEntity.class);
                List<ChessBetEntity.TouZhuListBean> touZhuList = chessBetEntity.getTouZhuList();
                RefreshUtil.success(pageNo,game_note_refresh,loading_linear,nodata_linear,touZhuList.size(),isLoadMore,isRefresh,chessBetEntityArrayList);
                chessBetEntityArrayList.addAll(touZhuList);
                chessBetNoteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,isLoadMore,pageNo,game_note_refresh);
            }
        });
    }
    @OnClick({R.id.game_note_time_iv,R.id.game_note_time_tv,R.id.game_note_back_iv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.game_note_time_iv:
            case R.id.game_note_time_tv:
                if(null==chooseTimePop){
                    chooseTimePop = new ChooseTimePop(this,this);
                }
                chooseTimePop.showAsDropDown(game_note_time_iv,0,20, Gravity.BOTTOM);
                ProgressDialogUtil.darkenBackground(this,0.5f);
                chooseTimePop.setOnChooseTimeClickLintener(new ChooseTimePop.OnChooseTimeClickLintener() {
                    @Override
                    public void onChooseTimeClick(View view) {
                        switch (view.getId()){
                            case R.id.today_tv:
                                initTodayDate();
                                requestReportList(false,false);
                                requestTotalAmount();
                                chooseTimePop.dismiss();
                                break;
                            case R.id.yesterday_tv:
                                initYesterdayDate();
                                requestReportList(false,false);
                                requestTotalAmount();
                                chooseTimePop.dismiss();
                                break;
                            case R.id.week_tv:
                                initWeekDate();
                                requestReportList(false,false);
                                requestTotalAmount();
                                chooseTimePop.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                });
                break;
            case R.id.game_note_back_iv:
                finish();
                break;
            default:
                break;
        }
    }

    private void initWeekDate() {
        Date start = DateUtil.getDateBefore(new Date(System.currentTimeMillis()- SharePreferencesUtil.getLong(GameNoteActivity.this, "shijiancha", 0l)), 6);//得到7天前的当前时间
        Calendar todayCalendar = DateUtil.getTodayCalendar(start);//得到7天前时间的Calendar对象startDate = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
        startDate = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
        endDate = new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(GameNoteActivity.this, "shijiancha", 0l));//当前时间为结束时间
        game_note_time_tv.setText(CommonStr.WEEK);
    }

    private void initYesterdayDate() {
        Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date(System.currentTimeMillis()- SharePreferencesUtil.getLong(GameNoteActivity.this, "shijiancha", 0l)));//得到昨天的当前时间
        startDate = DateUtil.getStartTime(yesterdayCalendar);//得到昨天的开始时间(00:00:00 )
        endDate = DateUtil.getEndTime(yesterdayCalendar);//得到昨天的结束时间(23:59:59)
        game_note_time_tv.setText(CommonStr.YESTERDAY);
    }

    private void initTodayDate() {
        Calendar todayCalendar = DateUtil.getTodayCalendar(new Date(System.currentTimeMillis()- SharePreferencesUtil.getLong(GameNoteActivity.this, "shijiancha", 0l)));
        startDate = DateUtil.getStartTime(todayCalendar);
        endDate = DateUtil.getEndTime(todayCalendar);
        game_note_time_tv.setText(CommonStr.TODAY);
    }

    public static void  startAty(Context context, String game, String time){
        Intent intent = new Intent(context, GameNoteActivity.class);
        intent.putExtra("game",game);
        intent.putExtra("time",time);
        context.startActivity(intent);
    }
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
