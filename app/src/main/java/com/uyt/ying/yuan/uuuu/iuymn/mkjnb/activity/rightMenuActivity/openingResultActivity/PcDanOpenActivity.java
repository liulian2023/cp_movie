package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity;

import androidx.annotation.NonNull;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.PcddOpentTodayResultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PcddTodayResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.ChooseTimePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PcDanOpenActivity extends BaseActivity implements ChooseTimePop.OnItemClidkListener, View.OnClickListener {
    private TextView chooseTimeText;
    private ImageView choosetimeIma;
    private LinearLayout chooseTimeLinear;
    private RecyclerView openResultRecy;
    private PcddOpentTodayResultAdapter pcddOpentTodayResultAdapter;
    private ArrayList<PcddTodayResultModel> pcddTodayResultModelArrayList =new ArrayList<>();
    private int pageNum =1;
    private RefreshLayout refreshLayout;
    private int type_id;
    private LinearLayout nodataLinear;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_dan_open);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.开奖结果));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        type_id = getIntent().getIntExtra("type_id", 0);
        bindview();
        ChooseTimePop.initPop(this,choosetimeIma,this);
        initRecycle();
        initRefresh();

    }

    @Override
    protected void init() {

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getOpenResult(type_id,1,chooseTimeText.getText().toString(),false,false,true);
    }

    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                refreshLayout.resetNoMoreData();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,true,false);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),true,false,false);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),true);

            }
        });
    }

    private void initRecycle() {
        openResultRecy=findViewById(R.id.pcdd_open_result_pop_recycle);
        pcddOpentTodayResultAdapter =new PcddOpentTodayResultAdapter(pcddTodayResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        openResultRecy.setLayoutManager(linearLayoutManager);
        openResultRecy.setAdapter(pcddOpentTodayResultAdapter);
//        getOpenResult(1,pageNum,chooseTimeText.getText().toString(),false);
        getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
    }
    public void getOpenResult(int type_id, int pageNo, String date, final boolean isLoadMore,boolean isRefresh,boolean showLoad){
        if(showLoad){
            nodataLinear.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        ErrorUtil.hideErrorLayout(openResultRecy,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",pageNo);
        data.put("pageSize",15);
        data.put("date",date);
        Utils.docking(data, RequestUtil.REQUEST_05dd, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(PcDanOpenActivity.this);

                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray danLotterylist = jsonObject1.getJSONArray("danLotterylist");
                int size = danLotterylist.size();
                if(!isLoadMore){
                    pcddTodayResultModelArrayList.clear();
                    if(size==0){
                        nodataLinear.setVisibility(View.VISIBLE);
                    }else {
                        nodataLinear.setVisibility(View.GONE);
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
                for (int i = 0; i < danLotterylist.size(); i++) {
                    JSONObject jsonObject = danLotterylist.getJSONObject(i);
                    String typeqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotterytime = jsonObject.getString("lotterytime");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String markdx = jsonObject.getString("markdx");//大小
                    String markds = jsonObject.getString("markds");//单双
                    pcddTodayResultModelArrayList.add(new PcddTodayResultModel(typeqishu,lotterytime,lotteryNo,sum,markdx,markds));
                }
                pcddOpentTodayResultAdapter.notifyDataSetChanged();


            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
                if(isLoadMore){
                    pageNum--;
                    refreshLayout.finishLoadMore(false);
                }
                ErrorUtil.showErrorLayout(PcDanOpenActivity.this,openResultRecy,errorLinear,reloadTv);
                ProgressDialogUtil.stop(PcDanOpenActivity.this);

            }
        });
    }
    private void bindview() {
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        nodataLinear=findViewById(R.id.nodata_linear);
        loadingLinear=findViewById(R.id.loading_linear);
        chooseTimeLinear=findViewById(R.id.time_choose_linear);//点击弹出时间pop
        choosetimeIma=findViewById(R.id.time_choose_img);//pop弹出时,旋转的图片
        chooseTimeText=findViewById(R.id.time_choose);
        refreshLayout=findViewById(R.id.refresh);
        chooseTimeLinear.setOnClickListener(this);

        long l = System.currentTimeMillis();
        Date date = new Date(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        chooseTimeText.setText(format);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.time_choose_linear:
                ChooseTimePop.showTimePop(this,chooseTimeLinear,choosetimeIma);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(TextView view, PopupWindow popupWindow) {
        switch (view.getId()){
            case R.id.time_one:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                chooseTimeText.setText(view.getText().toString());
                popupWindow.dismiss();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);
                break;
            case R.id.time_two:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                chooseTimeText.setText(view.getText().toString());
                popupWindow.dismiss();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);
                break;
            case R.id.time_three:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                chooseTimeText.setText(view.getText().toString());
                popupWindow.dismiss();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);
                break;
            case R.id.time_four:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                chooseTimeText.setText(view.getText().toString());
                popupWindow.dismiss();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);
                break;
            case R.id.time_five:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                chooseTimeText.setText(view.getText().toString());
                popupWindow.dismiss();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);
                break;
            case R.id.time_six:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                chooseTimeText.setText(view.getText().toString());
                popupWindow.dismiss();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);
                break;
            case R.id.time_seven:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                chooseTimeText.setText(view.getText().toString());
                popupWindow.dismiss();
                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false,false,true);
//                getOpenResult(type_id,pageNum,chooseTimeText.getText().toString(),false);
                break;
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
