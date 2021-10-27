package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.MineIntegralRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineIntegralMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
/*
我的积分 activity
 */
public class MineIntegralActivity extends BaseActivity implements View.OnClickListener {
    private ArrayList<MineIntegralMedol> mineIntegralMedolArrayList =new ArrayList<>();
    private MineIntegralRecycleAdapter mineIntegralRecycleAdapter;
    private RecyclerView recyclerView;
    private TextView back;
    private TextView growthIntegralText;
    private TextView actionbarCenter;
    private RefreshLayout refreshLayout;
    private int pageNum=1;
    private int pageSize=10;
    private int dataSize;
    private LinearLayout nothing;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_integral);
        bindView();
        initRecycle();
        getMineIntegral();//得到累计积分(累计积分在另一个接口,所以单独请求)
        getIntegral(pageNum+"",pageSize+"",false,false);
        refreshLoadMoreListener();
    }

    @Override
    protected void init() {

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getIntegral(1+"",pageSize+"",false,false);
        getMineIntegral();
    }

    private void refreshLoadMoreListener() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                refreshLayout.resetNoMoreData();
                getIntegral(pageNum+"",pageSize+"",false,true);
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getIntegral(pageNum+"",pageSize+"",true,false);
//               if(dataSize==0){
//                   refreshLayout.finishLoadMoreWithNoMoreData();
//               }else {
//                   refreshLayout.finishLoadMore();
//               }
            }
        });
    }

    private void getMineIntegral() {
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 1l);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {

                ProgressDialogUtil.stop(MineIntegralActivity.this);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject1.getJSONObject("memberMoney");
                BigDecimal point = memberMoney.getBigDecimal("point");
                growthIntegralText.setText(point+"");
                if(point.compareTo(BigDecimal.ZERO)==0){
                    nothing.setVisibility(View.VISIBLE);
                }else {
                    nothing.setVisibility(View.GONE);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(MineIntegralActivity.this);

            }
        });
    }

    private void getIntegral(String pageNum, String pageSize, final boolean isLoad,boolean isRefresh) {
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        if(!isLoad&&!isRefresh){
            loadingLinear.setVisibility(View.VISIBLE);
        }
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 1l);
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",pageNum);
        data.put("pageSize",pageSize);
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_05me, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                JSONObject jsonObject1= JSONObject.parseObject(content);
                Long totalSize = jsonObject1.getLong("totalSize");
                JSONArray memberGainPointList = jsonObject1.getJSONArray("memberGainPointList");
                int size = memberGainPointList.size();
                dataSize=size;
//                if(totalSize==0){
//                    nothing.setVisibility(View.VISIBLE);
//                }else {
//                    nothing.setVisibility(View.GONE);
//                }
                if(!isLoad){
                    mineIntegralMedolArrayList.clear();
                }else {
                    if(size==0){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        refreshLayout.finishLoadMore();
                    }
                }
                for (int i=0;i<memberGainPointList.size();i++) {
                    JSONObject jsonObject = memberGainPointList.getJSONObject(i);
                    String createdDate = jsonObject.getString("createdDate");//时间
                    BigDecimal price = jsonObject.getBigDecimal("price");//金额
                    Long type = jsonObject.getLong("type");//类型
                    mineIntegralMedolArrayList.add(new MineIntegralMedol(price,createdDate,type));
                }
                mineIntegralRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                ErrorUtil.showErrorLayout(MineIntegralActivity.this,recyclerView,errorLinear,reloadTv);
            }
        });

    }

    private void initRecycle() {
        mineIntegralRecycleAdapter=new MineIntegralRecycleAdapter(mineIntegralMedolArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mineIntegralRecycleAdapter);

    }

    private void bindView() {
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        loadingLinear=findViewById(R.id.loading_linear);
        recyclerView=findViewById(R.id.integral_recycle) ;
        back=findViewById(R.id.action_bar_return) ;
        actionbarCenter=findViewById(R.id.action_bar_text) ;
        actionbarCenter.setText(Utils.getString(R.string.积分));
        growthIntegralText=findViewById(R.id.add_integral) ;
        refreshLayout=findViewById(R.id.refresh_integral);
        back.setOnClickListener(this);
        nothing=findViewById(R.id.no_data_linear);
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
