package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildManegeRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildManageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
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



public class ChildManageActivity extends BaseActivity implements View.OnClickListener {
  private RadioButton time;//等级button
  private RadioButton level;//注册时间button
  private EditText nickNameEdit;//下级账号输入框
  private EditText invateEdit;//邀请码输入框
  private TextView invateSearch;//按邀请码搜索
  private TextView nikeNameSearch;//按下级账号搜索
  private TextView search;//搜索按钮
  private RecyclerView recyclerView;//我的下级列表
  private ChildManegeRecycleAdapter childManegeRecycleAdapter;//我的下级适配器
  private ArrayList<ChildManageModel> childManageModelArrayList = new ArrayList<>();//下级数据
  private ArrayList<String> childManageModelArrayListCopy = new ArrayList<>();//下级数据
  private ArrayList<String> childNickName = new ArrayList<>();//下级昵称
  private HashMap<String, String> childNumMap = new HashMap<>();
//  private PtrClassicFrameLayout ptrClassicFrameLayout;//下拉加载
  private  Long mID= SharePreferencesUtil.getLong(this, "user_id", 0l);
  private LinearLayout nothing;//暂无数据
  private TextView back;
  private TextView actionText;
  private TextView whoChild;
  private String childName;//按返回键时,赋值给请求接口的nickName
  private String editName;//点击搜索时,输入框的name
  private int dataSize;
//  private int count=1;
  private int pageNum=1;
  private int pageSize=500;
  private String order;
  private RefreshLayout refreshLayout;
  private ConstraintLayout loadingLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_manage);
        bindView();
        initRecycleView();
//        initRefresh();
    }

    @Override
    protected void init() {

    }

    //暂时不要上拉 下拉, 需要用的时候, 解决nickName的问题, 即刷新时,不能获取到最新的nickName
    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getTeamList(mID+"",pageNum+"",pageSize+"",initNameEdit(),order,initInvateEdit(),true);
             if(dataSize==0){
                 refreshLayout.finishLoadMoreWithNoMoreData();
             }
             refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                getTeamList(mID+"",pageNum+"",pageSize+"",initNameEdit(),order,initInvateEdit(),false);
                refreshLayout.finishRefresh();
            }
        });
    }

    private void initRecycleView() {
        recyclerView =findViewById(R.id.child_manage_recycle);
        childManegeRecycleAdapter = new ChildManegeRecycleAdapter(childManageModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(childManegeRecycleAdapter);
        childManegeRecycleAdapter.setOnItemClickListener(new ChildManegeRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            switch (view.getId()){
                case R.id.wrap_linear:
                    LinearLayout linearLayout =view.findViewById(R.id.hide_liear);
                    ImageView imageView =view.findViewById(R.id.move_img);
                    imageView.setPivotX(imageView.getWidth()/2);
                    imageView.setPivotY(imageView.getHeight()/2);//支点在图片中心
                    if(linearLayout.getVisibility()==View.GONE){
                        linearLayout.setVisibility(View.VISIBLE);
                        imageView.setRotation(90);
                    }else{
                        linearLayout.setVisibility(View.GONE);
                        imageView.setRotation(0);
                    }
                    break;
                case R.id.ckxj:
//                   count=1;
                   pageNum=1;
                   refreshLayout.resetNoMoreData();
                   childName=childManageModelArrayList.get(position).getName();
                    whoChild.setText(childManageModelArrayList.get(position).getName()+Utils.getString(R.string.的下级));
                    nickNameEdit.setText("");
                    invateEdit.setText("");
                    /*
                    没有下级,显示没有数据
                     */
                    if(childManageModelArrayList.get(position).getChildNUm().equals(Utils.getString(R.string.零人))){
                        childManageModelArrayList.clear();
                        nothing.setVisibility(View.VISIBLE);
                        childManegeRecycleAdapter.notifyDataSetChanged();
                    }
                    else{
                        getTeamList(mID+"",pageNum+"",pageSize+"",childName,childManageModelArrayList.get(position).getOrderBy(),initInvateEdit(),false);
                    }
                    childManageModelArrayListCopy.add(childName);

                 break;
                case R.id.ckfd:
                    LayoutInflater inflater = LayoutInflater.from(ChildManageActivity.this);//获得LayoutInflater对象
                    View inflate = inflater.inflate(R.layout.show_return_pupopwindow, null);//读取布局管理器
                    ImageView showReturnImg =inflate.findViewById(R.id.showReturnImg);
                    LinearLayout bigLinearLayout  =inflate.findViewById(R.id.show_linea);
//                    bigLinearLayout.getBackground().setAlpha(50);//设置背景色的透明度
                    TextView k3Text =inflate.findViewById(R.id.k3);
                    TextView sscText =inflate.findViewById(R.id.ssc);
                    TextView happy8Text =inflate.findViewById(R.id.happy8);
                    TextView xuan5Text =inflate.findViewById(R.id.xuan5);
                    TextView farmText =inflate.findViewById(R.id.farm);
                    TextView raceText =inflate.findViewById(R.id.race);
                    TextView sixText =inflate.findViewById(R.id.six);
                    TextView dandanText =inflate.findViewById(R.id.dandan);
                    TextView happy10Text =inflate.findViewById(R.id.happy10);
                    k3Text.setText(childManageModelArrayList.get(position).getK3Rate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    sscText.setText(childManageModelArrayList.get(position).getSscaiRate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    happy8Text.setText(childManageModelArrayList.get(position).getHappy8Rate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    xuan5Text.setText(childManageModelArrayList.get(position).getXuanwuRate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    farmText.setText(childManageModelArrayList.get(position).getFarmRate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    raceText.setText(childManageModelArrayList.get(position).getRaceRate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    sixText.setText(childManageModelArrayList.get(position).getSixRate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    dandanText.setText(childManageModelArrayList.get(position).getDanRate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                    happy10Text.setText(childManageModelArrayList.get(position).getHappytenRate().setScale(2,BigDecimal.ROUND_HALF_DOWN)+"");
                   final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);//实例化popupWindow
                    popupWindow.setAnimationStyle(R.style.progressBarAnimation);//设置进出动画
                    popupWindow.setTouchable(true);//设置popupWindow能否响应内部的点击事件
//                popupWindow.setOutsideTouchable(true); //设置popupWindow能否响应外部的点击事件
                    ProgressDialogUtil.darkenBackground(ChildManageActivity.this,0.4f);//pop弹出,将activity背景调暗
                    popupWindow.showAtLocation(ChildManageActivity.this.getWindow().getDecorView() , Gravity.BOTTOM, 0, 0); // 相对于父元素的位置弹出窗口
                    showReturnImg.setOnClickListener(new View.OnClickListener() {//下方的退出窗口图片按钮
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            ProgressDialogUtil.darkenBackground(ChildManageActivity.this,1f); //dismiss时,恢复背景亮度
                        }
                    });
                    break;
            }
            }
        });

    }

    private void getTeamList(String user_id, String pageNo, String pageSize , final String childNickname, final String orderBy, String invitationCode, final boolean isLoad) {
        nothing.setVisibility(View.GONE);
        loadingLinear.setVisibility(View.VISIBLE);
        HashMap<String, Object> dataTeamList = new HashMap<>();
        dataTeamList.put("user_id",user_id);
        dataTeamList.put("pageNo",pageNo);
        dataTeamList.put("pageSize",pageSize);
        dataTeamList.put("childNickname",childNickname);
        dataTeamList.put("orderBy",orderBy);//按注册时间为0,按等级为1
        dataTeamList.put("invitationCode",invitationCode);
        Utils.docking(dataTeamList, RequestUtil.REQUEST_wt31, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
//                ProgressDialogUtil.stop(ChildManageActivity.this);
                if(!isLoad){
                childManageModelArrayList.clear();
                }
               
                JSONObject jsonObject = JSONObject.parseObject(content);
                Long totalsize = jsonObject.getLong("totalsize");
                if(totalsize==0){
                    nothing.setVisibility(View.VISIBLE);
                }else{
                    nothing.setVisibility(View.GONE);
                }
                JSONArray teamList = jsonObject.getJSONArray("teamList");
                dataSize = teamList.size();
                for (int i=0;i<teamList.size();i++) {
                    JSONObject jsonObject1 = teamList.getJSONObject(i);
                    String nickname = jsonObject1.getString("nickname");//用户昵称
                    String pointGrade = jsonObject1.getString(CommonStr.GRADE);//下级等级
                    String childNums = jsonObject1.getString("childNums");//下级人数
                    String loginDate = jsonObject1.getString("loginDate");//上次登录时间
                    String registerDate = jsonObject1.getString("registerDate");//注册时间
                    String agentLevel = jsonObject1.getString("agentLevel");//代理等级(1一级代理,2二级代理)
                    String user_id = jsonObject1.getString("user_id");//用户id
                    BigDecimal k3Rate = jsonObject1.getBigDecimal("k3Rate");//快三返点
                    BigDecimal sscaiRate = jsonObject1.getBigDecimal("sscaiRate");//时时彩返点
                    BigDecimal happytenRate = jsonObject1.getBigDecimal("happytenRate");//快乐10返点
                    BigDecimal happy8Rate = jsonObject1.getBigDecimal("happy8Rate");//快乐8返点
                    BigDecimal xuanwuRate = jsonObject1.getBigDecimal("xuanwuRate");//选5返点
                    BigDecimal farmRate = jsonObject1.getBigDecimal("farmRate");//农场返点
                    BigDecimal raceRate = jsonObject1.getBigDecimal("raceRate");//赛车返点
                    BigDecimal sixRate = jsonObject1.getBigDecimal("sixRate");//六合彩返点
                    BigDecimal danRate = jsonObject1.getBigDecimal("danRate");//蛋蛋返点
                    BigDecimal commission = jsonObject1.getBigDecimal("commission");//累计返佣
                    BigDecimal amount = jsonObject1.getBigDecimal("amount");//当前余额
                    childManageModelArrayList.add(
                            new ChildManageModel(nickname,pointGrade,childNums+Utils.getString(R.string.人),loginDate,registerDate,
                            agentLevel,user_id,k3Rate,sscaiRate,happytenRate,happy8Rate,xuanwuRate,farmRate,raceRate,sixRate,danRate,commission ,amount,orderBy));
                    childNickName.add(nickname);//添加昵称,用于判断是否有该下级
                    childNumMap.put(nickname,childNums);
                }
                childManegeRecycleAdapter.notifyDataSetChanged();
//                childName=childNickname;
            }

            @Override
            public void failed(MessageHead messageHead) {
//            ProgressDialogUtil.stop(ChildManageActivity.this);
                loadingLinear.setVisibility(View.GONE);
            showToast(messageHead.getInfo());
                nothing.setVisibility(View.VISIBLE);
                /*
                请求失败后,点击等级和时间,如果不做处理,会请求默认数据
                点击等级和时间时,nickName传入的是childName,而childName只在每次按下返回键时赋值,默认为空值,将空值传入时,默认请求的数据是当前用户的所有下级
                在失败后,将childName设置为昵称输入框的值,点击时间和等级时,传入的值还是nickNameEdit.getText().toString();
                 */
                childManageModelArrayList.clear();
                childName= nickNameEdit.getText().toString();
            }
        });
    }
    private void bindView() {
        loadingLinear=findViewById(R.id.loading_linear);
        refreshLayout=findViewById(R.id.refresh) ;
        refreshLayout.setEnableRefresh(false);//取消下拉刷新
        time =findViewById(R.id.time);
        level =findViewById(R.id.level);
        nickNameEdit =findViewById(R.id.child_edit);
        invateEdit=findViewById(R.id.invite_edit);
        invateSearch=findViewById(R.id.invite_text);
        nikeNameSearch=findViewById(R.id.child_text);
        search=findViewById(R.id.search);
        nothing=findViewById(R.id.nodata_linear);
        back=findViewById(R.id.action_bar_return);
        whoChild=findViewById(R.id.who_child);
        actionText=findViewById(R.id.action_bar_text);
        actionText.setText(Utils.getString(R.string.下级管理));
        search.setOnClickListener(this);
        back.setOnClickListener(this);
        time.setOnClickListener(this);
        time.performClick();
        level.setOnClickListener(this);
        invateSearch.setOnClickListener(this);
        nikeNameSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.invite_text:
                invateSearch.setVisibility(View.GONE);
                nickNameEdit.setVisibility(View.GONE);
                nikeNameSearch.setVisibility(View.VISIBLE);
                invateEdit.setVisibility(View.VISIBLE);
                nickNameEdit.setText("");
                invateEdit.setText("");
            break;
            case R.id.child_text:
                nikeNameSearch.setVisibility(View.GONE);
                invateEdit.setVisibility(View.GONE);
                invateSearch.setVisibility(View.VISIBLE);
                nickNameEdit.setVisibility(View.VISIBLE);
                nickNameEdit.setText("");
                invateEdit.setText("");
            break;
            case R.id.search:
                if(nickNameEdit.getVisibility()==View.VISIBLE){
                    if(time.isChecked()){
                        pageNum=1;
                        refreshLayout.resetNoMoreData();
//                        if(!childNickName.contains(initNameEdit())){
//                            showtoast(Utils.getString(R.string.未搜索到该下级,请重新输入));
//                        }else{
//                            ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                            if(childNumMap.get(initNameEdit()).equals("0")){
//                                childManageModelArrayList.clear();
//                                childManegeRecycleAdapter.notifyDataSetChanged();
//                                nothing.setVisibility(View.VISIBLE);
////                                PopupWindowUtil.disMiss(ChildManageActivity.this);
//                                ProgressDialogUtil.stop(ChildManageActivity.this);
//                                whoChild.setText(Utils.getString(R.string.搜索结果如下));
//                            }else{
                                whoChild.setText(Utils.getString(R.string.搜索结果如下));
                                getTeamList(mID+"",pageNum+"",pageSize+"",initNameEdit(),order,initInvateEdit(),false);
//                                nothing.setVisibility(View.GONE);
//                            }
//                        }
                    }else if (level.isChecked()){
                        pageNum=1;
                        refreshLayout.resetNoMoreData();
//                        if(!childNickName.contains(initNameEdit())){
//                            showtoast(Utils.getString(R.string.未搜索到该下级,请重新输入));
//                        }else{
//                            ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                            if(childNumMap.get(initNameEdit()).equals("0")){
//                                childManageModelArrayList.clear();
//                                childManegeRecycleAdapter.notifyDataSetChanged();
//                                nothing.setVisibility(View.VISIBLE);
//                                ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                            }else{
                        whoChild.setText(Utils.getString(R.string.搜索结果如下));
                        getTeamList(mID+"",pageNum+"",pageSize+"",initNameEdit(),order,initInvateEdit(),false);
//                            }
//                        }
                    }
                }else {
                    if(time.isChecked()){
                        pageNum=1;
                        refreshLayout.resetNoMoreData();
//                            ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                            if(childNumMap.get(initNameEdit()).equals("0")){
//                                childManageModelArrayList.clear();
//                                childManegeRecycleAdapter.notifyDataSetChanged();
//                                nothing.setVisibility(View.VISIBLE);
//                                ProgressDialogUtil.stop(ChildManageActivity.this);
//                                whoChild.setText(Utils.getString(R.string.搜索结果如下));
//                            }else{
                        whoChild.setText(Utils.getString(R.string.搜索结果如下));
                        getTeamList(mID+"",pageNum+"",pageSize+"",initNameEdit(),order,initInvateEdit(),false);
//                                nothing.setVisibility(View.GONE);
//                            }

                    }else if (level.isChecked()){
                        pageNum=1;
                        refreshLayout.resetNoMoreData();
//                            ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                            if(childNumMap.get(initNameEdit()).equals("0")){
//                                childManageModelArrayList.clear();
//                                childManegeRecycleAdapter.notifyDataSetChanged();
//                                nothing.setVisibility(View.VISIBLE);
//                                ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                            }else{
                        whoChild.setText(Utils.getString(R.string.搜索结果如下));
                        getTeamList(mID+"",pageNum+"",pageSize+"",initNameEdit(),order,initInvateEdit(),false);
//                                nothing.setVisibility(View.GONE);
//                            }

                    }
                }

                break;

                /*
                点击按等级排序 或者按时间排序时, nickName 为按下返回键 和点击查看下级时设置的name
                 */
            case R.id.time:
                order="0";
                pageNum=1;
                refreshLayout.resetNoMoreData();
//                ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
                getTeamList(mID+"",pageNum+"",pageSize+"",childName,order,initInvateEdit(),false);
                break;
            case R.id.level:
                order="1";
                pageNum=1;
                refreshLayout.resetNoMoreData();
//                ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
                getTeamList(mID+"",pageNum+"",pageSize+"",childName,order,initInvateEdit(),false);
                break;
                /*
                index    size    name
                 0        1      123
                 1        2      1234
                 2        3      12345
                 */
            case R.id.action_bar_return:
                pageNum=1;
                refreshLayout.resetNoMoreData();
                /*
                childManageModelArrayListCopy在每次点击查看下级时都将点击的nickName添加,用于返回键和物理按键的逻辑判断
                 */
                if(childManageModelArrayListCopy.size()!=0){
                    /*
                    点击查看下级2次以上,此时按下返回键,将childName设置为index为size-2的值(childManageModelArrayListCopy在每一次点击查看下级,都会将name添加进容器),实现返回上一级数据的需求
                    size 为2  取index为0,即第1个nickName的数据
                    size 为3  取index为1 即第2个nickName的数据
                    size为4   取index为2 即第3个nickName的数据

                    并且将childName设置为所取的name,避免按下返回键,再点击切换注册时间和等级时,childName没有更新

                    每次点击返回,都移除掉最后一个nickName数据
                     */
                    if(childManageModelArrayListCopy.size()>=2){
//                        ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
                        String name = childManageModelArrayListCopy.get(childManageModelArrayListCopy.size() - 2);
//                        nickNameEdit.setText(name);
                        whoChild.setText(name+Utils.getString(R.string.的下级));
                        childName=name;
                        getTeamList(mID+"",pageNum+"",pageSize+"",name,order,initInvateEdit(),false);
                        //将最后一个nickName数据移除
                        childManageModelArrayListCopy.remove(childManageModelArrayListCopy.size()-1);
//                        ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
                    }
//                    else if(childManageModelArrayListCopy.size()==2){
//                        ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                        String name = childManageModelArrayListCopy.get(childManageModelArrayListCopy.size() - 2);
//                        childName=name;
////                        nickNameEdit.setText(name);
//                        whoChild.setText(name+Utils.getString(R.string.的下级));
//                        getTeamList(mID+"",pageNum+"",pageSize+"",name,order,initInvateEdit(),false);
//                        childManageModelArrayListCopy.remove(childManageModelArrayListCopy.size()-1);
//                    ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
//                    }
                    /*
                    size为1,按下返回键,即请求我的数据,将childNmae设置为空. 并清空数据
                     */
                    else if(childManageModelArrayListCopy.size()==1) {
                        getTeamList(mID+"",pageNum+"",pageSize+"","",order,initInvateEdit(),false);
                        whoChild.setText(Utils.getString(R.string.我的下级));
                        childName="";
                        childManageModelArrayListCopy.remove(childManageModelArrayListCopy.size()-1);
                    }

                }
                //childManageModelArrayListCopy.size()为0,此时请求到的是用户自身的下级,再次按返回键,直接退出
                else{
//                    if(count==1){
//                        nickNameEdit.setText("");
//                         whoChild.setText(Utils.getString(R.string.我的下级));
//                        getTeamList(mID+"",pageNum+"",pageSize+"",initNameEdit(),order,initInvateEdit(),false);
//                    }
//                    if(count==0){
                        finish();
//                    }

//                    count--;
                }
                break;
        }
    }
    private String initInvateEdit() {
        String s = invateEdit.getText().toString();
        if(s.isEmpty()){
            s="";
        }
        return s;
    }
    private String initNameEdit() {
        editName = nickNameEdit.getText().toString();
        if(editName.isEmpty()){
            editName="";
        }
        return editName;
    }
        /*
        监听手机物理按键, 逻辑跟actionBar返回键一样
        (不作处理是时按下物理返回键,默认是finish当前页面,跟需求不符)
         */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            pageNum=1;
            refreshLayout.resetNoMoreData();
            if(childManageModelArrayListCopy.size()!=0){
                if(childManageModelArrayListCopy.size()>=2){
//                    ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
                    String name = childManageModelArrayListCopy.get(childManageModelArrayListCopy.size() - 2);
                    whoChild.setText(name+Utils.getString(R.string.的下级));
                    childName=name;
                    getTeamList(mID+"",pageNum+"",pageSize+"",name,order,initInvateEdit(),false);
                    childManageModelArrayListCopy.remove(childManageModelArrayListCopy.size()-1);
//                    ProgressDialogUtil.show(ChildManageActivity.this, "loading", false);
                }
                else if(childManageModelArrayListCopy.size()==1) {
                    getTeamList(mID+"",pageNum+"",pageSize+"","",order,initInvateEdit(),false);
                    whoChild.setText(Utils.getString(R.string.我的下级));
                    childName="";
                    childManageModelArrayListCopy.remove(childManageModelArrayListCopy.size()-1);
                }
            }
            else{
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
