package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.twoLongDragon;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter.TwoChangLongAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TwoChangLongMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;

public class TwoLongDragonActivity extends BaseActivity {
    private RecyclerView mrecy;
    private TwoChangLongAdapter twoChangLongAdapter;
    private ArrayList<TwoChangLongMedol>twoChangLongMedolArrayList=new ArrayList<>();
    private int game;//游戏game
    private int type_id;//游戏game
    private LinearLayout nodataLinear;
    private ConstraintLayout loadingLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_chang_long);
        CommonToolbarUtil.initToolbar(this,getString(R.string.两面长龙));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        game = getIntent().getIntExtra("game",1);
        type_id = getIntent().getIntExtra("type_id",1);
        bindView();
        initRecylce();

    }

    @Override
    protected void init() {

    }

    private void initRecylce() {
        mrecy=findViewById(R.id.changlong_recycle);
        twoChangLongAdapter=new TwoChangLongAdapter(twoChangLongMedolArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mrecy.setLayoutManager(linearLayoutManager);
        mrecy.setAdapter(twoChangLongAdapter);
        requestList(game,type_id);
    }

    private void requestList(final int game, int type_id) {
        loadingLinear.setVisibility(View.VISIBLE);
        nodataLinear.setVisibility(View.GONE);
        String url="";
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        if(game==3){
            url=RequestUtil.REQUEST_06sc;
        }else if(game==2){
            url=RequestUtil.REQUEST_22r;
        }else if(game==9){
            url=RequestUtil.REQUEST_05xuanwu;
        } else if(game==8){
            url=RequestUtil.REQUEST_05happyten;
        }
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                loadingLinear.setVisibility(View.GONE);
                twoChangLongMedolArrayList.clear();
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray list = jsonObject.getJSONArray("list");
                if(list==null||list.size()==0){
                    nodataLinear.setVisibility(View.VISIBLE);
                }else {
                    nodataLinear.setVisibility(View.GONE);
                    for (int i = 0; i < list.size(); i++) {
                        JSONObject jsonObject1 = list.getJSONObject(i);
                        String sum = jsonObject1.getString("sum");//次数
                        String aClass = jsonObject1.getString("class");//玩法
                        twoChangLongMedolArrayList.add(new TwoChangLongMedol(aClass,sum,game));
                    }
                }
                twoChangLongAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);

            }
        });
    }

    private void bindView() {
        nodataLinear=findViewById(R.id.nodata_linear);
        loadingLinear=findViewById(R.id.loading_linear);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
