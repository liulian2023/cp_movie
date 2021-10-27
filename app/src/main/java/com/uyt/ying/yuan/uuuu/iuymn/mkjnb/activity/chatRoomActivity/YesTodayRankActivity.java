

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter.YestodayRankAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.YesTodayRankModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
        /*
        昨日排行activity
         */
public class YesTodayRankActivity extends BaseActivity {
    private RecyclerView mRecy;
    private YestodayRankAdapter yestodayRankAdapter;
    private ArrayList<YesTodayRankModel>yesTodayRankModelArrayList=new ArrayList<>();
    private TextView actionBarBack;
    private TextView actionCenter;
    private ImageView numOneImg;
    private TextView  numOneName;
    private TextView numOneAmount;
    private ImageView numTwoImg;
    private  TextView  numTwoName;
    private  TextView numTwoAmount;
    private ImageView numThreeImg;
    private  TextView  nuThreeName;
    private  TextView numThreeAmount;
    private static String TAG="YesTodayRankActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_today_rank);
        bindView();
        initRecycle();
    }
    private void initRecycle() {
        mRecy=findViewById(R.id.yestoday_rank_recycle);
        yestodayRankAdapter=new YestodayRankAdapter(yesTodayRankModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecy.setLayoutManager(linearLayoutManager);
        mRecy.setAdapter(yestodayRankAdapter);
        View headView= LayoutInflater.from(this).inflate(R.layout.yestoday_rank_head,null);
        numOneImg=headView.findViewById(R.id.no1_img);
        numOneName=headView.findViewById(R.id.no1_name);
        numOneAmount=headView.findViewById(R.id.no1_acmount);
        numTwoImg=headView.findViewById(R.id.no2_img);
        numTwoName=headView.findViewById(R.id.no2_name);
        numTwoAmount=headView.findViewById(R.id.no2_amount);
        numThreeImg=headView.findViewById(R.id.no3_img);
        nuThreeName=headView.findViewById(R.id.no3_name);
        numThreeAmount=headView.findViewById(R.id.no3_amount);
        yestodayRankAdapter.addHeaderView(headView);
        getWinner();
    }

    private void getWinner() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize", 10);
        Utils.docking(data, RequestUtil.CHATROOMRANKLIST, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(YesTodayRankActivity.this);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray list = jsonObject1.getJSONArray("list");
                for (int i = 0; i < list.size(); i++) {
                    JSONObject jsonObject = list.getJSONObject(i);
                    String name = jsonObject.getString("name");//用户名
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    String image = jsonObject.getString("image");//头像
                    String firstImgurl = Utils.getFirstImgurl(YesTodayRankActivity.this);
                    //头像
                    String imgUrl=firstImgurl+image;
                    if(i==0){
                        Glide.with(YesTodayRankActivity.this)
                                .load(imgUrl)
                                .error(R.drawable.system_default_title)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(numOneImg);
                        numOneName.setText(name);
                        numOneAmount.setText(price+"");
                    }else if(i==1){
                        Glide.with(YesTodayRankActivity.this)
                                .load(imgUrl)
                                .error(R.drawable.system_default_title)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(numTwoImg);
                        numTwoName.setText(name);
                        numTwoAmount.setText(price+"");
                    }else if(i==2){
                        Glide.with(YesTodayRankActivity.this)
                                .load(imgUrl)
                                .error(R.drawable.system_default_title)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(numThreeImg);
                        nuThreeName.setText(name);
                        numThreeAmount.setText(price+"");
                    }else {
                        yesTodayRankModelArrayList.add(new YesTodayRankModel(i+1+"",name,price+""));
                    }

                }
                yestodayRankAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(YesTodayRankActivity.this);
                Utils.logE(TAG, Utils.getString(R.string.请求昨日排行失败)+messageHead.getInfo() );
            }
        });
    }

    private void bindView() {
        actionBarBack=findViewById(R.id.action_bar_return);
        actionCenter=findViewById(R.id.action_bar_text);
        actionCenter.setText(Utils.getString(R.string.昨日奖金排行));
        actionBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void init() {

    }

            @Override
            public void onNetChange(boolean netWorkState) {

            }
        }
