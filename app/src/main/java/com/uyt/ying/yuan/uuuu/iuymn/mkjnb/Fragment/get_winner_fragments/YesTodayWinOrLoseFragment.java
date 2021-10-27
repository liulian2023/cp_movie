package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.get_winner_fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter.YestodayWinRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.YestodayWinMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class YesTodayWinOrLoseFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private YestodayWinRecycleAdapter yestodayWinRecycleAdapter;
    private ArrayList<YestodayWinMedol>yestodayWinMedolArrayList =new ArrayList<>();


    public static YesTodayWinOrLoseFragment newInstant(){
        YesTodayWinOrLoseFragment yesTodayWinOrLoseFragment = new YesTodayWinOrLoseFragment();
        return yesTodayWinOrLoseFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.yestoday_winorlose_fragment,container,false);
        bindView(view);
        initRecycle(view);
        return view;
    }

    private void initRecycle(View view) {
        recyclerView=view.findViewById(R.id.yestoday_win_recycle);
        yestodayWinRecycleAdapter=new YestodayWinRecycleAdapter(yestodayWinMedolArrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(yestodayWinRecycleAdapter);
        yestodayWinRecycleAdapter.setOnItemClickListener(new YestodayWinRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ChatroomUserInfoActivity.class);
                YestodayWinMedol yestodayWinMedol = yestodayWinMedolArrayList.get(position);
                intent.putExtra("medol", yestodayWinMedol);
                intent.putExtra("fromYestoday",true);
                startActivity(intent);
            }
        });
              getData();
    }
        /*
        请求昨日盈利数据
         */
    private void getData() {
        ProgressDialogUtil.show(getContext());
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",21);
        Utils.docking(data, RequestUtil. REQUEST_zz5, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(getContext());
                yestodayWinMedolArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray bonusRecordList = jsonObject1.getJSONArray("bonusRecordList");
                for (int i = 0; i < bonusRecordList.size(); i++) {
                    JSONObject jsonObject = bonusRecordList.getJSONObject(i);
                    String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                    String image = jsonObject.getString("image");
                    String finalImg=firstImageUrl+image;
                    String nickname = jsonObject.getString("nickname");//用户昵称
                    BigDecimal yesterdayProfit = jsonObject.getBigDecimal("yesterdayProfit");//昨日盈利
                    String title = jsonObject.getString("title");//用户头衔
                    String sex = jsonObject.getString("sex");//性别
                    String grade = jsonObject.getString("grade");//用户等级
                    String checkedImages = jsonObject.getString("checkedImages");//喜欢的彩票
                    ArrayList<String> stringList = new ArrayList(Arrays.asList(checkedImages.split(",")));
                    String id = jsonObject.getString("id");//请求后端接口的参数(列表id)
                    //String imageURl, String nickName, BigDecimal price, String lotteryId, String title, String sex, String grade, ArrayList<String> stringList
                      yestodayWinMedolArrayList.add(new YestodayWinMedol(finalImg,nickname,yesterdayProfit.setScale(0,BigDecimal.ROUND_DOWN),id,title,sex,grade,stringList));
                }
                yestodayWinRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(getContext());
            }
        });
    }

    private void bindView(View view) {

    }
}
