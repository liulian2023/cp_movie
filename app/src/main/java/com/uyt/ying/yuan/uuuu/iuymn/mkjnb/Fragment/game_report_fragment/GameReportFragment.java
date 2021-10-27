package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.game_report_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.GameNoteActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineBetAcitivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.ReportAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ReportEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;

public class GameReportFragment extends BaseFragment {
    @BindView(R.id.game_report_refresh)
    RefreshLayout game_report_refresh;
    @BindView(R.id.game_report_recycler)
    RecyclerView game_report_recycler;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.profitAndLoss_tv)
    TextView profitAndLoss_tv;
    @BindView(R.id.activity_return_tv)
    TextView activity_return_tv;
    @BindView(R.id.mine_commission_tv)
    TextView mine_commission_tv;
    Date startDate;
    Date endDate;
    String time;
    int position;
    boolean statistisSuccess = false;
    boolean reportListSuccess = false;
    ArrayList<ReportEntity.GameReportBean> gameReportBeanArrayList = new ArrayList<>();
    ReportAdapter reportAdapter;
    //今日盈亏中的总盈亏
    private String profitAndLoss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_report, container, false);
        ButterKnife.bind(this,view);
        getArgumentsData();
        initRefresh();
        initRecycler();
        requestStatistics();
        return view;
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestReportList(false);
        requestStatistics();
    }

    private void requestStatistics() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> data = new HashMap<>();
        data.put("startDate",     simpleDateFormat.format(startDate));
        data.put("endDate",     simpleDateFormat.format(endDate));
        HttpApiUtils.CPnormalRequest(getActivity(),this, RequestUtil.REQUEST_06me, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String commissionTotalPrice = jsonObject.getString("commissionTotalPrice");//个人佣金
                String activityReturn = jsonObject.getString("activityReturn");//活动返点
                profitAndLoss = jsonObject.getString("profitAndLoss");//总盈亏
                statistisSuccess =true;
                activity_return_tv.setText(activityReturn);
                mine_commission_tv.setText(commissionTotalPrice);
                addWinOrLose(gameReportBeanArrayList);
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void getArgumentsData() {
        position=getArguments().getInt("position");
        switch (position){
            case 0:
                //今日
                startDate = new Date(System.currentTimeMillis()- SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
                endDate =  new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));
                time=CommonStr.TODAY;
                break;
            case 1:
                //昨日
                Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l)));//得到昨天的当前时间
                startDate = DateUtil.getStartTime(yesterdayCalendar);//得到昨天的开始时间(00:00:00 )
                endDate = DateUtil.getEndTime(yesterdayCalendar);//得到昨天的结束时间(23:59:59)
                time=CommonStr.YESTERDAY;
                break;
            case 2:
                //7天
                Date start = DateUtil.getDateBefore(new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l)), 6);//得到7天前的当前时间
                Calendar todayCalendar = DateUtil.getTodayCalendar(start);//得到7天前时间的Calendar对象
                startDate = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
                endDate = new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l));//当前时间为结束时间
                time=CommonStr.WEEK;
                break;
            default:
                break;

        }
    }

    private void initRecycler() {
        reportAdapter= new ReportAdapter(gameReportBeanArrayList,this);
        game_report_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        game_report_recycler.setAdapter(reportAdapter);
        requestReportList(false);
        reportAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ReportEntity.GameReportBean gameReportBean = gameReportBeanArrayList.get(position);
                if(gameReportBean.getGame()>=50){
                    GameNoteActivity.startAty(getContext(),gameReportBean.getGame()+"",time);
                }else {
//                    startActivity(new Intent(getContext(), MineBetAcitivity.class));
                    MineBetAcitivity.startAty(getContext(),time);
                }
            }
        });
    }
    private void requestReportList(boolean isRefresh) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> data = new HashMap<>();
        data.put("startDate",simpleDateFormat.format(startDate));
        data.put("endDate",simpleDateFormat.format(endDate));
        data.put("loginType",1);
        HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.GAME_REPORT, data, loading_linear, error_linear, reload_tv, (View) game_report_refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ReportEntity reportEntity = JSONObject.parseObject(result, ReportEntity.class);
                List<ReportEntity.GameReportBean> gameReportBeanList = reportEntity.getGameReport();
                RefreshUtil.success(1,game_report_refresh,loading_linear,nodata_linear,gameReportBeanList.size(),false,isRefresh,gameReportBeanArrayList);
                gameReportBeanArrayList.addAll(gameReportBeanList);
                reportAdapter.notifyDataSetChanged();
                reportListSuccess=true;
                addWinOrLose(gameReportBeanArrayList);
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,false,1,game_report_refresh);
            }
        });
    }

    /**
     * 计算今日总盈亏(需要加上game>50的盈亏金额)
     * @param gameReportBeanList
     */
    private void addWinOrLose(ArrayList<ReportEntity.GameReportBean> gameReportBeanList) {
/*        if(reportListSuccess&&statistisSuccess){
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (int i = 0; i <gameReportBeanArrayList.size() ; i++) {
                ReportEntity.GameReportBean gameReportBean = gameReportBeanList.get(i);
                int game = gameReportBean.getGame();
                if(game>=50){
                    String zjPrice = gameReportBean.getZjPrice();
                    totalPrice=new BigDecimal(zjPrice).add(totalPrice);
                }
            }
            profitAndLoss_tv.setText((new BigDecimal(profitAndLoss).add(totalPrice).setScale(2, RoundingMode.HALF_UP))+"");
            if(profitAndLoss_tv.getText().toString().length()>9){
                profitAndLoss_tv.setTextSize(18);
            }else {
                profitAndLoss_tv.setTextSize(20);
            }
        }*/

        if(reportListSuccess&&statistisSuccess){
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (int i = 0; i <gameReportBeanArrayList.size() ; i++) {
                ReportEntity.GameReportBean gameReportBean = gameReportBeanList.get(i);
                totalPrice=  new BigDecimal(gameReportBean.getProfitAndLoss()).add(totalPrice);

            }
            profitAndLoss_tv.setText(totalPrice.setScale(2, RoundingMode.HALF_UP)+"");
            if(profitAndLoss_tv.getText().toString().length()>9){
                profitAndLoss_tv.setTextSize(18);
            }else {
                profitAndLoss_tv.setTextSize(20);
            }
        }

    }

    private void initRefresh() {
        RefreshUtil.initRefresh(new SoftReference<>(getContext()), game_report_refresh, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestReportList(true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }
        });
    }

    public static GameReportFragment newInstance(Context context,int positon){
        GameReportFragment gameReportFragment = new GameReportFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",positon);
        gameReportFragment.setArguments(bundle);
        return gameReportFragment;
    }
}
