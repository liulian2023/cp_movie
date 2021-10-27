package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.child_open_fragemt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.ChildOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.InviteCodeRecylceAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.InviteCode;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;


public class InviteCodeFragment extends BaseFragment implements View.OnClickListener, InviteCodeRecylceAdapter.OnRecyclerViewItemClickListener {
    private ArrayList<InviteCode> inviteCodeArrayList =new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private InviteCodeRecylceAdapter inviteCodeRecylceAdapter;
    private RadioButton typeParent;
    private RadioButton typeUser;
    private PopupWindow popupWindow;
    private LinearLayout linearLayout;
    private LinearLayout  bigLinearLayout;
    private int pageNum=1;
    private int pageSize=100;
    private int isAgent;
    private LinearLayout nothing;
    private RefreshLayout refreshLayout;
    private int dataSize;
    private ConstraintLayout loadingLinear;
    private String shareContent;
    private String inviteAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.invite_code_fragment,container,false);
        inviteAddress = Utils.getRandomAppDownloadUrl();
        bindView(view);
        requestShareContent();
        initRecycleView(view);
        initRefreshLoadMore();
        return view;
    }

    private void initRefreshLoadMore() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getInvitecCode(pageNum+"",pageSize+"",isAgent,true);//请求创建的邀请码列表
                if(dataSize==0){
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                refreshLayout.resetNoMoreData();
                getInvitecCode(pageNum+"",pageSize+"",isAgent,false);//请求创建的邀请码列表
                refreshLayout.finishRefresh();
            }
        });
    }


    private void bindView(View view) {
        nothing=view.findViewById(R.id.nodata_linear);
        loadingLinear=view.findViewById(R.id.loading_linear);
        typeParent= view.findViewById(R.id.type_parent);//代理类型选项框
        typeUser= view.findViewById(R.id.type_user);//玩家类型选项框
        linearLayout =view.findViewById(R.id.linear);
        refreshLayout=view.findViewById(R.id.refreshLayout);
        typeUser.setOnClickListener(this);
        typeParent.setOnClickListener(this);
        typeParent.performClick();
    }
    private void initRecycleView(View view) {
        recyclerView =view.findViewById(R.id.invite_code_recycle);
        linearLayoutManager =new LinearLayoutManager(getContext());
        inviteCodeRecylceAdapter =new InviteCodeRecylceAdapter(inviteCodeArrayList);
        recyclerView.setAdapter(inviteCodeRecylceAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
//        View footView = LayoutInflater.from(getContext()).inflate(R.layout.invite_code_recycle_foot,null);//添加footView
//        inviteCodeRecylceAdapter.addFooterView(footView);
        //是否是代理
        getInvitecCode(pageNum+"",pageSize+"",isAgent,false);//请求创建的邀请码列表
        inviteCodeRecylceAdapter.setOnItemClickListener(this);
    }
    /*
    请求邀请码列表
     */
    private void getInvitecCode(String pageNum, String pageSize, int isAgent, final boolean isLoad) {
        loadingLinear.setVisibility(View.VISIBLE);
        nothing.setVisibility(View.GONE);
        Map<String, Object> showCode = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
        showCode.put("user_id",user_id);
        showCode.put("pageNo",pageNum);
        showCode.put("pageSize",pageSize);
        showCode.put("isagent",isAgent);
        Utils.docking(showCode, RequestUtil.REQUEST_FINDCODE, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
//                ProgressDialogUtil.stop(getActivity());
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray codeList = jsonObject.getJSONArray("codeList");
                Long count = jsonObject.getLong("count");
                int size = codeList.size();
                dataSize=size;
                if(!isLoad){
                    inviteCodeArrayList.clear();
                }
                if(count==0){
                    nothing.setVisibility(View.VISIBLE);
                }else {
                    nothing.setVisibility(View.GONE);
                }
                for (int i =0;i<codeList.size();i++) {
                    JSONObject jsonObject1 = codeList.getJSONObject(i);
                    String num = jsonObject1.getString("num");//注册人数
                    String url = jsonObject1.getString("url");//邀请码链接
                    String inviteCode = jsonObject1.getString("inviteCode");//邀请码;
                    long createdDate = jsonObject1.getLongValue("createdDate");//创建时间
                    String k3Rate = jsonObject1.getBigDecimal("k3Rate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String happytenRate = jsonObject1.getBigDecimal("happytenRate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String sscaiRate = jsonObject1.getBigDecimal("sscaiRate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String happy8Rate = jsonObject1.getBigDecimal("happy8Rate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String xuanwuRate = jsonObject1.getBigDecimal("xuanwuRate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String farmRate = jsonObject1.getBigDecimal("farmRate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String raceRate = jsonObject1.getBigDecimal("raceRate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String sixRate = jsonObject1.getBigDecimal("sixRate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    String danRate = jsonObject1.getBigDecimal("danRate").setScale(1, BigDecimal.ROUND_HALF_UP)+"";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(createdDate);
                    String format = simpleDateFormat.format(date);
//                    String inviteCode, String createdDate, String num, String codeUrl, String k3Rate, String happytenRate,
                    inviteCodeArrayList.add(new InviteCode(Utils.getString(R.string.邀请码:)+inviteCode,format,Utils.getString(R.string.注册左括号)+num+")",url,k3Rate,happytenRate,sscaiRate,happy8Rate,xuanwuRate,farmRate,raceRate,sixRate,danRate));
                }
                inviteCodeRecylceAdapter.notifyDataSetChanged();
            }
            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop(getActivity());
                loadingLinear.setVisibility(View.GONE);
            }
        });
    }
    /**
     *直接分享的url路径
     */
    private void requestShareContent() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type",10);
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_28rzq, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject promptWords = jsonObject.getJSONObject("promptWords");
                shareContent = promptWords.getString("contentTxt");
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.type_user://玩家类型
//                Map<String, Object> showCode = new HashMap<>();
//                Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
//                showCode.put("user_id",user_id);
//                showCode.put("pageNo",1);
//                showCode.put("pageSize",10);
//                showCode.put("isagent",0);//是否是代理
                isAgent=0;
                pageNum=1;
                refreshLayout.resetNoMoreData();
                getInvitecCode(pageNum+"",pageSize+"",isAgent,false);//请求创建的邀请码列表
                break;
            case R.id.type_parent:
//            Map<String, Object> showCode1 = new HashMap<>();
//            Long user_id1 = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
//            showCode1.put("user_id",user_id1);
//            showCode1.put("pageNo",1);
//            showCode1.put("pageSize",10);
//            showCode1.put("isagent",1);//是否是代理
                isAgent=1;
                pageNum=1;
                refreshLayout.resetNoMoreData();
                getInvitecCode(pageNum+"",pageSize+"",isAgent,false);//请求创建的邀请码列表
                break;
        }
    }
    /*
    recycleView点击事件的处理
     */
    @Override
    public void onItemClick(View view, final int position) {
        switch (view.getId()){
            case R.id.copy_invite_code://复制邀请码

                String inviteCode = inviteCodeArrayList.get(position).getInviteCode();
                String str1=inviteCode.substring(0,inviteCode.indexOf(":"));
                String copyStr =inviteCode.substring(str1.length()+1,inviteCode.length() );
                Utils.copyStr("inciteCode",copyStr);
                break;
            case R.id.copy_invite_url://立即邀请
                RouteUtils.start2Share(getContext(),shareContent+inviteAddress+"?code="+inviteCodeArrayList.get(position).getInviteCode());
                break;
            case   R.id.show_return:
                LayoutInflater inflater = LayoutInflater.from(getContext());//获得LayoutInflater对象
                View inflate = inflater.inflate(R.layout.show_return_pupopwindow, null);//读取布局管理器
                ImageView showReturnImg =inflate.findViewById(R.id.showReturnImg);
                TextView k3Text =inflate.findViewById(R.id.k3);
                TextView sscText =inflate.findViewById(R.id.ssc);
                TextView happy8Text =inflate.findViewById(R.id.happy8);
                TextView xuan5Text =inflate.findViewById(R.id.xuan5);
                TextView farmText =inflate.findViewById(R.id.farm);
                TextView raceText =inflate.findViewById(R.id.race);
                TextView sixText =inflate.findViewById(R.id.six);
                TextView dandanText =inflate.findViewById(R.id.dandan);
                TextView happy10Text =inflate.findViewById(R.id.happy10);
                k3Text.setText(inviteCodeArrayList.get(position).getK3Rate());
                sscText.setText(inviteCodeArrayList.get(position).getSscaiRate());
                happy8Text.setText(inviteCodeArrayList.get(position).getHappy8Rate());
                xuan5Text.setText(inviteCodeArrayList.get(position).getXuanwuRate());
                farmText.setText(inviteCodeArrayList.get(position).getFarmRate());
                raceText.setText(inviteCodeArrayList.get(position).getRaceRate());
                sixText.setText(inviteCodeArrayList.get(position).getSixRate());
                dandanText.setText(inviteCodeArrayList.get(position).getDanRate());
                happy10Text.setText(inviteCodeArrayList.get(position).getHappytenRate());
                popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
                popupWindow.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
                popupWindow.setTouchable(true);//设置popupWindow能否响应内部的点击事件
//                popupWindow.setOutsideTouchable(true); //设置popupWindow能否响应外部的点击事件
                popupWindow.showAtLocation(getActivity().getWindow().getDecorView() , Gravity.BOTTOM, 0, 0); // 相对于父元素的位置弹出窗口
                ProgressDialogUtil.darkenBackground(getActivity(),0.3f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ProgressDialogUtil.darkenBackground(getActivity(),1f);
                    }
                });
                showReturnImg.setOnClickListener(new View.OnClickListener() {//下方的退出窗口图片按钮
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();

                    }
                });
                break;

            case R.id.delete_img:

                AlertDialog isExit = new AlertDialog.Builder(getContext()).create();
                isExit.setTitle(Utils.getString(R.string.删除邀请码));
                isExit.setMessage(Utils.getString(R.string.确定删除?));
                isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HashMap<String, Object> updateCode = new HashMap<>();
                        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
                        updateCode.put("user_id",user_id);
                        String inviteCode1 = inviteCodeArrayList.get(position).getInviteCode();
                        String substring = inviteCode1.substring(inviteCode1.indexOf(":" )+1);//截取邀请码
                        updateCode.put("inviteCode",substring);
                        updateCode.put("delete",1);

                        Utils.docking(updateCode, RequestUtil.REQUEST_wt30, 0, new DockingUtil.ILoaderListener() {
                            @Override
                            public void success(String content) {

                                showToast(Utils.getString(R.string.删除成功));
                                Intent intent = new Intent(getContext(),ChildOpenActivity.class);
                                intent.putExtra("isStartInviteCode", true);
                                startActivity(intent);
                                getActivity().finish();
                            }
                            @Override
                            public void failed(MessageHead messageHead) {

                            }
                        });

                    }
                });
                isExit.show();


        }
    }
}
