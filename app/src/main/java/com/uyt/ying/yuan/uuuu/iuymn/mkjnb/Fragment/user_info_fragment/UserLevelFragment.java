package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.user_info_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.user_info_adapter.UserLevelRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GradeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserLevel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.RecycleViewDivider;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

/*
等级机制
 */
public class UserLevelFragment extends BaseFragment {
    private ArrayList<UserLevel> userLevelArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private  UserLevelRecycleAdapter userLevelRecycleAdapter;
    private ImageView levelImage;
    private TextView levelName;
    private ImageView levelVip;
    private TextView levelVip2;
    private TextView levelVip3;
    private TextView levelTouXian;
    private TextView chengzhangzhi;
    private TextView gap;
    private ProgressBar levelPro;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private static  int GET_PERSON_INFO=200;


    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    getPersonInfo();
                    break;
                    default:
                        break;
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.user_level_fragemnt,container,false);
        initView(view);
        return view;
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getDengji();
    }

    private void initView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        loadingLinear=view.findViewById(R.id.loading_linear);
        recyclerView =view.findViewById(R.id.level_recycle);
        levelImage= view.findViewById(R.id.level_image);
        levelName =view.findViewById(R.id.level_name);
        levelVip =view.findViewById(R.id.level_vip);
        levelVip2 =view.findViewById(R.id.level_vip2);
        levelVip3 =view.findViewById(R.id.level_vip3);
        levelTouXian =view.findViewById(R.id.level_touxian);
        chengzhangzhi =view.findViewById(R.id.level_chengzhangzhi);
        gap =view.findViewById(R.id.level_gap);
        levelPro=view.findViewById(R.id.level_progress);

        userLevelRecycleAdapter = new UserLevelRecycleAdapter(R.layout.user_level_recycle_item,userLevelArrayList);
        linearLayoutManager =new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userLevelRecycleAdapter);

        getDengji();//获得等级列表

//        getPersonMoney();//获得资金信息
//        getPersonInfo();//获得个人信息


    }
/*
获得用户基本信息
 */
    private void getPersonInfo() {

        HashMap<String, Object> aboutPerson = new HashMap<>();//REQUEST_06rzq
        long userId =SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        aboutPerson.put("user_id",userId);
        Utils.docking(aboutPerson,RequestUtil.REQUEST_13r,1, new DockingUtil.ILoaderListener(){
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                /*
                用户头像
                 */
                String image = memberInfo.getString("image");
                String finalImgUrl="";
                String firstImgurl = Utils.getFirstImgurl(getContext());
                if(!StringMyUtil.isEmptyString(image)){//新注册的账号没有image字段,需要给一个默认头像
                    image =image.replace(firstImgurl,"");
                    finalImgUrl = firstImgurl+image;
                    SharePreferencesUtil.putString(getContext(),"oldUserImage",finalImgUrl);

                }else{//默认头像
                    finalImgUrl=Utils.getString(R.string.沒有头像);
                }
                SharePreferencesUtil.putString(getContext(),"oldUserImage",finalImgUrl);

                Glide.with(MyApplication.getInstance())
                        .load(finalImgUrl)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .error(R.drawable.system_default_title)
                        .into(levelImage);
                   /*
                  用户名
                  */
                String nickname = memberInfo.getString("nickname");
                levelName.setText(nickname);
                  /*
                用户等级
                 */
                Integer pointGrade = memberInfo.getInteger(CommonStr.GRADE);
                SharePreferencesUtil.putInt(getContext(),CommonStr.GRADE,pointGrade+1);

            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

/*
获取等级列表
 */
    private void getDengji( ) {
//        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
//        loadingLinear.setVisibility(View.VISIBLE);
//        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        HashMap<String, Object> dataMoneny = new HashMap<>();
        long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
        dataMoneny.put("user_id",user_id);
        HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.REQUEST_08rzq, dataMoneny, loadingLinear, errorLinear, reloadTv, recyclerView, false, false, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                GradeEntity gradeEntity = JSONObject.parseObject(result, GradeEntity.class);
                String nextGradeGrowthIntegra = gradeEntity.getNextGradeGrowthIntegral();
                String growthIntegral = gradeEntity.getGrowthIntegral();
                String pointGrade = gradeEntity.getPointGrade();
                String  nextGradeNeedGrowthIntegral = gradeEntity.getNextGradeNeedGrowthIntegral();
                chengzhangzhi.setText(Utils.getString(R.string.成长值)+growthIntegral+Utils.getString(R.string.分));
//                levelVip.setImageResource(Utils.getLevelDrawble(Integer.parseInt(pointGrade)+1));

                GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(Integer.parseInt(pointGrade)+1)),levelVip);
                levelVip2.setText("vip"+(Integer.parseInt(pointGrade)+1));
                List<GradeEntity.MemberGradeMechanismListBean> memberGradeMechanismList = gradeEntity.getMemberGradeMechanismList();
                RefreshUtil.success(1,null,loadingLinear,null,memberGradeMechanismList.size(),false,false,userLevelArrayList);
                userLevelArrayList.add(new UserLevel(Utils.getString(R.string.等级),Utils.getString(R.string.头衔),Utils.getString(R.string.成长积分)));
                String mineIntegral="1";//达到当前等级需要的积分
                for (int i=0;i<memberGradeMechanismList.size();i++){
                    GradeEntity.MemberGradeMechanismListBean memberGradeMechanismListBean = memberGradeMechanismList.get(i);
                    String  growthIntegralList = memberGradeMechanismListBean.getGrowthIntegral();
                    String  title  = memberGradeMechanismListBean.getTitle();
                    String  grade = memberGradeMechanismListBean.getGrade();
                    if (grade!=null){
                        UserLevel userLevel = new UserLevel(Integer.parseInt(grade)+1+"", title, growthIntegralList);
                        userLevelArrayList.add(userLevel);
                    }
                    if(pointGrade.equals(memberGradeMechanismListBean.getGrade())){
                        mineIntegral=memberGradeMechanismListBean.getGrowthIntegral();
                    }
                }

                DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
                GradeEntity.MemberGradeMechanismListBean lastItem = memberGradeMechanismList.get(memberGradeMechanismList.size() - 1);
                GradeEntity.MemberGradeMechanismListBean currentBean = memberGradeMechanismList.get(Integer.parseInt(pointGrade));
                if(pointGrade.equals(lastItem.getGrade())){
                    //等级与列表中最后一个item的等级相同,即为最高等级
                    levelTouXian.setText(Utils.getString(R.string.头衔冒号)+lastItem.getTitle());
                    gap.setText(Utils.getString(R.string.距离下一等级需要0分,每充值1元加1分));
                    levelPro.setProgress((0));
                    levelVip3.setText(Utils.getString(R.string.最高等级));
                }else {
                    gap.setText(Utils.getString(R.string.距离下一等级需要)+nextGradeNeedGrowthIntegral+Utils.getString(R.string.分,每充值1元加1分));
                    int nextGrade = Integer.parseInt(pointGrade) + 1;
                    levelVip3.setText("vip"+ (nextGrade+1));
                    GradeEntity.MemberGradeMechanismListBean nextBean = memberGradeMechanismList.get(nextGrade);
                    levelTouXian.setText(Utils.getString(R.string.头衔:)+ currentBean.getTitle());
                    // growthIntegral 自身成长积分  mineIntegral 当前等级需要的积分  nextGradeGrowthIntegral下一等级需要的积分
                    String format = df.format((float) (Double.parseDouble(growthIntegral)- Double.parseDouble(mineIntegral)) / (Double.parseDouble(nextGradeGrowthIntegra)-Double.parseDouble(mineIntegral)));
                    int v = (int) (Float.parseFloat(format) * 100);
                    levelPro.setProgress(v);
                }
                userLevelRecycleAdapter.notifyDataSetChanged();
                Message message = new Message();
                message.what= GET_PERSON_INFO;
                handler.sendMessage(message);
            }

            @Override
            public void onFailed(String msg) {
            RefreshUtil.fail(false,false,1,null);
            }
        });
    }

}
