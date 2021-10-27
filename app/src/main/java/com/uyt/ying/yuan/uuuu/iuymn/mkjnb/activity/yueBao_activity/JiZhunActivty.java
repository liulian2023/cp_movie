
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.yueBao_activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.UpLevelRewardRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UpLevelRewardModel;

import java.util.ArrayList;

/*
余额宝基准收益页面
 */
public class JiZhunActivty extends BaseActivity {
        private RecyclerView recyclerView;
        private UpLevelRewardRecycleAdapter upLevelRewardRecycleAdapter;
        private ArrayList<UpLevelRewardModel> upLevelRewardModelArrayList =new ArrayList<>();
        private TextView backTv;
        //有效份额tv
        private TextView jiZhunValidAmountTv;
        //七日年化tv
        private TextView jiZhunRateTv;
        //万份收益tv
        private TextView jiZhunAmountTv;

        private String jiZhunValidAmount;
        private String jiZhunRate;
        private String jiZhunAmount;

        private TextView actionBarTextTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_zhun_activty);
        Intent intent = getIntent();
        jiZhunValidAmount= intent.getStringExtra("jiZhunValidAmount");
        jiZhunRate= intent.getStringExtra("jiZhunRate");
        jiZhunAmount= intent.getStringExtra("jiZhunAmount");
        bindView();
        initRecycle();
    }

    @Override
    protected void init() {

    }

    private void initRecycle() {
        upLevelRewardRecycleAdapter =new UpLevelRewardRecycleAdapter(upLevelRewardModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(upLevelRewardRecycleAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.jizhun_recycle_head,null);
        upLevelRewardRecycleAdapter.addHeaderView(headView);
        initRecycleData();

        jiZhunValidAmountTv=headView.findViewById(R.id.jiZhunValidTv);
        jiZhunRateTv=headView.findViewById(R.id.jiZhunRateTv);
        jiZhunAmountTv=headView.findViewById(R.id.jiZhunAmountTv);
        jiZhunValidAmountTv.setText(jiZhunValidAmount);
        jiZhunRateTv.setText(jiZhunRate);
        jiZhunAmountTv.setText(jiZhunAmount);
    }

    private void initRecycleData() {
        upLevelRewardModelArrayList.add(new UpLevelRewardModel(Utils.getString(R.string.时间),Utils.getString(R.string.操作),Utils.getString(R.string.余额宝余额),Utils.getString(R.string.有效份额)));
        upLevelRewardModelArrayList.add(new UpLevelRewardModel("00:00~23:50",Utils.getString(R.string.未转入),"1W","1W"));
        upLevelRewardModelArrayList.add(new UpLevelRewardModel("23:50",Utils.getString(R.string.转入2W),"3W","3W"));
        upLevelRewardModelArrayList.add(new UpLevelRewardModel("23:55",Utils.getString(R.string.转出1W),"2W","2W"));
        upLevelRewardModelArrayList.add(new UpLevelRewardModel("00:05",Utils.getString(R.string.转入1W),"3W","2W"));
        upLevelRewardModelArrayList.add(new UpLevelRewardModel("00:10",Utils.getString(R.string.转出0.5W),"2.5W","2W"));
        upLevelRewardModelArrayList.add(new UpLevelRewardModel("00:15",Utils.getString(R.string.转出1W),"1.5W","1.5W"));
    }
    private void bindView() {
        actionBarTextTv=findViewById(R.id.action_bar_text);
        actionBarTextTv.setText(Utils.getString(R.string.基准收益));
        recyclerView=findViewById(R.id.jizhun_recycle);
        backTv=findViewById(R.id.action_bar_return);
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
