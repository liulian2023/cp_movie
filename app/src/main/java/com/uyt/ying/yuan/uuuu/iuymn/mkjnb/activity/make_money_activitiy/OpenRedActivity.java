package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money.OpenRedAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.OpenRedBeen;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class OpenRedActivity extends BaseActivity {
    @BindView(R.id.open_red_back_iv)
    ImageView open_red_back_iv;
    @BindView(R.id.open_red_recycler)
    RecyclerView open_red_recycler;
    @BindView(R.id.open_red_btn)
    ImageView open_red_btn;
    private ArrayList<OpenRedBeen> openRedBeenArrayList = new ArrayList<>();

    OpenRedAdapter openRedAdapter;
    int redNum;
    String chatRoomId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_red);
        ButterKnife.bind(this);
        getIntentData();
        initRecycle();
        StatusBarUtil.setLightMode(this,true);
        StatusBarUtil.setColor(this, Color.WHITE);
        YoYo.with(Techniques.Shake)
                .duration(1200)
                .repeat(10000)
                .playOn(open_red_btn);
    }
    public static void startAty(Activity activity,int redNum,String chatRoomId){
        Intent intent = new Intent(activity, OpenRedActivity.class);
        intent.putExtra("redNum",redNum);
        intent.putExtra("chatRoomId",chatRoomId);
        activity.startActivityForResult(intent,1);
    }
    @Override
    protected void init() {

    }
    @OnClick({R.id.open_red_btn,R.id.open_red_back_iv})
    public void  onClick(View view){
        switch (view.getId()){
            case R.id.open_red_btn:

                resOpenQyRed();
/*                for (int i = 0; i < redNum; i++) {
                    BigDecimal bigDecimal = new BigDecimal("200");
                    OpenRedBeen openRedBeen = openRedBeenArrayList.get(i);
                    openRedBeen.setAmount(bigDecimal+"");
                    openRedBeen.setOpened(true);
                }
                openRedAdapter.notifyDataSetChanged();*/
                break;
            case R.id.open_red_back_iv:
                finish();
                break;
                default:
                    break;

        }
    }
    private void getIntentData() {
        redNum=  getIntent().getIntExtra("redNum",1);
        chatRoomId=  getIntent().getStringExtra("chatRoomId");
    }

    private void initRecycle() {
        openRedAdapter = new OpenRedAdapter(openRedBeenArrayList,this);
        open_red_recycler.setLayoutManager(new GridLayoutManager(this,2));
        open_red_recycler.setAdapter(openRedAdapter);
        ArrayList<Integer> drawableIdList = new ArrayList<>();
        for (int i = 0; i <redNum ; i++) {
            OpenRedBeen openRedBeen = new OpenRedBeen();
            if(drawableIdList.size()==0){
                    drawableIdList.add(R.drawable.open_red1);
                    drawableIdList.add(R.drawable.open_red2);
                    drawableIdList.add(R.drawable.open_red3);
                    drawableIdList.add(R.drawable.open_red4);
                    drawableIdList.add(R.drawable.open_red5);
                    drawableIdList.add(R.drawable.open_red6);
            }
            int nextInt = new Random().nextInt(drawableIdList.size());
            Integer resId = drawableIdList.get(nextInt);
            openRedBeen.setDrawableId(resId);
            drawableIdList.remove(resId);
            openRedBeenArrayList.add(openRedBeen);
        }
        openRedAdapter.notifyDataSetChanged();
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
                        OpenRedBeen openRedBeen = openRedBeenArrayList.get(i);
                        openRedBeen.setAmount(bigDecimal+"");
                        openRedBeen.setOpened(true);
                    }
                    MediaPlayer mPlayer = MediaPlayer.create(OpenRedActivity.this, R.raw.open_pack);
                    mPlayer.start();
                    openRedAdapter.notifyDataSetChanged();;
                    open_red_btn.setVisibility(View.INVISIBLE);
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
    public void onNetChange(boolean netWorkState) {

    }
}
