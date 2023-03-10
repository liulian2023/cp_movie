package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity;

import android.content.Intent;
import androidx.annotation.NonNull;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.Happy8RecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy8OpenResultMedol;
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

public class Happy8OpentActivity extends BaseActivity implements View.OnClickListener, ChooseTimePop.OnItemClidkListener {
    private RadioButton numRadioButton;
    private RadioButton zongheRadioButton;
    private TextView chooseTimeText;
    private ImageView choosetimeIma;
    private LinearLayout chooseTimeLinear;
    private ArrayList<RadioButton> radioButtons =new ArrayList<>();
    private String todayDate;
    private RefreshLayout refreshLayout;//???????????? ????????????
    private int pageNum=1;
    private int type_id;//??????type_id
    private RecyclerView mRecy;
    private Happy8RecycleAdapter happy8RecycleAdapter;
    private ArrayList<Happy8OpenResultMedol> happy8OpenResultMedolArrayList =new ArrayList<>();
    private ConstraintLayout loadingLinear;
    private LinearLayout nodataLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private boolean isZongHe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_opent);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.????????????));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        Intent intent = getIntent();
        type_id = intent.getIntExtra("type_id", 1);//?????????activity??????????????????
        bindView();//????????????????????????????????????
        initRecycleView();//recycleview??????????????????
        initView();//?????????(??????recycleView??????)??????????????????
        ChooseTimePop.initPop(this,choosetimeIma,this);
        initRefresh();//???????????? ???????????????????????????
    }

    @Override
    protected void init() {

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getDada(1,15,chooseTimeText.getText().toString(),type_id,isZongHe,false,false,true);
    }

    /*
    ????????????????????????????????????
     */
    private void bindView() {
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        nodataLinear=findViewById(R.id.nodata_linear);
        loadingLinear=findViewById(R.id.loading_linear);
        refreshLayout=findViewById(R.id.refresh);//????????????
        happy8RecycleAdapter=new Happy8RecycleAdapter(happy8OpenResultMedolArrayList);
        mRecy=findViewById(R.id.happy8_open_recycle);//recycleView
        numRadioButton=findViewById(R.id.radio_button_num);//?????? ??????
        zongheRadioButton=findViewById(R.id.radio_button_zonghe);//?????? ??????
        chooseTimeText=findViewById(R.id.time_choose);//????????????????????????textView
        choosetimeIma=findViewById(R.id.time_choose_img);//??????textView?????????img(????????????????????????)
        chooseTimeLinear=findViewById(R.id.time_choose_linear);//????????????????????????pop?????????
        //??????????????????????????????,????????????????????????????????????????????????
        radioButtons.add(numRadioButton);
        radioButtons.add(zongheRadioButton);
        /*
        ??????????????????
         */
        numRadioButton.setOnClickListener(this);
        zongheRadioButton.setOnClickListener(this);

        chooseTimeLinear.setOnClickListener(this);
        numRadioButton.performClick();
    }
    /*
  recycleView???????????????
   */
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecy.setLayoutManager(linearLayoutManager);
        mRecy.setAdapter(happy8RecycleAdapter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//????????????
        //???????????????????????????
        getDada(1,15,todayDate,1,false,false,false,true);
//        getDada(1,15,todayDate,type_id,true,false,false,false,false);
    }
    //?????????????????????,????????????textView
    public void initView() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//????????????
        chooseTimeText.setText(todayDate);
    }

    /**
     * ???????????????????????????
     * @param pageNo ?????????????????????(???????????????????????????)
     * @param pageSize ???????????????????????????(???????????????15???)
     * @param date     ?????? (?????? 2019-6-10)
     * @param typeId   ??????type_id(?????????????????????)
     * @param isLoadMore ?????????????????????????????????(???????????????,????????????recycleView?????????)
     */
    private void getDada(int pageNo, int pageSize, String date, int typeId, final boolean isZongHe ,final boolean isLoadMore,boolean isRefresh,boolean showLoad) {
        this.isZongHe=isZongHe;
        if(showLoad){
            loadingLinear.setVisibility(View.VISIBLE);
            nodataLinear.setVisibility(View.GONE);
        }
        ErrorUtil.hideErrorLayout(mRecy,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",pageNo);
        data.put("pageSize",pageSize);
        data.put("date",date);
        data.put("type_id",typeId);
        Utils.docking(data, RequestUtil.REQUEST_04ha, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(Happy8OpentActivity.this);
                JSONObject jsonObject1= JSONObject.parseObject(content);
                JSONArray happyLotterylist = jsonObject1.getJSONArray("happyLotterylist");
                int size = happyLotterylist.size();
                if(!isLoadMore){//??????????????????,????????????,????????????????????????????????????
                    happy8OpenResultMedolArrayList.clear();
                    if(size==0){//??????????????????????????????0???, ?????????refreshLayout?????????
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

                for (int i = 0; i < happyLotterylist.size(); i++) {
                    JSONObject jsonObject = happyLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//????????????
                    String createdDate = jsonObject.getString("lotterytime");//??????
                    String lotteryNo = jsonObject.getString("lotteryNo");//????????????
                    String sum = jsonObject.getString("sum");//???????????? ???????????????
                    String markdx = jsonObject.getString("markdx");//???????????? ???????????????(??????)
                    String markds = jsonObject.getString("markds");//???????????? ???????????????(??????)
                    String markwu = jsonObject.getString("markwu");//???????????? ???????????????(??????)
                    String markqhh = jsonObject.getString("markqhh");//???????????? ???????????????(????????? )
                    String markddh = jsonObject.getString("markddh");//???????????? ???????????????(????????? )
                    if(!isZongHe){
                        happy8OpenResultMedolArrayList.add(new Happy8OpenResultMedol(lotteryqishu,createdDate,lotteryNo,false,sum,markdx,markds,markwu,markqhh,markddh,lotteryNo));
                    }else {
                        String needString = "";
                        needString+=sum+",";
//                        needString+=needString+","+markdx+",";
                        needString=needString+markdx+",";
                        needString=needString+markds+",";
                        needString=needString+markwu+",";
                        needString=needString+markqhh+",";
                        needString=needString+markddh+",";
                        needString = needString.substring(0, needString.length()-1);
                        happy8OpenResultMedolArrayList.add (new Happy8OpenResultMedol(lotteryqishu,createdDate,needString,true,sum,markdx,markds,markwu,markqhh,markddh,lotteryNo));
                    }
                }
                happy8RecycleAdapter.notifyDataSetChanged();

            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(Happy8OpentActivity.this);
                loadingLinear.setVisibility(View.GONE);
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
                if(isLoadMore){
                    pageNum--;
                    refreshLayout.finishLoadMore(false);
                }
                ErrorUtil.showErrorLayout(Happy8OpentActivity.this,mRecy,errorLinear,reloadTv);
            }
        });

    }
    private void initSwich(String type){
        for (int i = 0; i < happy8OpenResultMedolArrayList.size(); i++) {
            Happy8OpenResultMedol happy8OpenResultMedol = happy8OpenResultMedolArrayList.get(i);
            String lotteryCopy = happy8OpenResultMedol.getLotteryCopy();
            String sum = happy8OpenResultMedol.getSum();
            String markdx = happy8OpenResultMedol.getMarkdx();
            String markddh = happy8OpenResultMedol.getMarkddh();
            String markqhh = happy8OpenResultMedol.getMarkqhh();
            String markds = happy8OpenResultMedol.getMarkds();
            String markwu = happy8OpenResultMedol.getMarkwu();
            //?????? ?????? ??????,????????????lotteryCopy
            if(!type.equals(Utils.getString(R.string.??????))){
                happy8OpenResultMedol.setZongHe(false);
                happy8OpenResultMedol.setLotteryNo(lotteryCopy);
            }else {
                //?????? ?????? ?????? ????????????
                String needString = "";
                needString+=sum+",";
//                        needString+=needString+","+markdx+",";
                needString=needString+markdx+",";
                needString=needString+markds+",";
                needString=needString+markwu+",";
                needString=needString+markqhh+",";
                needString=needString+markddh+",";
                needString = needString.substring(0, needString.length()-1);
                happy8OpenResultMedol.setLotteryNo(needString);
                happy8OpenResultMedol.setZongHe(true);
            }
        }
        happy8RecycleAdapter.notifyDataSetChanged();
    }
    /*
???????????? ???????????? ???????????????
 */
    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(Happy8OpentActivity.this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(Happy8OpentActivity.this));
        //????????????
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;//??????????????????,pagenum??????
                /*
                ?????????????????????????????????(???????????????,?????????????????????????????????)
                 */
                if(numRadioButton.isChecked()){//??????????????????
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,true,false,false);
                }else {//??????????????????
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,true,false,false);
                }

            }
        });

        //????????????
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;//????????????,???pagenum?????????
                if(numRadioButton.isChecked()){//??????????????????
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,true,false);
                }else {//??????????????????
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,true,false);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_button_num://????????????
                pageNum=1;
                refreshLayout.resetNoMoreData();
//                getDada(1, 15, chooseTimeText.getText().toString(), type_id, false, false);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,false);
                initSwich("??????????????????");
                break;
            case R.id.radio_button_zonghe://????????????
                pageNum=1;
                refreshLayout.resetNoMoreData();
//                getDada(1, 15, chooseTimeText.getText().toString(), type_id, true, false);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,false);
                initSwich(Utils.getString(R.string.??????));
                break;
            case R.id.time_choose_linear://????????????????????????pop
                ChooseTimePop.showTimePop(Happy8OpentActivity.this,chooseTimeLinear,choosetimeIma);
                break;

        }
    }

    @Override
    public void onItemClick(TextView view, PopupWindow popupWindow) {
        switch (view.getId()){
            case R.id.time_one:
                initRadioButton(view);
                popupWindow.dismiss();

                break;
            case R.id.time_two:
                initRadioButton(view);
                popupWindow.dismiss();
                break;
            case R.id.time_three:
                initRadioButton(view);
                popupWindow.dismiss();
                break;
            case R.id.time_four:
                initRadioButton(view);
                popupWindow.dismiss();
                break;
            case R.id.time_five:
                initRadioButton(view);
                popupWindow.dismiss();
                break;
            case R.id.time_six:
                initRadioButton(view);
                popupWindow.dismiss();
                break;
            case R.id.time_seven:
                initRadioButton(view);
                popupWindow.dismiss();
                break;
        }
    }

    private void initRadioButton(TextView view) {
        pageNum=1;
        refreshLayout.resetNoMoreData();
        chooseTimeText.setText(view.getText().toString());
        if(numRadioButton.isChecked()){
            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,false,true);
        }else {
            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,false,true);
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
