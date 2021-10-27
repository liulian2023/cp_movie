package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money.OpenInviteAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.OpenInviteRedModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Headers;


public class OpenInviteRedActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back_iv;
    private TextView red_num_tv;
    private ImageView open_red_iv;
    private RecyclerView open_invite_red_recycle;
    private OpenInviteAdapter openInviteAdapter;
    private ArrayList<OpenInviteRedModel> openInviteRedModelArrayList  = new ArrayList<>();

    private int redNum;
    private String chatRoomId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_invite_red);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.transparent));
        getIntentData();
        bindView();
        initRecy();
    }

    private void getIntentData() {
       redNum=   getIntent().getIntExtra("redNum",0);
        chatRoomId=   getIntent().getStringExtra("chatRoomId");

    }

    public static void startAty(Activity activity, int redNum, String chatRoomId){
        Intent intent = new Intent(activity, OpenInviteRedActivity.class);
        intent.putExtra("redNum",redNum);
        intent.putExtra("chatRoomId",chatRoomId);
        activity.startActivityForResult(intent,1);

    }

    private void initRecy() {
            openInviteAdapter = new OpenInviteAdapter(openInviteRedModelArrayList,this);

        open_invite_red_recycle.setLayoutManager(new GridLayoutManager(this,3));
        open_invite_red_recycle.setAdapter(openInviteAdapter);
        for (int i = 0; i < redNum; i++) {
            openInviteRedModelArrayList.add(new OpenInviteRedModel());
        }
        openInviteAdapter.notifyDataSetChanged();
    }

    private void bindView() {
        back_iv=findViewById(R.id.back_iv);
        red_num_tv=findViewById(R.id.red_num_tv);
        open_invite_red_recycle=findViewById(R.id.open_invite_red_recycle);
        open_red_iv=findViewById(R.id.open_red_iv);
        back_iv.setOnClickListener(this);
        open_red_iv.setOnClickListener(this);
        red_num_tv.setText(Utils.getString(R.string.恭喜您获得)+redNum+Utils.getString(R.string.个红包));
    }
    private void resOpenQyRed() {
        HashMap<String, Object> data = new HashMap<>();
        if(StringMyUtil.isNotEmpty(chatRoomId)){
            data.put("chatRoomId",chatRoomId);
        }
        HttpApiUtils.CpRequest(this,null, RequestUtil.HB_OPEN_QUYUE_HB, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                if(jsonObject.containsKey("price")){
                    JSONArray price = jsonObject.getJSONArray("price");
                    for (int i = 0; i < price.size(); i++) {
                        BigDecimal bigDecimal = price.getBigDecimal(i);
                        OpenInviteRedModel openInviteRedModel = openInviteRedModelArrayList.get(i);
                        openInviteRedModel.setAmount(bigDecimal);
                        openInviteRedModel.setOpened(true);
                    }
                    open_red_iv.setImageResource(R.drawable.kaihongbao_hui);
                    open_red_iv.setClickable(false);
                    openInviteAdapter.notifyDataSetChanged();
                    setResult(RESULT_OK);
                }else {
                    String message = jsonObject.getString("message");
                    showToast(message);
                }

            }
            @Override
            public void onFailed(String msg) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.open_red_iv:
                resOpenQyRed();

          /*      for (int i = 0; i < redNum; i++) {
                    OpenInviteRedModel openInviteRedModel = openInviteRedModelArrayList.get(i);
                    openInviteRedModel.setAmount(new BigDecimal("22"));
                    openInviteRedModel.setOpened(true);
        *//*            if(startAnimationListener!=null){
                        startAnimationListener.onStartAnimation();
                    }*//*
//                    openInviteAdapter.startAnimation();
                }
                open_red_iv.setImageResource(R.drawable.kaihongbao_hui);
                open_red_iv.setClickable(false);
                openInviteAdapter.notifyDataSetChanged();
                setResult(RESULT_OK);*/
                break;
                default:
                    break;
        }
    }
    @Override
    protected void init() {

    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
    public interface  OnStartAnimationListener{
        void  onStartAnimation();
    }
    OnStartAnimationListener startAnimationListener;

    public void setStartAnimationListener(OnStartAnimationListener startAnimationListener) {
        this.startAnimationListener = startAnimationListener;
    }
}
