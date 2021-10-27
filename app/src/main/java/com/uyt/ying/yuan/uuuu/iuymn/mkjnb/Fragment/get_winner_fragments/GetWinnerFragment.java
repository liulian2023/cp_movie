package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.get_winner_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity.ChatroomUserInfoActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.GetWinnerRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GetWinnerMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GetWinnerFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<GetWinnerMedol> getWinnerMedolArrayList=new ArrayList<>();
    private GetWinnerRecycleAdapter getWinnerRecycleAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.get_winner_fragment,container,false);
        bindView(view);
        initRecycle(view);
        return view;
    }

    private void bindView(View view) {

    }
        Handler handler= new Handler();
    /*
    循环添加中奖信息(添加到第一个,删除最后一个)
     */
         Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int size = getWinnerMedolArrayList.size();
                if(size !=0){
//                    Collections.swap(getWinnerMedolArrayList,0, size -1);
                    GetWinnerMedol getWinnerMedol = getWinnerMedolArrayList.get(getWinnerMedolArrayList.size() - 1);
                    getWinnerMedolArrayList.remove(getWinnerMedol);//先删除再添加(不然数据会错乱)
                    getWinnerMedolArrayList.add(0,getWinnerMedol);
//                    getWinnerMedolArrayList.remove(getWinnerMedolArrayList.get(getWinnerMedolArrayList.size()-1));
                    getWinnerRecycleAdapter.notifyItemInserted(0);
                    getWinnerRecycleAdapter.notifyItemRemoved(size-1);
//                    getWinnerRecycleAdapter.notifyDataSetChanged();
                    recyclerView.getLayoutManager().scrollToPosition(0);
//                    getWinnerRecycleAdapter.notifyDataSetChanged();
                    handler.postDelayed(runnable,4000);
                }
            }
        };

    @Override
    public void onResume() {
        if(runnable!=null){
           handler.removeCallbacks(runnable);
        }
        handler.postDelayed(runnable,4000);
        super.onResume();
    }

    private void initRecycle(View view) {
        recyclerView=view.findViewById(R.id.getwinner_recycel);
        getWinnerRecycleAdapter=new GetWinnerRecycleAdapter(getWinnerMedolArrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(getWinnerRecycleAdapter);
        getWinnerRecycleAdapter.setOnItemClickListener(new GetWinnerRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, GetWinnerMedol getWinnerMedol) {
                Intent intent = new Intent(getContext(), ChatroomUserInfoActivity.class);
                intent.putExtra("medol",getWinnerMedol);
                intent.putExtra("fromGetWinner",true);
                startActivity(intent);
            }
        });
        /*
        获取中奖信息列表数据
         */
        getData();
    }

    @Override
    public void onPause() {
        super.onPause();
        ProgressDialogUtil.stop(getContext());
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private void getData() {
        ProgressDialogUtil.show(getContext());
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",15);
        Utils.docking(data, RequestUtil.REQUEST_zz3, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(getContext());
                getWinnerMedolArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                JSONArray bonusRecordList = jsonObject1.getJSONArray("bonusRecordList");
                for (int i = 0; i < bonusRecordList.size(); i++) {
                    JSONObject jsonObject = bonusRecordList.getJSONObject(i);
                    String image = jsonObject.getString("image");
                    String finalurl=firstImageUrl+image;//头像url
                    String nickname = jsonObject.getString("nickname");//用户昵称
                    String lotteryType = jsonObject.getString("lotteryType");//彩票名
                    String lotteryTypeIds = jsonObject.getString("id");//列表id
                    BigDecimal winningPrice = jsonObject.getBigDecimal("winningPrice");//中奖金额
                    String checkedImages = jsonObject.getString("checkedImages");
                    ArrayList<String> stringList = new ArrayList(Arrays.asList(checkedImages.split(",")));
                    String grade = jsonObject.getString("grade");
                    String sex = jsonObject.getString("sex");
                    String title = jsonObject.getString("title");
                    getWinnerMedolArrayList.add(new GetWinnerMedol(finalurl,nickname,lotteryType,winningPrice,lotteryTypeIds,sex,title,grade,stringList));
                }
                getWinnerRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(getContext());
            }
        });
    }

}
