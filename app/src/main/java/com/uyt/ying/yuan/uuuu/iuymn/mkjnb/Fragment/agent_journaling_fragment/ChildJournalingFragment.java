package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.agent_journaling_fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.AgentJournalingActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildJournalingFragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ShowByLotteryRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildJournaling;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShowByLotteryType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import okhttp3.Headers;

public class ChildJournalingFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<ChildJournaling>childJournalingArrayList =new ArrayList<>();//????????????????????????
    private ArrayList<ShowByLotteryType>showByLotteryTypeArrayList =new ArrayList<>();//???????????????????????????
    private ArrayList<String>nickNameList =new ArrayList<>();//??????????????????(?????????????????????????????????????????????)
    private RecyclerView recyclerView;//??????????????????recycleView
    private ChildJournalingFragmentAdapter childJournalingFragmentAdapter;//????????????recycle?????????
    private ShowByLotteryRecycleAdapter showByLotteryRecycleAdapter;//???????????????recycle?????????
    private View inflateChild;//?????????????????????
    private PopupWindow popupWindowChild;//??????????????????
    private LinearLayout actionBar;
    private LinearLayout chooseLinear;//??????popupwindow???????????????
    private RadioButton today;//??????????????????
    private RadioButton yestoday;//??????????????????
    private RadioButton seven;//??????????????????
    private RadioButton touzhu;//??????????????????
    private RadioButton yinkui;//??????????????????
    private RadioButton chongzhi;//??????????????????
    private RadioButton fanyong;//??????????????????
    private RadioButton shengxu;//??????????????????
    private RadioButton jiangxu;//??????????????????
    private Button chooseButton;//????????????
    private EditText searchEdit;//???????????????
    private  TextView searchText;
    private  Date start;//??????????????????????????????
    private  Date end;//??????????????????????????????
    private  String orderBy;//??????????????????????????????
    private int pageNum=1;
    private int pageSize=15;
    private TextView cz;//???????????????????????????
    private TextView tx;
    private TextView tz;
    private TextView zj;
    private TextView gryj;
    private TextView hdfd;
    private TextView gryk;
    private TextView teamTable;//?????????????????????Utils.getString(R.string.??????????????????)??????
    private TextView lettoryShow;//?????????????????????Utils.getString(R.string.???????????????)??????
    private LinearLayout linearLayoutParrent;
    private RefreshLayout refreshLayout;
    private LinearLayout nothing;
    private int dataSize;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private boolean isShowLoad=false;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.child_journaling_fragment,container,false);
        initView();
        bindView(view);
        initRecycle(view);
        initRefresh();
        return view;
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",true,isShowLoad);
    }

    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        //?????? Footer ??? ????????????
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initOrder( false);
//                getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",false);
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
               pageNum++;
//                initOrder();
               getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",true,false);

                refreshLayout.finishLoadMore();
            }
        });

    }

    public void initView() {
         /*
        ???????????????????????????(????????????)
         */
        LayoutInflater inflater = LayoutInflater.from(getContext());//???????????????
        inflateChild = inflater.inflate(R.layout.child_journaling_popupwindow, null);//?????????????????????
        popupWindowChild = new PopupWindow(inflateChild, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//???????????????popupWindow
        popupWindowChild.setAnimationStyle(R.style.popAlphaanim0_3);//??????????????????
        popupWindowChild.setTouchable(true);//??????????????????
        popupWindowChild.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
            }
        });
    }



    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        reloadTv.setOnClickListener(this);
        loadingLinear=view.findViewById(R.id.loading_linear);
        chooseLinear=getActivity().findViewById(R.id.choose_linear2);
        actionBar=getActivity().findViewById(R.id.linear);//??????????????????????????????
        searchEdit =view.findViewById(R.id.search_edit);
        today =inflateChild.findViewById(R.id.today);
        yestoday =inflateChild.findViewById(R.id.yestoday);
        seven =inflateChild.findViewById(R.id.seven);
        touzhu =inflateChild.findViewById(R.id.touzhu);
        yinkui =inflateChild.findViewById(R.id.yinkui);
        chongzhi =inflateChild.findViewById(R.id.chongzhi);
        fanyong =inflateChild.findViewById(R.id.fanyong);
        shengxu =inflateChild.findViewById(R.id.shengxu);
        jiangxu =inflateChild.findViewById(R.id.jiangxu);
        chooseButton =inflateChild.findViewById(R.id.choose_button);
        searchText =view.findViewById(R.id.search_text);
        refreshLayout=view.findViewById(R.id.refresh);
        nothing=view.findViewById(R.id.nodata_linear);
        chooseLinear.setOnClickListener(this);
        chooseButton.setOnClickListener(this);
        today.setOnClickListener(this);
        today.performClick();
        seven.setOnClickListener(this);
        touzhu.setOnClickListener(this);
        touzhu.performClick();
        yinkui.setOnClickListener(this);
        chongzhi.setOnClickListener(this);
        fanyong.setOnClickListener(this);
        shengxu.setOnClickListener(this);
        jiangxu.setOnClickListener(this);
        jiangxu.performClick();
        searchText.setOnClickListener(this);;
    }

/*
????????????recycleView??????
 */
    private void initRecycle(View view) {
        recyclerView =  view.findViewById(R.id.child_journaling_recycle);
        childJournalingFragmentAdapter = new ChildJournalingFragmentAdapter(childJournalingArrayList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(childJournalingFragmentAdapter);
        if(start==null||end==null){
            start=new Date();end=new Date();
        }
        getChildInfo(pageNum+"",pageSize+"",start,end,"","2","0",false,true);
        //recycleView item????????????
        childJournalingFragmentAdapter.setOnItemClickListener(new ChildJournalingFragmentAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                switch (view.getId()){
                    case R.id.big_linear:
                        teamTable=view.findViewById(R.id.team_table);
                        linearLayoutParrent =view.findViewById(R.id.lettory_show_linear);// ????????????????????????(?????????????????????????????????)
                        if(childJournalingArrayList.get(position).getTouzhu().equals("0.00")){
                            linearLayoutParrent.setVisibility(View.GONE);//????????????,????????????????????????
                      }
                        ImageView imageView =view.findViewById(R.id.img);
                        imageView.setPivotX(imageView.getWidth()/2);
                        imageView.setPivotY(imageView.getHeight()/2);//?????????????????????
                        LinearLayout linearLayout = view.findViewById(R.id.buttom_linear);
                        if(linearLayout.getVisibility()==View.GONE){
                            linearLayout.setVisibility(View.VISIBLE);
                            imageView.setRotation(90);
                        }else{
                            linearLayout.setVisibility(View.GONE);
                            imageView.setRotation(0);
                        }
                        break;
                    case R.id.team_table:
                        //?????????????????????????????????,????????????????????????????????????
                        Intent intent = new Intent(getContext(), AgentJournalingActivity.class);
                        //??????activity?????????????????????????????????????????????
                         intent.putExtra("isSearch", true);
                        //??????????????????nikeName??????
                        intent.putExtra("nikename", childJournalingArrayList.get(position).getName());
                        startActivity(intent);
                        getActivity().finish();//????????????,????????????activity,??????????????????activity????????????
                        break;
                    case R.id.lettory_show:
                        LayoutInflater inflater = LayoutInflater.from(getContext());//???????????????
                        View view1 = inflater.inflate(R.layout.show_by_lottery_type_popupwindow, null);//?????????????????????
                        TextView textView =view1.findViewById(R.id.dingwei);//??????????????????
                        LinearLayout back = view1.findViewById(R.id.x);//????????????
                        final PopupWindow popupWindow = new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//???????????????popupWindow
                        popupWindow.setAnimationStyle(R.style.popAlphaanim0_3);//??????????????????
                        popupWindow.setTouchable(true);//??????????????????
                        popupWindow.showAtLocation(textView,Gravity.BOTTOM,0,0);
                        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                        back.setOnClickListener(new View.OnClickListener() {//????????????
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                ProgressDialogUtil.darkenBackground(getActivity(),1f);
                            }
                        });
//                        popupwindow???recycleView????????????
                        RecyclerView recyclerView =view1.findViewById(R.id.show_by_lottery_type_recycle);

                        showByLotteryRecycleAdapter = new ShowByLotteryRecycleAdapter(showByLotteryTypeArrayList);
                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                        recyclerView.setAdapter(showByLotteryRecycleAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager1);
                        //?????????????????????item??????????????????(??????????????????)
                        Date start = childJournalingArrayList.get(position).getStart();
                        Date end = childJournalingArrayList.get(position).getEnd();
                        String orderBy = childJournalingArrayList.get(position).getOrderBy();
                        String name = childJournalingArrayList.get(position).getName();
                        long id = childJournalingArrayList.get(position).getId();

                        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formatStart = simpleDateFormat.format(start);
                        String formatEnd = simpleDateFormat.format(end);
                        HashMap<String, Object> showByType = new HashMap<>();
                        showByType.put("user_id",user_id);
                        showByType.put("pageNo",1);
                        showByType.put("pageSize",20);
                        showByType.put("startDate",formatStart);
                        showByType.put("endDate",formatEnd);
                        showByType.put("childNickname",name);
                        showByType.put("orderBy",orderBy);
                        showByType.put("isColor","1");//?????????????????????(0???1???)
                        HttpApiUtils.CPnormalRequest(getActivity(), ChildJournalingFragment.this, RequestUtil.REQUEST_REPORT_LIST, showByType, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                showByLotteryTypeArrayList.clear();
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                JSONArray colorList = jsonObject.getJSONArray("colorList");
                                for (int i=0;i<colorList.size();i++) {
                                    JSONObject jsonObject1 = colorList.getJSONObject(i);
                                    String typename = jsonObject1.getString("typename");//????????????
                                    BigDecimal profitTotalPrice = jsonObject1.getBigDecimal("profitTotalPrice");//??????
                                    BigDecimal bettingTotalPrice = jsonObject1.getBigDecimal("bettingTotalPrice");//??????
                                    BigDecimal lotteryTotalPrice = jsonObject1.getBigDecimal("lotteryTotalPrice");//????????????
                                    showByLotteryTypeArrayList.add(new ShowByLotteryType(typename,bettingTotalPrice+"",lotteryTotalPrice+"",profitTotalPrice+""));
                                }
                                showByLotteryRecycleAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailed(String msg) {

                            }
                        });

                }

            }
        });

    }
    /*
    ????????????????????????
     */
    private void getChildInfo(String pagenum,String pageSize,final Date start, final Date end, final String nickName, final String orderBy, String isColor, final boolean isLoad,boolean showLoad) {
//        ProgressDialogUtil.show(getActivity());
        isShowLoad=showLoad;
        if(showLoad){
            nothing.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        HashMap<String, Object> dataChild = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatStart = simpleDateFormat.format(start);
        String formatEnd = simpleDateFormat.format(end);
        dataChild.put("user_id",user_id);
        dataChild.put("pageNo",pagenum);
        dataChild.put("pageSize",pageSize);
        dataChild.put("startDate",formatStart);
        dataChild.put("endDate",formatEnd);
        dataChild.put("childNickname",nickName);
        dataChild.put("orderBy",orderBy);
       Utils.docking(dataChild, RequestUtil.REQUEST_REPORT_LIST, 0, new DockingUtil.ILoaderListener() {
           @Override
           public void success(String content) {
//               ProgressDialogUtil.stop(getActivity());
               loadingLinear.setVisibility(View.GONE);
               JSONObject jsonObject = JSONObject.parseObject(content);
               JSONArray teamList = jsonObject.getJSONArray("teamList");
               Long totalsize = jsonObject.getLong("totalsize");
               int size = teamList.size();
               dataSize=size;
               if(!isLoad){
               childJournalingArrayList.clear();
               nickNameList.clear();
               }

             if(totalsize==0){
                 if(!isLoad){
                     nothing.setVisibility(View.VISIBLE);
                 }else{
                     nothing.setVisibility(View.GONE);
                 }
                 refreshLayout.finishLoadMoreWithNoMoreData();
             }
               for (int i=0;i<teamList.size();i++) {
                   JSONObject jsonObject1 = teamList.getJSONObject(i);
                   String pointGrade = jsonObject1.getString(CommonStr.GRADE);//vip??????
                   BigDecimal profitTotalPrice = jsonObject1.getBigDecimal("profitTotalPrice");//??????
                   String nickname = jsonObject1.getString("nickname");//??????
                   BigDecimal investmentAmount = jsonObject1.getBigDecimal("investmentAmount");//????????????
                   BigDecimal czPrice = jsonObject1.getBigDecimal("czPrice");//????????????
                   BigDecimal txPrice = jsonObject1.getBigDecimal("txPrice");//????????????
                   BigDecimal winMoney = jsonObject1.getBigDecimal("winMoney");//????????????
                   BigDecimal activityReturn = jsonObject1.getBigDecimal("activityReturn");//????????????
                   BigDecimal commission = jsonObject1.getBigDecimal("commission");//????????????
                   Long id = jsonObject1.getLong("id");//??????id
                   nickNameList.add(nickname);//???????????????,???????????????????????????????????????
                   childJournalingArrayList.add(new ChildJournaling(nickname,pointGrade,investmentAmount+"",profitTotalPrice+"",czPrice+"",txPrice+"",winMoney+"",commission+"",activityReturn+"",id,start,end,orderBy));
               }
               childJournalingFragmentAdapter.notifyDataSetChanged();
           }

           @Override
           public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
//               ProgressDialogUtil.stop(getActivity());
               ErrorUtil.showErrorLayout(ChildJournalingFragment.this,recyclerView,errorLinear,reloadTv);
           }
       });

    }
/*
???????????????????????????
 */
    private String initEdit(){
        String editText = searchEdit.getText().toString();
        if (editText.isEmpty()) {
            editText = "";
        }
        return  editText;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload_tv:
                getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",false,isShowLoad);//????????????????????????
                break;
            //?????????????????????
            case R.id.choose_linear2:
                popupWindowChild.showAsDropDown(actionBar , 0, 0); // ??????????????????,?????????
                ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                break;
            case R.id.choose_button:
            if(today.isChecked()){
                start = new Date();
                end = new Date();
                initOrder(true);
            }
            else if(yestoday.isChecked()){
                Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());//???????????????????????????
                start= DateUtil.getStartTime(yesterdayCalendar);//???????????????????????????
                end = DateUtil.getEndTime(yesterdayCalendar);//??????????????????????????????
                initOrder(true);
            }
            else if(seven.isChecked()){
               start = DateUtil.getDateBefore(new Date(),6);
               end=new Date();
               initOrder(true);
            }
            popupWindowChild.dismiss();
                break;
            case R.id.search_text:
//                System.out.println(searchEdit.getText().toString()+"  tostring");
                if(nickNameList.contains(searchEdit.getText().toString())){
                    if(today.isChecked()){
                        start = new Date();
                        end = new Date();
                        initOrder(true);
                    }
                    else if(yestoday.isChecked()){
                        Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());//???????????????????????????
                        start= DateUtil.getStartTime(yesterdayCalendar);//???????????????????????????
                        end = DateUtil.getEndTime(yesterdayCalendar);//??????????????????????????????
                        initOrder(true);
                    }
                    else if(seven.isChecked()){
                        start = DateUtil.getWeekTimeStart();
                        end = DateUtil.getWeekTimeEnd();
                        initOrder(true);
                    }
                }else {
                    showToast(Utils.getString(R.string.?????????????????????????????????));
                }
                break;
        }
    }
        /*
        ??????orberBy??????,???????????????????????????
         */
    private void initOrder(boolean showLoad) {
        refreshLayout.resetNoMoreData();
        pageNum=1;
        if(jiangxu.isChecked()){
            if(touzhu.isChecked()){
                orderBy ="2";
            }else if(yinkui.isChecked()){
                orderBy="4";
            }else if(chongzhi.isChecked()){
                orderBy="6";
            }else if(fanyong.isChecked()){
                orderBy="8";
            }
        }
        else if(shengxu.isChecked()){
            if(touzhu.isChecked()){
                orderBy ="1";
            }else if(yinkui.isChecked()){
                orderBy="3";
            }else if(chongzhi.isChecked()){
                orderBy="5";
            }else if(fanyong.isChecked()){
                orderBy="7";
            }
        }
        getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",false,showLoad);//????????????????????????
    }
}
