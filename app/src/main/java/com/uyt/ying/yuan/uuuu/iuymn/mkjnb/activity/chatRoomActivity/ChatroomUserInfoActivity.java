
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.UserInfoAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChatUserinfoModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GetWinnerMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.YestodayWinMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TouXiangUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
    /*
    用户信息界面(聊天室跳转时,请求真实数据,  首页中奖信息跳转时,不请求接口,直接使用跳转传递的值)
     */
public class ChatroomUserInfoActivity extends BaseActivity {
    private ImageView mineTouXiangIv;
    private TextView nicknameTv;
    private TextView sexTv;
    private TextView touxiantV;
    private TextView amountTv;
    private TextView dengjiTv;
    private RecyclerView mRecycle;
    private UserInfoAdapter userInfoAdapter;
    private ArrayList<ChatUserinfoModel>chatUserinfoModelArrayList=new ArrayList<>();
    private long user_id;
    private ImageView backIv;
    private TextView actionTitleTv;
    private LinearLayout noDataLinear;
    private YestodayWinMedol yestodayWinMedol;
    private GetWinnerMedol getWinnerMedol;
    private boolean fromYestoday;
    private boolean fromGetWinner;
    private ConstraintLayout loadingLiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chatroom_user_info);
            Intent intent = getIntent();
            //昨日盈利跳转
            fromYestoday=intent.getBooleanExtra("fromYestoday",false);
            //中奖信息跳转
            fromGetWinner=intent.getBooleanExtra("fromGetWinner",false);
            if(fromYestoday){
                yestodayWinMedol= (YestodayWinMedol) intent.getSerializableExtra("medol");
            }else if(fromGetWinner) {
                getWinnerMedol= (GetWinnerMedol) intent.getSerializableExtra("medol");
            }else {
                //聊天室跳转
                user_id = Long.parseLong(intent.getStringExtra("user_id"));
            }

            bindView();
            initRecycle();
            //聊天室跳转,请求真实数据
            if(!fromGetWinner&&!fromYestoday){
                getUserInfo();
            }else {
                //中奖信息跳转,使用传递的值
                if(fromGetWinner){
                    String imageURl = getWinnerMedol.getImageURl();
                    Glide.with(ChatroomUserInfoActivity.this)
                            .load(imageURl)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .error(R.drawable.system_default_title)
                            .into(mineTouXiangIv);
                    nicknameTv.setText(getWinnerMedol.getNickName());
                    String sex = getWinnerMedol.getSex();
                    String sexStr;
                    if(sex.equals("0")){
                        sexStr=Utils.getString(R.string.女);
                    }else if(sex.equals("1")){
                        sexStr=Utils.getString(R.string.男);
                    }else{
                        sexStr=Utils.getString(R.string.保密);
                    }
                    sexTv.setText(Utils.getString(R.string.性别冒号 )+sexStr);
                    touxiantV.setText(getWinnerMedol.getTitle());
                    amountTv.setText(getWinnerMedol.getPrice().setScale(2, RoundingMode.HALF_UP)+"");
                    String grade = getWinnerMedol.getGrade();
                    int i1 = Integer.parseInt(grade);
                    dengjiTv.setText("VIP"+(i1+1));
                    ArrayList<String> stringList = getWinnerMedol.getStringList();
                    String firstImgurl = Utils.getFirstImgurl(ChatroomUserInfoActivity.this);
                    for (int i = 0; i < stringList.size(); i++) {
                        chatUserinfoModelArrayList.add(new ChatUserinfoModel(firstImgurl+stringList.get(i)));
                    }
                    userInfoAdapter.notifyDataSetChanged();
//                    chatUserinfoModelArrayList=getWinnerMedol.getStringList();
                }
                //昨日盈利跳转,使用跳转传递的数据
                else if(fromYestoday){
                    String imageURl = yestodayWinMedol.getImageURl();
                    Glide.with(ChatroomUserInfoActivity.this)
                            .load(imageURl)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .error(R.drawable.system_default_title)
                            .into(mineTouXiangIv);
                    nicknameTv.setText(yestodayWinMedol.getNickName());
                    String sex = yestodayWinMedol.getSex();
                    String sexStr;
                    if(sex.equals("0")){
                        sexStr=Utils.getString(R.string.女);
                    }else if(sex.equals("1")){
                        sexStr=Utils.getString(R.string.男);
                    }else{
                        sexStr=Utils.getString(R.string.保密);
                    }
                    sexTv.setText(Utils.getString(R.string.性别冒号 )+sexStr);
                    touxiantV.setText(yestodayWinMedol.getTitle());
                    amountTv.setText(yestodayWinMedol.getPrice().setScale(2, RoundingMode.HALF_UP)+"");
                    String grade = yestodayWinMedol.getGrade();
                    int i1 = Integer.parseInt(grade);
                    dengjiTv.setText("VIP"+(i1+1) );
                    String firstImgurl = Utils.getFirstImgurl(ChatroomUserInfoActivity.this);
                    ArrayList<String> stringList = yestodayWinMedol.getStringList();
                    for (int i = 0; i < stringList.size(); i++) {
                        chatUserinfoModelArrayList.add(new ChatUserinfoModel(firstImgurl+stringList.get(i)));
                    }
                    userInfoAdapter.notifyDataSetChanged();
                }
            }
        }
    @Override
    protected void init() {

    }
    private void getUserInfo() {
        loadingLiner.setVisibility(View.VISIBLE);
        noDataLinear.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId",user_id);
        Utils.docking(data, RequestUtil.CHATROOM_USERINFO, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                try {
                    loadingLiner.setVisibility(View.GONE);
                    ProgressDialogUtil.stop(ChatroomUserInfoActivity.this);
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    JSONArray favoriteLottery = jsonObject.getJSONArray("favoriteLottery");//喜欢的彩种
                    if(favoriteLottery.size()==0){
                        noDataLinear.setVisibility(View.VISIBLE);
                    }else {
                        noDataLinear.setVisibility(View.GONE);
                    }
                    int pointGrade = jsonObject.getInteger(CommonStr.GRADE);//等级
                    BigDecimal totalAmount = jsonObject.getBigDecimal("totalAmount");//今日奖金
                    String sex = jsonObject.getString("sex");//性别 0女1男2保密
                    String headImage = jsonObject.getString("headImage");//用户头像
                    String userNickName = jsonObject.getString("userNickName");//用户昵称
                    String title = jsonObject.getString("title");//用户头衔
                    String otherTouXiang = TouXiangUtil.getOtherTouXiang(ChatroomUserInfoActivity.this,headImage);
                    Glide.with(ChatroomUserInfoActivity.this)
                            .load(otherTouXiang)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .error(R.drawable.system_default_title)
                            .into(mineTouXiangIv);
                    nicknameTv.setText(userNickName);
                    String sexStr="";
                    if(sex.equals("0")){
                        sexStr=Utils.getString(R.string.女);
                    }else if(sex.equals("1")){
                        sexStr=Utils.getString(R.string.男);
                    }else{
                        sexStr=Utils.getString(R.string.保密);
                    }
                    sexTv.setText(Utils.getString(R.string.性别冒号 )+sexStr);
                    touxiantV.setText(title);
                    amountTv.setText(totalAmount.setScale(2, RoundingMode.HALF_UP)+"");
                    dengjiTv.setText("VIP"+(pointGrade+1)+"");
                    for (int i = 0; i < favoriteLottery.size(); i++) {
                        JSONObject jsonObject1 = favoriteLottery.getJSONObject(i);
                        String image = jsonObject1.getString("image");//喜欢的彩种图片
                        String bonus = jsonObject1.getString("bonus");//喜欢的彩种图片
                        chatUserinfoModelArrayList.add(new ChatUserinfoModel( Utils.getFirstImgurl(ChatroomUserInfoActivity.this)+image));
                    }
                    userInfoAdapter.notifyDataSetChanged();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLiner.setVisibility(View.GONE);
            ProgressDialogUtil.stop(ChatroomUserInfoActivity.this);
            showToast(messageHead.getInfo());
            }
        });
    }

    private void initRecycle() {
        userInfoAdapter = new UserInfoAdapter(chatUserinfoModelArrayList, this);
        mRecycle.setLayoutManager(new GridLayoutManager(this, 5));
        mRecycle.setAdapter(userInfoAdapter);

    }

    private void bindView() {
        loadingLiner=findViewById(R.id.loading_linear);
        noDataLinear=findViewById(R.id.no_data_linear);
        mineTouXiangIv=findViewById(R.id.mine_touxiang);
        nicknameTv=findViewById(R.id.nickname);
        sexTv=findViewById(R.id.sex);
        touxiantV=findViewById(R.id.touxian);
        amountTv=findViewById(R.id.amount);
        dengjiTv=findViewById(R.id.dengji);
        mRecycle=findViewById(R.id.chatroom_userinfo_recycle);
        backIv=findViewById(R.id.action_bar_left_img);
        actionTitleTv=findViewById(R.id.action_bar_text);
        actionTitleTv.setText(Utils.getString(R.string.玩家信息));
        backIv.setOnClickListener(new View.OnClickListener() {
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
