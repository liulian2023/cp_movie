package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildBetRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.ChildBetEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class ChildBetFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ChildBetRecycleAdapter childBetRecycleAdapter;
    private ArrayList<ChildBetModel> childBetModelArrayList =new ArrayList<>();
    private PopupWindow popupWindowMore;
    private LinearLayout wrapLinear;
    private String ordercode;//????????? (?????????????????????)
    private RefreshLayout refreshLayout;//???????????? ???????????? ??????
    private   Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
    private int loadMoreNum=1;
    private int pageSize=15;
    private LinearLayout noData;
    private Date startDate;
    private Date endDate;
    private int pageNo;
    private String remark;
    private String nickName;
    private  String game;
    private  String typeId;
    private ConstraintLayout loadingConstraint;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private boolean isShowLoad;
    private boolean isLoadCopy;
    ChildBetEvenModel childBetEvenModel;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_child_bet,container,false);
        bindView(view);
        initView(view);
        initRycycleView(view);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//??????eventBus
        }
        return view;
    }
    @Subscribe (threadMode = ThreadMode.MAIN,sticky = true)
    public void getEvenData(ChildBetEvenModel childBetEvenModel){
        String tabName = getArguments().getString("tabName");
        pageNo=childBetEvenModel.getPageNo();
        pageSize=childBetEvenModel.getPageSize();
        remark=childBetEvenModel.getRemark();
        startDate=childBetEvenModel.getStartDate();
        endDate=childBetEvenModel.getEndDate();
        nickName=childBetEvenModel.getNickName();
        game=childBetEvenModel.getGame();
        typeId=childBetEvenModel.getTypeId();
        this. childBetEvenModel=childBetEvenModel;
        if(tabName.equals(Utils.getString(R.string.??????))){
            remark="";
        }else if(tabName.equals(getString(R.string.?????????))){
            remark=getString(R.string.??????);
        }else  if(tabName.equals(getString(R.string.?????????))){
            remark=getString(R.string.??????);
        }else if(tabName.equals(getString(R.string.????????????))){
            remark=getString(R.string.????????????);
        }
        boolean showLoad = childBetEvenModel.isShowLoad();
        getChildBet(user_id,pageNo,pageSize,remark,startDate,endDate,nickName,game,typeId,false,false,showLoad);
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        refreshLoadMoreRefrash();
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLoadMoreRefrash();
    }

    private void refreshLoadMoreRefrash() {

        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        //?????? Footer ??? ????????????
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadMoreNum=1;//???????????????????????????,?????????????????????,???pageNum?????????????????????????????????,????????????????????????????????????
                getChildBet(user_id,loadMoreNum,pageSize,remark,startDate,endDate,nickName,game,typeId,false,true,false);
//                getChildBet(user_id,pageNum+"",pageSize+"",status,startDate,endDate,initEdit(), childBetAndDealAllPopArrayList.get(RadioButtonPosition).getGame(), childBetAndDealAllPopArrayList.get(RadioButtonPosition).getTypoeId(),false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreNum++;
//                getChildBet(user_id,pageNum+"",pageSize+"",status,startDate,endDate,initEdit(), childBetAndDealAllPopArrayList.get(RadioButtonPosition).getGame(), childBetAndDealAllPopArrayList.get(RadioButtonPosition).getTypoeId(),true);
                   getChildBet(user_id,loadMoreNum,pageSize,remark,startDate,endDate,nickName,game,typeId,true,false,false);
            }
        });


    }
    private void initRycycleView(View view) {
        recyclerView=view.findViewById(R.id.child_bet_recycle);
        childBetRecycleAdapter=new ChildBetRecycleAdapter(childBetModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(childBetRecycleAdapter);
        childBetRecycleAdapter.setOnItemClickListener(new ChildBetRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                 View inflate = inflater.inflate(R.layout.child_bet_popwindow, null);
                TextView back =inflate.findViewById(R.id.action_bar_return);
                 LinearLayout wrap =inflate.findViewById(R.id.wrap_linear);
                TextView actionBarText =inflate.findViewById(R.id.action_bar_text);
                final TextView copy =inflate.findViewById(R.id.copy_zhudanhao);
                final TextView lotteryName =inflate.findViewById(R.id.lottery_name);
                final TextView qishu =inflate.findViewById(R.id.lottery_qishu);
                final  TextView neirong =inflate.findViewById(R.id.touzhuneirong);
                final  TextView jine =inflate.findViewById(R.id.touzhujine);
                final  TextView zhudanhao =inflate.findViewById(R.id.zhudanhao);
                final  TextView time =inflate.findViewById(R.id.touzhushijian);
                final  TextView peilv =inflate.findViewById(R.id.peilv);
                final TextView kaijianghaoma =inflate.findViewById(R.id.kaijianghaoma);
                final TextView zhuangtai =inflate.findViewById(R.id.zhuangtai);
                final TextView zhongjinagjine =inflate.findViewById(R.id.zhongjiangjine);
                final TextView yingkui =inflate.findViewById(R.id.yingkui);
                actionBarText.setText(Utils.getString(R.string.????????????));
                final ImageView imageView =inflate.findViewById(R.id.lottery_image);
                back.setOnClickListener(ChildBetFragment.this);
                HashMap<String, Object> orderDetail = new HashMap<>();
                orderDetail.put("perordercode",childBetModelArrayList.get(position).getPerordercode());
                Utils.docking(orderDetail, RequestUtil.REQUEST_13rzq, 1, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content) {
                        JSONObject jsonObject1 = JSONArray.parseObject(content);
                        JSONObject gametouzhu = jsonObject1.getJSONObject("gametouzhu");
                          String lotteryNo = gametouzhu.getString("lotteryNo");//????????????
                          BigDecimal amount = gametouzhu.getBigDecimal("amount");//????????????
                          String typename = gametouzhu.getString("typename");//?????????
                           BigDecimal bonus = gametouzhu.getBigDecimal("bonus");//????????????
                           ordercode = gametouzhu.getString("ordercode");//?????????
                           String remark = gametouzhu.getString("remark");//??????
                         String rulename = gametouzhu.getString("rulename");//???????????? ???
                        String groupname = gametouzhu.getString("groupname");//???????????? ???
                        String createdDate = gametouzhu.getString("createdDate");//????????????
                         BigDecimal odds = gametouzhu.getBigDecimal("odds");//??????
                        String lotteryqishu = gametouzhu.getString("lotteryqishu");//????????????
                        String image = gametouzhu.getString("image");//????????????
                        String firstImageUrl = SharePreferencesUtil.getString(getActivity(), "FirstImageUrl", "");
                        String finalImg = firstImageUrl + image;//????????????
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        long l = Long.parseLong(createdDate);
                          String format = simpleDateFormat.format(l);
                                        lotteryName.setText(typename);
                                        qishu.setText(lotteryqishu);
                                        neirong.setText("["+groupname+"]"+rulename);
                                        jine.setText(amount.setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.???));
                                        zhudanhao.setText(ordercode);
                                        time.setText(format);
                                        peilv.setText(odds.setScale(2,BigDecimal.ROUND_DOWN)+"");
                                        kaijianghaoma.setText(lotteryNo);
                                        zhuangtai.setText(remark);
                                        zhongjinagjine.setText(bonus.setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.???));
                                        yingkui.setText(bonus.subtract(amount).setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.???));
                        Glide.with(getActivity())
                                .load(finalImg)
                                .into(imageView);

                    }

                    @Override
                    public void failed(MessageHead messageHead) {

                    }
                });

                popupWindowMore = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
                popupWindowMore.setAnimationStyle(R.style.popAlphaanim0_3);//??????????????????
                popupWindowMore.setTouchable(true);//??????????????????
                popupWindowMore.showAsDropDown(wrap,Gravity.CENTER,0,0);
                copy.setOnClickListener(new View.OnClickListener() {
                    /*
                    ?????????????????????
                     */
                    @Override
                    public void onClick(View v) {
                        Utils.copyStr("orderCode",ordercode);
                    }
                });

            }
        });

    }

    private void getChildBet(Long userId, int pageNo, int pageSize, String remark, Date startDate, Date endDate, String childNikeName, String game, String typeId, final boolean isLoad,boolean isRefresh,boolean showLoad) {
        if (showLoad) {
            noData.setVisibility(View.GONE);
            loadingConstraint.setVisibility(View.VISIBLE);
        }
        isShowLoad=showLoad;
        isLoadCopy=isLoad;
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        HashMap<String, Object> dataBet = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = simpleDateFormat.format(startDate);
        String end = simpleDateFormat.format(endDate);
        dataBet.put("user_id",userId);
        dataBet.put("pageNo",pageNo);
        dataBet.put("pageSize",pageSize);
        dataBet.put("remark",remark);//???????????? ????????? ????????? ???????????? (?????????)
        dataBet.put("startDate",start);
        dataBet.put("endDate",end);
        dataBet.put("childNickname",childNikeName);//?????????????????????
        dataBet.put("game",game);//???????????????
        dataBet.put("type_id",typeId);//????????????
        Utils.docking(dataBet, RequestUtil.REQUEST_18rzq, 0, new DockingUtil.ILoaderListener() {
                @Override
            public void success(String content) {
                    loadingConstraint.setVisibility(View.GONE);
//                    ProgressDialogUtil.stop(getContext());
                JSONObject jsonObject = JSONObject.parseObject(content);
                Long totalsize = jsonObject.getLong("totalsize");
                JSONArray childBetOrderList = jsonObject.getJSONArray("childBetOrderList");
                int size = childBetOrderList.size();
                    if(!isLoad){
                        childBetModelArrayList.clear();
                        loadMoreNum=1;
                        if(size==0){
                            noData.setVisibility(View.VISIBLE);
                        }else {
                            noData.setVisibility(View.GONE);
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
//                loadMoreList.clear();
                for (int i=0;i<childBetOrderList.size();i++) {
                    JSONObject jsonObject1 = childBetOrderList.getJSONObject(i);
                    String lotteryNo = jsonObject1.getString("lotteryNo");//????????????
                    BigDecimal amount = jsonObject1.getBigDecimal("amount");//????????????
                    String createdDate = jsonObject1.getString("createdDate");//????????????
                    BigDecimal bonus = jsonObject1.getBigDecimal("bonus");//????????????
                    String nickname = jsonObject1.getString("nickname");//????????????
                    String lotteryqishu = jsonObject1.getString("lotteryqishu");//????????????
                    String perordercode = jsonObject1.getString("perordercode");//?????????
                    String rulename = jsonObject1.getString("rulename");//????????????
                    String remark = jsonObject1.getString("remark");//??????(?????? ?????? ????????????)
                    String groupname = jsonObject1.getString("groupname");//????????????
                    String typename = jsonObject1.getString("typename");//?????????
                    childBetModelArrayList.add(new ChildBetModel(lotteryNo,amount,createdDate,bonus,nickname,lotteryqishu,perordercode,rulename,remark,groupname,typename));

                }
                childBetRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                  loadingConstraint.setVisibility(View.GONE);
//                  ProgressDialogUtil.stop(getContext());
                    showToast(messageHead.getInfo());
                    ErrorUtil.showErrorLayout(ChildBetFragment.this,recyclerView,errorLinear,reloadTv);
                    if(isRefresh){
                        refreshLayout.finishRefresh(false);
                    }
                    if(isLoad){

                        refreshLayout.finishLoadMore(false);
                        loadMoreNum--;
                    }
            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        String tabName = getArguments().getString("tabName");
        if(tabName.equals(Utils.getString(R.string.??????))){
            remark="";
        }else if(tabName.equals(getString(R.string.?????????))){
            remark=getString(R.string.??????);
        }else if(tabName.equals(getString(R.string.?????????))){
            remark=getString(R.string.??????);
        }else if(tabName.equals(getString(R.string.????????????))){
            remark=getString(R.string.????????????);
        }
        getChildBet(user_id,1,pageSize,remark,startDate,endDate,nickName,game,typeId,false,false,true);
    }

    private void initView(View view) {

    }

    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        wrapLinear =view.findViewById(R.id.linear);
        refreshLayout=view.findViewById(R.id.refresh);
        noData=view.findViewById(R.id.nodata_linear);
        loadingConstraint =view.findViewById(R.id.loading_linear);
        loadingConstraint.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                popupWindowMore.dismiss();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//??????eventBus??????
    }
}
