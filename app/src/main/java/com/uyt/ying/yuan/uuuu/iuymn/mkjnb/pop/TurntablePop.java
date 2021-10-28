package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SkipModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TurntableEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TurntableResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.turntableView.RotateListener;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.turntableView.WheelSurfView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sunfusheng.marqueeview.MarqueeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import okhttp3.Headers;

public class TurntablePop extends BasePopupWindow2 implements View.OnClickListener{
    ImageView lottery_one_iv;
    ImageView lottery_five_iv;
    ImageView lottery_ten_iv;
    TextView invite_people_tv;
    TextView remaining_times_tv;
    MarqueeView turntable_marquee_tv;
    LinearLayout invite_now_linear;
    WheelSurfView turntable_view;
    LiveFragment liveFragment;
    TextView turntable_rule_tv;
    boolean isAnimalOn=false;
     int[] pos ;
     int[] turntablePos = new int[]{1,8,7,6,5,4,3,2} ;
     int count =5 ;
    private String lastNum;
    private List<SpannableStringBuilder> noticeList = new ArrayList<>();//跑马灯字符串数据
    TurntableResultPop turntableResultPop ;
    List<Bitmap> mListBitmap = new ArrayList<>();
    List<Integer> drawableIdList = new ArrayList<>();
/*    String[] turntableString = new String[]{Utils.getString(R.string.苹 果 手 机 * 1), Utils.getString(R.string.18 元 现 金 劵*1), Utils.getString(R.string.主 播 名 片 * 1)
            , Utils.getString(R.string.推 广 至 尊 称 号 * 30 天), Utils.getString(R.string.VIP 发 言 权 限* 7 天), Utils.getString(R.string.188 元 现 金 劵 * 1),
            Utils.getString(R.string.入 场 特 效 * 7 天),Utils.getString(R.string.魅 力 新 秀 荣 誉 称 号 * 30 天)};  //转盘显示的list*/

    String[] dataRemarkList = new String[]{Utils.getString(R.string.十元现金劵1), Utils.getString(R.string.一百八十八元现金劵1), Utils.getString(R.string.主播名片1)
            , Utils.getString(R.string.入场特效7天), Utils.getString(R.string.VIP发言权限7天), Utils.getString(R.string.推广新秀称号30天),
            Utils.getString(R.string.推广至尊称号30天),Utils.getString(R.string. 苹果手机1)};
    private List<TurntableEntity.ZjListBean> zjList;

    public TurntablePop(Context context, boolean focusable,LiveFragment liveFragment) {
        super(context, focusable);
        this.liveFragment = liveFragment;
        ColorDrawable dw = new ColorDrawable(0xE6000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        initData();
        requestCount();
        getWinner();
    }

    private void requestCount() {
        HttpApiUtils.CPnormalRequest((Activity) mContext, liveFragment, RequestUtil.TURNTABLE_COUNT, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                lastNum = jsonObject.getString("lastNum");//剩余次数
                String effectivePerson = jsonObject.getString("effectivePerson");//有效邀请
                invite_people_tv.setText(effectivePerson);
                remaining_times_tv.setText(lastNum);
            }

            @Override
            public void onFailed(String msg) {
                ToastUtil.showToast(msg);
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.turntable_pop_layout,null);
        lottery_one_iv = rootView.findViewById(R.id.lottery_one_iv);
        lottery_five_iv = rootView.findViewById(R.id.lottery_five_iv);
        lottery_ten_iv = rootView.findViewById(R.id.lottery_ten_iv);
        turntable_view = rootView.findViewById(R.id.turntable_view);
        invite_people_tv = rootView.findViewById(R.id.invite_people_tv);
        remaining_times_tv = rootView.findViewById(R.id.remaining_times_tv);
        invite_now_linear = rootView.findViewById(R.id.invite_now_linear);
        turntable_marquee_tv = rootView.findViewById(R.id.turntable_marquee_tv);
        turntable_rule_tv = rootView.findViewById(R.id.turntable_rule_tv);
        initClick();
    }
    private void getWinner() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageSize", "200");
        data.put("pageNo",1);
        Utils.docking(data, RequestUtil.REQUEST_zz3, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject object = JSONObject.parseObject(content);
                JSONArray bonusRecordList = object.getJSONArray("bonusRecordList");
                for (int i = 0; i < bonusRecordList.size(); i++) {
                    JSONObject jsonObject = bonusRecordList.getJSONObject(i);
                    String nickname = jsonObject.getString("nickname");
                    String yqRedPrice = jsonObject.getString("yqRedPrice");
                    int nextInt = new Random().nextInt(dataRemarkList.length);
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    ForegroundColorSpan defaultSpan = new ForegroundColorSpan(Color.WHITE);
                    ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#F46518"));
                    spannableStringBuilder.append(Utils.getString(R.string.恭喜),defaultSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    spannableStringBuilder.append(" "+nickname+" ",span,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableStringBuilder.append(Utils.getString(R.string.获得)+dataRemarkList[nextInt].replace(" ",""));
                    noticeList.add(spannableStringBuilder);
                    turntable_marquee_tv.startWithList(noticeList);
                    turntable_marquee_tv.startFlipping();
                }
            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    private void initData() {
        //颜色
        Integer[] colors = new Integer[]{Color.parseColor("#DBBB68"), Color.parseColor("#D59941")
                , Color.parseColor("#DBBB68"), Color.parseColor("#D59941")
                , Color.parseColor("#DBBB68"), Color.parseColor("#D59941")
                , Color.parseColor("#DBBB68"),Color.parseColor("#D59941")};
/*        String[] dataRemarkList = new String[]{Utils.getString(R.string.18 元 现 金 劵*1), Utils.getString(R.string.188 元 现 金 劵 * 1), Utils.getString(R.string.主 播 名 片 * 1)
                , Utils.getString(R.string.入 场 特 效 * 7 天), Utils.getString(R.string.VIP 发 言 权 限* 7 天), Utils.getString(R.string.推 广 新 秀 称 号 * 30),
                Utils.getString(R.string.推 广 至 尊 称 号 * 30),Utils.getString(R.string. 苹 果 手 机 * 1)};*/
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.yuan_18));
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.yuan_188));
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.zbmp));
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.paoche));
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.vipfy));
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.xx_icon));
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.zz_icon));
        mListBitmap.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.iphone));

        drawableIdList.add(R.drawable.yuan_18);
        drawableIdList.add(R.drawable.yuan_188);
        drawableIdList.add(R.drawable.zbmp);
        drawableIdList.add(R.drawable.paoche);
        drawableIdList.add(R.drawable.vipfy);
        drawableIdList.add(R.drawable.xx_icon);
        drawableIdList.add(R.drawable.zz_icon);
        drawableIdList.add(R.drawable.iphone);

        //主动旋转一下图片
        mListBitmap = WheelSurfView.rotateBitmaps(mListBitmap);

        //获取第三个视图
        WheelSurfView.Builder build = new WheelSurfView.Builder()
                .setmColors(colors)
                .setmDeses(dataRemarkList)
                .setmIcons(mListBitmap)
                .setmType(1)
                .setmHuanImgRes(R.drawable.wy_dzp)
                .setmTypeNum(8)
                .build();
        turntable_view.setConfig(build);

        //添加滚动监听
        turntable_view.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des) {
                isAnimalOn=false;
                TurntablePop.this.setTouchable(true);
                TurntablePop.this.setOutsideTouchable(true);
                TurntablePop.this.setBackgroundDrawable( new ColorDrawable(0xE6000000));
                TurntablePop.this.update();

                ArrayList<TurntableResultModel> turntableResultModelArrayList  = new ArrayList<>();
                for (int i = 0; i < zjList.size(); i++) {
                    TurntableEntity.ZjListBean zjListBean = zjList.get(i);
                    TurntableResultModel turntableResultModel = new TurntableResultModel();
                    turntableResultModel.setDrawableId(drawableIdList.get(zjListBean.getQzIndex()));
                    turntableResultModel.setRemark(dataRemarkList[zjListBean.getQzIndex()]);
                    turntableResultModelArrayList.add(turntableResultModel);
                }
                turntableResultPop = new TurntableResultPop(mContext,true,turntableResultModelArrayList);
                turntableResultPop.showAtLocation(liveFragment.getRootView(), Gravity.CENTER,0,0);
                ProgressDialogUtil.darkenBackground((Activity) mContext,0.7f);

            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {
                isAnimalOn=true;
                TurntablePop.this.setTouchable(false);
                TurntablePop.this.setOutsideTouchable(false);
                TurntablePop.this.setBackgroundDrawable(null);
                TurntablePop.this.update();
            }

            @Override
            public void rotateBefore(ImageView goImg) {
                requestTurntable(1);
            }

            @Override
            public void rotateAgain(ImageView goImg, int index) {
                count--;
                if(pos.length>index){
                    turntable_view.startRotate(goImg,pos[index], count);
//                    Utils.logE("TAG", "onSuccess:sss: "+Utils.getString(R.string.rotateAgain下标:)+turntablePos[pos[index]] );

                }
            }
        });
    }

    private void initClick() {
        lottery_one_iv.setOnClickListener(this);
        lottery_five_iv.setOnClickListener(this);
        lottery_ten_iv.setOnClickListener(this);
        invite_now_linear.setOnClickListener(this);
        turntable_rule_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lottery_one_iv:
                if(!isAnimalOn){
                    YoYo.with(Techniques.StandUp)
                            .duration(800)
                            .playOn(lottery_one_iv);
                    count=1;
                    requestTurntable(1);
                }
                break;
            case R.id.lottery_five_iv:
                if(!isAnimalOn){
                    YoYo.with(Techniques.StandUp)
                            .duration(800)
                            .playOn(lottery_five_iv);
                    requestTurntable(5);
                }
                break;
            case R.id.lottery_ten_iv:
                if(!isAnimalOn){
                    YoYo.with(Techniques.StandUp)
                            .duration(800)
                            .playOn(lottery_ten_iv);
                    requestTurntable(10);
                }
                break;
            case R.id.invite_now_linear:
                InviteAndMakeMoneyActivity.startAty(mContext,"");
                EventBus.getDefault().postSticky(new SkipModel(true));
                break;
            case R.id.turntable_rule_tv:
                if(liveFragment!=null){
                       liveFragment.initTurntablePop();
                }
                break;
            default:
                break;
        }
    }

    private void requestTurntable(int num) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("nums",num);
        HttpApiUtils.CpRequest((Activity) mContext, liveFragment, RequestUtil.REQUEST_TURNTABLE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                TurntableEntity turntableEntity = JSONObject.parseObject(result, TurntableEntity.class);
                String effectivePerson = turntableEntity.getEffectivePerson();
                 lastNum = turntableEntity.getLastNum();
                invite_people_tv.setText(effectivePerson);
                remaining_times_tv.setText(lastNum);
                 zjList = turntableEntity.getZjList();
                pos = new int[zjList.size()];
                for (int i = 0; i < zjList.size(); i++) {
                    int qzIndex = zjList.get(i).getQzIndex();
                    pos[i]=turntablePos[qzIndex];
                    Utils.logE("TAG", "onSuccess:sss "+zjList.get(i).getRemark() );
                }
                turntable_view.startRotate(lottery_one_iv,pos[0], num);

            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }
}
