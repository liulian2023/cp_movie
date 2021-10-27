package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.AllLotteryAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Lottety;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 购彩大厅 全部彩票fragment
 */
public class AllLotteryFragment extends BaseFragment implements AllLotteryAdapter.OnRecyclerViewItemClickListener {
    private RecyclerView mRecy;
    private RefreshLayout refreshLayout;

    private ArrayList<Lottety> lotteties =new ArrayList<>();
    private ArrayList<Lottety> lottetiesTwo =new ArrayList<>();
    AllLotteryAdapter allLotteryAdapter;

    private ConstraintLayout loadingLinear;
    /*
    recycleView最后一次点击的view.具体作用参考viewpagerFragment
     */
    private View copyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_tottery_fragment, container, false);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mRecy = view.findViewById(R.id.all_tottety_recycleview);
        loadingLinear=view.findViewById(R.id.loading_linear);
        refreshLayout =view.findViewById(R.id.refresh);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            initview();
            iniData();
            refreshLayout.finishRefresh();

        }
    });
        initview();
        iniData();
        return view;
    }
    @Subscribe (threadMode = ThreadMode.MAIN,sticky =true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        selectorId(hbGameClassModel);
    }

    /**
     * 筛选限定彩票(显示限定图标)
     * @param hbGameClassModel
     */
    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        for (int i = 0; i < lottetiesTwo.size(); i++) {
            Lottety lottety = lottetiesTwo.get(i);
            for (int j = 0; j < idList.size(); j++) {
                if((lottety.getId()+"").equals(idList.get(j))){
                    lottety.setXian(true);
                    break;
                }
            }
        }
        if(null!=allLotteryAdapter){
            allLotteryAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        if(copyView!=null){
            copyView.setClickable(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initview () {
            mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
            allLotteryAdapter = new AllLotteryAdapter(lotteties,getActivity(),lottetiesTwo);
            mRecy.setAdapter(allLotteryAdapter);
            allLotteryAdapter.setOnItemClickListener(this);
        }


      public  void  iniData(){
          loadingLinear.setVisibility(View.VISIBLE);
              Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, -1, new DockingUtil.ILoaderListener() {
                  @Override
                  public void success(String content) {
                      loadingLinear.setVisibility(View.GONE);
                        handleJson(content);
                  }
                  @Override
                  public void failed(MessageHead messageHead) {
                    loadingLinear.setVisibility(View.GONE);
                  }
              });
      }
    private void handleJson(String content) {
        lotteties.clear();
        lottetiesTwo.clear();
        JSONObject gameInfoList = JSONObject.parseObject(content);//得到购彩大厅jsonObject
        JSONArray jsonArray = gameInfoList.getJSONArray("gameInfoList");//得到购彩大厅彩票分类
        String imaUrl = SharePreferencesUtil.getString(getActivity(), "FirstImageUrl", "");
        JSONArray gameClassList = gameInfoList.getJSONArray("gameClassList");//所有彩票
        for (int i = 0 ;i<jsonArray.size();i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String name = jsonObject1.getString("name");//得到彩票类型名称
            String image = jsonObject1.getString("image");//得到彩票类型图片路劲
//            Integer id = jsonObject1.getInteger("id");//彩票类型id (用于投注页面右侧菜单请求开奖结果时,弹出哪种pop)
            String classIds1 = jsonObject1.getString("classIds");//类型下的彩票数组
            String[] split = classIds1.split(",");
            List<String> strings = Arrays.asList(split);//得到彩票id
            lotteties.add(new Lottety("",name,1,"","",1,"",strings,""));
            for (int c= 0;c<gameClassList.size();c++){//遍历所有彩票
                JSONObject jsonObject = gameClassList.getJSONObject(c);
                String id1 = jsonObject.getString("id");//得到彩票id
                if(strings.contains(id1)){
                    String typename = jsonObject.getString("typename");
                    String image1 = jsonObject.getString("image");
                    String lotteryImg = imaUrl+image1;
                    String isStart = jsonObject.getString("isStart");
                    String isopenOffice = jsonObject.getString("isopenOffice");
                    Integer game = jsonObject.getInteger("game");
                    Integer type_id = jsonObject.getInteger("type_id");
                    String remark = jsonObject.getString("remark");
//                    if(isStart.equals("1")){
                        lottetiesTwo.add(new Lottety(lotteryImg, typename, type_id, isopenOffice, isStart, game, remark,strings,id1));
//                    }
                    allLotteryAdapter.notifyDataSetChanged();
                }
            }
        }
        HbGameClassModel instance = HbGameClassModel.getInstance();
        if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
            selectorId(instance);
        }
    }
    @Override
    public void onItemClick(View view, ArrayList<Lottety> lottetyArraytList, int position) {
        view.setClickable(false);
        copyView=view;
        Lottety lottety = lottetyArraytList.get(position);
        int type_id = lottety.getType_id();
        String isopenOffice = lottety.getIsopenOffice();
        String isStart = lottety.getIsStart();
        String name = lottety.getName();
        int game = lottety.getGame();
        if(!LoginIntentUtil.isLogin(getActivity())){
            LoginIntentUtil.toLogin(getActivity());
        }
        else{
            ToBetAtyUtils.toBetActivity(getActivity(),game,name,type_id, isopenOffice, isStart);
        }
    }

}